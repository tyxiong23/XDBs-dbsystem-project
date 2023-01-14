from .basicClass.outputUnit import LookupOutput
from sys import stderr, stdout
from typing import List
import csv

class CSVPrinter:
    def print(self, results: List[LookupOutput]):
        for output in results:
            if output:
                
                if output._database:
                    self.inUse = output._database
                
                if output.headers:
                    csv.writer(stdout).writerow(output.headers)
                    csv.writer(stdout).writerows(output.data)
                    
                if output._message:
                    print(output._message, file = stderr)
            else:
                return