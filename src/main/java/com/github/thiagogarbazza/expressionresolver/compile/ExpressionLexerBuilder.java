package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ExpressionLexerBuilder {

  private static final ExpressionLexerBuilder INSTANCE = new ExpressionLexerBuilder();

  public ExpressionLexer build(final ExpressionInputStream in) {
    ExpressionLexer lexer = new ExpressionLexer(in);
    lexer.removeErrorListeners();
    lexer.addErrorListener(new LexerErrorListener());
    return lexer;
  }

  public static ExpressionLexerBuilder getExpressionLexerBuilder() {
    return INSTANCE;
  }
}
