from .leaf_node1 import LeafNode
from .nonleaf_node1 import NoneLeafNode
from .index_handler import IndexHandler
from RecordSystem.basicClass.rid import RID
# from ..RecordSystem.rid import RID
import numpy as np
from utils.macro import *

# from ..RecordSystem import macro


class FileIndex:
    def __init__(self, index_handler: IndexHandler, root_id):
        self._root = root_id
        self._handler = index_handler
        self._root_node = NoneLeafNode(page=root_id, father=root_id, child_key_list=[], child_list=[],
                                       index_handler=self._handler)
        self._is_modified = False

    @property
    def is_modified(self):
        return self._is_modified

    def insert(self, key, value: RID):
        self._is_modified = True
        self._root_node.insert(key=key, value=value)
        node_page_size = self._root_node.page_size()
        if node_page_size <= PAGE_SIZE:
            return None

        else:
            print(f"NEW FATHRE NODE!!!!!! (_root, {self._root}, root_node.page {self._root_node.page,}, root_node.father {self._root_node._father})", node_page_size)
            new_left_id = self.handler.new_page()
            new_right_id = self.handler.new_page()
            
            root_right_idx = len(self._root_node.child_key_list) - 1
            right_key_max = self._root_node.child_key_list[root_right_idx]
            right_key_list, right_val_list, mid_key_max = self._root_node.split()
            mid_key_list, mid_val_list = self._root_node.child_key_list, self._root_node.child_list
            new_left_node = NoneLeafNode(new_left_id, self._root, mid_key_list, mid_val_list, self.handler, self._root_node.depth+1)
            new_right_node = NoneLeafNode(new_right_id, self._root, right_key_list, right_val_list, self.handler, self._root_node.depth+1)
            root_key_list = [mid_key_max, right_key_max]
            root_val_list = [new_left_node, new_right_node]
            self._root_node._child_key_list = root_key_list
            self._root_node._child_list = root_val_list

            return None
        # else:
        #     print(f"NEW FATHRE NODE!!!!!! (_root, {self._root}, root_node.page {self._root_node.page,}, root_node.father {self._root_node._father})", node_page_size)
        #     new_page_id_father = self._handler.new_page()
        #     new_root_father = NoneLeafNode(page=new_page_id_father, father=new_page_id_father, child_key_list=[],
        #                                    child_list=[],
        #                                    index_handler=self._handler)
        #     self._root_node._father = new_page_id_father
        #     right_key_index = len(self._root_node.child_key_list) - 1
        #     right_key = self._root_node.child_key_list[right_key_index]
        #     
        #     new_root_son = NoneLeafNode(page=self._handler.new_page(), father=new_page_id_father,
        #                                 child_key_list=right_child_key_list, child_list=right_child_list,
        #                                 index_handler=self._handler, depth=1)
        #     old_root_node = self._root_node
        #     assert old_root_node.depth == 0
        #     old_root_node.depth += 1
        #     self._root_node = new_root_father
        #     self._root = new_page_id_father
        #     father_child_key_list = [origin_mi_key, right_key]
        #     father_child_node_list = [old_root_node, new_root_son]
        #     self._root_node._child_key_list = father_child_key_list
        #     self._root_node._child_list = father_child_node_list
        #     # import pdb; pdb.set_trace()
        #     # print("Finish New Root",  f"(_root, {self._root}, root_node.page {self._root_node.page,}, root_node.father {self._root_node._father})", self._root_node.page_size())
        #     return new_page_id_father

    def delete(self, key, value: RID):
        self._is_modified = True
        self._root_node.remove(key=key, value=value)
        return None

    def search(self, key):
        return self._root_node.search(key=key)

    def range(self, lo, hi):
        return self._root_node.range(lo=lo, hi=hi)

    def pour(self):
        temp_node_list = []
        temp_node = None
        temp_node_list.append(self._root_node)
        while len(temp_node_list) > 0:
            temp_node = temp_node_list.pop(0)
            page_id = temp_node.page
            data = temp_node.to_array()
            if isinstance(temp_node, NoneLeafNode):
                for item in temp_node.child_list:
                    temp_node_list.append(item)
            self._handler.put_page(page_id=page_id, data=data)
        return None

    def build_node(self, page_id):
        # print("BUILD NODE", page_id)
        self._is_modified = True
        page_data: np.ndarray = self._handler.get_page(page_id=page_id)
        page_data.dtype = np.int64
        node_type = page_data[0]
        if node_type == 0:
            res: NoneLeafNode = self._build_node_type_0(page_id=page_id, data=page_data)
        elif node_type == 1:
            res: LeafNode = self._build_node_type_1(page_id=page_id, data=page_data)
        else:
            raise ValueError('node_type error!')
        return res

    def _build_node_type_1(self, page_id, data: np.ndarray):
        data.dtype = np.int64
        child_num = data[4]
        child_key_list = []
        child_rid_list = []
        for i in range(child_num):
            child_key_list.append(data[5 + 3 * i])
            rid = RID(int(data[6 + 3 * i]), int(data[7 + 3 * i]))
            child_rid_list.append(rid)
        leaf_node = LeafNode(page=page_id, father=data[1], left=data[2], right=data[3], child_key_list=child_key_list,
                             child_list=child_rid_list, index_handler=self._handler)
        return leaf_node

    def _build_node_type_0(self, page_id, data: np.ndarray):
        data.dtype = np.int64
        child_num = data[2]
        child_key_list = []
        child_node_list = []
        for i in range(child_num):
            child_key_list.append(data[3 + 2 * i])
            child_node_list.append(self.build_node(data[4 + 2 * i]))
        nonleaf_node = NoneLeafNode(page=page_id, father=data[1], child_key_list=child_key_list,
                                    child_list=child_node_list, index_handler=self._handler)
        return nonleaf_node

    def take(self):
        # print("take!!!", self._root)
        page_data: np.ndarray = self._handler.get_page(page_id=self._root)
        page_data.dtype = np.int64
        assert (page_data[1] == self._root), f'page take error! root {self.root, page_data[1]} page{page_data.shape}'
        self._root_node = self.build_node(page_id=self._root)
        return None

    @property
    def handler(self):
        return self._handler

    @property
    def root(self):
        return self._root
