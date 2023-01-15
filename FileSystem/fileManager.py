from utils.constants import *
import numpy as np
import os


class FileManager:
    def __init__(self) -> None:
        # self.fd = np.zeros(FILE_MAX_AMOUNT, dtype=int)
        self.file_dict = dict()
        self.OPEN_FILE_MOD = os.O_RDWR

    def create_file(self, name: str):
        f = open(name, "w")
        assert f is not None, f"Fail to create file [{name}]!!"
        f.close()
        pass
    
    def open_file(self, name: str):
        fileID: int = os.open(name, self.OPEN_FILE_MOD)
        assert fileID != -1, f"Cannot open file [{name}]"
        return fileID

    def remove_file(self, name: str):
        os.remove(name)

    def write_page(self, fileID: int, pageID: int, buf: np.ndarray):
        # print("FileManager write page", fileID, pageID)
        os.lseek(fileID, pageID << PAGE_SIZE_BITS, os.SEEK_SET)
        os.write(fileID, buf.tobytes())

    def new_page(self, fileID: int, buf: np.ndarray):
        offset = os.lseek(fileID, 0, os.SEEK_END)
        bt_buffers = buf.tobytes()
        os.write(fileID, bt_buffers)
        return offset >> PAGE_SIZE_BITS

    def read_page(self, fileID: int, pageID: int):
        os.lseek(fileID, pageID << PAGE_SIZE_BITS, os.SEEK_SET)
        error = os.read(fileID, PAGE_SIZE)
        assert error is not None, f"Cannot read page {pageID} in file {fileID}!!"
        return error

    def close_file(self, fileID: int):
        os.close(fileID)
    
    def move_file(self, src: str, dst: str):
        os.rename(src, dst)

    def file_exists(self, name: str):
        return True if os.path.exists(name) else False