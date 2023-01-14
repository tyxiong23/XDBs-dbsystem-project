class ColumnInfo: 
    def __init__(self, type: str, name: str, size: int, null_permit: bool = True, default =None):
        self.type = type
        self.name = name
        self.size = size
        self.null_permit = null_permit
        self.default = default

    def getSize(self):
        if self.type != "VARCHAR":
            return 8
        else:
            return self.size + 1

    def getDESC(self):
        """name, type, null, keytype, default, extra"""
        return [self.name, self.type, "OK" if self.null_permit else "NO", "", self.default, ""]