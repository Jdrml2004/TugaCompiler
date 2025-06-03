grammar Tuga;

prog
    : declaration* functionDecl+ EOF
    ;

functionDecl
    : 'funcao' IDENT '(' (param (',' param)*)? ')' (':' type)? block
    ;

param
    : IDENT ':' type
    ;

declaration
    : identList ':' type ';'
    ;

identList
    : IDENT (',' IDENT)*
    ;

type
    : 'inteiro'
    | 'real'
    | 'booleano'
    | 'string'
    ;

stat
    : 'escreve' expr ';'                            # WriteStat
    | IDENT '(' (expr (',' expr)*)? ')' ';'         # CallStat
    | IDENT '<-' expr ';'                           # AssignStat
    | 'retorna' expr? ';'                           # ReturnStat
    | 'enquanto' '(' expr ')' stat                  # WhileStat
    | 'se' '(' expr ')' stat ('senao' stat)?        # IfStat
    | block                                         # BlockStat
    | ';'                                           # EmptyStat
    ;

block
    : 'inicio' (declaration | stat)* 'fim'
    ;

expr
    : '(' expr ')'                                  # ParensExpr
    | op=('-'|'nao') expr                           # UnaryExpr
    | expr op=('*' | '/' | '%') expr                # MulDivExpr
    | expr op=('+' | '-') expr                      # AddSubExpr
    | expr op=('<' | '>' | '<=' | '>=') expr        # RelationalExpr
    | expr op=('igual' | 'diferente') expr          # EqualityExpr
    | expr 'e' expr                                 # AndExpr
    | expr 'ou' expr                                # OrExpr
    | IDENT '(' (expr (',' expr)*)? ')'             # FunctionCallExpr
    | IDENT                                         # IdentExpr
    | INT                                           # IntExpr
    | DOUBLE                                        # DoubleExpr
    | STRING                                        # StringExpr
    | BOOLEAN                                       # BooleanExpr
    ;

INT       : [0-9]+ ;
DOUBLE    : [0-9]+ '.' [0-9]+ ;
STRING    : '"' (~["\r\n])* '"' ;
BOOLEAN   : 'verdadeiro' | 'falso' ;

IDENT     : [a-zA-Z_] [a-zA-Z_0-9]* ;

WS        : [ \t\r\n]+ -> skip ;
SL_COMMENT: '//' ~[\r\n]* -> skip ;
ML_COMMENT: '/*' .*? '*/' -> skip ;
