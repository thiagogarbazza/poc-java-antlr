package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
class ParseTreeFactory {

  public ParseTree create(final ExpressionParser parser, final PredictionMode predictionMode) {
    parser.reset();
    parser.getInterpreter().setPredictionMode(predictionMode);

    return parser.parse();
  }
}
