from FileSystem.FileManager import FileManager
from FileSystem.BufManager import BufManager
from .FileHandler import FileHandler

from Exceptions.exception import *
from utils.macro import *

from json import dumps, loads
import numpy as np

class RecordManager:
    def __init__(self, bm: BufManager):
        self.BM = bm
        self.opened = {}
        
    @staticmethod
    def toSerial(d: dict):
        serial = dumps(d, ensure_ascii=False).encode('utf-8')
        results = np.zeros(PAGE_SIZE, dtype=np.uint8)
        for i in range(len(serial)):
            results[i] = list(serial)[i]
        return results

    def getHead(self, recordLen: int, name: str):
        recordAmount = ( (PAGE_SIZE - RECORD_PAGE_FIXED_HEADER )* 8) // (1 + (recordLen * 8)) + 1
        total = ((recordAmount + 7) / 8) + recordAmount * recordLen
        
        while total > (PAGE_SIZE - RECORD_PAGE_FIXED_HEADER):
            recordAmount -= 1
            total = ((recordAmount + 7) / 8) + recordAmount * recordLen
            
        # if recordAmount <= 0:
        #      print("WRONG-----RECORD TOO LONG")
             
        bitmapLength = int((recordAmount + 7) >> 3)
        
        return {'RecordLen': recordLen, 'RecordNum': recordAmount, 
                'PageNum': 1,'AllRecord': 0, 'NextAvai': 0, 
                'BitmapLen': bitmapLength, 'filename': str(name)}
            
    def createFile(self, name: str, recordLen: int):
        self.BM.FM.createFile(name)
        fid = self.BM.openFile(name)
        self.BM.FM.newPage(fid, self.toSerial(self.getHead(recordLen, name)))
        self.BM.closeFile(fid)

    def openFile(self, name: str):
        if name in self.opened:
            # print(f"RecordManager::openFile {name, self.opened.keys()}")
            return self.opened[name]
        self.opened[name] = FileHandler(self, self.BM.openFile(name), name)
        return self.opened[name]
   
    def closeFile(self, name: str):
        print("RecordManager::closeFIle", name)
        if self.opened.get(name) is None:
            return
        handler = self.opened.get(name)
        if handler.headChanged:
            handler.changeHead()
        fid = handler.fileID
        self.BM.closeFile(fid)
        self.opened.pop(name)
        handler.open = False

    def renameFile(self, src: str, dst: str):
        if self.opened.get(src) is not None:
            self.closeFile(src)
        self.BM.FM.renameFile(src, dst) 
    
    def replaceFile(self, src: str, dst: str):
        if self.opened.get(src) is not None:
            self.closeFile(src)
        if self.opened.get(dst) is not None:
            self.closeFile(dst)
        self.destroyFile(dst)
        self.BM.FM.renameFile(src, dst)

    def destroyFile(self, name: str):
        self.closeFile(name)
        self.BM.FM.destroyFile(name)
    
    def shutdown(self):
        for name in tuple(self.opened.keys()):
            self.closeFile(name)