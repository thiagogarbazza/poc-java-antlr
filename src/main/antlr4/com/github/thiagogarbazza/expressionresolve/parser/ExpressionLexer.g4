lexer grammar ExpressionLexer;

IF    : 'if';
ELSE  : 'else';

NULL  : 'null';

FN_CALENDAR_TODAY  : 'today';
FN_CALENDAR_DATE   : 'date';
FN_CALENDAR_DAY    : 'day';
FN_CALENDAR_MONTH  : 'month';
FN_CALENDAR_YEAR   : 'year';

FN_MATH_SQRT  : 'sqrt';
FN_MATH_COS   : 'cos';
FN_MATH_ACOS  : 'acos';
FN_MATH_SIN   : 'sin';
FN_MATH_ASIN  : 'asin';
FN_MATH_TAN   : 'tan';
FN_MATH_ATAN  : 'atan';
FN_MATH_LN    : 'ln';
FN_MATH_LOG   : 'log';

FN_COMPARE_DATE    : 'compareDate';
FN_COMPARE_STRING  : 'compareString';

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

COMPARISON   : GT | GE | LT | LE | EQ | NE;
NOT          : '!';
ASSIG        : '=';

LPAREN  : '(';
RPAREN  : ')';
LBRACK  : '[';
RBRACK  : ']';
LBRACE  : '{';
RBRACE  : '}';

BOOLEAN  : ('true' | 'false');
INTEGER  : Digits;
DECIMAL  : Digits POINT Digits;

STRING
  : QuotesSimple Anything QuotesSimple
  | QuotesDouble Anything QuotesDouble
  ;

DATE  : DateYear '/' DateMonth '/' DateDay;

IDENTIFIER  : Letter LetterOrDigit*;

fragment Digit   : [0-9];
fragment Digit4  : Digit Digit Digit Digit;
fragment Digits  : Digit+;

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
COMMENT_LINE  : '//' ~[\r\n]*       -> channel(HIDDEN);
COMMENT_BLOCK : '/*' .*? '*/'       -> channel(HIDDEN);
WHITESPACE    : [ \f\n\r\t\u000C]+  -> channel(HIDDEN);
