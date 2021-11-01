lexer grammar ExpressionLexer;

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write the function names here                                                                                                  ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

FN_ACOS               : 'acos';
FN_ASIN               : 'asin';
FN_ATAN               : 'atan';
FN_COMPARE_DATE       : 'compareDate';
FN_COMPARE_NUMBER     : 'compareNumber';
FN_COMPARE_STRING     : 'compareString';
FN_COLLECTION_ADD     : 'collectionAdd';
FN_COS                : 'cos';
FN_DATE               : 'date';
FN_DATE_FROM_RANGE    : 'datesFromRange';
FN_DAY                : 'day';
FN_LN                 : 'ln';
FN_LOG                : 'log';
FN_MONTH              : 'month';
FN_SIN                : 'sin';
FN_SQRT               : 'sqrt';
FN_TAN                : 'tan';
FN_TODAY              : 'today';
FN_YEAR               : 'year';

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write the primitive types here                                                                                                 ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

BOOLEAN
  : 'true'
  | 'false'
  ;

DATE
  : QuotesSimple DateYear MINUS DateMonth MINUS DateDay QuotesSimple;

NUMBER
  : Integer
  | Decimal
  ;

STRING
  : QuotesSimple Anything QuotesSimple
  | QuotesDouble Anything QuotesDouble
  ;

IDENTIFIER       : Dollar Letter LetterOrDigit*;
IDENTIFIER_ATTR  : IDENTIFIER (POINT Letter LetterOrDigit*)*;

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write the other tokens here                                                                                                    ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

IF      : 'if';
ELSE    : 'else';
NULL    : 'null';
RETURN  : 'return';

FOR : 'for';
IN  : 'in';

OR   : '||';
AND  : '&&';

POW        : '^';
MULT       : '*';
DIV        : '/';
MOD        : '%';
PLUS       : '+';
MINUS      : '-';
POINT      : '.';
TWO_POINT  : ':';
COMMA      : ',';
SEMICOLON  : ';';
PIPE       : '|';

COMPARISON   : EQ | NE | GT | GE | LT | LE;
NOT          : '!';
ASSIG        : '=';

LPAREN  : '(';
RPAREN  : ')';
LBRACK  : '[';
RBRACK  : ']';
LBRACE  : '{';
RBRACE  : '}';

//╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
//╠═════════ Write the fragments here to assemble the types                                                                                 ═════════╣
//╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝

fragment Digit    : [0-9];
fragment Digit4   : Digit Digit Digit Digit;
fragment Digits   : Digit+;
fragment Dollar   : '$';
fragment Integer  : Digits;
fragment Decimal  : Digits POINT Digits;

fragment QuotesSimple  : '\'';
fragment QuotesDouble  : '"';
fragment Anything      : .*?;
fragment GT            : '>';
fragment GE            : '>=';
fragment LT            : '<';
fragment LE            : '<=';
fragment EQ            : '==';
fragment NE            : '!=';

fragment LetterOrDigit
  : Letter
  | [0-9]
  ;

fragment Letter
  : [a-zA-Z$_] // these are the "java letters" below 0x7F
  | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
  | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
  ;

fragment DateDay    : [0-3]? Digit;
fragment DateMonth  : ([0-1]? Digit | MonthType);
fragment DateYear   : Digit4;
fragment MonthType  : Jan | Feb | Mar | Apr | May | Jun | Jul | Aug | Sep | Oct | Nov | Dec;

fragment Jan  : [Jj][Aa][Nn];
fragment Feb  : [Ff][Ee][Bb];
fragment Mar  : [Mm][Aa][Rr];
fragment Apr  : [Aa][Pp][Rr];
fragment May  : [Mm][Aa][Yy];
fragment Jun  : [Jj][Uu][Nn];
fragment Jul  : [Jj][Uu][Ll];
fragment Aug  : [Aa][Uu][Gg];
fragment Sep  : [Ss][Ee][Pp];
fragment Oct  : [Oo][Cc][Tt];
fragment Nov  : [Nn][Oo][Vv];
fragment Dec  : [Dd][Ee][Cc];

// COMMENT and WS are stripped from the output token stream by sending to a different channel 'skip'
COMMENT_LINE  : '//' ~[\r\n]*     -> channel(HIDDEN);
COMMENT_BLOCK : '/*' .*? '*/'     -> channel(HIDDEN);
WHITESPACE    : [ \n\r\t\u000C]+  -> channel(HIDDEN);
