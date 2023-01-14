from prettytable import PrettyTable
from sys import stderr
from typing import List
from .basicClass.outputUnit import LookupOutput
from datetime import timedelta

class TablePrinter:
    def __init__(self):
        self.inUse = None

    class MyPT(PrettyTable):
        def _format_value(self, field, value):
            if value is not None:
                return super()._format_value(field, value)
            return 'NULL'

    def print(self, results: List[LookupOutput]):
        for output in results:
            if output:
                
                if output._database:
                    self.inUse = output._database
                    print('Database changed to', self.inUse)
                    print()
                    
                if output.headers:
                    table = self.MyPT()
                    table.field_names = output.headers
                    table.add_rows(output.data)
                    
                    if len(output.data):
                        print(table.get_string())
                        print(f'{len(output.data)}' + ' results in ' + f'{(timedelta(output.cost).total_seconds() / 10 ** 5):.3f}s')
                    else:
                        print("Empty set in " + f'{(timedelta(output.cost).total_seconds() / 10 ** 5):.3f}' + "s")
                        
                    print()
                if output._message:
                    print(output._message, file=stderr)
                    
            else:
                return
    
