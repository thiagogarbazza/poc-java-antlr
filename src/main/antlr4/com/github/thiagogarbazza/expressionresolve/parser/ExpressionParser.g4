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
  | statement
  ;

statement
  : assignment
  | ifExpression
  | expression
  ;

assignment
  : IDENTIFIER ASSIG expression SEMICOLON
  ;

ifExpression
  : IF booleanExpression statementBlock (ELSE IF booleanExpression statementBlock)* (ELSE statementBlock)?
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
  | BOOLEAN                                        # primitiveBoolean
  | IDENTIFIER                                     # identifierBoolean
  ;

dateExpresion
  : functionsThatReturnDate  # functionThatReturnDate
  | DATE                     # primitiveDate
  | IDENTIFIER               # identifierDate
  ;

numberExpresion
  : LBRACE numberExpresion RBRACE          # mathematicsGroupedBy
  | LBRACK numberExpresion RBRACK          # mathematicsGroupedBy
  | LPAREN numberExpresion RPAREN          # mathematicsGroupedBy
  | op = (PLUS | MINUS) numberExpresion    # mathematicsOperationSign
  | numberExpresion POW   numberExpresion  # mathematicsOperationPow
  | numberExpresion MULT  numberExpresion  # mathematicsOperationMultiply
  | numberExpresion DIV   numberExpresion  # mathematicsOperationDivide
  | numberExpresion MOD   numberExpresion  # mathematicsOperationModulo
  | numberExpresion MINUS numberExpresion  # mathematicsOperationSubtract
  | numberExpresion PLUS  numberExpresion  # mathematicsOperationAddition
  | functionsThatReturnNumber              # functionThatReturnNumber
  | NUMBER                                 # primitiveNumber
  | IDENTIFIER                             # identifierNumber
  ;

stringExpression
  : STRING      # primitiveString
  | IDENTIFIER  # identifierString
  ;

functionsThatReturnDate
  : FN_CALENDAR_DATE LPAREN DateYear COMMA DateMonth COMMA DateDay RPAREN  # calendarFunctionDate
  | FN_CALENDAR_TODAY                                                      # functionToday
  ;

functionsThatReturnNumber
  : FN_MATH_COS  LPAREN numberExpresion RPAREN                        # functionCos
  | FN_MATH_ACOS LPAREN numberExpresion RPAREN                        # functionAcos
  | FN_MATH_SIN  LPAREN numberExpresion RPAREN                        # functionSin
  | FN_MATH_ASIN LPAREN numberExpresion RPAREN                        # functionAsin
  | FN_MATH_TAN  LPAREN numberExpresion RPAREN                        # functionTan
  | FN_MATH_ATAN LPAREN numberExpresion RPAREN                        # functionAtan
  | FN_MATH_LN   LPAREN numberExpresion RPAREN                        # functionLn
  | FN_MATH_LOG  LPAREN numberExpresion RPAREN                        # functionLog
  | FN_MATH_SQRT LPAREN numberExpresion RPAREN                        # functionSqrt
  | FN_COMPARE LPAREN numberExpresion  COMMA numberExpresion  RPAREN  # functionCompareNumbers
  | FN_COMPARE LPAREN stringExpression COMMA stringExpression RPAREN  # functionCompareStrings
  | FN_COMPARE LPAREN dateExpresion    COMMA dateExpresion    RPAREN  # functionCompareDates
  | FN_CALENDAR_DAY   LPAREN dateExpresion RPAREN                     # functionDay
  | FN_CALENDAR_MONTH LPAREN dateExpresion RPAREN                     # functionMonth
  | FN_CALENDAR_YEAR  LPAREN dateExpresion RPAREN                     # functionYear
  ;
