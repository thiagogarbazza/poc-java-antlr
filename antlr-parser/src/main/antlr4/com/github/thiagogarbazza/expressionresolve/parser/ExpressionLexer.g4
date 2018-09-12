lexer grammar ExpressionLexer;

channels {
  OFF_CHANNEL  // non-default channel for whitespace and comments
}

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

fragment GT  : '>';
fragment GE  : '>=';
fragment LT  : '<';
fragment LE  : '<=';
fragment EQ  : '==';
fragment NE  : '!=';
COMPARISON   : GT | GE | LT | LE | EQ | NE;
NOT          : '!';
ASSIG        : '=';

LPAREN  : '(';
RPAREN  : ')';
LBRACK  : '[';
RBRACK  : ']';
LBRACE  : '{';
RBRACE  : '}';

fragment DIGIT  : [0-9];
fragment INT4   : DIGIT DIGIT DIGIT DIGIT;

fragment QUOTES_SIMPLE  : '\'';
fragment QUOTES_DOUBLE  : '"';
fragment ANYTHING       : .*?;

fragment JAN  : [Jj][Aa][Nn];
fragment FEB  : [Ff][Ee][Bb];
fragment MAR  : [Mm][Aa][Rr];
fragment APR  : [Aa][Pp][Rr];
fragment MAY  : [Mm][Aa][Yy];
fragment JUN  : [Jj][Uu][Nn];
fragment JUL  : [Jj][Uu][Ll];
fragment AUG  : [Aa][Uu][Gg];
fragment SEP  : [Ss][Ee][Pp];
fragment OCT  : [Oo][Cc][Tt];
fragment NOV  : [Nn][Oo][Vv];
fragment DEC  : [Dd][Ee][Cc];

fragment MONTH_TYPE  : JAN | FEB | MAR | APR | MAY | JUN | JUL | AUG | SEP | OCT | NOV | DEC;

fragment DATE_DAY    : [0-3]? DIGIT;
fragment DATE_MONTH  : ([0-1]? DIGIT | MONTH_TYPE);
fragment DATE_YEAR   : INT4;

//##########################################################################################################################
//#########    Types                                                                                       #################
//##########################################################################################################################

BOOLEAN  : ('true' | 'false');
INTEGER  : DIGIT+;
DECIMAL  : INTEGER POINT INTEGER;

STRING
  : QUOTES_SIMPLE ANYTHING QUOTES_SIMPLE
  | QUOTES_DOUBLE ANYTHING QUOTES_DOUBLE
  ;

DATE  : DATE_YEAR '/' DATE_MONTH '/' DATE_DAY;

// COMMENT and WS are stripped from the output token stream by sending to a different channel 'skip'
COMMENT_LINE   : '//' ANYTHING '\r'? '\n'   -> channel(OFF_CHANNEL);
COMMENT_BLOCK  : '/*' ANYTHING '*/'         -> channel(OFF_CHANNEL);
WHITESPACE     : [ \f\n\r\t\u000C]+         -> channel(OFF_CHANNEL);
//NEWLINE       : '\r'? '\n';

IDENTIFIER: Letter LetterOrDigit*;

fragment Digits
  : [0-9] ([0-9_]* [0-9])?
  ;
    
fragment LetterOrDigit
  : Letter
  | [0-9]
  ;
    
fragment Letter
  : [a-zA-Z$_] // these are the "java letters" below 0x7F
  | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
  | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
  ;
