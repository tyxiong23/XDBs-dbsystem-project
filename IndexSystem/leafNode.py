from .basic_node import BasicNode
from .index_handler import IndexHandler
from RecordSystem.basicClass.rid import RID
# from ..RecordSystem.rid import RID
from utils.macro import *
# from ..FileSystem import macro
import numpy as np


class LeafNode(BasicNode):
    def __init__(self, page, father, left, right, child_key_list, child_list, index_handler: IndexHandler, depth: int = 0):
        super(LeafNode, self).__init__(index_handler)
        self._node_type = 1

        self.child_keys = child_key_list
        self.child_vals = child_list

        self.page_id = page
        self._father = father
        self._left = left
        self._right = right
        self.depth = depth

    def insert(self, key, value: RID):
        upper = self.upper_bound(key=key)
        if upper is None:
            self.child_keys.insert(0, key)
            self.child_vals.insert(0, value)
        else:
            self.child_keys.insert(upper, key)
            self.child_vals.insert(upper, value)
        return None

    def remove(self, key, value: RID):
        lower = self.lower_bound(key=key)
        cursor = upper = self.upper_bound(key=key)
        len_key_list = len(self.child_keys)
        if upper < len_key_list:
            upper = upper + 1
            cursor = cursor + 1
        for index in range(lower, upper):
            if self.child_vals[index] != value:
                continue
            else:
                cursor = index
                break
        if cursor != upper:
            self.child_keys.pop(cursor)
            self.child_vals.pop(cursor)
            len_key_list = len(self.child_keys)
            if len_key_list > 0:
                if cursor == 0:
                    return self.child_keys[0]
        else:
            return None

    def page_size(self) -> int:
        # todo:modify
        len_key_list: int = len(self.child_keys)
        res = 64 + 24 * len_key_list
        return res

    def to_array(self) -> np.ndarray:
        # todo:modify
        num: int = int(PAGE_SIZE >> 3)
        array = np.zeros(num, np.int64)
        array[0] = 1
        array[1] = self._father
        array[2] = self._left
        array[3] = self._right
        len_key_list = len(self.child_keys)
        array[4] = len_key_list
        for i in range(len_key_list):
            rid: RID = self.child_vals[i]
            array[3 * i + 5] = self.child_keys[i]
            array[3 * i + 6] = rid.page
            array[3 * i + 7] = rid.slot
        array.dtype = np.uint8
        assert array.size == 8192
        return array

    def range(self, lo, hi):  
        # print(f"LeafRange {self.page, self.depth}, {lo, hi}, {self.child_key_list[-1]}")      
        if self.child_keys[-1] < lo:
            return []
        elif self.child_key_list[0] > hi:
            return []
        lower = self.lower_bound(key=lo)
        upper = self.upper_bound(key=hi)
        # print(lo, hi, self.child_keys[lower:upper])
        # print(f"leaf {self.depth}: l-u lid{lower} uid{upper}  len{len(self.child_key_list)-1} {self.child_key_list[lower], lo, hi, self.child_key_list[-1]}")
        
        return self.child_vals[lower:upper]

        # print("l-u", lower, upper, self.child_keys[-1], lo)
        # if lower is None or upper is None:
        #     return None
        # elif lower > upper:
        #     return None
        # else:
            
    def search(self, key):
        index = self.lower_bound(key=key)
        if index == len(self.child_key_list):
            return None
        elif self.child_keys[index] == key:
            return self.child_vals[index]
        return None
