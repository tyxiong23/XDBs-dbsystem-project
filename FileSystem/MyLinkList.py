import numpy as np


class LinkList:
    def __init__(self, c: int, n: int):
        self.cap = c
        self.list_num = n
        sum_res = self.cap + self.list_num
        self.next = np.arange(sum_res)
        self.prev = np.arange(sum_res)

    def delete(self, index: int):
        if self.prev[index] != index:
            prev = self.prev[index]
            next = self.next[index]
            self.link(prev, next)
            self.prev[index] = index
            self.next[index] = index
        else:
            return

    def link(self, prev: int, next: int):
        # print("link", prev, next)
        self.next[prev] = next
        self.prev[next] = prev

    def insert(self, listID: int, ele: int):
        self.delete(ele)
        node = listID + self.cap
        prev = self.prev[node]
        self.link(prev, ele)
        self.link(ele, node)

    def insertFirst(self, listID: int, ele: int):
        self.delete(ele)
        next_id = self.next[listID + self.cap]
        self.link(listID + self.cap, ele)
        self.link(ele, next_id)

    def isHead(self, index: int):
        return index >= self.cap

    def getFirst(self, listID: int):
        return self.next[listID + self.cap]

    def isAlone(self, index: int):
        return self.next[index] == index
