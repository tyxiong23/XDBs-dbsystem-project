from .basic_node import BasicNode
from .leafNode import LeafNode
from .index_handler import IndexHandler
from utils.macro import *
# from ..FileSystem import macro
import numpy as np


class InterNode(BasicNode):
    def __init__(self, page, father, child_key_list, child_list, index_handler: IndexHandler, depth: int = 0):
        super(InterNode, self).__init__(index_handler)
        self._node_type = 0

        self.child_keys = child_key_list
        self.child_vals = child_list

        self.page_id = page
        self._father = father
        self.depth = depth

    def insert(self, key, value):
        # print(f"NonLeaf::insert{self.page} {key}")
        cursor = self.lower_bound(key)
        # cursor_new = self.upper_bound(key)
        if cursor is not None:
            node: BasicNode = self.child_vals[cursor]
            node.insert(key=key, value=value)
            if key > self.child_keys[cursor]:
                self.child_keys[cursor] = key
            node_page_size = node.page_size()
            if node_page_size <= PAGE_SIZE:
                return
            else:
                rightchild_keys, rightchild_vals, origin_mi_key = node.split()
                old_key = self.child_keys[cursor]
                self.child_keys[cursor] = origin_mi_key
                cursor = cursor + 1
                self.child_keys.insert(cursor, old_key)
                new_page_id = self._handler.new_page()
                if node._node_type == 0:
                    new_node = InterNode(new_page_id, self.page_id, rightchild_keys, rightchild_vals, self._handler, self.depth+1)
                    self.child_vals.insert(cursor, new_node)
                elif node._node_type == 1:
                    new_node = LeafNode(new_page_id, self.page_id, node.page_id, new_page_id, rightchild_keys, rightchild_vals, self._handler, self.depth+1)
                    self.child_vals.insert(cursor, new_node)
                else:
                    raise ValueError('node_type error!')
                return None
        else:
            new_page = self._handler.new_page()
            node = LeafNode(new_page, self.page_id, 0, 0, [], [], self._handler, self.depth+1)
            self.child_vals.append(node)
            self.child_keys.append(key)
            node.insert(key, value)
            return None

    def remove(self, key, value):
        lower = self.lower_bound(key)
        upper = self.upper_bound(key)
        delta: int = 0
        res = None
        len_key_list = len(self.child_keys)
        if upper < len_key_list:
            upper = upper + 1
        if lower is None or upper is None or lower >= upper:
            return res
        for index in range(lower, upper):
            index = index - delta
            node: BasicNode = self.child_vals[index]
            temp = node.remove(key=key, value=value)
            if temp is not None:
                self.child_keys[index] = temp
                if index == 0:
                    res = temp
                else:
                    res = res
            len_node_key_list = len(node.child_keys)
            if len_node_key_list == 0:
                delta = delta + 1
                self.child_keys.pop(index)
                self.child_vals.pop(index)
                if index == 0:
                    len_key_list = len(self.child_keys)
                    if len_key_list > 0:
                        res = self.child_keys[0]
        return res

    def page_size(self) -> int:
        return 48 + 16 * len(self.child_keys)

    def to_array(self) -> np.ndarray:
        # todo:modify
        num: int = int(PAGE_SIZE / 8)
        array = np.zeros(num, np.int64)
        len_key_list = len(self.child_keys)
        array[0] = 0
        array[1] = self._father
        array[2] = len_key_list
        for i in range(len_key_list):
            array[2 * i + 3] = self.child_keys[i]
            node: BasicNode = self.child_vals[i]
            array[2 * i + 4] = node.page_id
        array.dtype = np.uint8
        assert array.size == PAGE_SIZE
        return array

    def search(self, key):
        index = self.lower_bound(key)
        if len(self.child_vals) == index:
            index = index - 1
        # search in child
        return self.child_vals[index].search(key)

    def range(self, low, high):
        res = []  
        if len(self.child_keys) <= 0 or self.child_keys[-1] < low:
            return res
        lower = self.lower_bound(low)
       
        # if lower is not None and upper is not None:
        #     print(f"nonleaf {self.depth}: l-u lid{lower} uid{upper} {self.child_key_list[lower], lo, hi, self.child_key_list}")
        if lower is None:
            return res
        else:
            upper = self.upper_bound(high)
            lenchild_keys = len(self.child_keys)
            if upper is not None:
                upper = upper + 1 if upper < lenchild_keys else upper
            for index in range(lower, upper):
                node = self.child_vals[index]
                node_range = node.range(low, high)
                if node_range is not None:
                    res = res + node_range
            # print("Res", res)
            return res
