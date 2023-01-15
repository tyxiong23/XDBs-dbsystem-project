from .leafNode import LeafNode
from .interNode import InterNode
from .basic_node import BasicNode
from .index_handler import IndexHandler
from RecordSystem.basicClass.rid import RID
import numpy as np
from utils.constants import *
from typing import List


class FileIndex:

    class UnitFIDX:
        def __init__(self, table_name, index_root_id):
            self.table_name = table_name
            self.index_root_id = index_root_id
        
        def __hash__(self):
            return hash((self.table_name, self.index_root_id))

        def __str__(self):
            return f'{{table_name: {self.table_name}, index_root_id: {self.index_root_id}}}'

        def __eq__(self, other):
            return self.index_root_id == other.index_root_id and self.table_name == other.table_name

    def __init__(self, index_handler: IndexHandler, root_id):
        self.root_id = root_id
        self.index_handler = index_handler
        self.root_node = InterNode(root_id, root_id, [], [], self.index_handler)
        self.dirty = False



    def insert(self, key, value: RID):
        self.dirty = True
        self.root_node.insert(key, value)
        node_page_size = self.root_node.page_size()
        if node_page_size <= PAGE_SIZE:
            return None

        else:
            print(f"NEW FATHRE NODE!!!!!! (_root, {self.root_id}, root_node.page {self.root_node.page_id,}, root_node.father {self.root_node.father_id})", node_page_size)
            new_left_id = self.index_handler.new_page()
            new_right_id = self.index_handler.new_page()
            
            root_right_idx = len(self.root_node.child_keys) - 1
            right_key_max = self.root_node.child_keys[root_right_idx]
            right_keys, right_vals, left_key_max = self.root_node.split()
            left_keys, left_vals = self.root_node.child_keys, self.root_node.child_vals
            new_left_node = InterNode(new_left_id, self.root_id, left_keys, left_vals, self.index_handler, self.root_node.depth+1)
            new_right_node = InterNode(new_right_id, self.root_id, right_keys, right_vals, self.index_handler, self.root_node.depth+1)
            root_key_list = [left_key_max, right_key_max]
            root_val_list = [new_left_node, new_right_node]
            self.root_node.child_keys = root_key_list
            self.root_node.child_vals = root_val_list
            return None

    def delete(self, key, value: RID):
        self.dirty = True
        self.root_node.remove(key=key, value=value)
        return None

    def range(self, low, high):
        # print(f"FileIndex::range {low, high}")
        ranges = self.root_node.range(low, high)
        return ranges

    def write_back(self):
        temp_node_list: List[BasicNode] = []
        temp_node = None
        temp_node_list.append(self.root_node)
        while len(temp_node_list) > 0:
            temp_node = temp_node_list.pop(0)
            data = temp_node.serialize()
            if isinstance(temp_node, InterNode):
                if len(temp_node.child_vals) > 0:
                    temp_node_list += temp_node.child_vals
            self.index_handler.put_page(temp_node.page_id, data)
        return None

    def build_node(self, page_id, depth=0):
        # print("BUILD NODE", page_id)
        self.dirty = True
        page_data: np.ndarray = self.index_handler.get_page(page_id=page_id)
        page_data.dtype = np.int64
        node_type = page_data[0]
        if node_type == INTER_NODE_TYPE:
            res: InterNode = self.build_inter_node(page_id, page_data, depth=depth)
        elif node_type == LEAF_NODE_TYPE:
            res: LeafNode = self.build_leaf_node(page_id, page_data, depth=depth)
        else:
            raise ValueError('node type error!')
        return res

    def build_leaf_node(self, page_id, data: np.ndarray, depth):
        data.dtype = np.int64
        child_keys, child_rids = [], []
        for i in range(data[2]):
            rid = RID(int(data[3 + 3 * i]), int(data[4 + 3 * i]))
            child_keys.append(data[5 + 3 * i])
            child_rids.append(rid)
        leaf_node = LeafNode(page_id, data[1], data[2], data[3], child_keys,
                             child_rids, self.index_handler, depth)
        return leaf_node

    def build_inter_node(self, page_id, data: np.ndarray, depth):
        data.dtype = np.int64
        child_num = data[2]
        child_key_list = []
        child_node_list = []
        for i in range(child_num):
            child_key_list.append(data[3 + 2 * i])
            child_node_list.append(self.build_node(data[4 + 2 * i]))
        nonleaf_node = InterNode(page_id, data[1], child_key_list,
                                    child_node_list, self.index_handler, depth)
        return nonleaf_node

    def build_index_tree(self):
        page_data: np.ndarray = self.index_handler.get_page(self.root_id)
        page_data.dtype = np.int64
        assert (page_data[1] == self.root_id), f'FileIndex::build index tree error!!! root {self.root_id, page_data[1]} page{page_data.shape}'
        self.root_node = self.build_node(page_id=self.root_id)


    @property
    def root(self):
        return self.root_id
