from FileSystem.bufPageManager import BufPageManager
from .FileHandler import FileHandler

from Exceptions.exception import *
from utils.constants import *

from json import dumps, loads
import numpy as np

class RecordManager:
    def __init__(self, bm: BufPageManager):
        self.BM = bm
        self.opened = {}
        
    @staticmethod
    def toSerial(d: dict):
        serial = dumps(d, ensure_ascii=False).encode('utf-8')
        results = np.zeros(PAGE_SIZE, dtype=np.uint8)
        for i in range(len(serial)):
            results[i] = list(serial)[i]
        return results

    def head_get(self, recordLen: int, name: str):
        recordAmount = ( (PAGE_SIZE - PAGE_FIXED_HEADER )* 8) // (1 + (recordLen * 8)) + 1
        total = ((recordAmount + 7) / 8) + recordAmount * recordLen
        
        while total > (PAGE_SIZE - PAGE_FIXED_HEADER):
            recordAmount -= 1
            total = ((recordAmount + 7) / 8) + recordAmount * recordLen
             
        bitmapLength = int((recordAmount + 7) >> 3)
        
        return {'RecordLen': recordLen, 'RecordNum': recordAmount, 
                'PageNum': 1,'AllRecord': 0, 'NextAvai': 0, 
                'BitmapLen': bitmapLength, 'filename': str(name)}
            
    def file_create(self, name: str, recordLen: int):
        self.BM.create_file(name)
        fid = self.BM.open_file(name)
        self.BM.new_page(fid, self.toSerial(self.head_get(recordLen, name)))
        self.BM.close_file(fid)

    def file_open(self, name: str):
        if name in self.opened:
            # print(f"RecordManager::file_open {name, self.opened.keys()}")
            return self.opened[name]
        self.opened[name] = FileHandler(self, self.BM.open_file(name), name)
        return self.opened[name]
   
    def file_close(self, name: str):
        print("RecordManager::closeFIle", name)
        if self.opened.get(name) is None:
            return
        handler = self.opened.get(name)
        if handler.headChanged:
            handler.head_change()
        fid = handler.fileID
        self.BM.close_file(fid)
        self.opened.pop(name)
        handler.open = False

    def file_rename(self, src: str, dst: str):
        if self.opened.get(src) is not None:
            self.file_close(src)
        self.BM.move_file(src, dst) 
    
    def file_repalce(self, src: str, dst: str):
        if self.opened.get(src) is not None:
            self.file_close(src)
        if self.opened.get(dst) is not None:
            self.file_close(dst)
        self.file_destory(dst)
        self.BM.move_file(src, dst)

    def file_destory(self, name: str):
        self.file_close(name)
        self.BM.remove_file(name)
    
    def shut_down(self):
        for name in tuple(self.opened.keys()):
            self.file_close(name)