import numpy as np

class MyLinkList:
    def __init__(self, _cap: int, _list_num: int) -> None:
        self.cap = _cap
        self.list_num = _list_num
        self.next = np.arange(self.cap + self.list_num)
        self.prev = np.arange(self.cap + self.list_num)

    def link(self, prev: int, next: int):
        self.prev[next] = prev
        self.next[prev] = next

    def delete(self, idx: int):
        if self.prev[idx] == idx:
            return
        self.link(self.prev[idx], self.next[idx])
        self.prev[idx] = self.next[idx] = idx
    
    def insert(self, listID: int, ele: int):
        self.delete(ele)
        node = listID + self.cap
        prev = self.prev[node]
        self.link(prev, ele)
        self.link(ele, node)
    
    def insert_first(self, listID: int, ele: int):
        self.delete(ele)
        node = listID + self.cap
        next = self.next[node]
        self.link(node, ele)
        self.link(ele, next)

    def is_head(self, idx: int):
        return False if idx < self.cap else True
    
    def is_alone(self, idx: int):
        return self.next[idx] == idx

    def get_next(self, idx: int) -> int:
        return self.next[idx]
    
    def get_first(self, listID: int) -> int:
        return self.get_next(listID + self.cap)
