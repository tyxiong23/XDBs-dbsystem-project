
from utils.exception import *
from utils.constants import *
from .Column import ColumnInfo
from .Table import TableInfo
from typing import List
from utils.exception import DatabaseError

class DatabaseInfo:
    def __init__(self, name, tables):
        self.name = name
        self.indexMap = {}
        self.tableMap = {}
        for table in tables:
            self.tableMap[table.name]: List[TableInfo] = table
        

    def tb_insert(self, table: TableInfo):
        if self.tableMap.get(table.name) is not None:
            raise DatabaseError("WRONG ---- tb-insert NAME EXISTS")
            return
        self.tableMap[table.name] = table
        
    def tb_delete(self, table: str):
        if self.tableMap.get(table) is None:
            raise DatabaseError("WRONG ---- tb-delete NAME DOES NOT EXIST")
            return
        self.tableMap.pop(table)
        return

    def col_insert(self, table: str, col: ColumnInfo):
        if self.tableMap.get(table) is None:
            raise DatabaseError("WRONG ---- col-insert NAME DOES NOT EXIST")
            return
        self.tableMap[table].col_insert(col)


    def col_delete(self, table: str, col: str):
        if self.tableMap.get(table) is None:
            raise DatabaseError("WRONG ---- col-delete NAME DOES NOT EXIST")
            return
        self.tableMap[table].col_delete(col)
        return

    def idx_create(self, index: str, table: str, col: str):
        if self.indexMap.get(index) is not None:
            raise DatabaseError("WRONG ---- idx-create NAME EXISTS")
            return
        self.indexMap[index] = (table, col)

    def idx_delete(self, index: str):
        if self.indexMap.get(index) is None:
            raise DatabaseError("WRONG ---- idx_delete NAME DOES NOT EXIST")
            return
        self.indexMap.pop(index)
        return

    def idx_get(self, index: str):
        if self.indexMap.get(index) is not None:
            return self.indexMap.get(index)
        raise DatabaseError("WRONG ---- idx_get NAME DOES NOT EXIST")
        