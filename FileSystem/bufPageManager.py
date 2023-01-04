import numpy as np
from utils.constant import *
from .fileManager import FileManager
from .findReplace import FindReplace
from typing import Tuple

class BufUtils:
    @staticmethod
    def combine_ids(fileID: int, pageID: int):
        return fileID | (pageID << FILE_ID_BITS)
    
    @staticmethod
    def split_ids(idx: int) -> Tuple[int]:
        pageID = idx >> FILE_ID_BITS
        fileID = (idx >> FILE_ID_BITS) << FILE_ID_BITS
        return fileID, pageID


class BufPageManager(FileManager):
    def __init__(self) -> None:
        super().__init__()
        self.replace = FindReplace(CAP)
        self.reset()

    def reset(self):
        self.dirty = np.zeros(CAP, dtype=bool)
        self.buffer = np.zeros((CAP, PAGE_SIZE), dtype=np.uint8)
        self.fpID2idx = dict()
        self.idx2fpID = np.zeros((CAP, ), dtype=int) + ID_DEFAULT_VAL
        self.file_cache_pages = dict()
        self.last = ID_DEFAULT_VAL

    def fetch_page(self, fileID: int, pageID: int) -> np.ndarray:
        fpID = BufUtils.combine_ids(fileID, pageID)
        idx = self.fpID2idx.get(fpID)
        if idx is None:
            data = self.get_page(fileID, pageID)
            idx = self.fpID2idx.get(fpID)
        self.buffer = data
        self.make_dirty(idx)

    def get_page(self, fileID: int, pageID: int):
        fpID = BufUtils.combine_ids(fileID, pageID)
        idx = self.fpID2idx.get(fpID)
        if idx is not None:
            self.access(idx)
            
        else:
            ## TODO
            idx = self.replace.find()
            last_id = self.idx2fpID[idx]
            if last_id != ID_DEFAULT_VAL:
                self.write_back(idx)

            # new page
            self.file_cache_pages[fileID].add(idx)
            self.fpID2idx[fpID] = idx
            self.idx2fpID[idx] = fpID
            page_data = self.read_page(fileID, pageID)
            data = np.frombuffer(page_data, np.uint8, PAGE_SIZE)
            self.buffer[idx] = data
            pass
        copy_page = self.buffer[idx].copy()
        return copy_page

    def make_dirty(self, idx: int):
        self.dirty[idx] = 1
        self.access(idx)

    def access(self, idx: int):
        if self.last != idx:
            self.replace.access(idx)
            self.last = idx
    
    def release(self, idx: int):
        self.dirty[idx] = 0
        self.replace.free(idx)
        fpID = self.idx2fpID[idx]
        fileID, _ = BufUtils.split_ids(fpID)
        self.file_cache_pages[fileID].remove(idx)
        self.idx2fpID[idx] = ID_DEFAULT_VAL
        self.fpID2idx.pop(fpID)

    def write_back(self, idx: int):
        if self.dirty[idx]:
            fileID, pageID = BufUtils.split_ids(idx)
            self.write_page(fileID, pageID, self.buffer[idx])
        self.release(idx)


    def open_file(self, name: str):
        fileID = super().open_file(name)
        self.file_cache_pages[fileID] = set()
        return fileID

    def close_file(self, fileID: int):
        ## TODO debug
        page_set: set = self.file_cache_pages.get(fileID, set())
        if len(page_set) > 0:
            for idx in page_set:
                self.write_back(idx)
        self.file_cache_pages.pop(fileID, set())
        return super().close_file(fileID)


    def shut_down(self):
        # release cache
        dirty_ids = np.arange(CAP)[self.dirty]
        for idx in dirty_ids:
            self.write_back()
        for fileID in self.file_cache_pages.keys():
            self.close_file(fileID)
        self.reset()
        

