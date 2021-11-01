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
  : IDENTIFIER ASSIG value SEMICOLON                                                                                        # assignment
  | IDENTIFIER_ATTR ASSIG value SEMICOLON                                                                                   # assignmentAttr
  | IF LPAREN vlTpBoolean RPAREN statementBlock (ELSE IF LPAREN vlTpBoolean RPAREN statementBlock)* (ELSE statementBlock)?  # ifConditional
  | FOR LPAREN IDENTIFIER IN clTpAny RPAREN statementBlock                                                                  # iterableExpression
  | functionsThatDontReturn                                                                                                 # functionThatDontReturn
  | RETURN value SEMICOLON                                                                                                  # returnExpression
  ;

statementBlock
  : LBRACE statement RBRACE
  ;

value
  : vlTpAny
  | clTpAny
  ;

clTpAny
  : clTpBoolean
  | clTpDate
  | clTpObject
  | clTpNumber
  | clTpText
  ;

vlTpAny
  : vlTpBoolean
  | vlTpDate
  | vlTpNumber
  | vlTpObject
  | vlTpText
  ;

clTpBoolean
  : LBRACK vlTpBoolean (COMMA vlTpBoolean)* RBRACK  # createClTpBoolean
  | LBRACK RBRACK                                   # createEmptyClTpBoolean
  | NULL                                            # createNullClTpBoolean
  | IDENTIFIER                                      # identifierClTpBoolean
  | IDENTIFIER_ATTR                                 # identifierAttrClTpBoolean
  ;

clTpDate
  : functionsThatReturnClTpDate               # functionThatReturnClTpDate
  | LBRACK vlTpDate (COMMA vlTpDate)* RBRACK  # createClTpDate
  | LBRACK RBRACK                             # createEmptyClTpDate
  | NULL                                      # createNullClTpDate
  | IDENTIFIER                                # identifierClTpDate
  | IDENTIFIER_ATTR                           # identifierAttrClTpDate
  ;

clTpNumber
  : LBRACK vlTpNumber (COMMA vlTpNumber)* RBRACK  # createClTpNumber
  | LBRACK RBRACK                                 # createEmptyClTpNumber
  | NULL                                          # createNullClTpNumber
  | IDENTIFIER                                    # identifierClTpNumber
  | IDENTIFIER_ATTR                               # identifierAttrClTpNumber
  ;

clTpObject
  : LBRACK vlTpObject (COMMA vlTpObject)* RBRACK  # createClTpObject
  | LBRACK RBRACK                                 # createEmptyClTpObject
  | NULL                                          # createNullClTpObject
  | IDENTIFIER                                    # identifierClTpObject
  | IDENTIFIER_ATTR                               # identifierAttrClTpObject
  ;

clTpText
  : LBRACK vlTpText (COMMA vlTpText)* RBRACK  # createClTpText
  | LBRACK RBRACK                             # createEmptyClTpText
  | NULL                                      # createNullClTpText
  | IDENTIFIER                                # identifierClTpText
  | IDENTIFIER_ATTR                           # identifierAttrClTpText
  ;

vlTpBoolean
  : LPAREN vlTpBoolean RPAREN            # booleanGroupedBy
  | NOT vlTpBoolean                      # booleanNegation
  | vlTpBoolean AND vlTpBoolean          # booleanAND
  | vlTpBoolean OR  vlTpBoolean          # booleanOR
  | vlTpDate op=COMPARISON vlTpDate      # booleanComparisonVlTpDate
  | vlTpNumber op=COMPARISON vlTpNumber  # booleanComparisonVlTpNumber
  | vlTpText op=COMPARISON vlTpText      # booleanComparisonVlTpText
  | NULL                                 # createNullVlTpBoolean
  | IDENTIFIER                           # identifierVlTpBoolean
  | IDENTIFIER_ATTR                      # identifierAttrVlTpBoolean
  | BOOLEAN                              # primitiveBoolean
  ;

vlTpDate
  : functionsThatReturnVlTpDate  # functionThatReturnVlTpDate
  | NULL                         # createNullVlTpDate
  | IDENTIFIER                   # identifierVlTpDate
  | IDENTIFIER_ATTR              # identifierAttrVlTpDate
  | DATE                         # primitiveDate
  ;

