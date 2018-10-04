package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import lombok.NoArgsConstructor;

import static com.github.thiagogarbazza.expressionresolver.compile.LexerErrorListener.getLexerErrorListener;
import static lombok.AccessLevel.PRIVATE;
import static org.antlr.v4.runtime.CharStreams.fromString;

@NoArgsConstructor(access = PRIVATE)
final class ExpressionLexerBuilder {

  private static final ExpressionLexerBuilder INSTANCE = new ExpressionLexerBuilder();

  public ExpressionLexer build(final Expression expression) {
    ExpressionLexer lexer = new ExpressionLexer(fromString(expression.getValue()));
    lexer.removeErrorListeners();
    lexer.addErrorListener(getLexerErrorListener());

    return lexer;
  }

  public static ExpressionLexerBuilder getExpressionLexerBuilder() {
    return INSTANCE;
  }
}
