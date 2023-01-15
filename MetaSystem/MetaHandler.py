
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
            self.updateMetaPath()
            

    def updateMetaPath(self):
        metaInfo = open(os.path.join(self.systemPath, self.databaseName, self.databaseName + ".me"), 'wb')
        pic.dump(self.databaseInfo, metaInfo)
        metaInfo.close()

    def setPrimary(self, table: str, pri):
        self.collectTableInfo(table).primary = pri
        self.updateMetaPath()

    def removePrimary(self, table: str):
        self.collectTableInfo(table).primary = None
        self.updateMetaPath()

    def addUnique(self, table: str, column: str, uniq: str):
        self.collectTableInfo(table).addUnique(column, uniq)
        self.updateMetaPath()

    def createIndex(self, index: str, table: str, column: str):
        self.databaseInfo.createIndex(index, table, column)
        self.updateMetaPath()

    def removeIndex(self, index: str):
        self.databaseInfo.removeIndex(index)
        self.updateMetaPath()
    
    def renameIndex(self, src: str, dst: str):
        if self.databaseInfo.indexMap.get(src) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.databaseInfo.indexMap[dst] = self.databaseInfo.indexMap.pop(src)
        self.updateMetaPath()
    
    def insertColumn(self, table: str, col: ColumnInfo):
        self.databaseInfo.insertColumn(table, col)
        self.updateMetaPath()

    def removeColumn(self, table: str, column: str):
        self.databaseInfo.removeColumn(table, column)
        self.updateMetaPath()
    
    def insertTable(self, table: TableInfo):
        self.databaseInfo.insertTable(table)
        self.updateMetaPath()

    def tb_delete(self, table: str):
        self.databaseInfo.tb_delete(table)
        self.updateMetaPath()

    def collectTableInfo(self, table: str):
        if self.databaseInfo.tableMap.get(table) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        return self.databaseInfo.tableMap[table]

    def renameTable(self, src: str, dst: str):
        if self.databaseInfo.tableMap.get(src) is None:
            print("WRONG----NAME DOES NOT EXIST")
            return
        self.databaseInfo.tableMap[dst] = self.databaseInfo.tableMap.pop(src)
        indexMap = self.databaseInfo.indexMap
        for index in indexMap.keys():
            if indexMap.get(index)[0] == src:
                colName = indexMap.get(index)[1]
                self.databaseInfo.indexMap[index] = (dst, colName)
        self.updateMetaPath()

    def getColumn2Table(self, tables: list):
        results = {}
        for table in tables:
            tableInfo = self.collectTableInfo(table)
            for col in tableInfo.columnMap.keys():
                if results.get(col) is not None:
                    results[col].append(table)
                    
                else:
                    results[col] = [table]   
        return results
    

    def shut_down(self):
        self.updateMetaPath()
