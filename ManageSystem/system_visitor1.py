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
# todo:move to SQL_parser
class SystemVistor1(SQLVisitor):
    def __init__(self, systemManager=None) -> None:
        super(SQLVisitor, self).__init__()
        self.systemManager: SystemManger = systemManager
        self.time_start = None

    def setManager(self, sm):
        # print("setManager", sm)
        assert isinstance(sm, SystemManger)
        self.systemManager = sm

    # @staticmethod
    def get_str(self, context):
        if isinstance(context, ParserRuleContext):
            context = context.getText()
            res = str(context)
            return res
        else:
            res = str(context)
            return res

    def get_int(self, context):
        str_context = self.get_str(context)
        int_context = int(str_context)
        return int_context

    def get_float(self, context):
        str_context = self.get_str(context)
        float_context = float(str_context)
        return float_context

    def get_time_delta(self):
        if self.time_start is None:
            self.time_start = time.time()
            return None
        else:
            new_time = time.time()
            delta = new_time - self.time_start
            self.time_start = new_time
            return delta

    def aggregateResult(self, aggregate, next_result):
        return aggregate if next_result is None else next_result

        # Visit a parse tree produced by SQLParser#program.
    def visitProgram(self, ctx:SQLParser.ProgramContext):
        ## TODO
        # print("SV::visitProgram")
        res = []
        for item in ctx.statement():
            output: LookupOutput = item.accept(self)
            if output is not None:
                output._cost = self.get_time_delta()
                output.simplify()
                res.append(output)
        return res


    # Visit a parse tree produced by SQLParser#statement.
    def visitStatement(self, ctx:SQLParser.StatementContext):
        ## TODO
        # print("SV::visitStatement", ctx, type(ctx), isinstance(ctx, SQLParser.Show_dbsContext))
        return self.visitChildren(ctx)


    # Visit a parse tree produced by SQLParser#create_db.
    def visitCreate_db(self, ctx:SQLParser.Create_dbContext):
        db_name = self.get_str(ctx.Identifier())
        return self.systemManager.createDatabase(db_name)


    # Visit a parse tree produced by SQLParser#drop_db.
    def visitDrop_db(self, ctx:SQLParser.Drop_dbContext):
        db_name = self.get_str(ctx.Identifier())
        return self.systemManager.removeDatabase(db_name)


    # Visit a parse tree produced by SQLParser#show_dbs.
    def visitShow_dbs(self, ctx:SQLParser.Show_dbsContext):
        print("visitSHow_dbs")
        return LookupOutput("databases", self.systemManager.displayDataBaseNames())


    # Visit a parse tree produced by SQLParser#use_db.
    def visitUse_db(self, ctx:SQLParser.Use_dbContext):
        print("visitUSE_dbs")
        db_name = self.get_str(ctx.Identifier())
        return self.systemManager.useDatabase(db_name)


    # Visit a parse tree produced by SQLParser#show_tables.
    def visitShow_tables(self, ctx:SQLParser.Show_tablesContext):
        return LookupOutput("tables", self.systemManager.displayTableNames())


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
        columns, primary, foreign_keys = ctx.field_list().accept(self)
        table_name = self.get_str(ctx.Identifier())
        print("visitCreateTable", table_name, foreign_keys, primary)
        tableInfo = TableInfo(table_name, columns)
        res = manager.createTable(tableInfo)
        manager.setPrimary(table_name, primary)
        for item in foreign_keys:
            key = foreign_keys[item]
            manager.addForeign(table_name, item, key)
        return res


    # Visit a parse tree produced by SQLParser#drop_table.
    def visitDrop_table(self, ctx:SQLParser.Drop_tableContext):
        table_name = self.get_str(ctx.Identifier())
        return self.systemManager.removeTable(table_name)


    # Visit a parse tree produced by SQLParser#describe_table.
    def visitDescribe_table(self, ctx:SQLParser.Describe_tableContext):
        table_name = self.get_str(ctx.Identifier())
        return self.systemManager.descTable(table_name)


    # Visit a parse tree produced by SQLParser#insert_into_table.
    def visitInsert_into_table(self, ctx:SQLParser.Insert_into_tableContext):
        manager = self.systemManager
        data = ctx.value_lists().accept(self)
        for d in data:
            table_name = self.get_str(ctx.getChild(2))
            manager.insertRecord(table_name, d)
        ret = LookupOutput('inserted_items', (len(data),))
        return ret


    # Visit a parse tree produced by SQLParser#delete_from_table.
    def visitDelete_from_table(self, ctx:SQLParser.Delete_from_tableContext):
        table_name = self.get_str(ctx.Identifier())
        cond = ctx.where_and_clause().accept(self)
        return self.systemManager.deleteRecords(table_name, cond)


    # Visit a parse tree produced by SQLParser#update_table.
    def visitUpdate_table(self, ctx:SQLParser.Update_tableContext):
        table_name = self.get_str(ctx.Identifier())
        cond = ctx.where_and_clause().accept(self)
        valMap = ctx.set_clause.accept(self)
        return self.systemManager.updateRecords(table_name, limits=cond, valmap=valMap)


    # Visit a parse tree produced by SQLParser#select_table_.
    # def visitSelect_table_(self, ctx:SQLParser.Select_table_Context):
    #     return self.visitChildren(ctx)


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
        return self.systemManager.selectRecordsLimit(ctx.selectors().accept(self), ctx.identifiers().accept(self),
                                                      term, group_by,
                                                      limit, offset)

    # Visit a parse tree produced by SQLParser#alter_table_add.
    def visitAlter_table_add(self, ctx:SQLParser.Alter_table_addContext):
        manager = self.systemManager
        col: ColumnInfo = ctx.field().accept(self)
        pri = isinstance(ctx.field(), SQLParser.Primary_key_fieldContext)
        foreign = ctx.field().getChild(0).getText() == 'FOREIGN'
        manager.addColumn(self.get_str(ctx.Identifier()), col, pri, foreign)


    # Visit a parse tree produced by SQLParser#alter_table_drop.
    def visitAlter_table_drop(self, ctx:SQLParser.Alter_table_dropContext):
        manager = self.systemManager
        manager.removeColumn(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)))


    # Visit a parse tree produced by SQLParser#alter_table_change.
    def visitAlter_table_change(self, ctx:SQLParser.Alter_table_changeContext):
        manager = self.systemManager
        manager.renameTable(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)))



    # Visit a parse tree produced by SQLParser#alter_add_index.
    def visitAlter_add_index(self, ctx:SQLParser.Alter_add_indexContext):
        indices = ctx.identifiers().accept(self)
        table_name = self.get_str(ctx.Identifier(0))
        
        idx_name = self.to_str(ctx.Identifier(1))
        for idx in indices:
            self.systemManager.createIndex(idx_name, table=table_name, col=idx)


    # ALTER TODO
    # Visit a parse tree produced by SQLParser#alter_drop_index.
    def visitAlter_drop_index(self, ctx:SQLParser.Alter_drop_indexContext):
        manager = self.systemManager
        return manager.removeIndex(self.get_str(ctx.Identifier(1)))
        # return self.visitChildren(ctx)


    # Visit a parse tree produced by SQLParser#alter_table_drop_pk.
    def visitAlter_table_drop_pk(self, ctx:SQLParser.Alter_table_drop_pkContext):
        # TODO
        manager = self.systemManager
        manager.removePrimary(self.get_str(ctx.Identifier(0)))


    # Visit a parse tree produced by SQLParser#alter_table_drop_foreign_key.
    def visitAlter_table_drop_foreign_key(self, ctx:SQLParser.Alter_table_drop_foreign_keyContext):
        # TODO
        manager = self.systemManager
        manager.removeForeign(self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1)), None)



    # Visit a parse tree produced by SQLParser#alter_table_add_pk.
    def visitAlter_table_add_pk(self, ctx:SQLParser.Alter_table_add_pkContext):
        # TODO
        manager = self.systemManager
        manager.setPrimary(self.get_str(ctx.Identifier(0)), ctx.identifiers().accept(self))


    # Visit a parse tree produced by SQLParser#alter_table_add_foreign_key.
    def visitAlter_table_add_foreign_key(self, ctx:SQLParser.Alter_table_add_foreign_keyContext):
        # TODO
        manager = self.systemManager
        for (item1, item2) in zip(ctx.identifiers(0).accept(self), ctx.identifiers(1).accept(self)):
            manager.addForeign(self.get_str(ctx.Identifier(0)), item1,
                                           (self.get_str(ctx.Identifier(2)), item2), self.get_str(ctx.Identifier(1)))


    # Visit a parse tree produced by SQLParser#alter_table_add_unique.
    def visitAlter_table_add_unique(self, ctx:SQLParser.Alter_table_add_uniqueContext):
        # TODO
        manager = self.systemManager
        table, name, column = tuple(map(self.to_str, ctx.Identifier()))
        return manager.addUnique(table, column, name)


    # Visit a parse tree produced by SQLParser#field_list.
    def visitField_list(self, ctx:SQLParser.Field_listContext):
        name_to_column = {}
        foreign_keys = {}
        primary_key = None

        for field in ctx.field():
            if isinstance(field, SQLParser.Normal_fieldContext):
                col_name = self.get_str(field.Identifier())
                if (col_name in name_to_column):
                    raise NameError(f'Error!!! Duplicate ColName {col_name}')
                colInfo = field.accept(self)
                name_to_column[col_name] = colInfo
            elif isinstance(field, SQLParser.Primary_key_fieldContext):
                names = field.accept(self)
                if primary_key:
                    raise NameError('Error!!!Only one primary key supported')
                for item in names:
                    if item not in name_to_column:
                        raise NameError(f'Unknown field {item} field list')
                primary_key = names
            else:
                # print("visitForeignKeyField", field.accept(self))
                assert isinstance(field, SQLParser.Foreign_key_fieldContext), f"SystemVisitor::visitField_list unknown field type {type(field)}!!!!"
                field_name, table_name, refer_name = field.accept(self)
                if field_name in foreign_keys:
                    raise NameError(f'Foreign key named {field_name} is duplicated')
                foreign_keys[field_name] = table_name, refer_name

        return list(name_to_column.values()), primary_key, foreign_keys


    # Visit a parse tree produced by SQLParser#normal_field.
    def visitNormal_field(self, ctx:SQLParser.Normal_fieldContext):
        type_name, size = ctx.type_().accept(self)
        col_name = self.get_str(ctx.Identifier())
        null_permit = False if ctx.Null() else True
        default_value = None
        if ctx.value():
            idx = 0
            for idx in range(ctx.getChildCount() - 1):
                if "DEFAULT" == ctx.getChild(idx).getText():
                    default_value = ctx.getChild(idx + 1).accept(self)
        ret = ColumnInfo(type_name, col_name, size, null_permit, default_value)
        return ret


    # Visit a parse tree produced by SQLParser#primary_key_field.
    def visitPrimary_key_field(self, ctx:SQLParser.Primary_key_fieldContext):
        ret = ctx.identifiers().accept(self)
        return ret


    # Visit a parse tree produced by SQLParser#foreign_key_field.
    def visitForeign_key_field(self, ctx:SQLParser.Foreign_key_fieldContext):
        fields = tuple(self.get_str(i) for i in ctx.Identifier())
        return fields


    # Visit a parse tree produced by SQLParser#type_.
    def visitType_(self, ctx:SQLParser.Type_Context):
        size = 8
        if ctx.Integer():
            size = self.get_int(ctx.Integer())
        type_name = self.get_str(ctx.getChild(0))
        return type_name, size


    # Visit a parse tree produced by SQLParser#value_lists.
    def visitValue_lists(self, ctx:SQLParser.Value_listsContext):
        value_lists = tuple(item.accept(self) for item in ctx.value_list())
        return value_lists


    # Visit a parse tree produced by SQLParser#value_list.
    def visitValue_list(self, ctx:SQLParser.Value_listContext):
        values = tuple(item.accept(self) for item in ctx.value())
        return values


    # Visit a parse tree produced by SQLParser#value.
    def visitValue(self, ctx:SQLParser.ValueContext):
        if ctx.Float():
            return self.get_float(ctx)
        elif ctx.Integer():
            return self.get_int(ctx)
        elif ctx.String():
            return self.get_str(ctx)
        if ctx.Null():
            return None

    
    # Visit a parse tree produced by SQLParser#where_and_clause.
    def visitWhere_and_clause(self, ctx:SQLParser.Where_and_clauseContext):
        ret = tuple(item.accept(self) for item in ctx.where_clause())
        return ret

    ### TODO WHERE 相关的
    # Visit a parse tree produced by SQLParser#where_operator_expression.
    def visitWhere_operator_expression(self, ctx:SQLParser.Where_operator_expressionContext):
        operator = self.get_str(ctx.operator_())
        table_name, col_name = ctx.column().accept(self)
        expr = ctx.expression().accept(self)
        if not isinstance(expr, tuple):
            ret = Term(1, table_name, col_name, operator, value=expr)
        else:
            ret = Term(1, table_name, col_name, operator,
                        aim_table_name=expr[0], aim_col=expr[1])
        return ret


    # Visit a parse tree produced by SQLParser#where_operator_select.
    def visitWhere_operator_select(self, ctx:SQLParser.Where_operator_selectContext):
        table_name, col_name = ctx.column().accept(self)
        operator = self.get_str(ctx.operator_())
        manager = self.systemManager
        res: LookupOutput = ctx.select_table().accept(self)
        value = manager.resultToValue(result=res, is_in=False)
        return Term(1, table_name, col_name, operator, value=value)


    # Visit a parse tree produced by SQLParser#where_null.
    def visitWhere_null(self, ctx:SQLParser.Where_nullContext):
        table_name = ctx.parentCtx.parentCtx.identifiers().accept(self)[0]
        _, col_name = ctx.column().accept(self)
        ret = Term(0, table_name, col_name, value=(ctx.getChild(2).getText() != "NOT"))
        return ret

    # Visit a parse tree produced by SQLParser#where_in_list.
    def visitWhere_in_list(self, ctx:SQLParser.Where_in_listContext):
        table_name, col_name = ctx.column().accept(self)
        output: LookupOutput = ctx.select_table().accept(self)
        ret = Term(2, table_name, col_name, value=output)
        return ret


    # Visit a parse tree produced by SQLParser#where_in_select.
    def visitWhere_in_select(self, ctx:SQLParser.Where_in_selectContext):
        table_name, col_name = ctx.column().accept(self)
        output: LookupOutput = ctx.select_table().accept(self)
        ret = Term(2, table_name, col_name, value=self.systemManager.resultToValue(output, True))
        return ret


    # Visit a parse tree produced by SQLParser#where_like_string.
    def visitWhere_like_string(self, ctx:SQLParser.Where_like_stringContext):
        table_name, col_name = ctx.column().accept(self)
        pattern = self.get_str(ctx.String())[1:-1]
        ret = Term(3, table_name, col_name, value=pattern)
        return ret


    # Visit a parse tree produced by SQLParser#column.
    def visitColumn(self, ctx:SQLParser.ColumnContext):
        if len(ctx.Identifier()) == 1:
            ret = None, self.get_str(ctx.Identifier(0))
        else:
            ret = self.get_str(ctx.Identifier(0)), self.get_str(ctx.Identifier(1))
        return ret

    # Visit a parse tree produced by SQLParser#set_clause.
    def visitSet_clause(self, ctx:SQLParser.Set_clauseContext):
        valMap = dict()
        for identifier, value in zip(ctx.Identifier(), ctx.value()):
            tmp_str = self.get_str(identifier)
            valMap[tmp_str] = value.accept(self)
        return valMap


    # Visit a parse tree produced by SQLParser#selectors.
    def visitSelectors(self, ctx:SQLParser.SelectorsContext):
        # print("Selector", self.get_str(ctx.getChild(0)))
        if self.get_str(ctx.getChild(0)) == '*':
            ret = Reducer(0, '*', '*'),
        else:
            ret = tuple(i.accept(self) for i in ctx.selector())
        return ret


    # Visit a parse tree produced by SQLParser#selector.
    def visitSelector(self, ctx:SQLParser.SelectorContext):
        if ctx.Count():
            reducer_type = 3
            return Reducer(reducer_type, '*', '*')
        table_name, column_name = ctx.column().accept(self)
        if ctx.aggregator():
            reducer_type = 2
            aggre_name =self.get_str(ctx.aggregator())
            res = Reducer(reducer_type, table_name, column_name, aggre_name)
            return res
        else:
            reducer_type = 1
            return Reducer(reducer_type, table_name, column_name)


    # Visit a parse tree produced by SQLParser#identifiers.
    def visitIdentifiers(self, ctx:SQLParser.IdentifiersContext):
        identifiers = tuple(self.get_str(item) for item in ctx.Identifier())
        return identifiers


if __name__ == "__main__":
    test = SystemVistor1()