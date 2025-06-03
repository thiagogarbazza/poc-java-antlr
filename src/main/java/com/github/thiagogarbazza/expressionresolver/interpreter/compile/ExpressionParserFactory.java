package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.CommonTokenStream;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.LexerErrorListener.getLexerErrorListener;
import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ParserErrorStrategy.getParserErrorStrategy;
import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
class ExpressionParserFactory {

  public ExpressionParser create(final ExpressionLexer lexer) {
    final CommonTokenStream tokens = new CommonTokenStream(lexer);
    final ExpressionParser parser = new ExpressionParser(tokens);

    parser.removeErrorListeners();
    parser.addErrorListener(getLexerErrorListener());
    parser.setErrorHandler(getParserErrorStrategy());

    return parser;
  }
}
