// Generated from c:\Users\x-eri\Desktop\大四上\course\数据库系统概论\数据库大作业\reference\lius-pydb1\SQL_parser1\SQL_rcy.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQL_rcyParser extends Parser {
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
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, EqualOrAssign=52, 
		Less=53, LessEqual=54, Greater=55, GreaterEqual=56, NotEqual=57, Count=58, 
		Average=59, Max=60, Min=61, Sum=62, Null=63, Identifier=64, Integer=65, 
		String=66, Float=67, Whitespace=68, Annotation=69;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_system_statement = 2, RULE_db_statement = 3, 
		RULE_table_statement = 4, RULE_select_table = 5, RULE_index_statement = 6, 
		RULE_alter_statement = 7, RULE_field_list = 8, RULE_field = 9, RULE_type_ = 10, 
		RULE_value_lists = 11, RULE_value_list = 12, RULE_value = 13, RULE_where_and_clause = 14, 
		RULE_where_clause = 15, RULE_column = 16, RULE_expression = 17, RULE_set_clause = 18, 
		RULE_selectors = 19, RULE_selector = 20, RULE_identifiers = 21, RULE_operator = 22, 
		RULE_aggregator = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "system_statement", "db_statement", "table_statement", 
			"select_table", "index_statement", "alter_statement", "field_list", "field", 
			"type_", "value_lists", "value_list", "value", "where_and_clause", "where_clause", 
			"column", "expression", "set_clause", "selectors", "selector", "identifiers", 
			"operator", "aggregator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'SHOW'", "'DATABASES'", "'CREATE'", "'DATABASE'", "'DROP'", 
			"'USE'", "'TABLES'", "'TABLE'", "'('", "')'", "'DESC'", "'INSERT'", "'INTO'", 
			"'VALUES'", "'DELETE'", "'FROM'", "'WHERE'", "'UPDATE'", "'SET'", "'SELECT'", 
			"'GROUP'", "'BY'", "'LIMIT'", "'OFFSET'", "'INDEX'", "'ON'", "'ALTER'", 
			"'ADD'", "'CHANGE'", "'RENAME'", "'TO'", "'PRIMARY'", "'KEY'", "'FOREIGN'", 
			"'CONSTRAINT'", "'REFERENCES'", "'UNIQUE'", "','", "'NOT'", "'DEFAULT'", 
			"'INT'", "'VARCHAR'", "'DATE'", "'FLOAT'", "'AND'", "'IS'", "'IN'", "'LIKE'", 
			"'.'", "'*'", "'='", "'<'", "'<='", "'>'", "'>='", "'<>'", "'COUNT'", 
			"'AVG'", "'MAX'", "'MIN'", "'SUM'", "'NULL'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "EqualOrAssign", "Less", "LessEqual", "Greater", 
			"GreaterEqual", "NotEqual", "Count", "Average", "Max", "Min", "Sum", 
			"Null", "Identifier", "Integer", "String", "Float", "Whitespace", "Annotation"
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
	public String getGrammarFileName() { return "SQL_rcy.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQL_rcyParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SQL_rcyParser.EOF, 0); }
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
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__6) | (1L << T__11) | (1L << T__12) | (1L << T__15) | (1L << T__18) | (1L << T__20) | (1L << T__27) | (1L << Null))) != 0) || _la==Annotation) {
				{
				{
				setState(48);
				statement();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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
		public Table_statementContext table_statement() {
			return getRuleContext(Table_statementContext.class,0);
		}
		public Index_statementContext index_statement() {
			return getRuleContext(Index_statementContext.class,0);
		}
		public Alter_statementContext alter_statement() {
			return getRuleContext(Alter_statementContext.class,0);
		}
		public TerminalNode Annotation() { return getToken(SQL_rcyParser.Annotation, 0); }
		public TerminalNode Null() { return getToken(SQL_rcyParser.Null, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				system_statement();
				setState(57);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				db_statement();
				setState(60);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				table_statement();
				setState(63);
				match(T__0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				index_statement();
				setState(66);
				match(T__0);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				alter_statement();
				setState(69);
				match(T__0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				match(Annotation);
				setState(72);
				match(T__0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(73);
				match(Null);
				setState(74);
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
			setState(77);
			match(T__1);
			setState(78);
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
	public static class Drop_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Drop_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Show_tablesContext extends Db_statementContext {
		public Show_tablesContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Create_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Use_dbContext extends Db_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Use_dbContext(Db_statementContext ctx) { copyFrom(ctx); }
	}

	public final Db_statementContext db_statement() throws RecognitionException {
		Db_statementContext _localctx = new Db_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_db_statement);
		try {
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new Create_dbContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(T__3);
				setState(81);
				match(T__4);
				setState(82);
				match(Identifier);
				}
				break;
			case T__5:
				_localctx = new Drop_dbContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(T__5);
				setState(84);
				match(T__4);
				setState(85);
				match(Identifier);
				}
				break;
			case T__6:
				_localctx = new Use_dbContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				match(T__6);
				setState(87);
				match(Identifier);
				}
				break;
			case T__1:
				_localctx = new Show_tablesContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(88);
				match(T__1);
				setState(89);
				match(T__7);
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
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Where_and_clauseContext where_and_clause() {
			return getRuleContext(Where_and_clauseContext.class,0);
		}
		public Delete_from_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Insert_into_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Value_listsContext value_lists() {
			return getRuleContext(Value_listsContext.class,0);
		}
		public Insert_into_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Field_listContext field_list() {
			return getRuleContext(Field_listContext.class,0);
		}
		public Create_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Describe_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Describe_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Select_table_Context extends Table_statementContext {
		public Select_tableContext select_table() {
			return getRuleContext(Select_tableContext.class,0);
		}
		public Select_table_Context(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Drop_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Drop_tableContext(Table_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Update_tableContext extends Table_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
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
		enterRule(_localctx, 8, RULE_table_statement);
		try {
			setState(122);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				_localctx = new Create_tableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(T__3);
				setState(93);
				match(T__8);
				setState(94);
				match(Identifier);
				setState(95);
				match(T__9);
				setState(96);
				field_list();
				setState(97);
				match(T__10);
				}
				break;
			case T__5:
				_localctx = new Drop_tableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__5);
				setState(100);
				match(T__8);
				setState(101);
				match(Identifier);
				}
				break;
			case T__11:
				_localctx = new Describe_tableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				match(T__11);
				setState(103);
				match(Identifier);
				}
				break;
			case T__12:
				_localctx = new Insert_into_tableContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(104);
				match(T__12);
				setState(105);
				match(T__13);
				setState(106);
				match(Identifier);
				setState(107);
				match(T__14);
				setState(108);
				value_lists();
				}
				break;
			case T__15:
				_localctx = new Delete_from_tableContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(109);
				match(T__15);
				setState(110);
				match(T__16);
				setState(111);
				match(Identifier);
				setState(112);
				match(T__17);
				setState(113);
				where_and_clause();
				}
				break;
			case T__18:
				_localctx = new Update_tableContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				match(T__18);
				setState(115);
				match(Identifier);
				setState(116);
				match(T__19);
				setState(117);
				set_clause();
				setState(118);
				match(T__17);
				setState(119);
				where_and_clause();
				}
				break;
			case T__20:
				_localctx = new Select_table_Context(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(121);
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
		public List<TerminalNode> Integer() { return getTokens(SQL_rcyParser.Integer); }
		public TerminalNode Integer(int i) {
			return getToken(SQL_rcyParser.Integer, i);
		}
		public Select_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_table; }
	}

	public final Select_tableContext select_table() throws RecognitionException {
		Select_tableContext _localctx = new Select_tableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_select_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__20);
			setState(125);
			selectors();
			setState(126);
			match(T__16);
			setState(127);
			identifiers();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__17) {
				{
				setState(128);
				match(T__17);
				setState(129);
				where_and_clause();
				}
			}

			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__21) {
				{
				setState(132);
				match(T__21);
				setState(133);
				match(T__22);
				setState(134);
				column();
				}
			}

			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(137);
				match(T__23);
				setState(138);
				match(Integer);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__24) {
					{
					setState(139);
					match(T__24);
					setState(140);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_drop_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_add_indexContext extends Index_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Alter_add_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Create_indexContext extends Index_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Create_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Drop_indexContext extends Index_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Drop_indexContext(Index_statementContext ctx) { copyFrom(ctx); }
	}

	public final Index_statementContext index_statement() throws RecognitionException {
		Index_statementContext _localctx = new Index_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_index_statement);
		try {
			setState(173);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new Create_indexContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(T__3);
				setState(146);
				match(T__25);
				setState(147);
				match(Identifier);
				setState(148);
				match(T__26);
				setState(149);
				match(Identifier);
				setState(150);
				match(T__9);
				setState(151);
				identifiers();
				setState(152);
				match(T__10);
				}
				break;
			case 2:
				_localctx = new Drop_indexContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(T__5);
				setState(155);
				match(T__25);
				setState(156);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new Alter_add_indexContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
				match(T__27);
				setState(158);
				match(T__8);
				setState(159);
				match(Identifier);
				setState(160);
				match(T__28);
				setState(161);
				match(T__25);
				setState(162);
				match(Identifier);
				setState(163);
				match(T__9);
				setState(164);
				identifiers();
				setState(165);
				match(T__10);
				}
				break;
			case 4:
				_localctx = new Alter_drop_indexContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				match(T__27);
				setState(168);
				match(T__8);
				setState(169);
				match(Identifier);
				setState(170);
				match(T__5);
				setState(171);
				match(T__25);
				setState(172);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_table_drop_pkContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_add_foreign_keyContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_table_add_uniqueContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_dropContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_table_dropContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_addContext extends Alter_statementContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Alter_table_addContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_changeContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public Alter_table_changeContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_renameContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_table_renameContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_drop_foreign_keyContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Alter_table_drop_foreign_keyContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}
	public static class Alter_table_add_pkContext extends Alter_statementContext {
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public IdentifiersContext identifiers() {
			return getRuleContext(IdentifiersContext.class,0);
		}
		public Alter_table_add_pkContext(Alter_statementContext ctx) { copyFrom(ctx); }
	}

	public final Alter_statementContext alter_statement() throws RecognitionException {
		Alter_statementContext _localctx = new Alter_statementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alter_statement);
		int _la;
		try {
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new Alter_table_addContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				match(T__27);
				setState(176);
				match(T__8);
				setState(177);
				match(Identifier);
				setState(178);
				match(T__28);
				setState(179);
				field();
				}
				break;
			case 2:
				_localctx = new Alter_table_dropContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(T__27);
				setState(181);
				match(T__8);
				setState(182);
				match(Identifier);
				setState(183);
				match(T__5);
				setState(184);
				match(Identifier);
				}
				break;
			case 3:
				_localctx = new Alter_table_changeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
				match(T__27);
				setState(186);
				match(T__8);
				setState(187);
				match(Identifier);
				setState(188);
				match(T__29);
				setState(189);
				match(Identifier);
				setState(190);
				field();
				}
				break;
			case 4:
				_localctx = new Alter_table_renameContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				match(T__27);
				setState(192);
				match(T__8);
				setState(193);
				match(Identifier);
				setState(194);
				match(T__30);
				setState(195);
				match(T__31);
				setState(196);
				match(Identifier);
				}
				break;
			case 5:
				_localctx = new Alter_table_drop_pkContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				match(T__27);
				setState(198);
				match(T__8);
				setState(199);
				match(Identifier);
				setState(200);
				match(T__5);
				setState(201);
				match(T__32);
				setState(202);
				match(T__33);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Identifier) {
					{
					setState(203);
					match(Identifier);
					}
				}

				}
				break;
			case 6:
				_localctx = new Alter_table_drop_foreign_keyContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(206);
				match(T__27);
				setState(207);
				match(T__8);
				setState(208);
				match(Identifier);
				setState(209);
				match(T__5);
				setState(210);
				match(T__34);
				setState(211);
				match(T__33);
				setState(212);
				match(Identifier);
				}
				break;
			case 7:
				_localctx = new Alter_table_add_pkContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(213);
				match(T__27);
				setState(214);
				match(T__8);
				setState(215);
				match(Identifier);
				setState(216);
				match(T__28);
				setState(217);
				match(T__35);
				setState(218);
				match(Identifier);
				setState(219);
				match(T__32);
				setState(220);
				match(T__33);
				setState(221);
				match(T__9);
				setState(222);
				identifiers();
				setState(223);
				match(T__10);
				}
				break;
			case 8:
				_localctx = new Alter_table_add_foreign_keyContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(225);
				match(T__27);
				setState(226);
				match(T__8);
				setState(227);
				match(Identifier);
				setState(228);
				match(T__28);
				setState(229);
				match(T__35);
				setState(230);
				match(Identifier);
				setState(231);
				match(T__34);
				setState(232);
				match(T__33);
				setState(233);
				match(T__9);
				setState(234);
				identifiers();
				setState(235);
				match(T__10);
				setState(236);
				match(T__36);
				setState(237);
				match(Identifier);
				setState(238);
				match(T__9);
				setState(239);
				identifiers();
				setState(240);
				match(T__10);
				}
				break;
			case 9:
				_localctx = new Alter_table_add_uniqueContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(242);
				match(T__27);
				setState(243);
				match(T__8);
				setState(244);
				match(Identifier);
				setState(245);
				match(T__28);
				setState(246);
				match(T__37);
				setState(247);
				match(Identifier);
				setState(248);
				match(T__9);
				setState(249);
				match(Identifier);
				setState(250);
				match(T__10);
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
		enterRule(_localctx, 16, RULE_field_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			field();
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(254);
				match(T__38);
				setState(255);
				field();
				}
				}
				setState(260);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public Foreign_key_fieldContext(FieldContext ctx) { copyFrom(ctx); }
	}
	public static class Normal_fieldContext extends FieldContext {
		public TerminalNode Identifier() { return getToken(SQL_rcyParser.Identifier, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode Null() { return getToken(SQL_rcyParser.Null, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Normal_fieldContext(FieldContext ctx) { copyFrom(ctx); }
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_field);
		int _la;
		try {
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				_localctx = new Normal_fieldContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(Identifier);
				setState(262);
				type_();
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__39) {
					{
					setState(263);
					match(T__39);
					setState(264);
					match(Null);
					}
				}

				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__40) {
					{
					setState(267);
					match(T__40);
					setState(268);
					value();
					}
				}

				}
				break;
			case T__32:
				_localctx = new Primary_key_fieldContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				match(T__32);
				setState(272);
				match(T__33);
				setState(273);
				match(T__9);
				setState(274);
				identifiers();
				setState(275);
				match(T__10);
				}
				break;
			case T__34:
				_localctx = new Foreign_key_fieldContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(277);
				match(T__34);
				setState(278);
				match(T__33);
				setState(279);
				match(T__9);
				setState(280);
				match(Identifier);
				setState(281);
				match(T__10);
				setState(282);
				match(T__36);
				setState(283);
				match(Identifier);
				setState(284);
				match(T__9);
				setState(285);
				match(Identifier);
				setState(286);
				match(T__10);
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
		public TerminalNode Integer() { return getToken(SQL_rcyParser.Integer, 0); }
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 20, RULE_type_);
		try {
			setState(299);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__41:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(T__41);
				setState(290);
				match(T__9);
				setState(291);
				match(Integer);
				setState(292);
				match(T__10);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				match(T__42);
				setState(294);
				match(T__9);
				setState(295);
				match(Integer);
				setState(296);
				match(T__10);
				}
				break;
			case T__43:
				enterOuterAlt(_localctx, 3);
				{
				setState(297);
				match(T__43);
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 4);
				{
				setState(298);
				match(T__44);
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
		enterRule(_localctx, 22, RULE_value_lists);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			value_list();
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(302);
				match(T__38);
				setState(303);
				value_list();
				}
				}
				setState(308);
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
		enterRule(_localctx, 24, RULE_value_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(T__9);
			setState(310);
			value();
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(311);
				match(T__38);
				setState(312);
				value();
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(318);
			match(T__10);
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
		public TerminalNode Integer() { return getToken(SQL_rcyParser.Integer, 0); }
		public TerminalNode String() { return getToken(SQL_rcyParser.String, 0); }
		public TerminalNode Float() { return getToken(SQL_rcyParser.Float, 0); }
		public TerminalNode Null() { return getToken(SQL_rcyParser.Null, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_la = _input.LA(1);
			if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (Null - 63)) | (1L << (Integer - 63)) | (1L << (String - 63)) | (1L << (Float - 63)))) != 0)) ) {
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
		enterRule(_localctx, 28, RULE_where_and_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			where_clause();
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__45) {
				{
				{
				setState(323);
				match(T__45);
				setState(324);
				where_clause();
				}
				}
				setState(329);
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
		public TerminalNode Null() { return getToken(SQL_rcyParser.Null, 0); }
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
		public TerminalNode String() { return getToken(SQL_rcyParser.String, 0); }
		public Where_like_stringContext(Where_clauseContext ctx) { copyFrom(ctx); }
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_where_clause);
		int _la;
		try {
			setState(361);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				_localctx = new Where_operator_expressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				column();
				setState(331);
				operator();
				setState(332);
				expression();
				}
				break;
			case 2:
				_localctx = new Where_operator_selectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(334);
				column();
				setState(335);
				operator();
				setState(336);
				match(T__9);
				setState(337);
				select_table();
				setState(338);
				match(T__10);
				}
				break;
			case 3:
				_localctx = new Where_nullContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				column();
				setState(341);
				match(T__46);
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__39) {
					{
					setState(342);
					match(T__39);
					}
				}

				setState(345);
				match(Null);
				}
				break;
			case 4:
				_localctx = new Where_in_listContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				column();
				setState(348);
				match(T__47);
				setState(349);
				value_list();
				}
				break;
			case 5:
				_localctx = new Where_in_selectContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(351);
				column();
				setState(352);
				match(T__47);
				setState(353);
				match(T__9);
				setState(354);
				select_table();
				setState(355);
				match(T__10);
				}
				break;
			case 6:
				_localctx = new Where_like_stringContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(357);
				column();
				setState(358);
				match(T__48);
				setState(359);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public ColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column; }
	}

	public final ColumnContext column() throws RecognitionException {
		ColumnContext _localctx = new ColumnContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_column);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(363);
				match(Identifier);
				setState(364);
				match(T__49);
				}
				break;
			}
			setState(367);
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
		enterRule(_localctx, 34, RULE_expression);
		try {
			setState(371);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Null:
			case Integer:
			case String:
			case Float:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				value();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public List<TerminalNode> EqualOrAssign() { return getTokens(SQL_rcyParser.EqualOrAssign); }
		public TerminalNode EqualOrAssign(int i) {
			return getToken(SQL_rcyParser.EqualOrAssign, i);
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
		enterRule(_localctx, 36, RULE_set_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(373);
			match(Identifier);
			setState(374);
			match(EqualOrAssign);
			setState(375);
			value();
			setState(382);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(376);
				match(T__38);
				setState(377);
				match(Identifier);
				setState(378);
				match(EqualOrAssign);
				setState(379);
				value();
				}
				}
				setState(384);
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
		enterRule(_localctx, 38, RULE_selectors);
		int _la;
		try {
			setState(394);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__50:
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(T__50);
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
				setState(386);
				selector();
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__38) {
					{
					{
					setState(387);
					match(T__38);
					setState(388);
					selector();
					}
					}
					setState(393);
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
		public TerminalNode Count() { return getToken(SQL_rcyParser.Count, 0); }
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_selector);
		try {
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(396);
				column();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(397);
				aggregator();
				setState(398);
				match(T__9);
				setState(399);
				column();
				setState(400);
				match(T__10);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(402);
				match(Count);
				setState(403);
				match(T__9);
				setState(404);
				match(T__50);
				setState(405);
				match(T__10);
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
		public List<TerminalNode> Identifier() { return getTokens(SQL_rcyParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(SQL_rcyParser.Identifier, i);
		}
		public IdentifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiers; }
	}

	public final IdentifiersContext identifiers() throws RecognitionException {
		IdentifiersContext _localctx = new IdentifiersContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_identifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(Identifier);
			setState(413);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(409);
				match(T__38);
				setState(410);
				match(Identifier);
				}
				}
				setState(415);
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
		public TerminalNode EqualOrAssign() { return getToken(SQL_rcyParser.EqualOrAssign, 0); }
		public TerminalNode Less() { return getToken(SQL_rcyParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(SQL_rcyParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(SQL_rcyParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(SQL_rcyParser.GreaterEqual, 0); }
		public TerminalNode NotEqual() { return getToken(SQL_rcyParser.NotEqual, 0); }
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
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
		public TerminalNode Count() { return getToken(SQL_rcyParser.Count, 0); }
		public TerminalNode Average() { return getToken(SQL_rcyParser.Average, 0); }
		public TerminalNode Max() { return getToken(SQL_rcyParser.Max, 0); }
		public TerminalNode Min() { return getToken(SQL_rcyParser.Min, 0); }
		public TerminalNode Sum() { return getToken(SQL_rcyParser.Sum, 0); }
		public AggregatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator; }
	}

	public final AggregatorContext aggregator() throws RecognitionException {
		AggregatorContext _localctx = new AggregatorContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_aggregator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Count) | (1L << Average) | (1L << Max) | (1L << Min) | (1L << Sum))) != 0)) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3G\u01a7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3N\n\3\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5]\n\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6}\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0085"+
		"\n\7\3\7\3\7\3\7\5\7\u008a\n\7\3\7\3\7\3\7\3\7\5\7\u0090\n\7\5\7\u0092"+
		"\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00b0\n\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00cf\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u00fe\n\t\3\n\3\n\3\n\7\n\u0103\n\n\f\n\16\n"+
		"\u0106\13\n\3\13\3\13\3\13\3\13\5\13\u010c\n\13\3\13\3\13\5\13\u0110\n"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\5\13\u0122\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u012e\n\f\3\r\3\r\3\r\7\r\u0133\n\r\f\r\16\r\u0136\13\r\3\16\3\16"+
		"\3\16\3\16\7\16\u013c\n\16\f\16\16\16\u013f\13\16\3\16\3\16\3\17\3\17"+
		"\3\20\3\20\3\20\7\20\u0148\n\20\f\20\16\20\u014b\13\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u015a\n\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\5\21\u016c\n\21\3\22\3\22\5\22\u0170\n\22\3\22\3\22\3\23\3\23\5"+
		"\23\u0176\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u017f\n\24\f\24"+
		"\16\24\u0182\13\24\3\25\3\25\3\25\3\25\7\25\u0188\n\25\f\25\16\25\u018b"+
		"\13\25\5\25\u018d\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\5\26\u0199\n\26\3\27\3\27\3\27\7\27\u019e\n\27\f\27\16\27\u01a1\13"+
		"\27\3\30\3\30\3\31\3\31\3\31\2\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\2\5\4\2AACE\3\2\66;\3\2<@\2\u01c7\2\65\3\2\2\2\4M\3\2"+
		"\2\2\6O\3\2\2\2\b\\\3\2\2\2\n|\3\2\2\2\f~\3\2\2\2\16\u00af\3\2\2\2\20"+
		"\u00fd\3\2\2\2\22\u00ff\3\2\2\2\24\u0121\3\2\2\2\26\u012d\3\2\2\2\30\u012f"+
		"\3\2\2\2\32\u0137\3\2\2\2\34\u0142\3\2\2\2\36\u0144\3\2\2\2 \u016b\3\2"+
		"\2\2\"\u016f\3\2\2\2$\u0175\3\2\2\2&\u0177\3\2\2\2(\u018c\3\2\2\2*\u0198"+
		"\3\2\2\2,\u019a\3\2\2\2.\u01a2\3\2\2\2\60\u01a4\3\2\2\2\62\64\5\4\3\2"+
		"\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67"+
		"\65\3\2\2\289\7\2\2\39\3\3\2\2\2:;\5\6\4\2;<\7\3\2\2<N\3\2\2\2=>\5\b\5"+
		"\2>?\7\3\2\2?N\3\2\2\2@A\5\n\6\2AB\7\3\2\2BN\3\2\2\2CD\5\16\b\2DE\7\3"+
		"\2\2EN\3\2\2\2FG\5\20\t\2GH\7\3\2\2HN\3\2\2\2IJ\7G\2\2JN\7\3\2\2KL\7A"+
		"\2\2LN\7\3\2\2M:\3\2\2\2M=\3\2\2\2M@\3\2\2\2MC\3\2\2\2MF\3\2\2\2MI\3\2"+
		"\2\2MK\3\2\2\2N\5\3\2\2\2OP\7\4\2\2PQ\7\5\2\2Q\7\3\2\2\2RS\7\6\2\2ST\7"+
		"\7\2\2T]\7B\2\2UV\7\b\2\2VW\7\7\2\2W]\7B\2\2XY\7\t\2\2Y]\7B\2\2Z[\7\4"+
		"\2\2[]\7\n\2\2\\R\3\2\2\2\\U\3\2\2\2\\X\3\2\2\2\\Z\3\2\2\2]\t\3\2\2\2"+
		"^_\7\6\2\2_`\7\13\2\2`a\7B\2\2ab\7\f\2\2bc\5\22\n\2cd\7\r\2\2d}\3\2\2"+
		"\2ef\7\b\2\2fg\7\13\2\2g}\7B\2\2hi\7\16\2\2i}\7B\2\2jk\7\17\2\2kl\7\20"+
		"\2\2lm\7B\2\2mn\7\21\2\2n}\5\30\r\2op\7\22\2\2pq\7\23\2\2qr\7B\2\2rs\7"+
		"\24\2\2s}\5\36\20\2tu\7\25\2\2uv\7B\2\2vw\7\26\2\2wx\5&\24\2xy\7\24\2"+
		"\2yz\5\36\20\2z}\3\2\2\2{}\5\f\7\2|^\3\2\2\2|e\3\2\2\2|h\3\2\2\2|j\3\2"+
		"\2\2|o\3\2\2\2|t\3\2\2\2|{\3\2\2\2}\13\3\2\2\2~\177\7\27\2\2\177\u0080"+
		"\5(\25\2\u0080\u0081\7\23\2\2\u0081\u0084\5,\27\2\u0082\u0083\7\24\2\2"+
		"\u0083\u0085\5\36\20\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0089"+
		"\3\2\2\2\u0086\u0087\7\30\2\2\u0087\u0088\7\31\2\2\u0088\u008a\5\"\22"+
		"\2\u0089\u0086\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0091\3\2\2\2\u008b\u008c"+
		"\7\32\2\2\u008c\u008f\7C\2\2\u008d\u008e\7\33\2\2\u008e\u0090\7C\2\2\u008f"+
		"\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008b\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\r\3\2\2\2\u0093\u0094\7\6\2\2\u0094\u0095"+
		"\7\34\2\2\u0095\u0096\7B\2\2\u0096\u0097\7\35\2\2\u0097\u0098\7B\2\2\u0098"+
		"\u0099\7\f\2\2\u0099\u009a\5,\27\2\u009a\u009b\7\r\2\2\u009b\u00b0\3\2"+
		"\2\2\u009c\u009d\7\b\2\2\u009d\u009e\7\34\2\2\u009e\u00b0\7B\2\2\u009f"+
		"\u00a0\7\36\2\2\u00a0\u00a1\7\13\2\2\u00a1\u00a2\7B\2\2\u00a2\u00a3\7"+
		"\37\2\2\u00a3\u00a4\7\34\2\2\u00a4\u00a5\7B\2\2\u00a5\u00a6\7\f\2\2\u00a6"+
		"\u00a7\5,\27\2\u00a7\u00a8\7\r\2\2\u00a8\u00b0\3\2\2\2\u00a9\u00aa\7\36"+
		"\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ac\7B\2\2\u00ac\u00ad\7\b\2\2\u00ad"+
		"\u00ae\7\34\2\2\u00ae\u00b0\7B\2\2\u00af\u0093\3\2\2\2\u00af\u009c\3\2"+
		"\2\2\u00af\u009f\3\2\2\2\u00af\u00a9\3\2\2\2\u00b0\17\3\2\2\2\u00b1\u00b2"+
		"\7\36\2\2\u00b2\u00b3\7\13\2\2\u00b3\u00b4\7B\2\2\u00b4\u00b5\7\37\2\2"+
		"\u00b5\u00fe\5\24\13\2\u00b6\u00b7\7\36\2\2\u00b7\u00b8\7\13\2\2\u00b8"+
		"\u00b9\7B\2\2\u00b9\u00ba\7\b\2\2\u00ba\u00fe\7B\2\2\u00bb\u00bc\7\36"+
		"\2\2\u00bc\u00bd\7\13\2\2\u00bd\u00be\7B\2\2\u00be\u00bf\7 \2\2\u00bf"+
		"\u00c0\7B\2\2\u00c0\u00fe\5\24\13\2\u00c1\u00c2\7\36\2\2\u00c2\u00c3\7"+
		"\13\2\2\u00c3\u00c4\7B\2\2\u00c4\u00c5\7!\2\2\u00c5\u00c6\7\"\2\2\u00c6"+
		"\u00fe\7B\2\2\u00c7\u00c8\7\36\2\2\u00c8\u00c9\7\13\2\2\u00c9\u00ca\7"+
		"B\2\2\u00ca\u00cb\7\b\2\2\u00cb\u00cc\7#\2\2\u00cc\u00ce\7$\2\2\u00cd"+
		"\u00cf\7B\2\2\u00ce\u00cd\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00fe\3\2"+
		"\2\2\u00d0\u00d1\7\36\2\2\u00d1\u00d2\7\13\2\2\u00d2\u00d3\7B\2\2\u00d3"+
		"\u00d4\7\b\2\2\u00d4\u00d5\7%\2\2\u00d5\u00d6\7$\2\2\u00d6\u00fe\7B\2"+
		"\2\u00d7\u00d8\7\36\2\2\u00d8\u00d9\7\13\2\2\u00d9\u00da\7B\2\2\u00da"+
		"\u00db\7\37\2\2\u00db\u00dc\7&\2\2\u00dc\u00dd\7B\2\2\u00dd\u00de\7#\2"+
		"\2\u00de\u00df\7$\2\2\u00df\u00e0\7\f\2\2\u00e0\u00e1\5,\27\2\u00e1\u00e2"+
		"\7\r\2\2\u00e2\u00fe\3\2\2\2\u00e3\u00e4\7\36\2\2\u00e4\u00e5\7\13\2\2"+
		"\u00e5\u00e6\7B\2\2\u00e6\u00e7\7\37\2\2\u00e7\u00e8\7&\2\2\u00e8\u00e9"+
		"\7B\2\2\u00e9\u00ea\7%\2\2\u00ea\u00eb\7$\2\2\u00eb\u00ec\7\f\2\2\u00ec"+
		"\u00ed\5,\27\2\u00ed\u00ee\7\r\2\2\u00ee\u00ef\7\'\2\2\u00ef\u00f0\7B"+
		"\2\2\u00f0\u00f1\7\f\2\2\u00f1\u00f2\5,\27\2\u00f2\u00f3\7\r\2\2\u00f3"+
		"\u00fe\3\2\2\2\u00f4\u00f5\7\36\2\2\u00f5\u00f6\7\13\2\2\u00f6\u00f7\7"+
		"B\2\2\u00f7\u00f8\7\37\2\2\u00f8\u00f9\7(\2\2\u00f9\u00fa\7B\2\2\u00fa"+
		"\u00fb\7\f\2\2\u00fb\u00fc\7B\2\2\u00fc\u00fe\7\r\2\2\u00fd\u00b1\3\2"+
		"\2\2\u00fd\u00b6\3\2\2\2\u00fd\u00bb\3\2\2\2\u00fd\u00c1\3\2\2\2\u00fd"+
		"\u00c7\3\2\2\2\u00fd\u00d0\3\2\2\2\u00fd\u00d7\3\2\2\2\u00fd\u00e3\3\2"+
		"\2\2\u00fd\u00f4\3\2\2\2\u00fe\21\3\2\2\2\u00ff\u0104\5\24\13\2\u0100"+
		"\u0101\7)\2\2\u0101\u0103\5\24\13\2\u0102\u0100\3\2\2\2\u0103\u0106\3"+
		"\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\23\3\2\2\2\u0106"+
		"\u0104\3\2\2\2\u0107\u0108\7B\2\2\u0108\u010b\5\26\f\2\u0109\u010a\7*"+
		"\2\2\u010a\u010c\7A\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010f\3\2\2\2\u010d\u010e\7+\2\2\u010e\u0110\5\34\17\2\u010f\u010d\3"+
		"\2\2\2\u010f\u0110\3\2\2\2\u0110\u0122\3\2\2\2\u0111\u0112\7#\2\2\u0112"+
		"\u0113\7$\2\2\u0113\u0114\7\f\2\2\u0114\u0115\5,\27\2\u0115\u0116\7\r"+
		"\2\2\u0116\u0122\3\2\2\2\u0117\u0118\7%\2\2\u0118\u0119\7$\2\2\u0119\u011a"+
		"\7\f\2\2\u011a\u011b\7B\2\2\u011b\u011c\7\r\2\2\u011c\u011d\7\'\2\2\u011d"+
		"\u011e\7B\2\2\u011e\u011f\7\f\2\2\u011f\u0120\7B\2\2\u0120\u0122\7\r\2"+
		"\2\u0121\u0107\3\2\2\2\u0121\u0111\3\2\2\2\u0121\u0117\3\2\2\2\u0122\25"+
		"\3\2\2\2\u0123\u0124\7,\2\2\u0124\u0125\7\f\2\2\u0125\u0126\7C\2\2\u0126"+
		"\u012e\7\r\2\2\u0127\u0128\7-\2\2\u0128\u0129\7\f\2\2\u0129\u012a\7C\2"+
		"\2\u012a\u012e\7\r\2\2\u012b\u012e\7.\2\2\u012c\u012e\7/\2\2\u012d\u0123"+
		"\3\2\2\2\u012d\u0127\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e"+
		"\27\3\2\2\2\u012f\u0134\5\32\16\2\u0130\u0131\7)\2\2\u0131\u0133\5\32"+
		"\16\2\u0132\u0130\3\2\2\2\u0133\u0136\3\2\2\2\u0134\u0132\3\2\2\2\u0134"+
		"\u0135\3\2\2\2\u0135\31\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0138\7\f\2"+
		"\2\u0138\u013d\5\34\17\2\u0139\u013a\7)\2\2\u013a\u013c\5\34\17\2\u013b"+
		"\u0139\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013e\u0140\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0141\7\r\2\2\u0141"+
		"\33\3\2\2\2\u0142\u0143\t\2\2\2\u0143\35\3\2\2\2\u0144\u0149\5 \21\2\u0145"+
		"\u0146\7\60\2\2\u0146\u0148\5 \21\2\u0147\u0145\3\2\2\2\u0148\u014b\3"+
		"\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\37\3\2\2\2\u014b"+
		"\u0149\3\2\2\2\u014c\u014d\5\"\22\2\u014d\u014e\5.\30\2\u014e\u014f\5"+
		"$\23\2\u014f\u016c\3\2\2\2\u0150\u0151\5\"\22\2\u0151\u0152\5.\30\2\u0152"+
		"\u0153\7\f\2\2\u0153\u0154\5\f\7\2\u0154\u0155\7\r\2\2\u0155\u016c\3\2"+
		"\2\2\u0156\u0157\5\"\22\2\u0157\u0159\7\61\2\2\u0158\u015a\7*\2\2\u0159"+
		"\u0158\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015c\7A"+
		"\2\2\u015c\u016c\3\2\2\2\u015d\u015e\5\"\22\2\u015e\u015f\7\62\2\2\u015f"+
		"\u0160\5\32\16\2\u0160\u016c\3\2\2\2\u0161\u0162\5\"\22\2\u0162\u0163"+
		"\7\62\2\2\u0163\u0164\7\f\2\2\u0164\u0165\5\f\7\2\u0165\u0166\7\r\2\2"+
		"\u0166\u016c\3\2\2\2\u0167\u0168\5\"\22\2\u0168\u0169\7\63\2\2\u0169\u016a"+
		"\7D\2\2\u016a\u016c\3\2\2\2\u016b\u014c\3\2\2\2\u016b\u0150\3\2\2\2\u016b"+
		"\u0156\3\2\2\2\u016b\u015d\3\2\2\2\u016b\u0161\3\2\2\2\u016b\u0167\3\2"+
		"\2\2\u016c!\3\2\2\2\u016d\u016e\7B\2\2\u016e\u0170\7\64\2\2\u016f\u016d"+
		"\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172\7B\2\2\u0172"+
		"#\3\2\2\2\u0173\u0176\5\34\17\2\u0174\u0176\5\"\22\2\u0175\u0173\3\2\2"+
		"\2\u0175\u0174\3\2\2\2\u0176%\3\2\2\2\u0177\u0178\7B\2\2\u0178\u0179\7"+
		"\66\2\2\u0179\u0180\5\34\17\2\u017a\u017b\7)\2\2\u017b\u017c\7B\2\2\u017c"+
		"\u017d\7\66\2\2\u017d\u017f\5\34\17\2\u017e\u017a\3\2\2\2\u017f\u0182"+
		"\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181\'\3\2\2\2\u0182"+
		"\u0180\3\2\2\2\u0183\u018d\7\65\2\2\u0184\u0189\5*\26\2\u0185\u0186\7"+
		")\2\2\u0186\u0188\5*\26\2\u0187\u0185\3\2\2\2\u0188\u018b\3\2\2\2\u0189"+
		"\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2"+
		"\2\2\u018c\u0183\3\2\2\2\u018c\u0184\3\2\2\2\u018d)\3\2\2\2\u018e\u0199"+
		"\5\"\22\2\u018f\u0190\5\60\31\2\u0190\u0191\7\f\2\2\u0191\u0192\5\"\22"+
		"\2\u0192\u0193\7\r\2\2\u0193\u0199\3\2\2\2\u0194\u0195\7<\2\2\u0195\u0196"+
		"\7\f\2\2\u0196\u0197\7\65\2\2\u0197\u0199\7\r\2\2\u0198\u018e\3\2\2\2"+
		"\u0198\u018f\3\2\2\2\u0198\u0194\3\2\2\2\u0199+\3\2\2\2\u019a\u019f\7"+
		"B\2\2\u019b\u019c\7)\2\2\u019c\u019e\7B\2\2\u019d\u019b\3\2\2\2\u019e"+
		"\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0-\3\2\2\2"+
		"\u01a1\u019f\3\2\2\2\u01a2\u01a3\t\3\2\2\u01a3/\3\2\2\2\u01a4\u01a5\t"+
		"\4\2\2\u01a5\61\3\2\2\2\36\65M\\|\u0084\u0089\u008f\u0091\u00af\u00ce"+
		"\u00fd\u0104\u010b\u010f\u0121\u012d\u0134\u013d\u0149\u0159\u016b\u016f"+
		"\u0175\u0180\u0189\u018c\u0198\u019f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}