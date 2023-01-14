
from .system_manager import SystemManger
from pathlib import Path
from Exceptions.exception import MyException
from OutputSystem.basicClass.outputUnit import LookupOutput
import time



class Executor:

    def __init__(self) -> None:
        self.time = time.time()
    
    def update_time(self)-> float:
        new_time = time.time()
        delta = new_time - self.time
        self.time = time.time()
        return delta

    def exec_csv(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        def load(iterator):
            m, tableInfo = manager.collectTableinfo(tbname)
            def parse(valtypePair):
                val, type = valtypePair
                if type == "INT":
                    return int(val) if val else None
                elif type == "FLOAT":
                    return float(val) if val else None
                elif type == "VARCHAR":
                    return val.rstrip()
                elif type == "DATE":
                    return val if val else None
            inserted = 0
            
            for id, row in enumerate(iterator):
                if id % 10000 == 0:
                    print(f"Excutor::load [{self.update_time()}] {id}, [{row}]")
                if row[-1] == '':
                    row = row[:-1]
                row = row.split(',')
                result = tuple(map(parse, zip(row, tableInfo.columnType)))
                # try:
                # print("Result", result)
                manager.insertRecord(tbname, list(result))
                inserted += 1
            return inserted

        if not tbname:
            tbname = path.stem
        manager.useDatabase(dbname)
        inserted = load(open(path, encoding='utf-8'))
        timeCost = manager.visitor.get_time_delta()
        return [LookupOutput('inserted_items', (inserted,), cost=timeCost)]

    def exec_sql(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        if not dbname:
            return manager.execute(open(path, encoding='utf-8').read())
        manager.useDatabase(dbname)
        return manager.execute(open(path, encoding='utf-8').read())

    def execute(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        manager.visitor.get_time_delta()
        if getattr(self, 'exec_' + path.suffix.lstrip('.')):
            try:
                func = getattr(self, 'exec_' + path.suffix.lstrip('.'))
                return func(manager, path, dbname, tbname)
            except MyException as e:
                timeCost = manager.visitor.get_time_delta()
                return [LookupOutput(message=str(e), cost=timeCost)]
        timeCost = manager.visitor.get_time_delta()
        return [LookupOutput(message="Unsupported format " + path.suffix.lstrip('.'), cost=timeCost)]