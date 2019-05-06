package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;

@CommonsLog
@NoArgsConstructor(access = PRIVATE)
final class ParseTreeBuilder {

  private static final ParseTreeBuilder INSTANCE = new ParseTreeBuilder();

  public ParseTree build(final ExpressionParser parser, final PredictionMode predictionMode) {
    parser.reset();
    parser.getInterpreter().setPredictionMode(predictionMode);

    return parser.parse();
  }

  public static ParseTreeBuilder getParseTreeBuilder() {
    return INSTANCE;
  }
}
