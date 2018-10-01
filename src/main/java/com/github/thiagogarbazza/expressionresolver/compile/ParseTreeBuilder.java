package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;
import static org.antlr.v4.runtime.atn.PredictionMode.LL;
import static org.antlr.v4.runtime.atn.PredictionMode.SLL;

@CommonsLog
@NoArgsConstructor(access = PRIVATE)
final class ParseTreeBuilder {

  private static final ParseTreeBuilder INSTANCE = new ParseTreeBuilder();

  public ParseTree build(final ExpressionParser parser) {
    try {
      return getParseTree(parser, SLL);
    } catch (Exception e) {
      return getParseTree(parser, LL);
    }
  }

  private ParseTree getParseTree(final ExpressionParser parser, final PredictionMode predictionMode) {
    parser.reset();
    parser.getInterpreter().setPredictionMode(predictionMode);
    return parser.parse();
  }

  public static ParseTreeBuilder getParseTreeBuilder() {
    return INSTANCE;
  }
}
