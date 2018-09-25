package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import org.antlr.v4.runtime.CommonTokenStream;

final class ParserBuilder {

  public ExpressionParser build(final ExpressionLexer lexer) {
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    ExpressionParser parser = new ExpressionParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(new LexerErrorListener());
    parser.setErrorHandler(new ParserErrorStrategy());
    return parser;
  }
}
