from .index_handler import IndexHandler
from .file_index import FileIndex
from typing import Dict
from FileSystem.bufPageManager import BufPageManager


class IndexManager:
    def __init__(self, buf_manager: BufPageManager, home_directory: str = '/'):
        self._home_directory = home_directory
        self.buf_manager = buf_manager
        self.db2indexHandler: Dict[str, IndexHandler] = {}
        self.FIDX2fileIndex: Dict[FileIndex.UnitFIDX, FileIndex] = {}


    def get_index_handler(self, database_name):
        if database_name in self.db2indexHandler:
            return self.db2indexHandler[database_name]
        else:
            new_handler: IndexHandler = IndexHandler(bm=self.buf_manager, database_name=database_name,
                                                     home_directory=self._home_directory)
            self.db2indexHandler[database_name]: IndexHandler = new_handler
            return self.db2indexHandler[database_name]


    def create_index(self, database_name, table_name):
        index_handler = self.get_index_handler(database_name)
        root_id = index_handler.new_page()
        FIDX = FileIndex.UnitFIDX(table_name, root_id)
        self.FIDX2fileIndex[FIDX] = FileIndex(index_handler, root_id)
        self.FIDX2fileIndex[FIDX].write_back()
        return self.FIDX2fileIndex[FIDX]

    def start_index(self, database_name, table_name, root_id):
        FIDX = FileIndex.UnitFIDX(table_name, root_id)
        file_index = self.FIDX2fileIndex.get(FIDX, None)
        if file_index:
            return file_index

        else:
            index_handler = self.get_index_handler(database_name=database_name)
            file_index = FileIndex(index_handler, root_id)
            # load data
            file_index.build_index_tree()
            self.FIDX2fileIndex[FIDX] = file_index
            return file_index

    def update_index(self, table_name, old_root_id, new_root_id):
        NEW_ID = FileIndex.UnitFIDX(table_name, new_root_id)
        OLD_ID = FileIndex.UnitFIDX(table_name, old_root_id)
        assert NEW_ID not in self.FIDX2fileIndex and OLD_ID in self.FIDX2fileIndex, f"{OLD_ID in self.FIDX2fileIndex} and {NEW_ID not in self.FIDX2fileIndex}"
        tmp_index = self.FIDX2fileIndex.pop(OLD_ID)
        self.FIDX2fileIndex[NEW_ID] = tmp_index
        tmp_index.write_back()

    def shut_down(self):
        for db_name in tuple(self.db2indexHandler):
            if db_name in self.db2indexHandler:
                index_handler = self.db2indexHandler.pop(db_name)
                dict_copy = self.FIDX2fileIndex.copy()
                copy_keys = dict_copy.keys()
                for key in copy_keys:
                    if key in self.FIDX2fileIndex:
                        tmp_file_index = self.FIDX2fileIndex.pop(key)
                        if tmp_file_index.dirty:
                            tmp_file_index.write_back()
                    else:
                        break
        return None