vlTpNumber
  : LPAREN vlTpNumber RPAREN        # numberGroupedBy
  | op = (PLUS | MINUS) vlTpNumber  # numberOperationSign
  | vlTpNumber POW   vlTpNumber     # numberOperationPow
  | vlTpNumber MULT  vlTpNumber     # numberOperationMultiply
  | vlTpNumber DIV   vlTpNumber     # numberOperationDivide
  | vlTpNumber MOD   vlTpNumber     # numberOperationModulo
  | vlTpNumber MINUS vlTpNumber     # numberOperationSubtract
  | vlTpNumber PLUS  vlTpNumber     # numberOperationAddition
  | functionsThatReturnVlTpNumber   # functionThatReturnVlTpNumber
  | NULL                            # createNullVlTpNumber
  | IDENTIFIER                      # identifierVlTpNumber
  | IDENTIFIER_ATTR                 # identifierAttrVlTpNumber
  | NUMBER                          # primitiveNumber
  ;

vlTpObject
  : LBRACE RBRACE                                       # primitiveEmptyVlTpObject
  | LBRACE vlExpJsonPair (COMMA vlExpJsonPair)* RBRACE  # primitiveVlTpObject
  | NULL                                                # createNullVlTpObject
  | IDENTIFIER                                          # identifierVlTpObject
  | IDENTIFIER_ATTR                                     # identifierAttrVlTpObject
  ;

vlExpJsonPair
  : STRING TWO_POINT value
  ;

vlTpText
  : NULL             # createNullVlTpText
  | IDENTIFIER       # identifierVlTpText
  | IDENTIFIER_ATTR  # identifierAttrVlTpText
  | STRING           # primitiveText
  ;


//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return vlTpBoolean                                                                                   ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return vlTpDate                                                                                      ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
functionsThatReturnVlTpDate
  : FN_DATE LPAREN vlTpNumber COMMA vlTpNumber COMMA vlTpNumber RPAREN  # functionDate
  | FN_TODAY                                                            # functionToday
  ;

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return vlTpNumber                                                                                    ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
functionsThatReturnVlTpNumber
  : FN_ACOS LPAREN vlTpNumber RPAREN                                # functionAcos
  | FN_ASIN LPAREN vlTpNumber RPAREN                                # functionAsin
  | FN_ATAN LPAREN vlTpNumber RPAREN                                # functionAtan
  | FN_COMPARE_DATE LPAREN vlTpDate    COMMA vlTpDate    RPAREN     # functionCompareDates
  | FN_COMPARE_STRING LPAREN vlTpText COMMA vlTpText RPAREN         # functionCompareStrings
  | FN_COMPARE_NUMBER LPAREN vlTpNumber  COMMA vlTpNumber  RPAREN   # functionCompareNumbers
  | FN_COS  LPAREN vlTpNumber RPAREN                                # functionCos
  | FN_DAY   LPAREN vlTpDate RPAREN                                 # functionDay
  | FN_LN   LPAREN vlTpNumber RPAREN                                # functionLn
  | FN_LOG  LPAREN vlTpNumber RPAREN                                # functionLog
  | FN_MONTH LPAREN vlTpDate RPAREN                                 # functionMonth
  | FN_SIN  LPAREN vlTpNumber RPAREN                                # functionSin
  | FN_SQRT LPAREN vlTpNumber RPAREN                                # functionSqrt
  | FN_TAN  LPAREN vlTpNumber RPAREN                                # functionTan
  | FN_YEAR  LPAREN vlTpDate RPAREN                                 # functionYear
  ;

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return vlTpObject                                                                                    ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return vlTpText                                                                                      ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return clTpBoolean                                                                                   ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return clTpDate                                                                                      ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
functionsThatReturnClTpDate
  : FN_DATE_FROM_RANGE LPAREN vlTpDate COMMA vlTpDate RPAREN  # functionDatesFromRange
  ;

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return clTpNumber                                                                                    ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return clTpObject                                                                                    ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that return clTpText                                                                                      ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝


//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write here functions that don't return                                                                                         ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝
functionsThatDontReturn
 : FN_COLLECTION_ADD LPAREN IDENTIFIER COMMA value RPAREN SEMICOLON # functionCollectionAdd
 ;