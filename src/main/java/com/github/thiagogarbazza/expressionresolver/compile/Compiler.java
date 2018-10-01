package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.compile.ExpressionLexerBuilder.getExpressionLexerBuilder;
import static com.github.thiagogarbazza.expressionresolver.compile.ExpressionParserBuilder.getExpressionParserBuilder;
import static com.github.thiagogarbazza.expressionresolver.compile.ParseTreeBuilder.getParseTreeBuilder;

public final class Compiler {

  public ParseTree compile(Expression expression) {
    ExpressionLexer lexer = getExpressionLexerBuilder().build(expression);
    ExpressionParser parser = getExpressionParserBuilder().build(lexer);

    return getParseTreeBuilder().build(parser);
  }
}
