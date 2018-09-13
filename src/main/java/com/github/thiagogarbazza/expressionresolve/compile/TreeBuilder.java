package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;
import lombok.extern.apachecommons.CommonsLog;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.atn.PredictionMode.LL;
import static org.antlr.v4.runtime.atn.PredictionMode.SLL;

@CommonsLog
final class TreeBuilder {

  public ParseTree build(final ExpressionParser parser) {
    ParseTree tree;
    try {
             /*
             * Tenta inicialmente utilizar o modo de predição mais simples e rápido:
             * o SLL.
             */
      tree = getParseTree(parser, SLL);
            /*
             * Se chegar até aqui, não há nenhum erro sintático e o modo SLL foi
             * suficiente, não havendo necessidade de se tentar o LL(*).
             */
    } catch (Exception e) {
            /* Tenta agora o modo de predição mais completo: o LL(*) */
      tree = getParseTree(parser, LL);
    }
    return tree;
  }

  private ParseTree getParseTree(final ExpressionParser parser, final PredictionMode predictionMode) {
    parser.reset();
    parser.getInterpreter().setPredictionMode(predictionMode);
    return parser.parse();
  }
}
