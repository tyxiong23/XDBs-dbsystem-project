from .linkList import LinkList

"""
基于自己实现的linkedList
完成了LRU替换算法
"""

class FindReplace:
    def __init__(self, cap: int) -> None:
        self.capacity = cap
        self.linkedList = LinkList(cap, 1)
        for idx in range(self.capacity):
            self.linkedList.insert_first(0, self.capacity - 1 - idx)
        
    def find(self):
        idx = self.linkedList.get_first(0)
        self.linkedList.delete(idx)
        self.linkedList.insert(0, idx)
        return idx

    def access(self, idx: int):
        self.linkedList.insert(0, idx)
    
    def free(self, idx: int):
        self.linkedList.insert_first(0, idx)