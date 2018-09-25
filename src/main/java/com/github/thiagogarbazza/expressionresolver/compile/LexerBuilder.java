package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;

final class LexerBuilder {

  public ExpressionLexer build(final ExpressionInputStream in) {
    ExpressionLexer lexer = new ExpressionLexer(in);
    lexer.removeErrorListeners();
    lexer.addErrorListener(new LexerErrorListener());
    return lexer;
  }
}
