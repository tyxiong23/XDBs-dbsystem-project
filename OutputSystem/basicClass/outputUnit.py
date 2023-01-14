class LookupOutput:
    def __init__(self, headers=None, data=None, message=None, change_db=None, cost=None):
        if headers:
            if not isinstance(headers, (list, tuple)):
                headers = (headers,)
            self._header_index = {h: i for i, h in enumerate(headers)}
        else:
            self._header_index = {}
            
        if data:
            if not isinstance(data[0], (list, tuple)):
                data = tuple((each,) for each in data)
                
        self._headers = headers
        self._data = data
        self._alias_map = {}
        self._cost = cost
        self._database = change_db
        self._message = message

    @property
    def data(self):
        return self._data

    @property
    def headers(self):
        return self._headers
    
    @property
    def alias_map(self):
        return self._alias_map

    @property
    def cost(self):
        return self._cost

    @cost.setter
    def cost(self, value):
        self._cost = value
        
    def size(self):
        size: int = len(self._data)
        return size


    def simplify(self):
        if self._headers:
            header = self._headers[0]
            if header.find('.') >= 0:
                prefix = header[:header.find('.') + 1]
                for header in self._headers:
                    if len(header) <= len(prefix):
                        break
                    if not header.startswith(prefix):
                        break
                else:
                    self._headers = tuple(header[len(prefix):] for header in self._headers)


    def header_id(self, header) -> int:
        if header in self._alias_map:
            header = self._alias_map[header]
        if header in self._header_index:
            result = self._header_index[header]
            return result


    def insert_alias(self, alias, header):
        self._alias_map[alias] = header
        return None


   
