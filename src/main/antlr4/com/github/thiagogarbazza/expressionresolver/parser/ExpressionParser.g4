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
  : IDENTIFIER ASSIG valueExpression SEMICOLON                                                                                # assignment
  | IF LPAREN vlExpBoolean RPAREN statementBlock (ELSE IF LPAREN vlExpBoolean RPAREN statementBlock)* (ELSE statementBlock)?  # ifConditional
  | FOR LPAREN IDENTIFIER IN arrayExpression RPAREN statementBlock                                                            # iterableExpression
  | RETURN valueExpression SEMICOLON                                                                                          # returnExpression
  ;

statementBlock
  : LBRACE statement RBRACE
  ;

valueExpression
  : singleValueExpression
  | arrayExpression
  ;

singleValueExpression
  : vlExpBoolean
  | vlExpDate
  | vlExpJson
  | vlExpNumber
  | vlExpString
  ;

arrayExpression
  : arrExpBoolean
  | arrExpDate
  | arrExpNumber
  | arrExpString
  ;

arrExpBoolean
  : LBRACK vlExpBoolean (COMMA vlExpBoolean)* RBRACK  # collectionBooleanExpresion
  ;

arrExpDate
  : functionsThatReturnDates                    # functionThatReturnDates
  | LBRACK vlExpDate (COMMA vlExpDate)* RBRACK  # collectionDateExpresion
  | IDENTIFIER                                  # identifierDates
  ;

arrExpNumber
  : LBRACK vlExpNumber (COMMA vlExpNumber)* RBRACK  # collectionNumberExpresion
  | IDENTIFIER                                      # identifierNumbers
  ;

arrExpString
  : LBRACK vlExpString (COMMA vlExpString)* RBRACK  # collectionStringExpresion
  ;

vlExpBoolean
  : LPAREN vlExpBoolean RPAREN             # booleanGroupedBy
  | NOT vlExpBoolean                       # booleanNegation
  | vlExpBoolean AND vlExpBoolean          # booleanAND
  | vlExpBoolean OR  vlExpBoolean          # booleanOR
  | vlExpNumber op=COMPARISON vlExpNumber  # booleanNumberComparison
  | IDENTIFIER                             # identifierBoolean
  | BOOLEAN                                # primitiveBoolean
  ;

vlExpDate
  : functionsThatReturnDate  # functionThatReturnDate
  | IDENTIFIER               # identifierDate
  | DATE                     # primitiveDate
  ;

vlExpJson
  : LBRACE RBRACE
  | LBRACE vlExpJsonPair (COMMA vlExpJsonPair)* RBRACE
  ;

vlExpJsonPair
  : STRING TWO_POINT valueExpression
  ;

vlExpNumber
  : LBRACE vlExpNumber RBRACE        # numberGroupedBy
  | LBRACK vlExpNumber RBRACK        # numberGroupedBy
  | LPAREN vlExpNumber RPAREN        # numberGroupedBy
  | op = (PLUS | MINUS) vlExpNumber  # numberOperationSign
  | vlExpNumber POW   vlExpNumber    # numberOperationPow
  | vlExpNumber MULT  vlExpNumber    # numberOperationMultiply
  | vlExpNumber DIV   vlExpNumber    # numberOperationDivide
  | vlExpNumber MOD   vlExpNumber    # numberOperationModulo
  | vlExpNumber MINUS vlExpNumber    # numberOperationSubtract
  | vlExpNumber PLUS  vlExpNumber    # numberOperationAddition
  | functionsThatReturnNumber        # functionThatReturnNumber
  | IDENTIFIER                       # identifierNumber
  | NUMBER                           # primitiveNumber
  ;

vlExpString
  : IDENTIFIER  # identifierString
  | STRING      # primitiveString
  ;

functionsThatReturnDate
  : FN_DATE LPAREN vlExpNumber COMMA vlExpNumber COMMA vlExpNumber RPAREN  # functionDate
  | FN_TODAY                                                               # functionToday
  ;

functionsThatReturnDates
  : FN_DATE_FROM_RANGE LPAREN vlExpDate COMMA vlExpDate RPAREN  # functionDatesFromRange
  ;

functionsThatReturnNumber
  : FN_ACOS LPAREN vlExpNumber RPAREN                                # functionAcos
  | FN_ASIN LPAREN vlExpNumber RPAREN                                # functionAsin
  | FN_ATAN LPAREN vlExpNumber RPAREN                                # functionAtan
  | FN_COMPARE_DATE LPAREN vlExpDate    COMMA vlExpDate    RPAREN    # functionCompareDates
  | FN_COMPARE_STRING LPAREN vlExpString COMMA vlExpString RPAREN    # functionCompareStrings
  | FN_COMPARE_NUMBER LPAREN vlExpNumber  COMMA vlExpNumber  RPAREN  # functionCompareNumbers
  | FN_COS  LPAREN vlExpNumber RPAREN                                # functionCos
  | FN_DAY   LPAREN vlExpDate RPAREN                                 # functionDay
  | FN_LN   LPAREN vlExpNumber RPAREN                                # functionLn
  | FN_LOG  LPAREN vlExpNumber RPAREN                                # functionLog
  | FN_MONTH LPAREN vlExpDate RPAREN                                 # functionMonth
  | FN_SIN  LPAREN vlExpNumber RPAREN                                # functionSin
  | FN_SQRT LPAREN vlExpNumber RPAREN                                # functionSqrt
  | FN_TAN  LPAREN vlExpNumber RPAREN                                # functionTan
  | FN_YEAR  LPAREN vlExpDate RPAREN                                 # functionYear
  ;
