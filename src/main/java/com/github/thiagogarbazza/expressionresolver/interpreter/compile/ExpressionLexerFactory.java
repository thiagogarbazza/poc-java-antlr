package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.LexerErrorListener.getLexerErrorListener;
import static lombok.AccessLevel.PRIVATE;
import static org.antlr.v4.runtime.CharStreams.fromString;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
class ExpressionLexerFactory {

  public ExpressionLexer create(final String expression) {
    final ExpressionLexer lexer = new ExpressionLexer(fromString(expression));
    lexer.removeErrorListeners();
    lexer.addErrorListener(getLexerErrorListener());

    return lexer;
  }
}
