
from Exceptions.exception import *
from utils.constants import *
from .Column import ColumnInfo
from .Table import TableInfo
from typing import List
class DatabaseInfo:
    def __init__(self, name, tables):
        self.name = name
        self.indexMap = {}
        self.tableMap = {}
        for table in tables:
            self.tableMap[table.name]: List[TableInfo] = table
        

    def insertTable(self, table: TableInfo):
        if self.tableMap.get(table.name) is not None:
            print("WRONG----NAME EXISTS")
            return
        self.tableMap[table.name] = table
        
    def tb_delete(self, table: str):
        if self.tableMap.get(table) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.tableMap.pop(table)
        return

    def insertColumn(self, table: str, col: ColumnInfo):
        if self.tableMap.get(table) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.tableMap[table].insertColumn(col)


    def removeColumn(self, table: str, col: str):
        if self.tableMap.get(table) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.tableMap[table].removeColumn(col)
        return

    def createIndex(self, index: str, table: str, col: str):
        if self.indexMap.get(index) is not None:
            print("WRONG----NAME EXISTS")
            return
        self.indexMap[index] = (table, col)

    def removeIndex(self, index: str):
        if self.indexMap.get(index) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.indexMap.pop(index)
        return

    def getIndex(self, index: str):
        if self.indexMap.get(index) is not None:
            return self.indexMap.get(index)
        print("WRONG----NAME DOES NOT EXIST")
        