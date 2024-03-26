// Expr.g4
grammar Expr;	
// Definição da gramática Expr
prog:	(expr NEWLINE)* ; // Definição do programa como uma seqüência de expressões separadas por uma nova linha
// Definição do programa como uma seqüência de expressões
expr:	expr ('*'|'/') expr  // Definição de uma expressão como uma operação entre duas expressões envolvendo multiplicação ou divisão
    |	expr ('+'|'-') expr  // Definição de uma expressão como uma operação entre duas expressões
    |	INT          // Definição de uma expressão como um número inteiro
    |	'(' expr ')' // Definição de uma expressão entre parênteses
    ;
// Definição de uma expressão como uma operação entre duas expressões, um número inteiro ou uma expressão entre parênteses      
NEWLINE : [\r\n]+ ; //  Definição do caractere de nova linha
INT     : [0-9]+ ; // Definição de um número inteiro
// Ignora espaços em branco entre tokens
WS : [ \t]+ -> skip ;

