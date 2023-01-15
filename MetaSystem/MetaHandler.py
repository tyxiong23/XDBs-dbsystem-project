
from .basicClass.Column import *
from .basicClass.Table import *
from .basicClass.Database import *
import pickle as pic
import os


class MetaHandler:
    def __init__(self, database: str, syspath: str):

        self.databaseName = database
        self.databaseInfo = None
        self.systemPath = syspath
        self.metaPath = os.path.join(self.systemPath, self.databaseName, self.databaseName + ".me")
        
        if  os.path.exists(self.metaPath):
            self.metaPath = os.path.join(self.systemPath, self.databaseName, self.databaseName + ".me")
            metaInfo = open(self.metaPath, 'rb')
            self.databaseInfo = pic.load(metaInfo)
            metaInfo.close()
        else:
            self.databaseInfo = DatabaseInfo(self.databaseName, [])
            self.path_update()
            

    def path_update(self):
        metaInfo = open(os.path.join(self.systemPath, self.databaseName, self.databaseName + ".me"), 'wb')
        pic.dump(self.databaseInfo, metaInfo)
        metaInfo.close()

    def primary_set(self, table: str, pri):
        self.tb_info(table).primary = pri
        self.path_update()

    def primary_delete(self, table: str):
        self.tb_info(table).primary = None
        self.path_update()

    def unique_add(self, table: str, column: str, uniq: str):
        self.tb_info(table).unique_add(column, uniq)
        self.path_update()

    def idx_create(self, index: str, table: str, column: str):
        self.databaseInfo.idx_create(index, table, column)
        self.path_update()

    def idx_delete(self, index: str):
        self.databaseInfo.idx_delete(index)
        self.path_update()
    
    def idx_rename(self, src: str, dst: str):
        if self.databaseInfo.indexMap.get(src) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.databaseInfo.indexMap[dst] = self.databaseInfo.indexMap.pop(src)
        self.path_update()
    
    def col_insert(self, table: str, col: ColumnInfo):
        self.databaseInfo.col_insert(table, col)
        self.path_update()

    def col_delete(self, table: str, column: str):
        self.databaseInfo.col_delete(table, column)
        self.path_update()
    
    def tb_insert(self, table: TableInfo):
        self.databaseInfo.tb_insert(table)
        self.path_update()

    def tb_delete(self, table: str):
        self.databaseInfo.tb_delete(table)
        self.path_update()

    def tb_info(self, table: str):
        if self.databaseInfo.tableMap.get(table) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        return self.databaseInfo.tableMap[table]

    def tb_rename(self, src: str, dst: str):
        if self.databaseInfo.tableMap.get(src) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.databaseInfo.tableMap[dst] = self.databaseInfo.tableMap.pop(src)
        indexMap = self.databaseInfo.indexMap
        for index in indexMap.keys():
            if indexMap.get(index)[0] == src:
                colName = indexMap.get(index)[1]
                self.databaseInfo.indexMap[index] = (dst, colName)
        self.path_update()

    def col2tb(self, tables: list):
        results = {}
        for table in tables:
            tableInfo = self.tb_info(table)
            for col in tableInfo.columnMap.keys():
                if results.get(col) is not None:
                    results[col].append(table)
                    
                else:
                    results[col] = [table]   
        return results
    

    def shut_down(self):
        self.path_update()
