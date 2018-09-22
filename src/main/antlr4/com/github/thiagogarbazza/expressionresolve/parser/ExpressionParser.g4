parser grammar ExpressionParser;

options {
  tokenVocab = ExpressionLexer;
}

parse
  : block EOF
  ;

block
  : statement*
  ;

statementBlock
  : LBRACE statement RBRACE
  ;

statement
  : IDENTIFIER ASSIG expression SEMICOLON   # assignment
  | conditionalStatements                   # conditionalStatement
  | RETURN expression SEMICOLON             # returnExpression
  ;

conditionalStatements
  : IF LPAREN booleanExpression RPAREN statementBlock (ELSE IF LPAREN booleanExpression RPAREN statementBlock)* (ELSE statementBlock)?  # ifConditional
  ;

expression
  : booleanExpression
  | dateExpresion
  | numberExpresion
  | stringExpression
  ;

booleanExpression
  : LPAREN booleanExpression RPAREN                # booleanGroupedBy
  | NOT booleanExpression                          # booleanNegation
  | booleanExpression AND booleanExpression        # booleanAND
  | booleanExpression OR  booleanExpression        # booleanOR
  | numberExpresion op=COMPARISON numberExpresion  # booleanNumberComparison
  | IDENTIFIER                                     # identifierBoolean
  | BOOLEAN                                        # primitiveBoolean
  ;

dateExpresion
  : functionsThatReturnDate  # functionThatReturnDate
  | IDENTIFIER               # identifierDate
  | DATE                     # primitiveDate
  ;

numberExpresion
  : LBRACE numberExpresion RBRACE          # numberGroupedBy
  | LBRACK numberExpresion RBRACK          # numberGroupedBy
  | LPAREN numberExpresion RPAREN          # numberGroupedBy
  | op = (PLUS | MINUS) numberExpresion    # numberOperationSign
  | numberExpresion POW   numberExpresion  # numberOperationPow
  | numberExpresion MULT  numberExpresion  # numberOperationMultiply
  | numberExpresion DIV   numberExpresion  # numberOperationDivide
  | numberExpresion MOD   numberExpresion  # numberOperationModulo
  | numberExpresion MINUS numberExpresion  # numberOperationSubtract
  | numberExpresion PLUS  numberExpresion  # numberOperationAddition
  | functionsThatReturnNumber              # functionThatReturnNumber
  | IDENTIFIER                             # identifierNumber
  | NUMBER                                 # primitiveNumber
  ;

stringExpression
  : IDENTIFIER  # identifierString
  | STRING      # primitiveString
  ;

functionsThatReturnDate
  : FN_DATE LPAREN numberExpresion COMMA numberExpresion COMMA numberExpresion RPAREN  # functionDate
  | FN_TODAY                                                                           # functionToday
  ;

functionsThatReturnNumber
  : FN_COS  LPAREN numberExpresion RPAREN                             # functionCos
  | FN_ACOS LPAREN numberExpresion RPAREN                             # functionAcos
  | FN_SIN  LPAREN numberExpresion RPAREN                             # functionSin
  | FN_ASIN LPAREN numberExpresion RPAREN                             # functionAsin
  | FN_TAN  LPAREN numberExpresion RPAREN                             # functionTan
  | FN_ATAN LPAREN numberExpresion RPAREN                             # functionAtan
  | FN_LN   LPAREN numberExpresion RPAREN                             # functionLn
  | FN_LOG  LPAREN numberExpresion RPAREN                             # functionLog
  | FN_SQRT LPAREN numberExpresion RPAREN                             # functionSqrt
  | FN_COMPARE LPAREN numberExpresion  COMMA numberExpresion  RPAREN  # functionCompareNumbers
  | FN_COMPARE LPAREN stringExpression COMMA stringExpression RPAREN  # functionCompareStrings
  | FN_COMPARE LPAREN dateExpresion    COMMA dateExpresion    RPAREN  # functionCompareDates
  | FN_DAY   LPAREN dateExpresion RPAREN                              # functionDay
  | FN_MONTH LPAREN dateExpresion RPAREN                              # functionMonth
  | FN_YEAR  LPAREN dateExpresion RPAREN                              # functionYear
  ;
