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
  : arithmeticExpression
  | dateExpresion
  | booleanExpression
  ;

arithmeticExpression
  : functionsExpression                              # mathematicsFunctions
  | LBRACE arithmeticExpression RBRACE               # mathematicsGroupedBy
  | LBRACK arithmeticExpression RBRACK               # mathematicsGroupedBy
  | LPAREN arithmeticExpression RPAREN               # mathematicsGroupedBy
  | op = (PLUS | MINUS) arithmeticExpression         # mathematicsOperationSign
  | arithmeticExpression POW   arithmeticExpression  # mathematicsOperationPow
  | arithmeticExpression MULT  arithmeticExpression  # mathematicsOperationMultiply
  | arithmeticExpression DIV   arithmeticExpression  # mathematicsOperationDivide
  | arithmeticExpression MOD   arithmeticExpression  # mathematicsOperationModulo
  | arithmeticExpression MINUS arithmeticExpression  # mathematicsOperationSubtract
  | arithmeticExpression PLUS  arithmeticExpression  # mathematicsOperationAddition
  | numberExpresion                                  # mathematicsNumeric
  ;

functionsExpression
  : FN_MATH_COS  LPAREN arithmeticExpression RPAREN  # mathematicsFunctionCos
  | FN_MATH_ACOS LPAREN arithmeticExpression RPAREN  # mathematicsFunctionAcos
  | FN_MATH_SIN  LPAREN arithmeticExpression RPAREN  # mathematicsFunctionSin
  | FN_MATH_ASIN LPAREN arithmeticExpression RPAREN  # mathematicsFunctionAsin
  | FN_MATH_TAN  LPAREN arithmeticExpression RPAREN  # mathematicsFunctionTan
  | FN_MATH_ATAN LPAREN arithmeticExpression RPAREN  # mathematicsFunctionAtan
  | FN_MATH_LN   LPAREN arithmeticExpression RPAREN  # mathematicsFunctionLn
  | FN_MATH_LOG  LPAREN arithmeticExpression RPAREN  # mathematicsFunctionLog
  | FN_MATH_SQRT LPAREN arithmeticExpression RPAREN  # mathematicsFunctionSqrt
  ;

numberExpresion
  : FN_COMPARE_DATE   LPAREN dateExpresion    COMMA dateExpresion    RPAREN  # compareDate
  | FN_COMPARE_STRING LPAREN stringExpression COMMA stringExpression RPAREN  # compareString
  | FN_CALENDAR_DAY   LPAREN dateExpresion RPAREN                            # calendarFunctionDay
  | FN_CALENDAR_MONTH LPAREN dateExpresion RPAREN                            # calendarFunctionMonth
  | FN_CALENDAR_YEAR  LPAREN dateExpresion RPAREN                            # calendarFunctionYear
  | functionsThatReturnsNumber                                               # functionThatReturnsNumber
  | NUMBER                                                                   # primitiveNumber
  | IDENTIFIER                                                               # identifierNumber
  ;

functionsThatReturnsNumber
  : FN_COMPARE LPAREN numberExpresion  COMMA numberExpresion  RPAREN  # compareNumbers
  | FN_COMPARE LPAREN stringExpression COMMA stringExpression RPAREN  # compareStrings
  ;

dateExpresion
  : FN_CALENDAR_DATE LPAREN DateYear COMMA DateMonth COMMA DateDay RPAREN  # calendarFunctionDate
  | FN_CALENDAR_TODAY                                                      # calendarFunctionToday
  | DATE                                                                   # primitiveDate
  | IDENTIFIER                                                             # identifierDate
  ;

booleanExpression
  : LPAREN booleanExpression RPAREN                          # booleanGroupedBy
  | NOT booleanExpression                                    # booleanNegation
  | booleanExpression AND booleanExpression                  # booleanAND
  | booleanExpression OR  booleanExpression                  # booleanOR
  | arithmeticExpression op=COMPARISON arithmeticExpression  # booleanArithmeticComparison
  | BOOLEAN                                                  # primitiveBoolean
  | IDENTIFIER                                               # identifierBoolean
  ;

stringExpression
  : STRING      # primitiveString
  | IDENTIFIER  # identifierString
  ;
