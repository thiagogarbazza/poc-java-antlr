package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.compile.ExpressionLexerBuilder.getExpressionLexerBuilder;
import static com.github.thiagogarbazza.expressionresolver.compile.ParseTreeBuilder.getParseTreeBuilder;

public final class Compiler {

  private final ParserBuilder parserBuilder = new ParserBuilder();

  public ParseTree compile(Expression expression) {
    ExpressionLexer lexer = getExpressionLexerBuilder().build(expression);
    ExpressionParser parser = parserBuilder.build(lexer);

    return getParseTreeBuilder().build(parser);
  }
}
