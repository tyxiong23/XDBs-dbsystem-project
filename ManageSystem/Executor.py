from OutputSystem.basicClass.outputUnit import LookupOutput
from Exceptions.exception import MyException
from .system_manager import SystemManger

from pathlib import Path

class Executor:

    def exec_csv(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        def load(iterator):
            _, tableInfo = manager.tb_info(tbname)
            
            def parse(valtypePair):
                val, type = valtypePair
                if val is None:
                    return None
                if type == "INT":
                    return int(val)
                elif type == "FLOAT":
                    return float(val)
                elif type == "DATE":
                    return val
                elif type == "VARCHAR":
                    return val.rstrip()

            insertNum = 0
            for num, data in enumerate(iterator):
                if num % 20000 == 0:
                    print(f"Excutor::load {num}, [{data}]")
                if data[-1] == '':
                    data = data[:-1]
                data = data.split(',')
                manager.record_insert(tbname, list(tuple(map(parse, zip(data, tableInfo.columnType)))))
                insertNum += 1
            return insertNum

        if not tbname:
            tbname = path.stem
        manager.db_change(dbname)
        return [LookupOutput('inserted_items', (load(open(path, encoding='utf-8')),), cost = manager.visitor.get_time_delta())]

    def exec_sql(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        if dbname:
            manager.db_change(dbname)
        return manager.execute(open(path, encoding='utf-8').read())
        
    def execute(self, manager: SystemManger, path: Path, dbname: str, tbname: str):
        manager.visitor.get_time_delta()
        func = getattr(self, 'exec_' + path.suffix.lstrip('.'))
        if func:
            try:
                return func(manager, path, dbname, tbname)
            except MyException as e:
                timeCost = manager.visitor.get_time_delta()
                return [LookupOutput(message=str(e), cost=timeCost)]
        timeCost = manager.visitor.get_time_delta()
        return [LookupOutput(message="Unsupported format " + path.suffix.lstrip('.'), cost=timeCost)]

