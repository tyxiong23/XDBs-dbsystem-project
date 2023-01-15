from FileSystem.bufPageManager import BufPageManager
from RecordSystem.FileScan import FileScan
from RecordSystem.FileHandler import FileHandler
from RecordSystem.basicClass.record import Record
from RecordSystem.basicClass.rid import RID
from RecordSystem.RecordManager import RecordManager
from IndexSystem.index_manager import IndexManager
from MetaSystem.MetaHandler import MetaHandler
from .basicClass.Term import Term
from .basicClass.Reducer import Reducer
from OutputSystem.basicClass.outputUnit import LookupOutput
from antlr_SQLparser.SQLParser import SQLParser
from antlr_SQLparser.SQLLexer import SQLLexer
from MetaSystem.basicClass.Table import TableInfo
from MetaSystem.basicClass.Column import ColumnInfo
from OutputSystem.TablePrinter import *
from utils.constants import *
from Exceptions.exception import *
from pathlib import Path
from .basicClass.Join import Join
from antlr4 import InputStream, CommonTokenStream
from antlr4.error.Errors import ParseCancellationException
from antlr4.error.ErrorListener import ErrorListener
from copy import deepcopy
from datetime import date
from typing import Tuple

import re


class SystemManger:
    def __init__(self, visitor, syspath: Path, bm: BufPageManager, rm: RecordManager, im: IndexManager):
        self.visitor = visitor
        self.systemPath = syspath
        self.BM = bm
        self.IM = im
        self.RM = rm
        self.metaHandlers = {}
        self.databaselist = []
        for item in syspath.iterdir():
            self.databaselist.append(item.name)
        self.inUse = None
        self.visitor.system_manager = self
        self.visitor.setManager(self)


    def __exit__(self, exc_type, exc_val, exc_tb):
        self.shut_down()

    @staticmethod
    def pattern_setup(pattern: str):
        pattern = pattern.replace('%%', '\r')
        pattern = pattern.replace('%?', '\n')
        pattern = pattern.replace('%_', '\0')
        pattern = re.escape(pattern)
        pattern = pattern.replace('%', '.*')
        pattern = pattern.replace(r'\?', '.')
        pattern = pattern.replace('_', '.')
        pattern = pattern.replace('\r', '%')
        pattern = pattern.replace('\n', r'\?')
        pattern = pattern.replace('\0', '_')
        pattern = re.compile('^' + pattern + '$')
        return pattern
     
    @staticmethod
    def versus(this, operator, other):
        if operator == "=":
            return lambda x: x[this] == x[other]
        elif operator == "<":
            return lambda x: x[this] < x[other]
        elif operator == ">":
            return lambda x: x[this] > x[other]
        elif operator == "<>":
            return lambda x: x[this] != x[other]
        elif operator == "<=":
            return lambda x: x[this] <= x[other]
        elif operator == ">=":
            return lambda x: x[this] >= x[other]
  
    @staticmethod
    def versusV(this, operator, val):
        if operator == '=':
            return lambda x: x[this] == val
        elif operator == '<':
            return lambda x: x is not None and x[this] < val
        elif operator == '>':
            return lambda x: x is not None and x[this] > val
        elif operator == '<>':
            return lambda x: x[this] != val
        elif operator == '<=':
            return lambda x: x is not None and x[this] <= val
        elif operator == '>=':
            return lambda x: x is not None and x[this] >= val
    
    @staticmethod
    def getVal(result: LookupOutput, is_in):
        if len(result.headers) <= 1:
            val = sum(result.data, ())
            if not is_in:
                if len(result.data) == 1:
                    val, = val
                    return val
                raise ValueError("expect one value, get " + str(len(result.data)))
            return val
        raise SelectError("expect one column, get " + str(len(result.headers)))    
    
    def execute(self, sql):
        class StringErrorListener(ErrorListener):
            def syntaxError(self, recognizer, offending_symbol, line, column, msg, e):
                raise ParseCancellationException("line " + str(line) + ":" + str(column) + " " + msg)

        self.visitor.get_time_delta()
        input_stream = InputStream(sql)
        lexer = SQLLexer(input_stream)
        lexer.removeErrorListeners()
        lexer.addErrorListener(StringErrorListener())
        tokens = CommonTokenStream(lexer)
        parser = SQLParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(StringErrorListener())
        try:
            tree = parser.program()
        except ParseCancellationException as e:
            ret = LookupOutput(None, None, str(e), cost=self.visitor.get_time_delta())
            return [ret]
        try:
            ret = self.visitor.visit(tree)
            return ret
        except XdbException as e:
            ret = LookupOutput(message=str(e), cost=self.visitor.get_time_delta())
            return [ret]

    def shut_down(self):
        self.IM.shut_down()
        self.RM.shut_down()
        self.BM.shut_down()

    def exmineIfActive(self):
        if self.inUse is None:
            print("OH NO")
            raise NoDatabaseInUse("use a database first")
        return

    def cond_join(self, res_map: dict, term):
        if self.inUse is None:
            raise ValueError("No using database!!!")
        else:
            join = Join(res_map=res_map, term=term)
            result: LookupOutput = join.get_output()
            return result

    def db_create(self, dbname: str):
        if dbname not in self.databaselist:
            path: Path = self.systemPath / dbname
            path.mkdir(parents=True)
            self.databaselist.append(dbname)
        else:
            print("OH NO")
            raise DatabaseAlreadyExist("this name exists")

    def db_change(self, dbname: str):
        if dbname in self.databaselist:
            self.inUse = dbname
            return LookupOutput(change_db=dbname) 
        print("OH NO")
        raise DatabaseNotExist("this name doesn't exist")

    def db_delete(self, dbname: str):
        if dbname in self.databaselist:
            self.IM.close_handler(dbname)
            if self.metaHandlers.get(dbname) is not None:
                self.metaHandlers.pop(dbname).shut_down()
            path: Path = self.systemPath / dbname
            for table in path.iterdir():
                # print(table.name, path.name)
                if table.name.endswith(".table"):
                    self.RM.closeFile(str(table))
                table.unlink()
            self.databaselist.remove(dbname)
            path.rmdir()
            if self.inUse == dbname:
                self.inUse = None
                return LookupOutput(change_db='None')
        
        else:
            print("OH NO")
            raise DatabaseNotExist("this name doesn't exist")

    def db_showNames(self):
        results = []
        rootDir: Path = self.systemPath
        for dir in rootDir.iterdir():
            if dir.is_dir():
                results.append(dir.stem)
        print("SM: dISPLAY db names", rootDir.name, results)
        return results

    def tb_path(self, table: str):
        self.exmineIfActive()
        return str(self.systemPath / self.inUse / table) + ".table"

    def tb_showNames(self):
        result = []
        self.exmineIfActive()
        usingDB = self.systemPath / self.inUse
        for file in usingDB.iterdir():
            if file.name.endswith(".table"):
                result.append(file.stem)
        return result

    def tb_create(self, table: TableInfo):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        metaHandler.tb_insert(table)
        tablePath = self.tb_path(table.name)
        self.RM.createFile(tablePath, table.rowSize)
        return

    def tb_delete(self, table: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        for col in tableInfo.columnMap:
            self.remove_clo_check(table, col)
        metaHandler.tb_delete(table)
        tablePath = self.tb_path(table)
        self.RM.destroyFile(tablePath)
        return

    def tb_info(self, table: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        return metaHandler, metaHandler.tb_info(table)

    def tb_show(self, table: str):
        return LookupOutput(('Field', 'Type', 'Null', 'Key', 'Default', 'Extra')
                            , self.tb_info(table)[1].desc()
                            )

    def tb_rename(self, src: str, dst: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        metaHandler.tb_rename(src, dst)
        self.RM.renameFile(self.tb_path(src),self.tb_path(dst))
    
    
 
    def idx_create(self, index: str, table: str, col: str):
        metaHandler, tableInfo = self.tb_info(table)
        if index in metaHandler.databaseInfo.indexMap:
            print("OH NO")
            raise IndexAlreadyExist("this name exists")
        if col not in tableInfo.index:
            print(f"IM create_index {table, col}, {tableInfo.index}")
            indexFile = self.IM.create_index(self.inUse, table)
            tableInfo.index[col] = indexFile.root
        else:
            print(f"IM create_index(already) {table, col}")
            metaHandler.idx_create(index, table, col)
            return
        if tableInfo.index_get(col) is not None:
            colIndex = tableInfo.index_get(col)
            for record in FileScan(self.RM.openFile(self.tb_path(table))):
                recordData = tableInfo.record_load(record)
                indexFile.insert(recordData[colIndex], record.rid)
            metaHandler.idx_create(index, table, col)
        else:
            print("OH NO")
            raise ColumnNotExist(col + "doesn't exist")

    def idx_delete(self, index: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        table, col = metaHandler.databaseInfo.idx_get(index)
        metaHandler.tb_info(table).index.pop(col)
        metaHandler.idx_delete(index)
        self.metaHandlers.pop(self.inUse).shut_down()
        return

    def idx_search(self, table: str, limits: tuple):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        functions = self.cond_setup(table, limits, metaHandler)
        index_filter = self.idx_examine(table, limits)
        fileHandler: FileHandler = self.RM.openFile(self.tb_path(table))
        records = []
        data = []
        # print("IDX FIL", index_filter, index_filter is not None)
        if index_filter is not None: # is not None:
            iterator = map(fileHandler.getRecord, index_filter)
            for record in iterator:
                valTuple = tableInfo.record_load(record)
                if all(map(lambda fun: fun(valTuple), functions)):
                    records.append(record)
                    data.append(valTuple)
        else:
            for record in FileScan(fileHandler):
                valTuple = tableInfo.record_load(record)
                old_valTuple = valTuple
                if all(map(lambda fun: fun(valTuple), functions)):
                    records.append(record)
                    old_valTuple = valTuple
                    data.append(valTuple)
            # print("ELSE", records)
        return records, data

    def idx_insert_handle(self, table: str, data: tuple, rid: RID):
        self.exmineIfActive()
        metaHandler:MetaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        for col in tableInfo.index:
            old_root_id = tableInfo.index[col]
            if data[tableInfo.index_get(col)] is not None:
                index = self.IM.start_index(self.inUse, table, old_root_id)
                ret = index.insert(data[tableInfo.index_get(col)], rid)
            else:
                index = self.IM.start_index(self.inUse, table, old_root_id)
                ret = index.insert(NULL_VALUE, rid)
            if ret is not None:
                tableInfo.index[col] = ret
                metaHandler.shut_down()
                self.IM.update_index(table, old_root_id, ret)
                print(col, metaHandler.tb_info(table).index[col])
        return

    def idx_delete_handle(self, table: str, data: tuple, rid: RID):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        for col in tableInfo.index:
            if data[tableInfo.index_get(col)]:
                index = self.IM.start_index(self.inUse, table, tableInfo.index[col])
                index.delete(data[tableInfo.index_get(col)], rid)
            else:
                index = self.IM.start_index(self.inUse, table, tableInfo.index[col])
                index.delete(NULL_VALUE, rid)
        return

    def idx_examine(self, table: str, limits: tuple) -> set:
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        condIndex = {}

        def build(limit: Term):
            if limit.type != 1:
                return None
            if limit.table and limit.table != table:
                return None
            limit_col = limit.col
            colIndex = tableInfo.index_get(limit_col)
            if colIndex is not None and limit.value is not None and limit.col in tableInfo.index:
                lo, hi = condIndex.get(limit.col, (-1 << 31 + 1, 1 << 31))
                tmp = limit.value
                val = int(tmp)
                if limit.operator == "=":
                    lower = max(lo, val)
                    upper = min(hi, val)
                elif limit.operator == "<":
                    lower = lo
                    upper = min(hi, val - 1)
                elif limit.operator == ">":
                    lower = max(lo, val + 1)
                    upper = hi
                elif limit.operator == "<=":
                    lower = lo
                    upper = min(hi, val)
                elif limit.operator == ">=":
                    lower = max(lo, val)
                    upper = hi
                else:
                    return None
                condIndex[limit.col] = lower, upper


        results = None
        tuple(map(build, limits))

        # print("IndexFilter::condIndex", condIndex)
        
        import time; t1 = time.time()
        for col in condIndex:
            if results is not None:
                lo, hi = condIndex.get(col)
                index = self.IM.start_index(self.inUse, table, tableInfo.index[col])
                results = results & set(index.range(lo, hi))
            else:
                lo, hi = condIndex.get(col)
                index = self.IM.start_index(self.inUse, table, tableInfo.index[col])
                results = set(index.range(lo, hi))
        # t2 = time.time(); print("INDEX FILTER TIME ", len(condIndex), t2 - t1)
        return results

    def unique_add(self, table: str, col: str, uniq: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        metaHandler.unique_add(table, col, uniq)
        if uniq not in metaHandler.databaseInfo.indexMap:
            self.idx_create(uniq, table, col)
        return

    def foreign_add(self, table: str, col: str, foreign, forName=None):
        print("add Foreign", table, col, foreign)
        metaHandler, tableInfo = self.tb_info(table)
        # if (table, col) not in metaHandler.databaseInfo.indexMap.values():
        #     raise AddForeignError(f"create index on this column first {table, col} [{list(metaHandler.databaseInfo.indexMap.values())}]")
        if forName:
            if forName not in metaHandler.databaseInfo.indexMap:
                self.idx_create(forName, foreign[0], foreign[1])
        else:
            indexName = foreign[0] + "." + foreign[1]
            if indexName not in metaHandler.databaseInfo.indexMap:
                self.idx_create(indexName, foreign[0], foreign[1])
        tableInfo.foreign_add(col, foreign)
        metaHandler.shut_down()
        
    def foreign_delete(self, table, col, forName=None):
        metaHandler, tableInfo = self.tb_info(table)
        if tableInfo.foreign.get(col) is not None:
            foreign = tableInfo.foreign[col][0] + "." + tableInfo.foreign[col][1]
            reftable: TableInfo = metaHandler.tb_info(tableInfo.foreign[col][0])
            if reftable.primary.count(tableInfo.foreign[col][1]) != 0:
                self.idx_delete(foreign)
            tableInfo.foreign_delete(col)
            metaHandler.shut_down()
        return None

    def primary_set(self, table: str, pri):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        metaHandler.primary_set(table, pri)
        if pri:
            for column in pri:
                indexName = table + "." + column
                if indexName not in metaHandler.databaseInfo.indexMap:
                    # import pdb; pdb.set_trace()
                    self.idx_create(indexName, table, column)

    def primary_delete(self, table: str):
        metaHandler, tableInfo = self.tb_info(table)
        if tableInfo.primary:
            for column in tableInfo.primary:
                indexName = table + "." + column
                if indexName in metaHandler.databaseInfo.indexMap:
                    self.idx_delete(indexName)
            metaHandler.primary_delete(table)

    def col_insert(self, table: str, column, pri: bool, foreign: bool):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        if pri:
            for co in column:
                if tableInfo.index_get(co) is None:
                    print("OH NO")
                    raise ColumnNotExist(co + " doesn't exist")
            self.primary_set(table, column)
        elif foreign:
            co = column[0]
            if tableInfo.index_get(co) is None:
                print("OH NO")
                raise ColumnNotExist(co + " doesn't exist")
            self.foreign_add(table, co, (column[1], column[2]), None)
        else:
            if not isinstance(column, ColumnInfo):
                raise AddError("unsupported add")
            col = column
            if tableInfo.index_get(col.name):
                print("OH NO")
                raise ColumnNotExist(col.name + " doesn't exist")
            oldTableInfo: TableInfo = deepcopy(tableInfo)
            metaHandler.databaseInfo.col_insert(table, col)
            metaHandler.shut_down()
            copyTableFile = self.tb_path(table + ".copy")
            self.RM.createFile(copyTableFile, tableInfo.rowSize)
            newRecordHandle: FileHandler = self.RM.openFile(copyTableFile)
            scan = FileScan(self.RM.openFile(self.tb_path(table)))
            for record in scan:
                recordVals = oldTableInfo.record_load(record)
                valList = list(recordVals)
                valList.append(col.default)
                newRecordHandle.insertRecord(tableInfo.record_setup(valList))
            self.RM.closeFile(self.tb_path(table))
            self.RM.closeFile(copyTableFile)
            self.RM.replaceFile(copyTableFile, self.tb_path(table))
        return

    def col_delete(self, table: str, col: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        self.remove_clo_check(table, col)
        tableInfo = metaHandler.tb_info(table)
        if col not in tableInfo.columnIndex:
            print("OH NO")
            raise ColumnNotExist(col + " doesn't exist")
        oldTableInfo: TableInfo = deepcopy(tableInfo)
        colIndex = tableInfo.index_get(col)
        metaHandler.col_delete(table, col)
        copyTableFile = self.tb_path(table + ".copy")
        self.RM.createFile(copyTableFile, tableInfo.rowSize)
        newRecordHandle: FileHandler = self.RM.openFile(copyTableFile)
        scan = FileScan(self.RM.openFile(self.tb_path(table)))
        for record in scan:
            recordVals = oldTableInfo.record_load(record)
            valList = list(recordVals)
            valList.pop(colIndex)
            newRecordHandle.insertRecord(tableInfo.record_setup(valList))
        self.RM.closeFile(self.tb_path(table))
        self.RM.closeFile(copyTableFile)
        self.RM.replaceFile(copyTableFile, self.tb_path(table))
        return
    
    def record_insert(self, table: str, val: list):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)

        info = tableInfo.record_setup(val)
        tempRecord = Record(RID(0, 0), info)
        valTuple = tableInfo.record_load(tempRecord)

        # print("valTuple", valTuple)

        import time
        t1 = time.time()
        self.insert_check(table, valTuple)
        t2 = time.time()
        fileHandle: FileHandler = self.RM.openFile(self.tb_path(table))
        rid = fileHandle.insertRecord(info)
        t3 = time.time()
        self.idx_insert_handle(table, valTuple, rid)
        t4 = time.time()
        # print(f"insert{t2 - t1, t3 - t2, t4 - t3}")
        
    def record_delete(self, table: str, limits: tuple):
        self.exmineIfActive()
        fileHandler = self.RM.openFile(self.tb_path(table))
        metaHandler = self.meta_fetchHandler()
        records, data = self.idx_search(table, limits)
        for record, valTuple in zip(records, data):
            self.remove_con_check(table, valTuple)
            fileHandler.deleteRecord(record.rid)
            self.idx_delete_handle(table, valTuple, record.rid)
        res = LookupOutput('deleted_items', (len(records),))
        return res

    def record_update(self, table: str, limits: tuple, valmap: dict):
        self.exmineIfActive()
        fileHandler = self.RM.openFile(self.tb_path(table))
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        tableInfo.val_check(valmap)
        records, data = self.idx_search(table, limits)
        for record, oldVal in zip(records, data):
            new = list(oldVal)
            for col in valmap:
                new[tableInfo.index_get(col)] = valmap.get(col)
            self.remove_con_check(table, oldVal)
            rid = record.rid
            self.insert_check(table, new, rid)
            self.idx_delete_handle(table, oldVal, rid)
            record.record = tableInfo.record_setup(new)
            fileHandler.updateRecord(record)
            self.idx_insert_handle(table, tuple(new), rid)
        return LookupOutput('updated_items', (len(records),))

    def record_select(self, reducers: Tuple[Reducer], tables: Tuple[str, ...],
                      limits: Tuple[Term], groupBy: Tuple[str, str]):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()

        def setTableName(object, tableName, colName):
            if getattr(object, colName) is None:
                return
            elif getattr(object, tableName) is None:
                tabs = col2tab.get(getattr(object, colName))
                if not tabs:
                    raise ColumnNotExist(getattr(object, colName) + " unknown")
                elif len(tabs) > 1:
                    raise SameNameError(getattr(object, colName) + " exists in multiple tables")
                setattr(object, tableName, tabs[0])
            return

        def getSelected(col2data):
            col2data['*.*'] = next(iter(col2data.values()))
            return tuple(map(lambda x: x.select(col2data[x.target()]), reducers))

        col2tab = metaHandler.col2tb(tables)
        groupTable, groupCol = groupBy
        for element in limits + reducers:
            if not isinstance(element, Term):
                setTableName(element, '_table_name', '_col')
            else:
                setTableName(element, 'aim_table', 'aim_col')

        groupTableName = groupTable or tables[0]
        groupTable = groupTableName
        groupBy = groupTable + '.' + groupCol
        reducerTypes = []
        for reducer in reducers:
            reducerTypes.append(reducer.reducer_type)
        reducerTypes = set(reducerTypes)
        if not groupCol and 1 in reducerTypes:
            if len(reducerTypes) > 1:
                raise SelectError("no-group select contains both field and aggregations")

        if not reducers and not groupCol and len(tables) == 1 and reducers[0].reducer_type == 3:
            tableInfo = metaHandler.tb_info(tables[0])
            fileHandler = self.RM.openFile(self.tb_path(tables[0]))
            return LookupOutput((reducers[0].to_string(False),), (fileHandler.head['AllRecord']))
        tab2results = {}
        for table in tables:
            tab2results[table] = self.scan_idx_cond(table, limits)
        result = None
        if len(tables) == 1:
            result = tab2results[tables[0]]
        else:
            result = self.cond_join(tab2results, limits)

        if not groupCol:
            if reducers[0].reducer_type == 0:
                if len(reducers) == 1:
                    return result
                raise SelectError("reducer num not 1")
            elif 1 in reducerTypes:
                heads = []
                headindexes = []
                for reducer in reducers:
                    heads.append(reducer.target())
                headers = tuple(heads)
                for head in headers:
                    headindexes.append(result.header_id(head))
                indexes = tuple(headindexes)

                def takeCol(row):
                    return tuple(row[ele] for ele in indexes)

                data = tuple(map(takeCol, result.data))
            else:
                if result.data is not None:
                    head2data = {}
                    for head, data in zip(result.headers, zip(*result.data)):
                        head2data[head] = data
                    data = getSelected(head2data)
                else:
                    data = (None,) * len(result.headers)
        else:
            def getRow(group):
                head2data = {}
                for item_head, item_data in zip(result.headers, zip(*group)):
                    head2data[item_head] = item_data
                return getSelected(head2data)

            index = result.header_id(groupBy)
            groups = {}
            for row in result.data:
                if groups.get(row[index]) is None:
                    groups[row[index]] = [row]
                else:
                    groups[row[index]].append(row)
            if reducers[0].reducer_type == 0:
                return LookupOutput(result.headers, tuple(group[0] for group in groups.values()))
            data = tuple(map(getRow, groups.values()))

        headers = []
        for reducer in reducers:
            headers.append(reducer.to_string(len(tables) > 1))
        return LookupOutput(tuple(headers), data)

    def recordLimit_select(self, reducers, tables, limits, groupBy, limit: int, off: int):
        result = self.record_select(reducers, tables, limits, groupBy)
        if limit is None:
            data = result.data[off:]
        else:
            right = off + limit
            data = result.data[off: right]
        res = LookupOutput(result.headers, data)
        return res

    def unique_check(self, table: str, pairs, thisRID: RID = None):
        conds = []
        for col in pairs:
            conds.append(Term(1, table, col, '=', value=pairs.get(col)))
        records, data = self.idx_search(table, tuple(conds))
        if len(records) <= 1:
            if records and records[0].rid == thisRID:
                return False
            elif records:
                return (tuple(pairs.keys()), tuple(pairs.values()))
            return False
        print("OH NO")
        raise CheckAnyUniqueError("get " + str(len(records)) + " same")

    def check_uniques(self, table: str, colVals, thisRID: RID = None):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        if tableInfo.unique:
            for col in tableInfo.unique:
                pairs = {col: colVals[tableInfo.index_get(col)]}
                if self.unique_check(table, pairs, thisRID):
                    return self.unique_check(table, pairs, thisRID)
        return False

    def primary_check(self, table: str, colVals, thisRID: RID = None):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        if tableInfo.primary:
            pairs = {}
            for col in tableInfo.primary:
                pairs[col] = colVals[tableInfo.index_get(col)]
            return self.unique_check(table, pairs, thisRID)
        return False

    def foreign_check(self, table: str, colVals):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        # print("CheckForeign", table, tableInfo.foreign)
        if len(tableInfo.foreign) > 0:
            for col in tableInfo.foreign:
                conds = []
                fortable = tableInfo.foreign[col][0]
                forcol = tableInfo.foreign[col][1]
                conds.append(Term(1, fortable, forcol, '=', value=colVals[tableInfo.index_get(col)]))
                import time; t1 = time.time()
                records, data = self.idx_search(fortable, tuple(conds))
                # t2 = time.time(); print(f"{t2 - t1}, {col, fortable}")
                if len(records) == 0:
                    return tableInfo.name, colVals[tableInfo.index_get(col)]
                # colVal = colVals[tableInfo.index_get(col)]
                # foreignTableInfo: TableInfo = metaHandler.tb_info(tableInfo.foreign[col][0])
                # index = self.IM.start_index(self.inUse, tableInfo.foreign[col][0],
                #                             foreignTableInfo.index[tableInfo.foreign[col][1]])
                # if len(set(index.range(colVal, colVal))) == 0:
                #     return col, colVal
        return False

    def insert_check(self, table: str, colVals, thisRID: RID = None):
        import time
        t1 = time.time()

        if self.foreign_check(table, colVals):
            miss = self.foreign_check(table, colVals)
            print("OH NO")
            raise MissForeignKeyError("miss: " + str(miss[0]) + ": " + str(miss[1]))
        t2 = time.time()
        if self.primary_check(table, colVals, thisRID):
            dup = self.primary_check(table, colVals, thisRID)
            print("OH NO")
            raise DuplicatedPrimaryKeyError("duplicated: " + str(dup[0]) + ": " + str(dup[1]))
        t3 = time.time()
        if self.check_uniques(table, colVals, thisRID):
            dup = self.check_uniques(table, colVals, thisRID)
            print("OH NO")
            raise DuplicatedUniqueKeyError("duplicated: " + str(dup[0]) + ": " + str(dup[1]))
        t4 = time.time()
        # print(f"insert{t2 - t1, t3 - t2, t4 - t3}")
        return

    def remove_clo_check(self, table: str, col: str):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        for tableInfo in metaHandler.databaseInfo.tableMap.values():
            if tableInfo.name != table and len(tableInfo.foreign) > 0:
                for fromcol, (tab, column) in tableInfo.foreign.items():
                    if tab == table and col == column:
                        raise RemoveError("referenced foreignkey column")
        return False

    def remove_con_check(self, table: str, colVals):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        thistable = metaHandler.tb_info(table)
        for tableInfo in metaHandler.databaseInfo.tableMap.values():
            if len(tableInfo.foreign) > 0:
                for fromcol, (tab, col) in tableInfo.foreign.items():
                    if tab == table:
                        colval = colVals[thistable.index_get(col)]
                        index = self.IM.start_index(self.inUse, tableInfo.name, tableInfo.index[fromcol])
                        if len(set(index.range(colval, colval))) != 0:
                            raise RemoveError("referenced foreignkey value")
        return False

    def cond_setup(self, table: str, limits, metahandler):
        tableInfo = metahandler.tb_info(table)

        def build(limit: Term):
            if limit.table is not None and limit.table != table:
                return None
            limit_col = limit.col
            colIndex = tableInfo.index_get(limit_col)
            if colIndex is not None:
                colType = tableInfo.columnType[colIndex]
                if limit.type == 1:
                    if limit.aim_col:
                        if limit.aim_table == table:
                            return self.versus(colIndex, limit.operator, tableInfo.index_get(limit.aim_col))
                        return None
                    else:
                        if colType == "DATE":
                            if type(limit.value) not in (str, date):
                                raise ValueTypeError("need str/date here")
                            val = limit.value
                            if type(val) is date:
                                return self.versusV(colIndex, limit.operator, val)
                            valist = val.replace("/", "-").split("-")
                            return self.versusV(colIndex, limit.operator, date(*map(int, valist)))
                        elif colType in ("INT", "FLOAT"):
                            if isinstance(limit.value, (int, float)):
                                return self.versusV(colIndex, limit.operator, limit.value)
                            raise ValueTypeError("need int/float here")
                        elif colType == "VARCHAR":
                            if isinstance(limit.value, str):
                                return self.versusV(colIndex, limit.operator, limit.value)
                            raise ValueTypeError("need varchar here")
                        raise ValueTypeError("limit value error")
                elif limit.type == 2:
                    if colType == "DATE":
                        values = []
                        for val in limit.value:
                            if type(val) is str:
                                valist = val.replace("/", "-").split("-")
                                values.append(date(*map(int, valist)))
                            elif type(val) is date:
                                values.append(val)
                            raise ValueTypeError("need str/date here")
                        return lambda x: x[colIndex] in tuple(values)
                    return lambda x: x[colIndex] in limit.value
                elif limit.type == 3:
                    if colType == "VARCHAR":
                        return lambda x: self.pattern_setup(limit.value).match(str(x[colIndex]))
                    raise ValueTypeError("like need varchar here")
                elif limit.type == 0:
                    if isinstance(limit.value, bool):
                        if limit.value:
                            return lambda x: x[colIndex] is None
                        return lambda x: x[colIndex] is not None
                    raise ValueTypeError("limit value need bool here")
                raise ValueTypeError("limit type unknown")
            raise ColumnNotExist("limit column name unknown")

        results = []
        for limit in limits:
            func = build(limit)
            if func is not None:
                results.append(func)
        return results

    def meta_fetchHandler(self):
        if self.metaHandlers.get(self.inUse) is None:
            self.metaHandlers[self.inUse] = MetaHandler(self.inUse, str(self.systemPath))
        return self.metaHandlers[self.inUse]

    def scan_idx_cond(self, table: str, limits: tuple):
        self.exmineIfActive()
        metaHandler = self.meta_fetchHandler()
        tableInfo = metaHandler.tb_info(table)
        records, data = self.idx_search(table, limits)
        headers = tuple(tableInfo.name + "." + colName for colName in tableInfo.columnMap.keys())
        return LookupOutput(headers, data)
