
from .FileHandler import FileHandler
from .basicClass.rid import RID
from utils.macro import *
import numpy as np

class FileScan:
    def __init__(self, handler: FileHandler):
        self.handler = handler

    def __iter__(self):
        for pageID in range(1, self.handler.head['PageNum']):
            page = self.handler.RM.BM.getPage(self.handler.fileID, pageID)
            if page[PAGE_FLAG_OFFSET] == RECORD_PAGE_FLAG:
                bitmap = self.handler.getBitmap(page)
                for slot in range(len(bitmap)):
                    if bitmap[slot] == 0:
                        yield self.handler.getRecord(RID(pageID, slot), page)
