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

statement
  : IDENTIFIER ASSIG expression SEMICOLON                                                                                     # assignment
  | IF LPAREN vlExpBoolean RPAREN statementBlock (ELSE IF LPAREN vlExpBoolean RPAREN statementBlock)* (ELSE statementBlock)?  # ifConditional
  | FOR LPAREN IDENTIFIER IN collectionExpression RPAREN statementBlock                                                       # iterableExpression
  | RETURN expression SEMICOLON                                                                                               # returnExpression
  ;

statementBlock
  : LBRACE statement RBRACE
  ;

expression
  : vlExpBoolean
  | vlExpDate
  | numberExpresion
  | stringExpression
  | collectionExpression
  | jsonExpression
  ;

collectionExpression
  : booleansExpression
  | datesExpresion
  | numbersExpresion
  | stringsExpression
  ;
  
jsonExpression
  : LBRACE RBRACE
  | LBRACE jsonExpressionPair (COMMA jsonExpressionPair)* RBRACE
  ;

jsonExpressionPair
   : STRING TWO_POINT expression
   ;

vlExpBoolean
  : LPAREN vlExpBoolean RPAREN                # booleanGroupedBy
  | NOT vlExpBoolean                          # booleanNegation
  | vlExpBoolean AND vlExpBoolean        # booleanAND
  | vlExpBoolean OR  vlExpBoolean        # booleanOR
  | numberExpresion op=COMPARISON numberExpresion  # booleanNumberComparison
  | IDENTIFIER                                     # identifierBoolean
  | BOOLEAN                                        # primitiveBoolean
  ;

vlExpDate
  : functionsThatReturnDate  # functionThatReturnDate
  | IDENTIFIER               # identifierDate
  | DATE                     # primitiveDate
  ;

booleansExpression
  : LBRACK vlExpBoolean (COMMA vlExpBoolean)* RBRACK  # collectionBooleanExpresion
  ;

stringsExpression
  : LBRACK stringExpression (COMMA stringExpression)* RBRACK  # collectionStringExpresion
  ;

datesExpresion
  : functionsThatReturnDates                            # functionThatReturnDates
  | LBRACK vlExpDate (COMMA vlExpDate)* RBRACK  # collectionDateExpresion
  | IDENTIFIER                                          # identifierDates
  ;

numbersExpresion
  : LBRACK numberExpresion (COMMA numberExpresion)* RBRACK  # collectionNumberExpresion
  | IDENTIFIER                                              # identifierNumbers
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

functionsThatReturnDates
  : FN_DATE_FROM_RANGE LPAREN vlExpDate COMMA vlExpDate RPAREN  # functionDatesFromRange
  ;

functionsThatReturnNumber
  : FN_ACOS LPAREN numberExpresion RPAREN                                    # functionAcos
  | FN_ASIN LPAREN numberExpresion RPAREN                                    # functionAsin
  | FN_ATAN LPAREN numberExpresion RPAREN                                    # functionAtan
  | FN_COMPARE_DATE LPAREN vlExpDate    COMMA vlExpDate    RPAREN    # functionCompareDates
  | FN_COMPARE_STRING LPAREN stringExpression COMMA stringExpression RPAREN  # functionCompareStrings
  | FN_COMPARE_NUMBER LPAREN numberExpresion  COMMA numberExpresion  RPAREN  # functionCompareNumbers
  | FN_COS  LPAREN numberExpresion RPAREN                                    # functionCos
  | FN_DAY   LPAREN vlExpDate RPAREN                                     # functionDay
  | FN_LN   LPAREN numberExpresion RPAREN                                    # functionLn
  | FN_LOG  LPAREN numberExpresion RPAREN                                    # functionLog
  | FN_MONTH LPAREN vlExpDate RPAREN                                     # functionMonth
  | FN_SIN  LPAREN numberExpresion RPAREN                                    # functionSin
  | FN_SQRT LPAREN numberExpresion RPAREN                                    # functionSqrt
  | FN_TAN  LPAREN numberExpresion RPAREN                                    # functionTan
  | FN_YEAR  LPAREN vlExpDate RPAREN                                     # functionYear
  ;
