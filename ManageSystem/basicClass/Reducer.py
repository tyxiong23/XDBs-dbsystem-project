# TODO:modified
class Reducer:
    """reducer_type:0 is all
                    1 is field
                    2 is aggregation
                    3 is counter
    """

    def __init__(self, reducer_type, table_name = None, col = None, aggregator = None):
        self._reducer_type: int = reducer_type
        self._table_name: str = table_name
        self._col: str = col
        self._aggregator: str = aggregator

    @property
    def reducer_type(self):
        return self._reducer_type
    
    def select(self, data: tuple):

        func = {
            'COUNT': lambda x: len(set(x)),
            'MAX': max,
            'MIN': min,
            'SUM': sum,
            'AVG': lambda x: sum(x) / len(x)
        }
        
        if self._reducer_type == 1:
            return data[0]
        
        elif self._reducer_type == 2:
            try:
                return func[self._aggregator](tuple(filter(lambda x: x is not None, data)))
            except TypeError:
                print("WRONG----incorrect value type for aggregation")
                return
        
        elif self._reducer_type == 3:
            return len(data)
    
    def target(self):
        return f'{self._table_name}.{self._col}'
    
    def to_string(self, prefix=True):
        base = f'{self._table_name}.{self._col}'
        
        if self._reducer_type == 1:
            return base if prefix else self._col
        
        elif self._reducer_type == 2:
            return f'{self._aggregator}({base})' if prefix else f'{self._aggregator}({self._col})'
        
        elif self._reducer_type == 3:
            return f'COUNT(*)'

    def __str__(self) -> str:
        return f"{self.reducer_type, self._table_name, self._col, self._aggregator}"


        
        

    