// Generated from C:/Users/limaj/OneDrive - Universidade do Algarve/uni/docs/uni/3ano/2_semestre/CPL/Trabalho_3/Tuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TugaParser}.
 */
public interface TugaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TugaParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(TugaParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(TugaParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDecl(TugaParser.FunctionDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#functionDecl}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDecl(TugaParser.FunctionDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(TugaParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(TugaParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(TugaParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(TugaParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#identList}.
	 * @param ctx the parse tree
	 */
	void enterIdentList(TugaParser.IdentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#identList}.
	 * @param ctx the parse tree
	 */
	void exitIdentList(TugaParser.IdentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(TugaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(TugaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWriteStat(TugaParser.WriteStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWriteStat(TugaParser.WriteStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(TugaParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(TugaParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(TugaParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(TugaParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(TugaParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(TugaParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(TugaParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(TugaParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(TugaParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(TugaParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(TugaParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(TugaParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EmptyStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStat(TugaParser.EmptyStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EmptyStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStat(TugaParser.EmptyStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link TugaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(TugaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link TugaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(TugaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(TugaParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(TugaParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(TugaParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(TugaParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentExpr(TugaParser.IdentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentExpr(TugaParser.IdentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(TugaParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(TugaParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(TugaParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(TugaParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(TugaParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(TugaParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DoubleExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDoubleExpr(TugaParser.DoubleExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DoubleExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDoubleExpr(TugaParser.DoubleExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpr(TugaParser.FunctionCallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpr(TugaParser.FunctionCallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(TugaParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(TugaParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(TugaParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(TugaParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(TugaParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(TugaParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(TugaParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(TugaParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(TugaParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(TugaParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpr(TugaParser.BooleanExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpr(TugaParser.BooleanExprContext ctx);
}