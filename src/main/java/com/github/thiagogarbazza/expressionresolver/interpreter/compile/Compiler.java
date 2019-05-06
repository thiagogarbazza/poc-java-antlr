package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ExpressionLexerBuilder.getExpressionLexerBuilder;
import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ExpressionParserBuilder.getExpressionParserBuilder;
import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ParseTreeBuilder.getParseTreeBuilder;
import static lombok.AccessLevel.PRIVATE;

@CommonsLog
@NoArgsConstructor(access = PRIVATE)
public class Compiler {

  private static final Compiler INSTANCE = new Compiler();

  public ParseTree compile(final Expression expression) {
    try {
      return compile(expression, PredictionMode.SLL);
    } catch (Exception e) {
      log.trace("Error trying to compile using the SLL prediction mode.", e);
      return compile(expression, PredictionMode.LL);
    }
  }

  private ParseTree compile(final Expression expression, final PredictionMode predictionMode) {
    ExpressionLexer lexer = getExpressionLexerBuilder().build(expression);
    ExpressionParser parser = getExpressionParserBuilder().build(lexer);

    return getParseTreeBuilder().build(parser, predictionMode);
  }

  public static Compiler getCompiler() {
    return INSTANCE;
  }
}
