package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.CommonTokenStream;

import static com.github.thiagogarbazza.expressionresolver.compile.LexerErrorListener.getLexerErrorListener;
import static com.github.thiagogarbazza.expressionresolver.compile.ParserErrorStrategy.getParserErrorStrategy;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ExpressionParserBuilder {

  private static final ExpressionParserBuilder INSTANCE = new ExpressionParserBuilder();

  public ExpressionParser build(final ExpressionLexer lexer) {
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    ExpressionParser parser = new ExpressionParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(getLexerErrorListener());
    parser.setErrorHandler(getParserErrorStrategy());

    return parser;
  }

  public static ExpressionParserBuilder getExpressionParserBuilder() {
    return INSTANCE;
  }
}
