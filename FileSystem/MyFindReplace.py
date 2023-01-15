from .MyLinkList import LinkList


class FindReplace:
    def __init__(self, cap: int):
        self.CAP = cap
        self.list = LinkList(cap, 1)
        num = self.CAP - 1
        for item in range(num, -1, -1):
            # print("item", item)
            self.list.insertFirst(0, item)

        # print(self.list.next, self.list.prev)

        # for i in range(10):
        #     print("findreplace", i, self.find())

    def access(self, index: int):
        self.list.insert(0, index)

    def free(self, index: int):
        self.list.insertFirst(0, index)

    def find(self):
        index = self.list.getFirst(0)
        self.list.delete(index)
        self.list.insert(0, index)
        return index