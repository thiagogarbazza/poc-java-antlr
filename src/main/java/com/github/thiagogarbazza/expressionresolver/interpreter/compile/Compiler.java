package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ExpressionLexerBuilder.getExpressionLexerBuilder;
import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ExpressionParserBuilder.getExpressionParserBuilder;
import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.ParseTreeBuilder.getParseTreeBuilder;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Compiler {

  private static final Compiler INSTANCE = new Compiler();

  public ParseTree compile(Expression expression) {
    ExpressionLexer lexer = getExpressionLexerBuilder().build(expression);
    ExpressionParser parser = getExpressionParserBuilder().build(lexer);

    return getParseTreeBuilder().build(parser);
  }

  public static Compiler getCompiler() {
    return INSTANCE;
  }
}
