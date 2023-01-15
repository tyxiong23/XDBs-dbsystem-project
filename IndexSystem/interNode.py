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

        self._child_key_list = child_key_list
        self._child_list = child_list

        self._page = page
        self._father = father
        self.depth = depth

    def insert(self, key, value):
        # print(f"NonLeaf::insert{self.page} {key}")
        cursor = self.lower_bound(key)
        # cursor_new = self.upper_bound(key)
        if cursor is not None:
            node: BasicNode = self._child_list[cursor]
            node.insert(key=key, value=value)
            if key > self._child_key_list[cursor]:
                self._child_key_list[cursor] = key
            node_page_size = node.page_size()
            if node_page_size <= PAGE_SIZE:
                return
            else:
                right_child_key_list, right_child_list, origin_mi_key = node.split()
                old_key = self._child_key_list[cursor]
                self._child_key_list[cursor] = origin_mi_key
                cursor = cursor + 1
                self._child_key_list.insert(cursor, old_key)
                new_page_id = self._handler.new_page()
                if node._node_type == 0:
                    new_node = InterNode(page=new_page_id, father=self._page, child_key_list=right_child_key_list,
                                            child_list=right_child_list, index_handler=self._handler, depth=self.depth+1)
                    self._child_list.insert(cursor, new_node)
                elif node._node_type == 1:
                    new_node = LeafNode(page=new_page_id, father=self._page, left=node._page, right=new_page_id,
                                        child_key_list=right_child_key_list,
                                        child_list=right_child_list, index_handler=self._handler, depth=self.depth+1)
                    self._child_list.insert(cursor, new_node)
                else:
                    raise ValueError('node_type error!')
                return None
        else:
            new_page = self._handler.new_page()
            node = LeafNode(page=new_page, father=self._page, left=0, right=0, child_key_list=[], child_list=[],
                            index_handler=self._handler, depth=self.depth+1)
            self._child_list.append(node)
            self._child_key_list.append(key)
            node.insert(key=key, value=value)
            return None

    def remove(self, key, value):
        lower = self.lower_bound(key=key)
        upper = self.upper_bound(key=key)
        delta: int = 0
        res = None
        len_key_list = len(self._child_key_list)
        if upper < len_key_list:
            upper = upper + 1
        if lower is None or upper is None or lower >= upper:
            return res
        for index in range(lower, upper):
            index = index - delta
            node: BasicNode = self._child_list[index]
            temp = node.remove(key=key, value=value)
            if temp is not None:
                self._child_key_list[index] = temp
                if index == 0:
                    res = temp
                else:
                    res = res
            len_node_key_list = len(node._child_key_list)
            if len_node_key_list == 0:
                delta = delta + 1
                self._child_key_list.pop(index)
                self._child_list.pop(index)
                if index == 0:
                    len_key_list = len(self._child_key_list)
                    if len_key_list > 0:
                        res = self._child_key_list[0]
        return res

    def page_size(self) -> int:
        # todo:modify
        len_key_list: int = len(self._child_key_list)
        res = 48 + 16 * len_key_list
        return res

    def to_array(self) -> np.ndarray:
        # todo:modify
        num: int = int(PAGE_SIZE / 8)
        array = np.zeros(num, np.int64)
        len_key_list = len(self._child_key_list)
        array[0] = 0
        array[1] = self._father
        array[2] = len_key_list
        for i in range(len_key_list):
            array[2 * i + 3] = self._child_key_list[i]
            node: BasicNode = self._child_list[i]
            array[2 * i + 4] = node._page
        array.dtype = np.uint8
        assert array.size == PAGE_SIZE
        return array

    def search(self, key):
        index = self.lower_bound(key=key)
        len_child_list = len(self._child_list)
        if len_child_list == index:
            index = index - 1
        # search in child
        return self._child_list[index].search(key=key)

    def range(self, lo, hi):
        res = []
        # print(f"NonLeafRange {self._page, self.depth}, {lo, hi}, {self.child_key_list}")      
        # if len(self._child_key_list) <= 0 or self._child_key_list[-1] < lo:
        #     return res
        lower = self.lower_bound(key=lo)
       
        # if lower is not None and upper is not None:
        #     print(f"nonleaf {self.depth}: l-u lid{lower} uid{upper} {self.child_key_list[lower], lo, hi, self.child_key_list}")
        if lower is None:
            return res
        else:
            upper = self.upper_bound(key=hi)
            len_child_key_list = len(self._child_key_list)
            if upper is not None:
                if upper< len_child_key_list:
                    upper = upper + 1
            for index in range(lower, upper):
                node = self._child_list[index]
                node_range = node.range(lo=lo, hi=hi)
                if node_range is not None:
                    res = res + node_range
            # print("Res", res)
            return res
