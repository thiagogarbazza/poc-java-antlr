package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.CommonTokenStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ExpressionParserBuilder {

  private static final ExpressionParserBuilder INSTANCE = new ExpressionParserBuilder();

  public ExpressionParser build(final ExpressionLexer lexer) {
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    ExpressionParser parser = new ExpressionParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(new LexerErrorListener());
    parser.setErrorHandler(new ParserErrorStrategy());
    return parser;
  }

  public static ExpressionParserBuilder getExpressionParserBuilder() {
    return INSTANCE;
  }
}
