from RecordSystem.basicClass.record import Record
from .Column import ColumnInfo
from Exceptions.exception import *
from utils.constants import *
import numpy as np
import struct
from numbers import Number
from datetime import date

class TableInfo:
    
    def __init__(self, name: str, contents: list):
        self.name = name
        self.contents = contents
        
        self.foreign = {}
        self.index = {}
        self.unique = {}
        self.primary = None

        self.columnType = [col.type for col in self.contents]
        self.columnMap = {col.name: col for col in self.contents}
        self.columnSize = [col.getSize() for col in self.contents]   
        self.columnIndex = {self.contents[i].name: i for i in range(len(self.contents))} 
        self.rowSize = sum(self.columnSize)
        
    def describe(self):
        description = {
            col.name: col.getDESC() for col in self.contents
        }
        
        for key in self.foreign:
            if description[key][3]:
                description[key][3] = 'multi'
            else:
                description[key][3] = 'foreign'
        
        if self.primary:
            for key in self.primary:
                description[key][3] = 'primary'
       
        for key in self.unique:
            if description[key][3] == "":
                description[key][3] = 'unique'
                
        return tuple(description.values())

    def updataParams(self):
        self.columnMap = {col.name: col for col in self.contents}
        self.columnType = [col.type for col in self.contents]
        self.columnSize = [col.getSize() for col in self.contents]
        self.columnIndex = {self.contents[i].name: i for i in range(len(self.contents))}
        self.rowSize = sum(self.columnSize)

    def insertColumn(self, col: ColumnInfo):
        if col.name in self.columnMap:
            print("WRONG-----COLUMN NAME EXISTS")
            return
        self.contents.append(col)
        self.updataParams()
                  
    def removeColumn(self, name: str):
        if name not in self.columnMap:
            print("WRONG-----COLUMN NAME EXISTS")
            return
        self.contents.pop(self.columnIndex.get(name))
        self.updataParams()

    def addForeign(self, column: str, foreign):
        self.foreign[column] = foreign

    def removeForeign(self, column: str):
        if column in self.foreign:
            self.foreign.pop(column)

    def addUnique(self, column: str, uniq):
        self.unique[column] = uniq

    def buildRecord(self, val: list):
        if len(val) != len(self.columnSize):
            print("WRONG-----THE NUMBER OF VALUE DOES NOT MATCH")
            return
        offset = 0
        records = np.zeros(self.rowSize, dtype = np.uint8)
        
        for i in range(len(self.columnSize)):
        
            if self.columnType[i] != "VARCHAR":
                for j in range(self.columnSize[i]):
                    records[offset + j] = self.serialedValue(val[i], self.columnType[i])[j]
            
            else:
                byte = (1, )
                if val[i] is not None:
                    try:
                        byte = (0, ) + tuple(val[i].encode())
                        if len(byte) > self.columnSize[i]:
                            print("WRONG----EXCEED MAX SIZE" + str(self.columnSize[i] - 1))
                            return
                    except AttributeError:
                        print("WRONG----WRONG VALUE TYPE")
                        return
                records[offset: offset + len(byte)] = byte
                for j in range(offset + len(byte), offset + self.columnSize[i]):
                    records[j] = 0
            offset += self.columnSize[i]
        return records

    def serialedValue(self, val, type: str):
        if val is None:
            val = NULL_VALUE
            if type != "FLOAT":
                return struct.pack('<q', val)
            return struct.pack('<d', val)
        
        if type == "INT":
            if isinstance(val, int):
                return struct.pack('<q', val)
            else:
                print("WRONG----INT EXPECTED")
                return
        
        elif type == "FLOAT":
            if isinstance(val, Number):
                return struct.pack('<d', val)
            else:
                print("WRONG----FLOAT EXPECTED")
                return
                
        elif type == "DATE":
            try:
                val = val.replace("/", "-")
                vals = val.split("-")
            except AttributeError:
                print("WRONG----DATE VALUE INVALID")
                return
            
            try:
                d = date(*map(int, vals))
                
            except ValueError:
                print("WRONG----DATE VALUE INVALID")
                return
            return struct.pack('<q', d.toordinal())
           
        else:
            print("WRONG----VALID TYPE EXPECTED")
            return

    def loadRecord(self, record: Record):
        resultList = []
        offset = 0
        for i in range(len(self.columnSize)):
            value = None
            data = record.record[offset: offset + self.columnSize[i]]
            
            if self.columnType[i] == "INT":
                value = struct.unpack('<q', data)[0]
                
            elif self.columnType[i] == "FLOAT":
                value = struct.unpack('<d', data)[0]
                
            elif self.columnType[i] == "VARCHAR":
                if not data[0]:
                    value = data.tobytes()[1:].rstrip(b'\x00').decode('utf-8')
                
            elif self.columnType[i] == "DATE":
                value = struct.unpack('<q', data)[0]
                if value > 0 :
                    value = date.fromordinal(value)
           
            else:
                print("WRONG----VALID TYPE EXPECTED")
                return
            
            if value != NULL_VALUE:
                resultList.append(value)
                
            else:
                resultList.append(None)
            offset += self.columnSize[i]
        
        return tuple(resultList)

    def getColumnIndex(self, name: str):
        return self.columnIndex.get(name)

    def checkValue(self, valueMap: dict):
        for valueName in valueMap:
            
            if self.columnMap.get(valueName) is None:
                
                print("WRONG----UNKOWN FIELD:" + valueName)
                return
            
            colType = self.columnMap.get(valueName)
            value = valueMap.get(valueName)
            valueType = type(value)
            
            if colType == "INT":
                
                if valueType is not int:
                    print("WRONG----" + valueName + " EXPECT INT")
                    return
                
            elif colType == "FLOAT":
                if valueType not in (int, float):
                    print("WRONG----" + valueName + " EXPECT FLOAT")
                    return
                
            elif colType == "DATE":
                if valueType not in (date, str):
                    print("WRONG----" + valueName + " EXPECT DATE")
                    return
                if valueType is str:
                    value = value.replace("/", "-")
                    vals = value.split("-")
                    valueMap[valueName] = date(*map(int, vals))
                
            elif colType == "VARCHAR":
                if valueType is not str:
                    print("WRONG----" + valueName + " EXPECT VARCHAR")
                    return
                
            