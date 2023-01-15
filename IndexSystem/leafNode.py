from .basic_node import BasicNode
from .index_handler import IndexHandler
from RecordSystem.basicClass.rid import RID
from utils.constants import *
import numpy as np


class LeafNode(BasicNode):
    def __init__(self, page, father, left, right, child_keys, child_list, index_handler: IndexHandler, depth: int = 0):
        super(LeafNode, self).__init__(index_handler)
        self._node_type = LEAF_NODE_TYPE

        self.child_keys = child_keys
        self.child_vals = child_list

        self.page_id = page
        self.father_id = father
        self.depth = depth

    def insert(self, key, value: RID):
        upper = self.upper_bound(key)
        if upper is None:
            self.child_keys.insert(0, key)
            self.child_vals.insert(0, value)
        else:
            self.child_keys.insert(upper, key)
            self.child_vals.insert(upper, value)
        return None

    def remove(self, key, value: RID):
        lower = self.lower_bound(key)
        upper = self.upper_bound(key)
        len_key_list = len(self.child_keys)
        if upper < len_key_list:
            upper = upper + 1
        pos = upper
        for index in range(lower, upper):
            if self.child_vals[index] != value:
                pos = index
                break
                
        if pos == upper:
            return None
        self.child_keys.pop(pos)
        self.child_vals.pop(pos)
        if len(self.child_keys) > 0 and pos == 0:
            return self.child_keys[0]


    def page_size(self) -> int:
        len_key_list: int = len(self.child_keys)
        res = 48 + 24 * len_key_list
        return res

    def serialize(self) -> np.ndarray:
        num: int = int(PAGE_SIZE >> 3)
        array = np.zeros(num, np.int64)
        array[0] = 1
        array[1] = self.father_id
        len_key_list = len(self.child_keys)
        array[2] = len_key_list
        for i in range(len_key_list):
            rid: RID = self.child_vals[i]
            array[3 * i + 3] = rid.page
            array[3 * i + 4] = rid.slot
            array[3 * i + 5] = self.child_keys[i]
        array.dtype = np.uint8
        assert array.size == 8192
        return array

    def range(self, low, high):  
        # print(f"LeafRange {self.page, self.depth}, {lo, hi}, {self.child_keys[-1]}")      
        if self.child_keys[-1] < low:
            return []
        elif self.child_keys[0] > high:
            return []
        lower = self.lower_bound(low)
        upper = self.upper_bound(high)
        # print(lo, hi, self.child_keys[lower:upper])
        # print(f"leaf {self.depth}: l-u lid{lower} uid{upper}  len{len(self.child_keys)-1} {self.child_keys[lower], lo, hi, self.child_keys[-1]}")
        
        return self.child_vals[lower:upper]

        # print("l-u", lower, upper, self.child_keys[-1], lo)
        # if lower is None or upper is None:
        #     return None
        # elif lower > upper:
        #     return None
        # else: