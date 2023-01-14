// Generated from c:\Users\x-eri\Desktop\大四上\course\数据库系统概论\数据库大作业\reference\lius-pydb1\SQL_parser1\SQL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, EqualOrAssign=56, Less=57, LessEqual=58, 
		Greater=59, GreaterEqual=60, NotEqual=61, Count=62, Average=63, Max=64, 
		Min=65, Sum=66, Null=67, Identifier=68, Integer=69, String=70, Float=71, 
		Whitespace=72, Annotation=73;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_system_statement = 2, RULE_db_statement = 3, 
		RULE_io_statement = 4, RULE_table_statement = 5, RULE_select_table = 6, 
		RULE_index_statement = 7, RULE_alter_statement = 8, RULE_field_list = 9, 
		RULE_field = 10, RULE_type_ = 11, RULE_value_lists = 12, RULE_value_list = 13, 
		RULE_value = 14, RULE_where_and_clause = 15, RULE_where_clause = 16, RULE_column = 17, 
		RULE_expression = 18, RULE_set_clause = 19, RULE_selectors = 20, RULE_selector = 21, 
		RULE_identifiers = 22, RULE_operator = 23, RULE_aggregator = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "system_statement", "db_statement", "io_statement", 
			"table_statement", "select_table", "index_statement", "alter_statement", 
			"field_list", "field", "type_", "value_lists", "value_list", "value", 
			"where_and_clause", "where_clause", "column", "expression", "set_clause", 
			"selectors", "selector", "identifiers", "operator", "aggregator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'SHOW'", "'DATABASES'", "'CREATE'", "'DATABASE'", "'DROP'", 
			"'USE'", "'TABLES'", "'INDEXES'", "'LOAD'", "'FROM'", "'FILE'", "'TO'", 
			"'TABLE'", "'DUMP'", "'('", "')'", "'DESC'", "'INSERT'", "'INTO'", "'VALUES'", 
			"'DELETE'", "'WHERE'", "'UPDATE'", "'SET'", "'SELECT'", "'GROUP'", "'BY'", 
			"'LIMIT'", "'OFFSET'", "'INDEX'", "'ON'", "'ALTER'", "'ADD'", "'CHANGE'", 
			"'RENAME'", "'PRIMARY'", "'KEY'", "'FOREIGN'", "'CONSTRAINT'", "'REFERENCES'", 
			"'UNIQUE'", "','", "'NOT'", "'DEFAULT'", "'INT'", "'VARCHAR'", "'DATE'", 
			"'FLOAT'", "'AND'", "'IS'", "'IN'", "'LIKE'", "'.'", "'*'", "'='", "'<'", 
			"'<='", "'>'", "'>='", "'<>'", "'COUNT'", "'AVG'", "'MAX'", "'MIN'", 
			"'SUM'", "'NULL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "EqualOrAssign", "Less", 
			"LessEqual", "Greater", "GreaterEqual", "NotEqual", "Count", "Average", 
			"Max", "Min", "Sum", "Null", "Identifier", "Integer", "String", "Float", 
			"Whitespace", "Annotation"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SQLParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__9) | (1L << T__14) | (1L << T__17) | (1L << T__18) | (1L << T__21) | (1L << T__23) | (1L << T__25) | (1L << T__32))) != 0) || _la==Null || _la==Annotation) {
				{
				{
				setState(50);
				statement();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public System_statementContext system_statement() {
			return getRuleContext(System_statementContext.class,0);
		}
		public Db_statementContext db_statement() {
			return getRuleContext(Db_statementContext.class,0);
		}
		public Io_statementContext io_statement() {
			return getRuleContext(Io_statementContext.class,0);
		}
		public Table_statementContext table_statement() {
			return getRuleContext(Table_statementContext.class,0);
		}
		public Index_statementContext index_statement() {
			return getRuleContext(Index_statementContext.class,0);
		}
		public Alter_statementContext alter_statement() {
			return getRuleContext(Alter_statementContext.class,0);
		}
		public TerminalNode Annotation() { return getToken(SQLParser.Annotation, 0); }
		public TerminalNode Null() { return getToken(SQLParser.Null, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				system_statement();
				setState(59);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				db_statement();
				setState(62);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				io_statement();
				setState(65);
				match(T__0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				table_statement();
				setState(68);
				match(T__0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				index_statement();
				setState(71);
				match(T__0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
				alter_statement();
				setState(74);
				match(T__0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(76);
				match(Annotation);
				setState(77);
				match(T__0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(78);
				match(Null);
				setState(79);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class System_statementContext extends ParserRuleContext {
		public System_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_system_statement; }
	}

	public final System_statementContext system_statement() throws RecognitionException {
		System_statementContext _localctx = new System_statementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_system_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__1);
			setState(83);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Db_statementContext extends ParserRuleContext {
		public Db_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_db_statement; }
	 
		public Db_statementContext() { }
		public void copyFrom(Db_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Show_dbsContext extends Db_statementContext {
		public Show_dbsContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Drop_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Drop_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Show_tablesContext extends Db_statementContext {
		public Show_tablesContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Create_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Use_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Use_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Show_indexesContext extends Db_statementContext {
		public Show_indexesContext(Db_statementContext ctx) { copyFrom(ctx); }
	}

	public final Db_statementContext db_statement() throws RecognitionException {
		Db_statementContext _localctx = new Db_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_db_statement);
		try {
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(T__3);
				setState(86);
				match(T__4);
				setState(87);
				match(Identifier);
				}
				break;
			case 2:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				match(T__5);
				setState(89);
				match(T__4);
				setState(90);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new Show_dbsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
				match(T__1);
				setState(92);
				match(T__2);
				}
				break;
			case 4:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				match(T__6);
				setState(94);
				match(Identifier);
				}
				break;
			case 5:
				_localctx = new Show_tablesContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(95);
				match(T__1);
				setState(96);
				match(T__7);
				}
				break;
			case 6:
				_localctx = new Show_indexesContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(97);
				match(T__1);
				setState(98);
				match(T__8);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Io_statementContext extends ParserRuleContext {
		public Io_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_io_statement; }
	 
		public Io_statementContext() { }
		public void copyFrom(Io_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Dump_dataContext extends Io_statementContext {
		public TerminalNode String() { return getToken(SQLParser.String, 0); }
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Dump_dataContext(Io_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Load_dataContext extends Io_statementContext {
		public TerminalNode String() { return getToken(SQLParser.String, 0); }
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Load_dataContext(Io_statementContext ctx) { copyFrom(ctx); }
	}

	public final Io_statementContext io_statement() throws RecognitionException {
		Io_statementContext _localctx = new Io_statementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_io_statement);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				_localctx = new Load_dataContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(101);
				match(T__9);
				setState(102);
				match(T__10);
				setState(103);
				match(T__11);
				setState(104);
				match(String);
				setState(105);
				match(T__12);
				setState(106);
				match(T__13);
				setState(107);
				match(Identifier);
				}
				break;
			case T__14:
				_localctx = new Dump_dataContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(T__14);
				setState(109);
				match(T__12);
				setState(110);
				match(T__11);
				setState(111);
				match(String);
				setState(112);
				match(T__10);
				setState(113);
				match(T__13);
				setState(114);
				match(Identifier);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_statementContext extends ParserRuleContext {
		public Table_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_statement; }
	 
		public Table_statementContext() { }
		public void copyFrom(Table_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Delete_from_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Where_and_clauseContext where_and_clause() {
			return getRuleContext(Where_and_clauseContext.class,0);
		}
		public Delete_from_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Insert_into_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Value_listsContext value_lists() {
			return getRuleContext(Value_listsContext.class,0);
		}
		public Insert_into_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public Create_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Describe_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Describe_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Select_table_Context extends Table_statementContext {
		public Select_tableContext select_table() {
			return getRuleContext(Select_tableContext.class,0);
		}
		public Select_table_Context(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Drop_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Drop_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Update_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Set_clauseContext set_clause() {
			return getRuleContext(Set_clauseContext.class,0);
		}
		public Where_and_clauseContext where_and_clause() {
			return getRuleContext(Where_and_clauseContext.class,0);
		}
		public Update_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}

	public final Table_statementContext table_statement() throws RecognitionException {
		Table_statementContext _localctx = new Table_statementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_table_statement);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new Create_tableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				match(T__3);
				setState(118);
				match(T__13);
				setState(119);
				match(Identifier);
				setState(120);
				match(T__15);
				setState(121);
				field_list();
				setState(122);
				match(T__16);
				}
				break;
			case T__5:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				match(T__5);
				setState(125);
				match(T__13);
				setState(126);
				match(Identifier);
				}
				break;
			case T__17:
				_localctx = new Describe_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				match(T__17);
				setState(128);
				match(Identifier);
				}
				break;
			case T__18:
				_localctx = new Insert_into_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(129);
				match(T__18);
				setState(130);
				match(T__19);
				setState(131);
				match(Identifier);
				setState(132);
				match(T__20);
				setState(133);
				value_lists();
				}
				break;
			case T__21:
				_localctx = new Delete_from_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(134);
				match(T__21);
				setState(135);
				match(T__10);
				setState(136);
				match(Identifier);
				setState(137);
				match(T__22);
				setState(138);
				where_and_clause();
				}
				break;
			case T__23:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(139);
				match(T__23);
				setState(140);
				match(Identifier);
				setState(141);
				match(T__24);
				setState(142);
				set_clause();
				setState(143);
				match(T__22);
				setState(144);
				where_and_clause();
				}
				break;
			case T__25:
				_localctx = new Select_table_Context(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(146);
				select_table();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_tableContext extends ParserRuleContext {
		public SelectorsContext selectors() {
			return getRuleContext(SelectorsContext.class,0);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Where_and_clauseContext where_and_clause() {
			return getRuleContext(Where_and_clauseContext.class,0);
		}
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public List<TerminalNode> Integer() { return getTokens(SQLParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(SQLParser.Integer, i);
		}
		public Select_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_table; }
	}

	public final Select_tableContext select_table() throws RecognitionException {
		Select_tableContext _localctx = new Select_tableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_select_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(T__25);
			setState(150);
			selectors();
			setState(151);
			match(T__10);
			setState(152);
			identifiers();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__22) {
				{
				setState(153);
				match(T__22);
				setState(154);
				where_and_clause();
				}
			}

			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__26) {
				{
				setState(157);
				match(T__26);
				setState(158);
				match(T__27);
				setState(159);
				column();
				}
			}

			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(162);
				match(T__28);
				setState(163);
				match(Integer);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__29) {
					{
					setState(164);
					match(T__29);
					setState(165);
					match(Integer);
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Index_statementContext extends ParserRuleContext {
		public Index_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_statement; }
	 
		public Index_statementContext() { }
		public void copyFrom(Index_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Alter_drop_indexContext extends Index_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_drop_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_add_indexContext extends Index_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Alter_add_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_indexContext extends Index_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Create_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Drop_indexContext extends Index_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Drop_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}

	public final Index_statementContext index_statement() throws RecognitionException {
		Index_statementContext _localctx = new Index_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_index_statement);
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new Create_indexContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				match(T__3);
				setState(171);
				match(T__30);
				setState(172);
				match(Identifier);
				setState(173);
				match(T__31);
				setState(174);
				match(Identifier);
				setState(175);
				match(T__15);
				setState(176);
				identifiers();
				setState(177);
				match(T__16);
				}
				break;
			case 2:
				_localctx = new Drop_indexContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(T__5);
				setState(180);
				match(T__30);
				setState(181);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new Alter_add_indexContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
				match(T__32);
				setState(183);
				match(T__13);
				setState(184);
				match(Identifier);
				setState(185);
				match(T__33);
				setState(186);
				match(T__30);
				setState(187);
				match(Identifier);
				setState(188);
				match(T__15);
				setState(189);
				identifiers();
				setState(190);
				match(T__16);
				}
				break;
			case 4:
				_localctx = new Alter_drop_indexContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(192);
				match(T__32);
				setState(193);
				match(T__13);
				setState(194);
				match(Identifier);
				setState(195);
				match(T__5);
				setState(196);
				match(T__30);
				setState(197);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_statementContext extends ParserRuleContext {
		public Alter_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_statement; }
	 
		public Alter_statementContext() { }
		public void copyFrom(Alter_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Alter_table_drop_pkContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_table_drop_pkContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_add_foreign_keyContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public List<IdentifiersContext> identifiers() {
			return getRuleContexts(IdentifiersContext.class);
		}
		public IdentifiersContext identifiers(int i) {
			return getRuleContext(IdentifiersContext.class,i);
		}
		public Alter_table_add_foreign_keyContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_add_uniqueContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_table_add_uniqueContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_dropContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_table_dropContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_addContext extends Alter_statementContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Alter_table_addContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_changeContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Alter_table_changeContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_renameContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_table_renameContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_drop_foreign_keyContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Alter_table_drop_foreign_keyContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_add_pkContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Alter_table_add_pkContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}

	public final Alter_statementContext alter_statement() throws RecognitionException {
		Alter_statementContext _localctx = new Alter_statementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_alter_statement);
		int _la;
		try {
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				_localctx = new Alter_table_addContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				match(T__32);
				setState(201);
				match(T__13);
				setState(202);
				match(Identifier);
				setState(203);
				match(T__33);
				setState(204);
				field();
				}
				break;
			case 2:
				_localctx = new Alter_table_dropContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				match(T__32);
				setState(206);
				match(T__13);
				setState(207);
				match(Identifier);
				setState(208);
				match(T__5);
				setState(209);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new Alter_table_changeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				match(T__32);
				setState(211);
				match(T__13);
				setState(212);
				match(Identifier);
				setState(213);
				match(T__34);
				setState(214);
				match(Identifier);
				setState(215);
				field();
				}
				break;
			case 4:
				_localctx = new Alter_table_renameContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(216);
				match(T__32);
				setState(217);
				match(T__13);
				setState(218);
				match(Identifier);
				setState(219);
				match(T__35);
				setState(220);
				match(T__12);
				setState(221);
				match(Identifier);
				}
				break;
			case 5:
				_localctx = new Alter_table_drop_pkContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(222);
				match(T__32);
				setState(223);
				match(T__13);
				setState(224);
				match(Identifier);
				setState(225);
				match(T__5);
				setState(226);
				match(T__36);
				setState(227);
				match(T__37);
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(228);
					match(Identifier);
					}
				}

				}
				break;
			case 6:
				_localctx = new Alter_table_drop_foreign_keyContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(231);
				match(T__32);
				setState(232);
				match(T__13);
				setState(233);
				match(Identifier);
				setState(234);
				match(T__5);
				setState(235);
				match(T__38);
				setState(236);
				match(T__37);
				setState(237);
				match(Identifier);
				}
				break;
			case 7:
				_localctx = new Alter_table_add_pkContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(238);
				match(T__32);
				setState(239);
				match(T__13);
				setState(240);
				match(Identifier);
				setState(241);
				match(T__33);
				setState(242);
				match(T__39);
				setState(243);
				match(Identifier);
				setState(244);
				match(T__36);
				setState(245);
				match(T__37);
				setState(246);
				match(T__15);
				setState(247);
				identifiers();
				setState(248);
				match(T__16);
				}
				break;
			case 8:
				_localctx = new Alter_table_add_foreign_keyContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(250);
				match(T__32);
				setState(251);
				match(T__13);
				setState(252);
				match(Identifier);
				setState(253);
				match(T__33);
				setState(254);
				match(T__39);
				setState(255);
				match(Identifier);
				setState(256);
				match(T__38);
				setState(257);
				match(T__37);
				setState(258);
				match(T__15);
				setState(259);
				identifiers();
				setState(260);
				match(T__16);
				setState(261);
				match(T__40);
				setState(262);
				match(Identifier);
				setState(263);
				match(T__15);
				setState(264);
				identifiers();
				setState(265);
				match(T__16);
				}
				break;
			case 9:
				_localctx = new Alter_table_add_uniqueContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(267);
				match(T__32);
				setState(268);
				match(T__13);
				setState(269);
				match(Identifier);
				setState(270);
				match(T__33);
				setState(271);
				match(T__41);
				setState(272);
				match(Identifier);
				setState(273);
				match(T__15);
				setState(274);
				match(Identifier);
				setState(275);
				match(T__16);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_listContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public Field_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_list; }
	}

	public final Field_listContext field_list() throws RecognitionException {
		Field_listContext _localctx = new Field_listContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_field_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			field();
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(279);
				match(T__42);
				setState(280);
				field();
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
	 
		public FieldContext() { }
		public void copyFrom(FieldContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Primary_key_fieldContext extends FieldContext {
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Primary_key_fieldContext(FieldContext ctx) { copyFrom(ctx); }
	}
	public static class Foreign_key_fieldContext extends FieldContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public Foreign_key_fieldContext(FieldContext ctx) { copyFrom(ctx); }
	}
	public static class Normal_fieldContext extends FieldContext {
		public TerminalNode Identifier() { return getToken(SQLParser.Identifier, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode Null() { return getToken(SQLParser.Null, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Normal_fieldContext(FieldContext ctx) { copyFrom(ctx); }
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_field);
		int _la;
		try {
			setState(312);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				_localctx = new Normal_fieldContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(286);
				match(Identifier);
				setState(287);
				type_();
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__43) {
					{
					setState(288);
					match(T__43);
					setState(289);
					match(Null);
					}
				}

				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__44) {
					{
					setState(292);
					match(T__44);
					setState(293);
					value();
					}
				}

				}
				break;
			case T__36:
				_localctx = new Primary_key_fieldContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(T__36);
				setState(297);
				match(T__37);
				setState(298);
				match(T__15);
				setState(299);
				identifiers();
				setState(300);
				match(T__16);
				}
				break;
			case T__38:
				_localctx = new Foreign_key_fieldContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(302);
				match(T__38);
				setState(303);
				match(T__37);
				setState(304);
				match(T__15);
				setState(305);
				match(Identifier);
				setState(306);
				match(T__16);
				setState(307);
				match(T__40);
				setState(308);
				match(Identifier);
				setState(309);
				match(T__15);
				setState(310);
				match(Identifier);
				setState(311);
				match(T__16);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_Context extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(SQLParser.Integer, 0); }
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_type_);
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__45:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				match(T__45);
				}
				break;
			case T__46:
				enterOuterAlt(_localctx, 2);
				{
				setState(315);
				match(T__46);
				setState(316);
				match(T__15);
				setState(317);
				match(Integer);
				setState(318);
				match(T__16);
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 3);
				{
				setState(319);
				match(T__47);
				}
				break;
			case T__48:
				enterOuterAlt(_localctx, 4);
				{
				setState(320);
				match(T__48);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Value_listsContext extends ParserRuleContext {
		public List<Value_listContext> value_list() {
			return getRuleContexts(Value_listContext.class);
		}
		public Value_listContext value_list(int i) {
			return getRuleContext(Value_listContext.class,i);
		}
		public Value_listsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_lists; }
	}

	public final Value_listsContext value_lists() throws RecognitionException {
		Value_listsContext _localctx = new Value_listsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_value_lists);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			value_list();
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(324);
				match(T__42);
				setState(325);
				value_list();
				}
				}
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Value_listContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Value_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value_list; }
	}

	public final Value_listContext value_list() throws RecognitionException {
		Value_listContext _localctx = new Value_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_value_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			match(T__15);
			setState(332);
			value();
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(333);
				match(T__42);
				setState(334);
				value();
				}
				}
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(340);
			match(T__16);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(SQLParser.Integer, 0); }
		public TerminalNode String() { return getToken(SQLParser.String, 0); }
		public TerminalNode Float() { return getToken(SQLParser.Float, 0); }
		public TerminalNode Null() { return getToken(SQLParser.Null, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			_la = _input.LA(1);
			if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (Null - 67)) | (1L << (Integer - 67)) | (1L << (String - 67)) | (1L << (Float - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_and_clauseContext extends ParserRuleContext {
		public List<Where_clauseContext> where_clause() {
			return getRuleContexts(Where_clauseContext.class);
		}
		public Where_clauseContext where_clause(int i) {
			return getRuleContext(Where_clauseContext.class,i);
		}
		public Where_and_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_and_clause; }
	}

	public final Where_and_clauseContext where_and_clause() throws RecognitionException {
		Where_and_clauseContext _localctx = new Where_and_clauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_where_and_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			where_clause();
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__49) {
				{
				{
				setState(345);
				match(T__49);
				setState(346);
				where_clause();
				}
				}
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_clauseContext extends ParserRuleContext {
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
	 
		public Where_clauseContext() { }
		public void copyFrom(Where_clauseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class Where_in_listContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public Value_listContext value_list() {
			return getRuleContext(Value_listContext.class,0);
		}
		public Where_in_listContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}
	public static class Where_operator_selectContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public Select_tableContext select_table() {
			return getRuleContext(Select_tableContext.class,0);
		}
		public Where_operator_selectContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}
	public static class Where_nullContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode Null() { return getToken(SQLParser.Null, 0); }
		public Where_nullContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}
	public static class Where_operator_expressionContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Where_operator_expressionContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}
	public static class Where_in_selectContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public Select_tableContext select_table() {
			return getRuleContext(Select_tableContext.class,0);
		}
		public Where_in_selectContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}
	public static class Where_like_stringContext extends Where_clauseContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public TerminalNode String() { return getToken(SQLParser.String, 0); }
		public Where_like_stringContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_where_clause);
		int _la;
		try {
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new Where_operator_expressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				column();
				setState(353);
				operator();
				setState(354);
				expression();
				}
				break;
			case 2:
				_localctx = new Where_operator_selectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				column();
				setState(357);
				operator();
				setState(358);
				match(T__15);
				setState(359);
				select_table();
				setState(360);
				match(T__16);
				}
				break;
			case 3:
				_localctx = new Where_nullContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
				column();
				setState(363);
				match(T__50);
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__43) {
					{
					setState(364);
					match(T__43);
					}
				}

				setState(367);
				match(Null);
				}
				break;
			case 4:
				_localctx = new Where_in_listContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(369);
				column();
				setState(370);
				match(T__51);
				setState(371);
				value_list();
				}
				break;
			case 5:
				_localctx = new Where_in_selectContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(373);
				column();
				setState(374);
				match(T__51);
				setState(375);
				match(T__15);
				setState(376);
				select_table();
				setState(377);
				match(T__16);
				}
				break;
			case 6:
				_localctx = new Where_like_stringContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(379);
				column();
				setState(380);
				match(T__52);
				setState(381);
				match(String);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ColumnContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(385);
				match(Identifier);
				setState(386);
				match(T__53);
				}
				break;
			}
			setState(389);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expression);
		try {
			setState(393);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case Integer:
			case String:
			case Float:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				value();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				column();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_clauseContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public List<TerminalNode> EqualOrAssign() { return getTokens(SQLParser.EqualOrAssign); }
		public TerminalNode EqualOrAssign(int i) {
			return getToken(SQLParser.EqualOrAssign, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public Set_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_clause; }
	}

	public final Set_clauseContext set_clause() throws RecognitionException {
		Set_clauseContext _localctx = new Set_clauseContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_set_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(Identifier);
			setState(396);
			match(EqualOrAssign);
			setState(397);
			value();
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(398);
				match(T__42);
				setState(399);
				match(Identifier);
				setState(400);
				match(EqualOrAssign);
				setState(401);
				value();
				}
				}
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectorsContext extends ParserRuleContext {
		public List<SelectorContext> selector() {
			return getRuleContexts(SelectorContext.class);
		}
		public SelectorContext selector(int i) {
			return getRuleContext(SelectorContext.class,i);
		}
		public SelectorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectors; }
	}

	public final SelectorsContext selectors() throws RecognitionException {
		SelectorsContext _localctx = new SelectorsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_selectors);
		int _la;
		try {
			setState(416);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__54:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(T__54);
				}
				break;
			case Count:
			case Average:
			case Max:
			case Min:
			case Sum:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(408);
				selector();
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__42) {
					{
					{
					setState(409);
					match(T__42);
					setState(410);
					selector();
					}
					}
					setState(415);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectorContext extends ParserRuleContext {
		public ColumnContext column() {
			return getRuleContext(ColumnContext.class,0);
		}
		public AggregatorContext aggregator() {
			return getRuleContext(AggregatorContext.class,0);
		}
		public TerminalNode Count() { return getToken(SQLParser.Count, 0); }
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selector);
		try {
			setState(428);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(418);
				column();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				aggregator();
				setState(420);
				match(T__15);
				setState(421);
				column();
				setState(422);
				match(T__16);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(424);
				match(Count);
				setState(425);
				match(T__15);
				setState(426);
				match(T__54);
				setState(427);
				match(T__16);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifiersContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(SQLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQLParser.Identifier, i);
		}
		public IdentifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiers; }
	}

	public final IdentifiersContext identifiers() throws RecognitionException {
		IdentifiersContext _localctx = new IdentifiersContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_identifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(Identifier);
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(431);
				match(T__42);
				setState(432);
				match(Identifier);
				}
				}
				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode EqualOrAssign() { return getToken(SQLParser.EqualOrAssign, 0); }
		public TerminalNode Less() { return getToken(SQLParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(SQLParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(SQLParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(SQLParser.GreaterEqual, 0); }
		public TerminalNode NotEqual() { return getToken(SQLParser.NotEqual, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EqualOrAssign) | (1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual) | (1L << NotEqual))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregatorContext extends ParserRuleContext {
		public TerminalNode Count() { return getToken(SQLParser.Count, 0); }
		public TerminalNode Average() { return getToken(SQLParser.Average, 0); }
		public TerminalNode Max() { return getToken(SQLParser.Max, 0); }
		public TerminalNode Min() { return getToken(SQLParser.Min, 0); }
		public TerminalNode Sum() { return getToken(SQLParser.Sum, 0); }
		public AggregatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator; }
	}

	public final AggregatorContext aggregator() throws RecognitionException {
		AggregatorContext _localctx = new AggregatorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_aggregator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			_la = _input.LA(1);
			if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (Count - 62)) | (1L << (Average - 62)) | (1L << (Max - 62)) | (1L << (Min - 62)) | (1L << (Sum - 62)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3K\u01bd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"S\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5f\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\5\6v\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0096"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009e\n\b\3\b\3\b\3\b\5\b\u00a3\n\b\3"+
		"\b\3\b\3\b\3\b\5\b\u00a9\n\b\5\b\u00ab\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u00c9\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\n\u00e8\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0117\n"+
		"\n\3\13\3\13\3\13\7\13\u011c\n\13\f\13\16\13\u011f\13\13\3\f\3\f\3\f\3"+
		"\f\5\f\u0125\n\f\3\f\3\f\5\f\u0129\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u013b\n\f\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u0144\n\r\3\16\3\16\3\16\7\16\u0149\n\16\f\16\16\16\u014c\13"+
		"\16\3\17\3\17\3\17\3\17\7\17\u0152\n\17\f\17\16\17\u0155\13\17\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\3\21\7\21\u015e\n\21\f\21\16\21\u0161\13\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0170"+
		"\n\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\5\22\u0182\n\22\3\23\3\23\5\23\u0186\n\23\3\23\3\23\3"+
		"\24\3\24\5\24\u018c\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0195"+
		"\n\25\f\25\16\25\u0198\13\25\3\26\3\26\3\26\3\26\7\26\u019e\n\26\f\26"+
		"\16\26\u01a1\13\26\5\26\u01a3\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u01af\n\27\3\30\3\30\3\30\7\30\u01b4\n\30\f\30\16"+
		"\30\u01b7\13\30\3\31\3\31\3\32\3\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\2\5\4\2EEGI\3\2:?\3\2@D\2\u01e0\2\67\3"+
		"\2\2\2\4R\3\2\2\2\6T\3\2\2\2\be\3\2\2\2\nu\3\2\2\2\f\u0095\3\2\2\2\16"+
		"\u0097\3\2\2\2\20\u00c8\3\2\2\2\22\u0116\3\2\2\2\24\u0118\3\2\2\2\26\u013a"+
		"\3\2\2\2\30\u0143\3\2\2\2\32\u0145\3\2\2\2\34\u014d\3\2\2\2\36\u0158\3"+
		"\2\2\2 \u015a\3\2\2\2\"\u0181\3\2\2\2$\u0185\3\2\2\2&\u018b\3\2\2\2(\u018d"+
		"\3\2\2\2*\u01a2\3\2\2\2,\u01ae\3\2\2\2.\u01b0\3\2\2\2\60\u01b8\3\2\2\2"+
		"\62\u01ba\3\2\2\2\64\66\5\4\3\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2"+
		"\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\2\2\3;\3\3\2\2\2<=\5\6\4\2=>"+
		"\7\3\2\2>S\3\2\2\2?@\5\b\5\2@A\7\3\2\2AS\3\2\2\2BC\5\n\6\2CD\7\3\2\2D"+
		"S\3\2\2\2EF\5\f\7\2FG\7\3\2\2GS\3\2\2\2HI\5\20\t\2IJ\7\3\2\2JS\3\2\2\2"+
		"KL\5\22\n\2LM\7\3\2\2MS\3\2\2\2NO\7K\2\2OS\7\3\2\2PQ\7E\2\2QS\7\3\2\2"+
		"R<\3\2\2\2R?\3\2\2\2RB\3\2\2\2RE\3\2\2\2RH\3\2\2\2RK\3\2\2\2RN\3\2\2\2"+
		"RP\3\2\2\2S\5\3\2\2\2TU\7\4\2\2UV\7\5\2\2V\7\3\2\2\2WX\7\6\2\2XY\7\7\2"+
		"\2Yf\7F\2\2Z[\7\b\2\2[\\\7\7\2\2\\f\7F\2\2]^\7\4\2\2^f\7\5\2\2_`\7\t\2"+
		"\2`f\7F\2\2ab\7\4\2\2bf\7\n\2\2cd\7\4\2\2df\7\13\2\2eW\3\2\2\2eZ\3\2\2"+
		"\2e]\3\2\2\2e_\3\2\2\2ea\3\2\2\2ec\3\2\2\2f\t\3\2\2\2gh\7\f\2\2hi\7\r"+
		"\2\2ij\7\16\2\2jk\7H\2\2kl\7\17\2\2lm\7\20\2\2mv\7F\2\2no\7\21\2\2op\7"+
		"\17\2\2pq\7\16\2\2qr\7H\2\2rs\7\r\2\2st\7\20\2\2tv\7F\2\2ug\3\2\2\2un"+
		"\3\2\2\2v\13\3\2\2\2wx\7\6\2\2xy\7\20\2\2yz\7F\2\2z{\7\22\2\2{|\5\24\13"+
		"\2|}\7\23\2\2}\u0096\3\2\2\2~\177\7\b\2\2\177\u0080\7\20\2\2\u0080\u0096"+
		"\7F\2\2\u0081\u0082\7\24\2\2\u0082\u0096\7F\2\2\u0083\u0084\7\25\2\2\u0084"+
		"\u0085\7\26\2\2\u0085\u0086\7F\2\2\u0086\u0087\7\27\2\2\u0087\u0096\5"+
		"\32\16\2\u0088\u0089\7\30\2\2\u0089\u008a\7\r\2\2\u008a\u008b\7F\2\2\u008b"+
		"\u008c\7\31\2\2\u008c\u0096\5 \21\2\u008d\u008e\7\32\2\2\u008e\u008f\7"+
		"F\2\2\u008f\u0090\7\33\2\2\u0090\u0091\5(\25\2\u0091\u0092\7\31\2\2\u0092"+
		"\u0093\5 \21\2\u0093\u0096\3\2\2\2\u0094\u0096\5\16\b\2\u0095w\3\2\2\2"+
		"\u0095~\3\2\2\2\u0095\u0081\3\2\2\2\u0095\u0083\3\2\2\2\u0095\u0088\3"+
		"\2\2\2\u0095\u008d\3\2\2\2\u0095\u0094\3\2\2\2\u0096\r\3\2\2\2\u0097\u0098"+
		"\7\34\2\2\u0098\u0099\5*\26\2\u0099\u009a\7\r\2\2\u009a\u009d\5.\30\2"+
		"\u009b\u009c\7\31\2\2\u009c\u009e\5 \21\2\u009d\u009b\3\2\2\2\u009d\u009e"+
		"\3\2\2\2\u009e\u00a2\3\2\2\2\u009f\u00a0\7\35\2\2\u00a0\u00a1\7\36\2\2"+
		"\u00a1\u00a3\5$\23\2\u00a2\u009f\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00aa"+
		"\3\2\2\2\u00a4\u00a5\7\37\2\2\u00a5\u00a8\7G\2\2\u00a6\u00a7\7 \2\2\u00a7"+
		"\u00a9\7G\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2"+
		"\2\2\u00aa\u00a4\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\17\3\2\2\2\u00ac\u00ad"+
		"\7\6\2\2\u00ad\u00ae\7!\2\2\u00ae\u00af\7F\2\2\u00af\u00b0\7\"\2\2\u00b0"+
		"\u00b1\7F\2\2\u00b1\u00b2\7\22\2\2\u00b2\u00b3\5.\30\2\u00b3\u00b4\7\23"+
		"\2\2\u00b4\u00c9\3\2\2\2\u00b5\u00b6\7\b\2\2\u00b6\u00b7\7!\2\2\u00b7"+
		"\u00c9\7F\2\2\u00b8\u00b9\7#\2\2\u00b9\u00ba\7\20\2\2\u00ba\u00bb\7F\2"+
		"\2\u00bb\u00bc\7$\2\2\u00bc\u00bd\7!\2\2\u00bd\u00be\7F\2\2\u00be\u00bf"+
		"\7\22\2\2\u00bf\u00c0\5.\30\2\u00c0\u00c1\7\23\2\2\u00c1\u00c9\3\2\2\2"+
		"\u00c2\u00c3\7#\2\2\u00c3\u00c4\7\20\2\2\u00c4\u00c5\7F\2\2\u00c5\u00c6"+
		"\7\b\2\2\u00c6\u00c7\7!\2\2\u00c7\u00c9\7F\2\2\u00c8\u00ac\3\2\2\2\u00c8"+
		"\u00b5\3\2\2\2\u00c8\u00b8\3\2\2\2\u00c8\u00c2\3\2\2\2\u00c9\21\3\2\2"+
		"\2\u00ca\u00cb\7#\2\2\u00cb\u00cc\7\20\2\2\u00cc\u00cd\7F\2\2\u00cd\u00ce"+
		"\7$\2\2\u00ce\u0117\5\26\f\2\u00cf\u00d0\7#\2\2\u00d0\u00d1\7\20\2\2\u00d1"+
		"\u00d2\7F\2\2\u00d2\u00d3\7\b\2\2\u00d3\u0117\7F\2\2\u00d4\u00d5\7#\2"+
		"\2\u00d5\u00d6\7\20\2\2\u00d6\u00d7\7F\2\2\u00d7\u00d8\7%\2\2\u00d8\u00d9"+
		"\7F\2\2\u00d9\u0117\5\26\f\2\u00da\u00db\7#\2\2\u00db\u00dc\7\20\2\2\u00dc"+
		"\u00dd\7F\2\2\u00dd\u00de\7&\2\2\u00de\u00df\7\17\2\2\u00df\u0117\7F\2"+
		"\2\u00e0\u00e1\7#\2\2\u00e1\u00e2\7\20\2\2\u00e2\u00e3\7F\2\2\u00e3\u00e4"+
		"\7\b\2\2\u00e4\u00e5\7\'\2\2\u00e5\u00e7\7(\2\2\u00e6\u00e8\7F\2\2\u00e7"+
		"\u00e6\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u0117\3\2\2\2\u00e9\u00ea\7#"+
		"\2\2\u00ea\u00eb\7\20\2\2\u00eb\u00ec\7F\2\2\u00ec\u00ed\7\b\2\2\u00ed"+
		"\u00ee\7)\2\2\u00ee\u00ef\7(\2\2\u00ef\u0117\7F\2\2\u00f0\u00f1\7#\2\2"+
		"\u00f1\u00f2\7\20\2\2\u00f2\u00f3\7F\2\2\u00f3\u00f4\7$\2\2\u00f4\u00f5"+
		"\7*\2\2\u00f5\u00f6\7F\2\2\u00f6\u00f7\7\'\2\2\u00f7\u00f8\7(\2\2\u00f8"+
		"\u00f9\7\22\2\2\u00f9\u00fa\5.\30\2\u00fa\u00fb\7\23\2\2\u00fb\u0117\3"+
		"\2\2\2\u00fc\u00fd\7#\2\2\u00fd\u00fe\7\20\2\2\u00fe\u00ff\7F\2\2\u00ff"+
		"\u0100\7$\2\2\u0100\u0101\7*\2\2\u0101\u0102\7F\2\2\u0102\u0103\7)\2\2"+
		"\u0103\u0104\7(\2\2\u0104\u0105\7\22\2\2\u0105\u0106\5.\30\2\u0106\u0107"+
		"\7\23\2\2\u0107\u0108\7+\2\2\u0108\u0109\7F\2\2\u0109\u010a\7\22\2\2\u010a"+
		"\u010b\5.\30\2\u010b\u010c\7\23\2\2\u010c\u0117\3\2\2\2\u010d\u010e\7"+
		"#\2\2\u010e\u010f\7\20\2\2\u010f\u0110\7F\2\2\u0110\u0111\7$\2\2\u0111"+
		"\u0112\7,\2\2\u0112\u0113\7F\2\2\u0113\u0114\7\22\2\2\u0114\u0115\7F\2"+
		"\2\u0115\u0117\7\23\2\2\u0116\u00ca\3\2\2\2\u0116\u00cf\3\2\2\2\u0116"+
		"\u00d4\3\2\2\2\u0116\u00da\3\2\2\2\u0116\u00e0\3\2\2\2\u0116\u00e9\3\2"+
		"\2\2\u0116\u00f0\3\2\2\2\u0116\u00fc\3\2\2\2\u0116\u010d\3\2\2\2\u0117"+
		"\23\3\2\2\2\u0118\u011d\5\26\f\2\u0119\u011a\7-\2\2\u011a\u011c\5\26\f"+
		"\2\u011b\u0119\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e"+
		"\3\2\2\2\u011e\25\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0121\7F\2\2\u0121"+
		"\u0124\5\30\r\2\u0122\u0123\7.\2\2\u0123\u0125\7E\2\2\u0124\u0122\3\2"+
		"\2\2\u0124\u0125\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0127\7/\2\2\u0127"+
		"\u0129\5\36\20\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u013b\3"+
		"\2\2\2\u012a\u012b\7\'\2\2\u012b\u012c\7(\2\2\u012c\u012d\7\22\2\2\u012d"+
		"\u012e\5.\30\2\u012e\u012f\7\23\2\2\u012f\u013b\3\2\2\2\u0130\u0131\7"+
		")\2\2\u0131\u0132\7(\2\2\u0132\u0133\7\22\2\2\u0133\u0134\7F\2\2\u0134"+
		"\u0135\7\23\2\2\u0135\u0136\7+\2\2\u0136\u0137\7F\2\2\u0137\u0138\7\22"+
		"\2\2\u0138\u0139\7F\2\2\u0139\u013b\7\23\2\2\u013a\u0120\3\2\2\2\u013a"+
		"\u012a\3\2\2\2\u013a\u0130\3\2\2\2\u013b\27\3\2\2\2\u013c\u0144\7\60\2"+
		"\2\u013d\u013e\7\61\2\2\u013e\u013f\7\22\2\2\u013f\u0140\7G\2\2\u0140"+
		"\u0144\7\23\2\2\u0141\u0144\7\62\2\2\u0142\u0144\7\63\2\2\u0143\u013c"+
		"\3\2\2\2\u0143\u013d\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0142\3\2\2\2\u0144"+
		"\31\3\2\2\2\u0145\u014a\5\34\17\2\u0146\u0147\7-\2\2\u0147\u0149\5\34"+
		"\17\2\u0148\u0146\3\2\2\2\u0149\u014c\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\33\3\2\2\2\u014c\u014a\3\2\2\2\u014d\u014e\7\22\2"+
		"\2\u014e\u0153\5\36\20\2\u014f\u0150\7-\2\2\u0150\u0152\5\36\20\2\u0151"+
		"\u014f\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\7\23\2\2\u0157"+
		"\35\3\2\2\2\u0158\u0159\t\2\2\2\u0159\37\3\2\2\2\u015a\u015f\5\"\22\2"+
		"\u015b\u015c\7\64\2\2\u015c\u015e\5\"\22\2\u015d\u015b\3\2\2\2\u015e\u0161"+
		"\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160!\3\2\2\2\u0161"+
		"\u015f\3\2\2\2\u0162\u0163\5$\23\2\u0163\u0164\5\60\31\2\u0164\u0165\5"+
		"&\24\2\u0165\u0182\3\2\2\2\u0166\u0167\5$\23\2\u0167\u0168\5\60\31\2\u0168"+
		"\u0169\7\22\2\2\u0169\u016a\5\16\b\2\u016a\u016b\7\23\2\2\u016b\u0182"+
		"\3\2\2\2\u016c\u016d\5$\23\2\u016d\u016f\7\65\2\2\u016e\u0170\7.\2\2\u016f"+
		"\u016e\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\7E"+
		"\2\2\u0172\u0182\3\2\2\2\u0173\u0174\5$\23\2\u0174\u0175\7\66\2\2\u0175"+
		"\u0176\5\34\17\2\u0176\u0182\3\2\2\2\u0177\u0178\5$\23\2\u0178\u0179\7"+
		"\66\2\2\u0179\u017a\7\22\2\2\u017a\u017b\5\16\b\2\u017b\u017c\7\23\2\2"+
		"\u017c\u0182\3\2\2\2\u017d\u017e\5$\23\2\u017e\u017f\7\67\2\2\u017f\u0180"+
		"\7H\2\2\u0180\u0182\3\2\2\2\u0181\u0162\3\2\2\2\u0181\u0166\3\2\2\2\u0181"+
		"\u016c\3\2\2\2\u0181\u0173\3\2\2\2\u0181\u0177\3\2\2\2\u0181\u017d\3\2"+
		"\2\2\u0182#\3\2\2\2\u0183\u0184\7F\2\2\u0184\u0186\78\2\2\u0185\u0183"+
		"\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0188\7F\2\2\u0188"+
		"%\3\2\2\2\u0189\u018c\5\36\20\2\u018a\u018c\5$\23\2\u018b\u0189\3\2\2"+
		"\2\u018b\u018a\3\2\2\2\u018c\'\3\2\2\2\u018d\u018e\7F\2\2\u018e\u018f"+
		"\7:\2\2\u018f\u0196\5\36\20\2\u0190\u0191\7-\2\2\u0191\u0192\7F\2\2\u0192"+
		"\u0193\7:\2\2\u0193\u0195\5\36\20\2\u0194\u0190\3\2\2\2\u0195\u0198\3"+
		"\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197)\3\2\2\2\u0198\u0196"+
		"\3\2\2\2\u0199\u01a3\79\2\2\u019a\u019f\5,\27\2\u019b\u019c\7-\2\2\u019c"+
		"\u019e\5,\27\2\u019d\u019b\3\2\2\2\u019e\u01a1\3\2\2\2\u019f\u019d\3\2"+
		"\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2"+
		"\u0199\3\2\2\2\u01a2\u019a\3\2\2\2\u01a3+\3\2\2\2\u01a4\u01af\5$\23\2"+
		"\u01a5\u01a6\5\62\32\2\u01a6\u01a7\7\22\2\2\u01a7\u01a8\5$\23\2\u01a8"+
		"\u01a9\7\23\2\2\u01a9\u01af\3\2\2\2\u01aa\u01ab\7@\2\2\u01ab\u01ac\7\22"+
		"\2\2\u01ac\u01ad\79\2\2\u01ad\u01af\7\23\2\2\u01ae\u01a4\3\2\2\2\u01ae"+
		"\u01a5\3\2\2\2\u01ae\u01aa\3\2\2\2\u01af-\3\2\2\2\u01b0\u01b5\7F\2\2\u01b1"+
		"\u01b2\7-\2\2\u01b2\u01b4\7F\2\2\u01b3\u01b1\3\2\2\2\u01b4\u01b7\3\2\2"+
		"\2\u01b5\u01b3\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6/\3\2\2\2\u01b7\u01b5"+
		"\3\2\2\2\u01b8\u01b9\t\3\2\2\u01b9\61\3\2\2\2\u01ba\u01bb\t\4\2\2\u01bb"+
		"\63\3\2\2\2\37\67Reu\u0095\u009d\u00a2\u00a8\u00aa\u00c8\u00e7\u0116\u011d"+
		"\u0124\u0128\u013a\u0143\u014a\u0153\u015f\u016f\u0181\u0185\u018b\u0196"+
		"\u019f\u01a2\u01ae\u01b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}