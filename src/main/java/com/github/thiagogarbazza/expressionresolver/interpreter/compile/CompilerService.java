package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;

@Service
@CommonsLog
@RequiredArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class CompilerService {

  private final ExpressionLexerFactory expressionLexerFactory;
  private final ExpressionParserFactory expressionParserFactory;
  private final ParseTreeFactory parseTreeFactory;

  public ParseTree compile(final String expression) {
    try {
      return compile(expression, PredictionMode.SLL);
    } catch (Exception e) {
      log.trace("Error trying to compile using the SLL prediction mode.", e);
      return compile(expression, PredictionMode.LL);
    }
  }

  private ParseTree compile(final String expression, final PredictionMode predictionMode) {
    final ExpressionLexer lexer = expressionLexerFactory.create(expression);
    final ExpressionParser parser = expressionParserFactory.create(lexer);

    return parseTreeFactory.create(parser, predictionMode);
  }
}
