class XdbException(Exception):
    def __str__(self) -> str:
        super_str = super().__str__()
        return "[XdbError::"  + str(self.__class__.__name__) + "] " + super_str

class FailCreateError(XdbException):
    pass

class FailOpenError(XdbException):
    pass

class FailReadPageError(XdbException):
    pass

class RecordTooLong(XdbException):
    pass

class ColumnNotExist(XdbException):
    pass

class ValueNumError(XdbException):
    pass

class VarcharTooLong(XdbException):
    pass

class ValueTypeError(XdbException):
    pass

class TableAlreadyExist(XdbException):
    pass

class TableNotExist(XdbException):
    pass

class IndexAlreadyExist(XdbException):
    pass

class IndexNotExist(XdbException):
    pass

class DatabaseAlreadyExist(XdbException):
    pass

class DatabaseNotExist(XdbException):
    pass

class NoDatabaseInUse(XdbException):
    pass

class CheckAnyUniqueError(XdbException):
    pass

class DuplicatedPrimaryKeyError(XdbException):
    pass

class DuplicatedUniqueKeyError(XdbException):
    pass

class MissForeignKeyError(XdbException):
    pass

class SameNameError(XdbException):
    pass

class SelectError(XdbException):
    pass

class DateValueError(XdbException):
    pass

class JoinError(XdbException):
    pass

class AddForeignError(XdbException):
    pass

class RemoveError(XdbException):
    pass

class AddError(XdbException):
    pass

if __name__ == "__main__":
    a = AddError()
    print(a)