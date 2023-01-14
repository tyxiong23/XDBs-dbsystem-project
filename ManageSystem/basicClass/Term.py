# TODO:modify

class Term:
    """term_type:   0 is null
                    1 is compare
                    2 is in
                    3 is like
    """

    def __init__(self, term_type, table_name, col, operator = None, aim_table_name = None, aim_col = None, value = None):
        
        self._type: int = term_type
        self._table: str = table_name
        self._col: str = col
        self._operator: str = operator
        self._aim_table: str = aim_table_name
        self._aim_col: str = aim_col
        self._value = value

    
    @property
    def type(self):
        return self._type
    
    @property
    def table(self):
        return self._table
    
    @property
    def col(self):
        return self._col
    
    @property
    def operator(self):
        return self._operator
    
    @property
    def aim_table(self):
        return self._aim_table

    @property
    def aim_col(self):
        return self._aim_col

    @property
    def value(self):
        return self._value

    