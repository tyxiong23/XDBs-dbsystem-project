# base class
class XdbException(Exception):
    def __str__(self) -> str:
        super_str = super().__str__()
        return "[XdbError::"  + str(self.__class__.__name__) + "] " + super_str

# file error
class XdbFileException(XdbException):
    pass

class CreateFileError(XdbFileException):
    pass

class OpenFileError(XdbFileException):
    pass

class ReadPageError(XdbFileException):
    pass


# Operation Error
class JoinError(XdbException):
    pass

class ReducerError(XdbException):
    pass


# metaSystemError
class MetaSystemException(XdbException):
    pass

class MetaHandlerError(MetaSystemException):
    pass

class TableError(MetaSystemException):
    pass

class DatabaseError(MetaSystemException):
    pass

# systemManagerError
class SystemManagerException(XdbException):
    pass

class ColumnNotExist(SystemManagerException):
    pass

class IndexAlreadyExist(SystemManagerException):
    pass

class NoDatabaseSelected(SystemManagerException):
    pass

class DatabaseNotExist(SystemManagerException):
    pass

class DatabaseAlreadyExist(SystemManagerException):
    pass


class ValueTypeMisMatched(SystemManagerException):
    pass


class DuplicatedPrimaryKey(SystemManagerException):
    pass

class DuplicatedUniqueKey(SystemManagerException):
    pass

class MissForeignKey(SystemManagerException):
    pass

class CheckAnyUniqueError(SystemManagerException):
    pass

class AddError(SystemManagerException):
    pass

class SameNameError(SystemManagerException):
    pass

class SelectError(SystemManagerException):
    pass

class RemoveError(SystemManagerException):
    pass



if __name__ == "__main__":
    a = AddError()
    print(a)
    assert 0 == 1, CreateFileError("Fail to create file [{name}]!!")