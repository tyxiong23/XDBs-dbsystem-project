from .basicClass.Reducer import Reducer
from .basicClass.Term import Term
from .basicClass.Join import Join

from MetaSystem.basicClass.Table import TableInfo
from MetaSystem.basicClass.Column import ColumnInfo
from OutputSystem.basicClass.outputUnit import LookupOutput

from antlr_SQLparser.SQLVisitor import SQLVisitor
from antlr_SQLparser.SQLParser import SQLParser
from antlr4 import ParserRuleContext

from .system_manager import SystemManger

import time

class SystemVistor(SQLVisitor):
    def __init__(self, systemManager=None) -> None:
        super(SQLVisitor, self).__init__()
        self.systemManager: SystemManger = systemManager
        self.time_start = None

    def setManager(self, sm):
        # print("setManager", sm)
        assert isinstance(sm, SystemManger)
        self.systemManager = sm

    def get_int(self, context):
        return int(self.get_str(context))
    
    def get_float(self, context):
        return float(self.get_str(context))

    def get_str(self, context):
        if isinstance(context, ParserRuleContext):
            context = context.getText()
        res = str(context)
        return res

    def get_time_delta(self):
        if self.time_start is not None:
            t1 = time.time()
            t2 = t1 - self.time_start
            self.time_start = t1
            return t2
        else:
            self.time_start = time.time()
            return None
            
    def aggregateResult(self, aggregate, next_result):
        return aggregate if next_result is None else next_result

        # Visit a parse tree produced by SQLParser#program.
    def visitProgram(self, ctx:SQLParser.ProgramContext):
        ## TODO
        # print("SV::visitProgram")
        results = []
        for term in ctx.statement():
            out: LookupOutput = term.accept(self)
            if out is None:
                continue
            out._cost = self.get_time_delta()
            out.simplify()
            results.append(out)
        return results
    
    # Visit a parse tree produced by SQLParser#statement.
    def visitStatement(self, ctx:SQLParser.StatementContext):
        ## TODO
        # print("SV::visitStatement", ctx, type(ctx), isinstance(ctx, SQLParser.Show_dbsContext))
        return self.visitChildren(ctx)

    # Visit a parse tree produced by SQLParser#create_db.
    def visitCreate_db(self, ctx:SQLParser.Create_dbContext):
        return self.systemManager.db_create(self.get_str(ctx.Identifier()))

    # Visit a parse tree produced by SQLParser#drop_db.
    def visitDrop_db(self, ctx:SQLParser.Drop_dbContext):
        return self.systemManager.db_delete(self.get_str(ctx.Identifier()))

    # Visit a parse tree produced by SQLParser#show_dbs.
    def visitShow_dbs(self, ctx:SQLParser.Show_dbsContext):
        print("visitSHow_dbs")
        return LookupOutput("databases", self.systemManager.db_showNames())

    # Visit a parse tree produced by SQLParser#use_db.
    def visitUse_db(self, ctx:SQLParser.Use_dbContext):
        print("visitUSE_dbs")
        return self.systemManager.db_change(self.get_str(ctx.Identifier()))

    # Visit a parse tree produced by SQLParser#show_tables.
    def visitShow_tables(self, ctx:SQLParser.Show_tablesContext):
        return LookupOutput("tables", self.systemManager.tb_showNames())

    # Visit a parse tree produced by SQLParser#show_indexes.
    def visitShow_indexes(self, ctx:SQLParser.Show_indexesContext):
        ## TODO
        return LookupOutput("indexes", [])

    # Visit a parse tree produced by SQLParser#load_data.
    def visitLoad_data(self, ctx:SQLParser.Load_dataContext):
        # TODO
        return self.visitChildren(ctx)

    # Visit a parse tree produced by SQLParser#dump_data.
    def visitDump_data(self, ctx:SQLParser.Dump_dataContext):
        # TODO
        return self.visitChildren(ctx)

    # Visit a parse tree produced by SQLParser#create_table.
    def visitCreate_table(self, ctx:SQLParser.Create_tableContext):
        manager = self.systemManager
        columns, primary, foreignKeys = ctx.field_list().accept(self)
        tableName = self.get_str(ctx.Identifier())
        print("visitCreateTable", tableName, foreignKeys, primary)
        tableInfo = TableInfo(tableName, columns)
        res = manager.tb_create(tableInfo)
        manager.primary_set(tableName, primary)
        for i in foreignKeys:
            manager.foreign_add(tableName, i, foreignKeys[i])
        return res

    # Visit a parse tree produced by SQLParser#drop_table.
    def visitDrop_table(self, ctx:SQLParser.Drop_tableContext):
        tableName = self.get_str(ctx.Identifier())
        return self.systemManager.tb_delete(tableName)

    # Visit a parse tree produced by SQLParser#describe_table.
    def visitDescribe_table(self, ctx:SQLParser.Describe_tableContext):
        return self.systemManager.tb_show(self.get_str(ctx.Identifier()))

    # Visit a parse tree produced by SQLParser#insert_into_table.
    def visitInsert_into_table(self, ctx:SQLParser.Insert_into_tableContext):
        manager = self.systemManager
        results = ctx.value_lists().accept(self)
        for result in results:
            manager.record_insert(self.get_str(ctx.getChild(2)), result)
        return LookupOutput('inserted_items', (len(results),))

    # Visit a parse tree produced by SQLParser#delete_from_table.
    def visitDelete_from_table(self, ctx:SQLParser.Delete_from_tableContext):
        return self.systemManager.record_delete(self.get_str(ctx.Identifier()), ctx.where_and_clause().accept(self))

    # Visit a parse tree produced by SQLParser#update_table.
    def visitUpdate_table(self, ctx:SQLParser.Update_tableContext):
        return self.systemManager.record_update(self.get_str(ctx.Identifier()), 
                                                limits=ctx.where_and_clause().accept(self), 
                                                valmap=ctx.set_clause().accept(self))

    # Visit a parse tree produced by SQLParser#select_table.
    def visitSelect_table(self, ctx:SQLParser.Select_tableContext):
        term = ()
        if ctx.where_and_clause() is not None:
            term = ctx.where_and_clause().accept(self)
        if ctx.column():
            group_by = ctx.column().accept(self)
        else:
            group_by = (None, '')
        limit = self.get_int(ctx.Integer(0)) if ctx.Integer() else None
        offset = self.get_int(ctx.Integer(1)) if ctx.Integer(1) else 0
        # print("Reducers", ctx.selectors().accept(self), "Limits", term)
        return self.systemManager.recordLimit_select(ctx.selectors().accept(self), ctx.identifiers().accept(self),
                                                      term, group_by,
                                                      limit, offset)

    # Visit a parse tree produced by SQLParser#alter_table_add.
    def visitAlter_table_add(self, ctx:SQLParser.Alter_table_addContext):
        self.systemManager.col_insert(self.get_str(ctx.Identifier()), ctx.field().accept(self), 
                          isinstance(ctx.field(), SQLParser.Primary_key_fieldContext), 
                          ctx.field().getChild(0).getText() == 'FOREIGN'
                          )

    # Visit a parse tree produced by SQLParser#alter_table_drop.
    def visitAlter_table_drop(self, ctx:SQLParser.Alter_table_dropContext):
        self.systemManager.col_delete(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)))

    # Visit a parse tree produced by SQLParser#alter_table_change.
    def visitAlter_table_change(self, ctx:SQLParser.Alter_table_changeContext):
        self.systemManager.tb_rename(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)))

    # Visit a parse tree produced by SQLParser#alter_add_index.
    def visitAlter_add_index(self, ctx:SQLParser.Alter_add_indexContext):
        indices = ctx.identifiers().accept(self)
        for i in indices:
            self.systemManager.idx_create(self.to_str(ctx.Identifier(1)), table=self.get_str(ctx.Identifier(0)), col=i)

    # ALTER TODO
    # Visit a parse tree produced by SQLParser#alter_drop_index.
    def visitAlter_drop_index(self, ctx:SQLParser.Alter_drop_indexContext):
        return self.systemManager.idx_delete(self.get_str(ctx.Identifier(1)))
        # return self.visitChildren(ctx)

    # Visit a parse tree produced by SQLParser#alter_table_drop_pk.
    def visitAlter_table_drop_pk(self, ctx:SQLParser.Alter_table_drop_pkContext):
        # TODO
        self.systemManager.primary_delete(self.get_str(ctx.Identifier(0)))

    # Visit a parse tree produced by SQLParser#alter_table_drop_foreign_key.
    def visitAlter_table_drop_foreign_key(self, ctx:SQLParser.Alter_table_drop_foreign_keyContext):
        # TODO
        self.systemManager.foreign_delete(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)), None)

    # Visit a parse tree produced by SQLParser#alter_table_add_pk.
    def visitAlter_table_add_pk(self, ctx:SQLParser.Alter_table_add_pkContext):
        # TODO
        self.systemManager.primary_set(self.get_str(ctx.Identifier(0)), ctx.identifiers().accept(self))

    # Visit a parse tree produced by SQLParser#alter_table_add_foreign_key.
    def visitAlter_table_add_foreign_key(self, ctx:SQLParser.Alter_table_add_foreign_keyContext):
        for (t1, t2) in zip(ctx.identifiers(0).accept(self), ctx.identifiers(1).accept(self)):
            self.systemManager.foreign_add(self.get_str(ctx.Identifier(0)), t1,
                                           (self.get_str(ctx.Identifier(2)), t2), self.get_str(ctx.Identifier(1)))

    # Visit a parse tree produced by SQLParser#alter_table_add_unique.
    def visitAlter_table_add_unique(self, ctx:SQLParser.Alter_table_add_uniqueContext):
        # TODO
        table, name, column = tuple(map(self.to_str, ctx.Identifier()))
        return self.systemManager.unique_add(table, column, name)

    # Visit a parse tree produced by SQLParser#field_list.
    def visitField_list(self, ctx:SQLParser.Field_listContext):
        primaryKey = None
        foreginKeys = {}
        name2Clo = {}

        for field in ctx.field():
            if isinstance(field, SQLParser.Normal_fieldContext):
                if (self.get_str(field.Identifier()) in name2Clo):
                    raise NameError(f'Error!!! Duplicate ColName {self.get_str(field.Identifier())}')
                name2Clo[self.get_str(field.Identifier())] = field.accept(self)
                
            elif isinstance(field, SQLParser.Primary_key_fieldContext):
                names = field.accept(self)
                if primaryKey:
                    raise NameError('Error!!!Only one primary key supported')
                for item in names:
                    if item not in name2Clo:
                        raise NameError(f'Unknown field {item} field list')
                primaryKey = names
            else:
                # print("visitForeignKeyField", field.accept(self))
                assert isinstance(field, SQLParser.Foreign_key_fieldContext), f"SystemVisitor::visitField_list unknown field type {type(field)}!!!!"
                field_name, table_name, refer_name = field.accept(self)
                if field_name in foreginKeys:
                    raise NameError(f'Foreign key named {field_name} is duplicated')
                foreginKeys[field_name] = table_name, refer_name

        return list(name2Clo.values()), primaryKey, foreginKeys

    # Visit a parse tree produced by SQLParser#normal_field.
    def visitNormal_field(self, ctx:SQLParser.Normal_fieldContext):
        typeName, size = ctx.type_().accept(self)
        permission = False if ctx.Null() else True
        default_value = None
        if ctx.value():
            for idx in range(ctx.getChildCount() - 1):
                if ctx.getChild(idx).getText() == "DEFAULT" :
                    default_value = ctx.getChild(idx + 1).accept(self)
        ret = ColumnInfo(typeName, self.get_str(ctx.Identifier()), size, permission, default_value)
        return ret

    # Visit a parse tree produced by SQLParser#primary_key_field.
    def visitPrimary_key_field(self, ctx:SQLParser.Primary_key_fieldContext):
        return ctx.identifiers().accept(self)

    # Visit a parse tree produced by SQLParser#foreign_key_field.
    def visitForeign_key_field(self, ctx:SQLParser.Foreign_key_fieldContext):
        return tuple(self.get_str(i) for i in ctx.Identifier())


    # Visit a parse tree produced by SQLParser#type_.
    def visitType_(self, ctx:SQLParser.Type_Context):
        if ctx.Integer():
            size = self.get_int(ctx.Integer())
        else:
            size = 8
        return self.get_str(ctx.getChild(0)), size

    # Visit a parse tree produced by SQLParser#value_lists.
    def visitValue_lists(self, ctx:SQLParser.Value_listsContext):
        return tuple(item.accept(self) for item in ctx.value_list())

    # Visit a parse tree produced by SQLParser#value_list.
    def visitValue_list(self, ctx:SQLParser.Value_listContext):
        return tuple(item.accept(self) for item in ctx.value())

    # Visit a parse tree produced by SQLParser#value.
    def visitValue(self, ctx:SQLParser.ValueContext):
        if ctx.Integer():
            return self.get_int(ctx)
        elif ctx.Float():
            return self.get_float(ctx)
        elif ctx.String():
            return self.get_str(ctx)
        if ctx.Null():
            return None

    
    # Visit a parse tree produced by SQLParser#where_and_clause.
    def visitWhere_and_clause(self, ctx:SQLParser.Where_and_clauseContext):
        return tuple(item.accept(self) for item in ctx.where_clause())

    ### TODO WHERE 相关的
    # Visit a parse tree produced by SQLParser#where_operator_expression.
    def visitWhere_operator_expression(self, ctx:SQLParser.Where_operator_expressionContext):
        tableName, colName = ctx.column().accept(self)
        if not isinstance(ctx.expression().accept(self), tuple):
            return Term(1, tableName, colName, self.get_str(ctx.operator_()), value = ctx.expression().accept(self))
        else:
            return Term(1, tableName, colName, self.get_str(ctx.operator_()),
                        aim_table_name=ctx.expression().accept(self)[0], aim_col=ctx.expression().accept(self)[1])

    # Visit a parse tree produced by SQLParser#where_operator_select.
    def visitWhere_operator_select(self, ctx:SQLParser.Where_operator_selectContext):
        tableName, colName = ctx.column().accept(self)
        manager = self.systemManager
        out = ctx.select_table().accept(self)
        return Term(1, tableName, colName, 
                    self.get_str(ctx.operator_()), 
                    value=manager.getVal(result=out, is_in=False)
                    )


    # Visit a parse tree produced by SQLParser#where_null.
    def visitWhere_null(self, ctx:SQLParser.Where_nullContext):
        _, colName = ctx.column().accept(self)
        return Term(0, ctx.parentCtx.parentCtx.identifiers().accept(self)[0], 
                    colName, value=(ctx.getChild(2).getText() != "NOT"))
        

    # Visit a parse tree produced by SQLParser#where_in_list.
    def visitWhere_in_list(self, ctx:SQLParser.Where_in_listContext):
        tableName, colName = ctx.column().accept(self)
        return Term(2, tableName, colName, value=ctx.select_table().accept(self))


    # Visit a parse tree produced by SQLParser#where_in_select.
    def visitWhere_in_select(self, ctx:SQLParser.Where_in_selectContext):
        tableName, colName = ctx.column().accept(self)
        return Term(2, tableName, colName, value=self.systemManager.getVal(ctx.select_table().accept(self), True))


    # Visit a parse tree produced by SQLParser#where_like_string.
    def visitWhere_like_string(self, ctx:SQLParser.Where_like_stringContext):
        table_name, col_name = ctx.column().accept(self)
        return Term(3, table_name, col_name, value=self.get_str(ctx.String())[1:-1])

    # Visit a parse tree produced by SQLParser#column.
    def visitColumn(self, ctx:SQLParser.ColumnContext):
        if len(ctx.Identifier()) > 1:
            return self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1))
        else:
            return None, self.get_str(ctx.Identifier(0))
            
    # Visit a parse tree produced by SQLParser#set_clause.
    def visitSet_clause(self, ctx:SQLParser.Set_clauseContext):
        vals = dict()
        for identifier, value in zip(ctx.Identifier(), ctx.value()):
            vals[self.get_str(identifier)] = value.accept(self)
        return vals

    # Visit a parse tree produced by SQLParser#selectors.
    def visitSelectors(self, ctx:SQLParser.SelectorsContext):
        # print("Selector", self.get_str(ctx.getChild(0)))
        if self.get_str(ctx.getChild(0)) == '*':
            return Reducer(0, '*', '*'),
        return tuple(i.accept(self) for i in ctx.selector())

    # Visit a parse tree produced by SQLParser#selector.
    def visitSelector(self, ctx:SQLParser.SelectorContext):
        if ctx.Count():
            return Reducer(3, '*', '*')
        table_name, column_name = ctx.column().accept(self)
        if ctx.aggregator():
            return Reducer(2, table_name, column_name, self.get_str(ctx.aggregator()))
        return Reducer(1, table_name, column_name)

    # Visit a parse tree produced by SQLParser#identifiers.
    def visitIdentifiers(self, ctx:SQLParser.IdentifiersContext):
        return tuple(self.get_str(item) for item in ctx.Identifier())


if __name__ == "__main__":
    test = SystemVistor()