from pathlib import Path
from argparse import ArgumentParser, Namespace
import os, stat, sys
from OutputSystem.TablePrinter import TablePrinter
from OutputSystem.CSVPrinter import CSVPrinter

from FileSystem.bufPageManager import BufPageManager
from RecordSystem.RecordManager import RecordManager
from IndexSystem.index_manager import IndexManager

from ManageSystem.system_manager import SystemManger

from ManageSystem.system_visitor import SystemVistor
from ManageSystem.Executor import Executor
from utils.options import getArgs



if __name__ == '__main__':
    args = getArgs()
    if not os.path.exists(args.base_dir):
        os.makedirs(args.base_dir)

    printer = TablePrinter() if args.printer == 'table' else CSVPrinter()   
    visitor = SystemVistor()
    syspath = Path(args.base_dir)
    bufPageManager = BufPageManager()
    recordManager = RecordManager(bufPageManager)
    indexManager = IndexManager(bufPageManager, syspath)
    systemManager = SystemManger(visitor, syspath, bufPageManager, recordManager, indexManager)

    # Use the given database
    if args.database:
        change_db = [systemManager.useDatabase(args.database)]
        printer.print(change_db)

    # operation from sql or csv file
    if args.file:
        file_suffix = args.file.suffix
        assert file_suffix in [".csv", ".sql"], f"Invalid File Format {file_suffix}"
        executor = Executor()
        result = executor.execute(systemManager, args.file, args.database, args.table)
        printer.print(result)
        systemManager.shut_down()

    # interaction via command line
    else:
        sql = ''
        while True:
            if not stat.S_ISREG(os.fstat(0).st_mode):
                prefix = f'Xbase({printer.inUse})'
                print_info = prefix if not sql else '-'.rjust(len(prefix))
                print(print_info + '> ', end='')
            try:
                sql += ' ' + input()
            except (KeyboardInterrupt, EOFError):
                systemManager.shut_down()
                break
            if sql.strip().lower() in ['exit', 'quit', '.exit', '.quit', "EXIT", "QUIT"]:
                systemManager.shut_down()
                break

            if sql.endswith(';'):
                result = systemManager.execute(sql)
                printer.print(result)
                sql = ""
