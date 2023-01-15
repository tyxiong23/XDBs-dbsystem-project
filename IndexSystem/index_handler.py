import numpy as np

from FileSystem.bufPageManager import BufPageManager
from utils.macro import *
import os

class IndexHandler:
    def __init__(self, bm: BufPageManager, database_name, home_directory):
        self._manager = bm
        index_file_name = database_name + INDEX_NAME
        # index_file_path = home_directory + '/' + database_name + '/' + index_file_name
        index_file_path = home_directory / database_name / index_file_name
        if not os.path.exists(index_file_path):
            self._manager.create_file(index_file_path)
        file_id = self._manager.open_file(index_file_path)
        self._file_id = file_id
        self._is_modified = False

    def get_page(self, page_id):
        res: np.ndarray = self._manager.get_page(fileID=self._file_id, pageID=page_id)
        return res

    def put_page(self, page_id, data):
        self._is_modified = True
        self._manager.fetch_page(self._file_id, page_id, data)
        return None

    def new_page(self):
        data = np.zeros(PAGE_SIZE, dtype=np.uint8)
        page_id: int = self._manager.new_page(self._file_id, data)
        return page_id

    def close_file(self):
        self._manager.close_file(self._file_id)
        return None
