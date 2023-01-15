import numpy as np

from FileSystem.bufPageManager import BufPageManager
from utils.constants import *
import os

class IndexHandler:
    def __init__(self, bm: BufPageManager, database_name, home_directory):
        self.bufPageManager = bm
        index_file_name = database_name + INDEX_NAME
        index_file_path = os.path.join(home_directory, database_name, index_file_name)
        if not os.path.exists(index_file_path):
            self.bufPageManager.create_file(index_file_path)
        self.file_id = self.bufPageManager.open_file(index_file_path)
        self.dirty = False

    def get_page(self, page_id):
        res: np.ndarray = self.bufPageManager.get_page(self.file_id, page_id)
        return res

    def put_page(self, page_id, data):
        self.dirty = True
        self.bufPageManager.fetch_page(self.file_id, page_id, data)
        return None

    def new_page(self):
        page_id: int = self.bufPageManager.new_page(self.file_id, np.zeros(PAGE_SIZE, dtype=np.uint8))
        return page_id

    def close_file(self):
        self.bufPageManager.close_file(self.file_id)
        return None
