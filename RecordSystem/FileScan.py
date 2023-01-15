
from .FileHandler import FileHandler
from .basicClass.rid import RID
from utils.macro import *
import numpy as np

class FileScan:
    def __init__(self, handler: FileHandler):
        self.file_handler = handler

    def __iter__(self):
        for pageID in range(1, self.file_handler.head['PageNum']):
            page = self.file_handler.RM.BM.get_page(self.file_handler.fileID, pageID)
            if page[PAGE_FLAG_OFFSET] == RECORD_PAGE_FLAG:
                bitmap = self.file_handler.getBitmap(page)
                for slot in range(len(bitmap)):
                    if bitmap[slot] == 0:
                        yield self.file_handler.getRecord(RID(pageID, slot), page)
