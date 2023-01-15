from .index_handler import IndexHandler
from abc import abstractmethod
import numpy as np


class BasicNode:
    def __init__(self, index_handler: IndexHandler):
        self.page_id = None
        self._father = None
        self._node_type = -1 # -1 is abstract, 1 is leaf ,0 is inter
        self._handler = index_handler
        self.depth = 0
        self.child_keys: list = []
        self.child_vals: list = []

    def lower_bound(self, key):
        if len(self.child_keys) <= 0:
            return None
        pos = high = len(self.child_keys) - 1
        if key > self.child_keys[-1]:
            return pos
        elif key <= self.child_keys[0]:
            return 0
        low = 0
        # todo:modified
        while low < high:
            mid = (low + high) // 2
            if self.child_keys[mid] < key:
                low = mid + 1
            else:
                high = mid
        if self.child_keys[low] >= key:
            return low
        else:
            return pos

    def upper_bound(self, key):
        if len(self.child_keys) == 0:
            return None
        low = 0
        high = len(self.child_keys) - 1
        pos = len(self.child_keys)
        while low < high:
            mid = (low + high) // 2
            if self.child_keys[mid] > key:
                high, pos = mid, mid
            else:
                low = mid + 1
        if self.child_keys[low] > key:
            return low
        else:
            return pos


    @abstractmethod
    def insert(self, key, value):
        raise NotImplemented

    @abstractmethod
    def remove(self, key, value):
        raise NotImplemented

    @abstractmethod
    def child_list(self):
        raise NotImplemented

    @property
    def page(self):
        res = self.page_id
        return res

    @abstractmethod
    def page_size(self):
        raise NotImplemented

    @abstractmethod
    def to_array(self):
        raise NotImplemented

    @abstractmethod
    def range(self, low, high):
        raise NotImplemented

    @abstractmethod
    def search(self, key):
        raise NotImplemented

    def split(self):
        num_children = (len(self.child_keys) + 1) // 2
        mid = (num_children + 1) // 2
        right_child_keys = self.child_keys[mid:]
        right_child_vals = self.child_vals[mid:]
        self.child_vals = self.child_vals[:mid]
        self.child_keys = self.child_keys[:mid]
        left_max = self.child_key_list[-1]
        return right_child_keys, right_child_vals, left_max
