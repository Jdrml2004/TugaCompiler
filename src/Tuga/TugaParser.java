// Generated from C:/Users/limaj/OneDrive - Universidade do Algarve/uni/docs/uni/3ano/2_semestre/CPL/Trabalho_3/Tuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TugaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, INT=33, DOUBLE=34, STRING=35, BOOLEAN=36, IDENT=37, WS=38, SL_COMMENT=39, 
		ML_COMMENT=40;
	public static final int
		RULE_prog = 0, RULE_functionDecl = 1, RULE_param = 2, RULE_declaration = 3, 
		RULE_identList = 4, RULE_type = 5, RULE_stat = 6, RULE_block = 7, RULE_expr = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "functionDecl", "param", "declaration", "identList", "type", 
			"stat", "block", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'funcao'", "'('", "','", "')'", "':'", "';'", "'inteiro'", "'real'", 
			"'booleano'", "'string'", "'escreve'", "'<-'", "'retorna'", "'enquanto'", 
			"'se'", "'senao'", "'inicio'", "'fim'", "'-'", "'nao'", "'*'", "'/'", 
			"'%'", "'+'", "'<'", "'>'", "'<='", "'>='", "'igual'", "'diferente'", 
			"'e'", "'ou'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "INT", "DOUBLE", 
			"STRING", "BOOLEAN", "IDENT", "WS", "SL_COMMENT", "ML_COMMENT"
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
	public String getGrammarFileName() { return "Tuga.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TugaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TugaParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<FunctionDeclContext> functionDecl() {
			return getRuleContexts(FunctionDeclContext.class);
		}
		public FunctionDeclContext functionDecl(int i) {
			return getRuleContext(FunctionDeclContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(18);
				declaration();
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(24);
				functionDecl();
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(29);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFunctionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFunctionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFunctionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclContext functionDecl() throws RecognitionException {
		FunctionDeclContext _localctx = new FunctionDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_functionDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(T__0);
			setState(32);
			match(IDENT);
			setState(33);
			match(T__1);
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENT) {
				{
				setState(34);
				param();
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(35);
					match(T__2);
					setState(36);
					param();
					}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(44);
			match(T__3);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(45);
				match(T__4);
				setState(46);
				type();
				}
			}

			setState(49);
			block();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(IDENT);
			setState(52);
			match(T__4);
			setState(53);
			type();
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

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public IdentListContext identList() {
			return getRuleContext(IdentListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			identList();
			setState(56);
			match(T__4);
			setState(57);
			type();
			setState(58);
			match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdentListContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(TugaParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(TugaParser.IDENT, i);
		}
		public IdentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterIdentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitIdentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitIdentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentListContext identList() throws RecognitionException {
		IdentListContext _localctx = new IdentListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_identList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(IDENT);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(61);
				match(T__2);
				setState(62);
				match(IDENT);
				}
				}
				setState(67);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitReturnStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatContext extends StatContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitAssignStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockStatContext extends StatContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public IfStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterIfStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitIfStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitIfStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatContext stat() {
			return getRuleContext(StatContext.class,0);
		}
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WriteStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public WriteStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterWriteStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitWriteStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitWriteStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallStatContext extends StatContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterCallStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitCallStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitCallStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStatContext extends StatContext {
		public EmptyStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterEmptyStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitEmptyStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitEmptyStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stat);
		int _la;
		try {
			setState(115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new WriteStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__10);
				setState(71);
				expr(0);
				setState(72);
				match(T__5);
				}
				break;
			case 2:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(IDENT);
				setState(75);
				match(T__1);
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 266289545220L) != 0)) {
					{
					setState(76);
					expr(0);
					setState(81);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(77);
						match(T__2);
						setState(78);
						expr(0);
						}
						}
						setState(83);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(86);
				match(T__3);
				setState(87);
				match(T__5);
				}
				break;
			case 3:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(88);
				match(IDENT);
				setState(89);
				match(T__11);
				setState(90);
				expr(0);
				setState(91);
				match(T__5);
				}
				break;
			case 4:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				match(T__12);
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 266289545220L) != 0)) {
					{
					setState(94);
					expr(0);
					}
				}

				setState(97);
				match(T__5);
				}
				break;
			case 5:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(98);
				match(T__13);
				setState(99);
				match(T__1);
				setState(100);
				expr(0);
				setState(101);
				match(T__3);
				setState(102);
				stat();
				}
				break;
			case 6:
				_localctx = new IfStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(104);
				match(T__14);
				setState(105);
				match(T__1);
				setState(106);
				expr(0);
				setState(107);
				match(T__3);
				setState(108);
				stat();
				setState(111);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(109);
					match(T__15);
					setState(110);
					stat();
					}
					break;
				}
				}
				break;
			case 7:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(113);
				block();
				}
				break;
			case 8:
				_localctx = new EmptyStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(114);
				match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(T__16);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 137439144000L) != 0)) {
				{
				setState(120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(118);
					declaration();
					}
					break;
				case 2:
					{
					setState(119);
					stat();
					}
					break;
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(125);
			match(T__17);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringExprContext extends ExprContext {
		public TerminalNode STRING() { return getToken(TugaParser.STRING, 0); }
		public StringExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterStringExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitStringExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitStringExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentExprContext extends ExprContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public IdentExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterIdentExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitIdentExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitIdentExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationalExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelationalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleExprContext extends ExprContext {
		public TerminalNode DOUBLE() { return getToken(TugaParser.DOUBLE, 0); }
		public DoubleExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterDoubleExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitDoubleExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitDoubleExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExprContext extends ExprContext {
		public TerminalNode IDENT() { return getToken(TugaParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionCallExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterFunctionCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitFunctionCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitFunctionCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterMulDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitMulDivExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitMulDivExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterParensExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitParensExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitParensExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntExprContext extends ExprContext {
		public TerminalNode INT() { return getToken(TugaParser.INT, 0); }
		public IntExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterIntExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitIntExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitIntExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterAddSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitAddSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitAddSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BooleanExprContext extends ExprContext {
		public TerminalNode BOOLEAN() { return getToken(TugaParser.BOOLEAN, 0); }
		public BooleanExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).enterBooleanExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TugaListener ) ((TugaListener)listener).exitBooleanExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TugaVisitor ) return ((TugaVisitor<? extends T>)visitor).visitBooleanExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new ParensExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(128);
				match(T__1);
				setState(129);
				expr(0);
				setState(130);
				match(T__3);
				}
				break;
			case 2:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__18 || _la==T__19) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(133);
				expr(13);
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(IDENT);
				setState(135);
				match(T__1);
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 266289545220L) != 0)) {
					{
					setState(136);
					expr(0);
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(137);
						match(T__2);
						setState(138);
						expr(0);
						}
						}
						setState(143);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(146);
				match(T__3);
				}
				break;
			case 4:
				{
				_localctx = new IdentExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				match(IDENT);
				}
				break;
			case 5:
				{
				_localctx = new IntExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(148);
				match(INT);
				}
				break;
			case 6:
				{
				_localctx = new DoubleExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				match(DOUBLE);
				}
				break;
			case 7:
				{
				_localctx = new StringExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(150);
				match(STRING);
				}
				break;
			case 8:
				{
				_localctx = new BooleanExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151);
				match(BOOLEAN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(172);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(155);
						((MulDivExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 14680064L) != 0)) ) {
							((MulDivExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(156);
						expr(13);
						}
						break;
					case 2:
						{
						_localctx = new AddSubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(158);
						((AddSubExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__18 || _la==T__23) ) {
							((AddSubExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(159);
						expr(12);
						}
						break;
					case 3:
						{
						_localctx = new RelationalExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(160);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(161);
						((RelationalExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 503316480L) != 0)) ) {
							((RelationalExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						expr(11);
						}
						break;
					case 4:
						{
						_localctx = new EqualityExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(163);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(164);
						((EqualityExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__28 || _la==T__29) ) {
							((EqualityExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(165);
						expr(10);
						}
						break;
					case 5:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(166);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(167);
						match(T__30);
						setState(168);
						expr(9);
						}
						break;
					case 6:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(169);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(170);
						match(T__31);
						setState(171);
						expr(8);
						}
						break;
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001(\u00b2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0001\u0000\u0005\u0000\u0014\b\u0000\n\u0000\f\u0000\u0017"+
		"\t\u0000\u0001\u0000\u0004\u0000\u001a\b\u0000\u000b\u0000\f\u0000\u001b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0005\u0001&\b\u0001\n\u0001\f\u0001)\t\u0001"+
		"\u0003\u0001+\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"0\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004@\b\u0004\n\u0004\f\u0004"+
		"C\t\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006P\b\u0006\n\u0006\f\u0006S\t\u0006\u0003\u0006U\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006`\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006p\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006t\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007y\b\u0007\n\u0007\f\u0007"+
		"|\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u008c"+
		"\b\b\n\b\f\b\u008f\t\b\u0003\b\u0091\b\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u0099\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u00ad\b\b\n\b\f\b\u00b0\t\b"+
		"\u0001\b\u0000\u0001\u0010\t\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0000\u0006\u0001\u0000\u0007\n\u0001\u0000\u0013\u0014\u0001\u0000\u0015"+
		"\u0017\u0002\u0000\u0013\u0013\u0018\u0018\u0001\u0000\u0019\u001c\u0001"+
		"\u0000\u001d\u001e\u00ca\u0000\u0015\u0001\u0000\u0000\u0000\u0002\u001f"+
		"\u0001\u0000\u0000\u0000\u00043\u0001\u0000\u0000\u0000\u00067\u0001\u0000"+
		"\u0000\u0000\b<\u0001\u0000\u0000\u0000\nD\u0001\u0000\u0000\u0000\fs"+
		"\u0001\u0000\u0000\u0000\u000eu\u0001\u0000\u0000\u0000\u0010\u0098\u0001"+
		"\u0000\u0000\u0000\u0012\u0014\u0003\u0006\u0003\u0000\u0013\u0012\u0001"+
		"\u0000\u0000\u0000\u0014\u0017\u0001\u0000\u0000\u0000\u0015\u0013\u0001"+
		"\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0019\u0001"+
		"\u0000\u0000\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0018\u001a\u0003"+
		"\u0002\u0001\u0000\u0019\u0018\u0001\u0000\u0000\u0000\u001a\u001b\u0001"+
		"\u0000\u0000\u0000\u001b\u0019\u0001\u0000\u0000\u0000\u001b\u001c\u0001"+
		"\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d\u001e\u0005"+
		"\u0000\u0000\u0001\u001e\u0001\u0001\u0000\u0000\u0000\u001f \u0005\u0001"+
		"\u0000\u0000 !\u0005%\u0000\u0000!*\u0005\u0002\u0000\u0000\"\'\u0003"+
		"\u0004\u0002\u0000#$\u0005\u0003\u0000\u0000$&\u0003\u0004\u0002\u0000"+
		"%#\u0001\u0000\u0000\u0000&)\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000"+
		"\u0000\'(\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000"+
		"\u0000\u0000*\"\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+,\u0001"+
		"\u0000\u0000\u0000,/\u0005\u0004\u0000\u0000-.\u0005\u0005\u0000\u0000"+
		".0\u0003\n\u0005\u0000/-\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000012\u0003\u000e\u0007\u00002\u0003\u0001\u0000"+
		"\u0000\u000034\u0005%\u0000\u000045\u0005\u0005\u0000\u000056\u0003\n"+
		"\u0005\u00006\u0005\u0001\u0000\u0000\u000078\u0003\b\u0004\u000089\u0005"+
		"\u0005\u0000\u00009:\u0003\n\u0005\u0000:;\u0005\u0006\u0000\u0000;\u0007"+
		"\u0001\u0000\u0000\u0000<A\u0005%\u0000\u0000=>\u0005\u0003\u0000\u0000"+
		">@\u0005%\u0000\u0000?=\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000\u0000"+
		"A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\t\u0001\u0000\u0000"+
		"\u0000CA\u0001\u0000\u0000\u0000DE\u0007\u0000\u0000\u0000E\u000b\u0001"+
		"\u0000\u0000\u0000FG\u0005\u000b\u0000\u0000GH\u0003\u0010\b\u0000HI\u0005"+
		"\u0006\u0000\u0000It\u0001\u0000\u0000\u0000JK\u0005%\u0000\u0000KT\u0005"+
		"\u0002\u0000\u0000LQ\u0003\u0010\b\u0000MN\u0005\u0003\u0000\u0000NP\u0003"+
		"\u0010\b\u0000OM\u0001\u0000\u0000\u0000PS\u0001\u0000\u0000\u0000QO\u0001"+
		"\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RU\u0001\u0000\u0000\u0000"+
		"SQ\u0001\u0000\u0000\u0000TL\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000VW\u0005\u0004\u0000\u0000Wt\u0005\u0006"+
		"\u0000\u0000XY\u0005%\u0000\u0000YZ\u0005\f\u0000\u0000Z[\u0003\u0010"+
		"\b\u0000[\\\u0005\u0006\u0000\u0000\\t\u0001\u0000\u0000\u0000]_\u0005"+
		"\r\u0000\u0000^`\u0003\u0010\b\u0000_^\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000at\u0005\u0006\u0000\u0000"+
		"bc\u0005\u000e\u0000\u0000cd\u0005\u0002\u0000\u0000de\u0003\u0010\b\u0000"+
		"ef\u0005\u0004\u0000\u0000fg\u0003\f\u0006\u0000gt\u0001\u0000\u0000\u0000"+
		"hi\u0005\u000f\u0000\u0000ij\u0005\u0002\u0000\u0000jk\u0003\u0010\b\u0000"+
		"kl\u0005\u0004\u0000\u0000lo\u0003\f\u0006\u0000mn\u0005\u0010\u0000\u0000"+
		"np\u0003\f\u0006\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"pt\u0001\u0000\u0000\u0000qt\u0003\u000e\u0007\u0000rt\u0005\u0006\u0000"+
		"\u0000sF\u0001\u0000\u0000\u0000sJ\u0001\u0000\u0000\u0000sX\u0001\u0000"+
		"\u0000\u0000s]\u0001\u0000\u0000\u0000sb\u0001\u0000\u0000\u0000sh\u0001"+
		"\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000sr\u0001\u0000\u0000\u0000"+
		"t\r\u0001\u0000\u0000\u0000uz\u0005\u0011\u0000\u0000vy\u0003\u0006\u0003"+
		"\u0000wy\u0003\f\u0006\u0000xv\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000"+
		"\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{}\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}~\u0005"+
		"\u0012\u0000\u0000~\u000f\u0001\u0000\u0000\u0000\u007f\u0080\u0006\b"+
		"\uffff\uffff\u0000\u0080\u0081\u0005\u0002\u0000\u0000\u0081\u0082\u0003"+
		"\u0010\b\u0000\u0082\u0083\u0005\u0004\u0000\u0000\u0083\u0099\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0007\u0001\u0000\u0000\u0085\u0099\u0003\u0010"+
		"\b\r\u0086\u0087\u0005%\u0000\u0000\u0087\u0090\u0005\u0002\u0000\u0000"+
		"\u0088\u008d\u0003\u0010\b\u0000\u0089\u008a\u0005\u0003\u0000\u0000\u008a"+
		"\u008c\u0003\u0010\b\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008f"+
		"\u0001\u0000\u0000\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u0088\u0001\u0000\u0000\u0000\u0090\u0091"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0099"+
		"\u0005\u0004\u0000\u0000\u0093\u0099\u0005%\u0000\u0000\u0094\u0099\u0005"+
		"!\u0000\u0000\u0095\u0099\u0005\"\u0000\u0000\u0096\u0099\u0005#\u0000"+
		"\u0000\u0097\u0099\u0005$\u0000\u0000\u0098\u007f\u0001\u0000\u0000\u0000"+
		"\u0098\u0084\u0001\u0000\u0000\u0000\u0098\u0086\u0001\u0000\u0000\u0000"+
		"\u0098\u0093\u0001\u0000\u0000\u0000\u0098\u0094\u0001\u0000\u0000\u0000"+
		"\u0098\u0095\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u00ae\u0001\u0000\u0000\u0000"+
		"\u009a\u009b\n\f\u0000\u0000\u009b\u009c\u0007\u0002\u0000\u0000\u009c"+
		"\u00ad\u0003\u0010\b\r\u009d\u009e\n\u000b\u0000\u0000\u009e\u009f\u0007"+
		"\u0003\u0000\u0000\u009f\u00ad\u0003\u0010\b\f\u00a0\u00a1\n\n\u0000\u0000"+
		"\u00a1\u00a2\u0007\u0004\u0000\u0000\u00a2\u00ad\u0003\u0010\b\u000b\u00a3"+
		"\u00a4\n\t\u0000\u0000\u00a4\u00a5\u0007\u0005\u0000\u0000\u00a5\u00ad"+
		"\u0003\u0010\b\n\u00a6\u00a7\n\b\u0000\u0000\u00a7\u00a8\u0005\u001f\u0000"+
		"\u0000\u00a8\u00ad\u0003\u0010\b\t\u00a9\u00aa\n\u0007\u0000\u0000\u00aa"+
		"\u00ab\u0005 \u0000\u0000\u00ab\u00ad\u0003\u0010\b\b\u00ac\u009a\u0001"+
		"\u0000\u0000\u0000\u00ac\u009d\u0001\u0000\u0000\u0000\u00ac\u00a0\u0001"+
		"\u0000\u0000\u0000\u00ac\u00a3\u0001\u0000\u0000\u0000\u00ac\u00a6\u0001"+
		"\u0000\u0000\u0000\u00ac\u00a9\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001"+
		"\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001"+
		"\u0000\u0000\u0000\u00af\u0011\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001"+
		"\u0000\u0000\u0000\u0012\u0015\u001b\'*/AQT_osxz\u008d\u0090\u0098\u00ac"+
		"\u00ae";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}