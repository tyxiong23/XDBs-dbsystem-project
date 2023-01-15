from .index_handler import IndexHandler
from .file_index import FileIndex
from typing import Dict
from FileSystem.BufManager1 import BufManager
from .FileIndexID import FileIndexID


class IndexManager:
    def __init__(self, buf_manager: BufManager, home_directory: str = '/'):
        self._buf_manager = buf_manager
        self._home_directory = home_directory
        self._started_index_handler: Dict[str, IndexHandler] = {}
        self._started_file_index: Dict[FileIndexID, FileIndex] = {}

    def close_manager(self):
        for db_name in tuple(self._started_index_handler):
            self.shut_handler(database_name=db_name)
        return None

    def catch_handler(self, database_name):
        if database_name in self._started_index_handler:
            return self._started_index_handler[database_name]
        else:
            # not exist
            new_handler: IndexHandler = IndexHandler(buf_manager=self._buf_manager, database_name=database_name,
                                                     home_directory=self._home_directory)
            self._started_index_handler[database_name]: IndexHandler = new_handler
            return self._started_index_handler[database_name]

    def shut_handler(self, database_name):
        # print("Shut_Handler!!!!")
        if database_name in self._started_index_handler:
            # print("Shut_Handler", database_name, self._started_index_handler[database_name]._is_modified)
            old_index_handler = self._started_index_handler.pop(database_name)
            index_handler = old_index_handler
            for_range = tuple(self._started_file_index.items())
            # print("for_range", [(key._table_name, key._file_index_root_id) for (key, _) in for_range])
            for key, file_index in for_range:
                # print(file_index.handler)
                # print()
                # if file_index.handler is not index_handler:
                #     continue
                fileIndexID = FileIndexID(key._table_name, key._file_index_root_id)
                if fileIndexID not in self._started_file_index:
                    return None
                tmp_file_index = self._started_file_index.pop(fileIndexID)
                # print("FORRR ", key._table_name, tmp_file_index._is_modified, tmp_file_index.is_modified)
                if tmp_file_index.is_modified:
                    tmp_file_index.pour()
            return True
            # for ID in self._started_file_index:
            #     file_index = self._started_file_index.get(ID)
            #     if file_index.handler is index_handler:
            #         # shut index
            #         if ID in self._started_file_index:
            #             tmp_file_index = self._started_file_index.pop(ID)
            #             if tmp_file_index.is_modified:
            #                 tmp_file_index.pour()
        else:
            return False

    def create_index(self, database_name, table_name):
        handler = self.catch_handler(database_name=database_name)
        root_id = handler.new_page()
        ID = FileIndexID(table_name=table_name, file_index_root_id=root_id)
        self._started_file_index[ID] = FileIndex(index_handler=handler, root_id=root_id)
        self._started_file_index[ID].pour()
        return self._started_file_index[ID]

    def start_index(self, database_name, table_name, root_id):
        ID = FileIndexID(table_name=table_name, file_index_root_id=root_id)
        # print("Start INDEX", ID in self._started_file_index)
        if ID in self._started_file_index:
            file_index = self._started_file_index.get(ID)
            return file_index
        else:
            handler = self.catch_handler(database_name=database_name)
            file_index = FileIndex(index_handler=handler, root_id=root_id)
            # load data
            file_index.take()
            self._started_file_index[ID] = file_index
            return file_index

    def update_index(self, table_name, old_root_id, new_root_id):
        NEW_ID = FileIndexID(table_name=table_name, file_index_root_id=new_root_id)
        OLD_ID = FileIndexID(table_name=table_name, file_index_root_id=old_root_id)
        # print("update_index", NEW_ID, OLD_ID, [str(s) for s in self._started_file_index.keys()])
        assert NEW_ID not in self._started_file_index and OLD_ID in self._started_file_index, f"{OLD_ID in self._started_file_index} and {NEW_ID not in self._started_file_index}"
        tmp_index = self._started_file_index.pop(OLD_ID)
        self._started_file_index[NEW_ID] = tmp_index
        tmp_index.pour()
