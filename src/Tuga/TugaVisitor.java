// Generated from C:/Users/limaj/OneDrive - Universidade do Algarve/uni/docs/uni/3ano/2_semestre/CPL/Trabalho_3/Tuga/src/Tuga.g4 by ANTLR 4.13.2
package Tuga;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TugaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TugaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TugaParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(TugaParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#functionDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDecl(TugaParser.FunctionDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(TugaParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(TugaParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#identList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentList(TugaParser.IdentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(TugaParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WriteStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStat(TugaParser.WriteStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStat(TugaParser.CallStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(TugaParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(TugaParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(TugaParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStat(TugaParser.IfStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(TugaParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EmptyStat}
	 * labeled alternative in {@link TugaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStat(TugaParser.EmptyStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link TugaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(TugaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(TugaParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(TugaParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentExpr(TugaParser.IdentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(TugaParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(TugaParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(TugaParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoubleExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleExpr(TugaParser.DoubleExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCallExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpr(TugaParser.FunctionCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(TugaParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualityExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(TugaParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(TugaParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(TugaParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(TugaParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanExpr}
	 * labeled alternative in {@link TugaParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(TugaParser.BooleanExprContext ctx);
}