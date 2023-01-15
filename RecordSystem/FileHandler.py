from utils.macro import *
from .basicClass.rid import RID
from .basicClass.record import Record

import numpy as np
from json import loads

class FileHandler:

    def __init__(self, rm, fid: int, name: str):
        self.RM = rm
        self.fileID = fid
        self.name = name
        
        self.open = True # not opened
        
        self.headpage = self.RM.BM.get_page(self.fileID, 0)
        # print(f"self.head_page, {type(self.headpage)}, {self.headpage.shape}, {name}, {self.headpage}")
        self.head = loads(self.headpage.tobytes().decode('utf-8').rstrip('\0'))
        self.headChanged = False

    def __del__(self):
        if self.open:
            self.RM.closeFile(self.name)
    
    def calRecordOffset(self,slotID):
        return RECORD_PAGE_FIXED_HEADER + self.head['BitmapLen'] + slotID * self.head['RecordLen']
    
    def getBitmap(self, pageBuf: np.ndarray):
        return np.unpackbits(pageBuf[RECORD_PAGE_FIXED_HEADER: RECORD_PAGE_FIXED_HEADER + self.head['BitmapLen']])[:self.head['RecordNum']]
    
    def getNextAvai(self, pageBuf: np.ndarray):
        byte = pageBuf[RECORD_PAGE_NEXT_OFFSET: RECORD_PAGE_NEXT_OFFSET + 4].tobytes()
        # print("FileHandler::getNextAvai", int.from_bytes(byte, 'big', signed=True))
        return int.from_bytes(byte, 'big')
    
    def setNextAvai(self, pageBuf: np.ndarray, pageID: int):
        # print("FileHandler::setNextAvai", pageID, type(pageID))
        pageBuf[RECORD_PAGE_NEXT_OFFSET: RECORD_PAGE_NEXT_OFFSET + 4] = np.frombuffer(pageID.to_bytes(4, 'big'), dtype=np.uint8)
    
    def changeHead(self):
        self.RM.BM.fetch_page(self.fileID, 0, self.RM.toSerial(self.head)) 

    def getPage(self, pageID: int):
        return self.RM.BM.get_page(self.fileID, pageID)

    def fetchPage(self, pageID: int, buf: np.ndarray):
        self.RM.BM.fetch_page(self.fileID, pageID, buf)

    def newPage(self):
        return self.RM.BM.new_page(self.fileID, np.zeros(PAGE_SIZE, dtype=np.uint8))

    def appendPage(self):
        buffer = np.full(PAGE_SIZE, -1, dtype=np.uint8)
        # print("buf", buf[:5])
        buffer[PAGE_FLAG_OFFSET] = RECORD_PAGE_FLAG
        appendPageNextAvai = self.head['NextAvai']
        self.setNextAvai(buffer, self.head['NextAvai'])
        self.headChanged = True
        self.head['PageNum'] += 1
        self.head['NextAvai'] = self.RM.BM.new_page(self.fileID, buffer)
        # print(f"FileHandle::appendPage pid,appendNextAvai {pID, appendPageNextAvai, self.getNextAvai(buf)}")
    
    def getRecord(self, rid: RID, buf=None):
        if buf is None:
            buf = self.RM.BM.get_page(self.fileID, rid.page)
        # recordOff = rid.slot * self.head['RecordLen'] + RECORD_PAGE_FIXED_HEADER + self.head['BitmapLen']
        return Record(rid, buf[self.calRecordOffset(rid.slot): self.calRecordOffset(rid.slot) + self.head['RecordLen']])

    def insertRecord(self, record: np.ndarray):
        if self.head['NextAvai'] == 0:
            self.appendPage()
        nextAvailableSlot = self.head['NextAvai']
        page = self.RM.BM.get_page(self.fileID, nextAvailableSlot)
        # print(f"fileHandler::insertRecord nextAvai {nextAvai} nextPage.nextAvai {self.getNextAvai(page)}, {page[:5]}")
        bitmap = self.getBitmap(page)
        slotID = np.where(bitmap)[0][0]
        
        if len(np.where(bitmap)[0]) == 1:
            self.head['NextAvai'] = self.getNextAvai(page)
            self.setNextAvai(page, nextAvailableSlot)
            # print(f"fileHandler::insertRecord len(np.where(bitmap)[0]) == 1 {np.where(bitmap)[0]}, {self.getNextAvai(page)}, {page}")

        self.headChanged = True
        bitmap[slotID] = False
        self.head['AllRecord'] += 1
        page[self.calRecordOffset(slotID): self.calRecordOffset(slotID) + self.head['RecordLen']] = record
        page[RECORD_PAGE_FIXED_HEADER: RECORD_PAGE_FIXED_HEADER + self.head['BitmapLen']] = np.packbits(bitmap)
        self.RM.BM.fetch_page(self.fileID, nextAvailableSlot, page)
        return RID(nextAvailableSlot, slotID)

    def deleteRecord(self, rid: RID):
        self.headChanged = True
        self.head['AllRecord'] -= 1
        page = self.RM.BM.get_page(self.fileID, rid.page)
        bitmap = self.getBitmap(page)
        
        if bitmap[rid.slot] == 0:
            bitmap[rid.slot] = True
        
        page[RECORD_PAGE_FIXED_HEADER: RECORD_PAGE_FIXED_HEADER + self.head['BitmapLen']] = np.packbits(bitmap)
        if self.getNextAvai(page) == rid.page:
            self.setNextAvai(page, self.head['NextAvai'])
            self.head['NextAvai'] = rid.page
            
        self.RM.BM.fetch_page(self.fileID, rid.page, page)

    def updateRecord(self, record: Record):
        page = self.RM.BM.get_page(self.fileID, record.rid.page)
        page[self.calRecordOffset(record.rid.slot): self.calRecordOffset(record.rid.slot) + self.head['RecordLen']] = record.record
        self.RM.BM.fetch_page(self.fileID, record.rid.page, page)
