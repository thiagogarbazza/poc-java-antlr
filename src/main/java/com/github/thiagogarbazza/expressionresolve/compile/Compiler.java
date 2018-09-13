package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;
import org.antlr.v4.runtime.tree.ParseTree;

public final class Compiler {

  private final LexerBuilder lexerBuilder = new LexerBuilder();
  private final ParserBuilder parserBuilder = new ParserBuilder();
  private final TreeBuilder treeBuilder = new TreeBuilder();

  public ParseTree compile(Expression expression) {
    ExpressionInputStream in = new ExpressionInputStream(expression);
    ExpressionLexer lexer = lexerBuilder.build(in);
    ExpressionParser parser = parserBuilder.build(lexer);
    ParseTree tree = treeBuilder.build(parser);
    return tree;
  }
}
