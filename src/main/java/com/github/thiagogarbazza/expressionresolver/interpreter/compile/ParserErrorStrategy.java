package com.github.thiagogarbazza.expressionresolver.interpreter.compile;

import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.DefaultErrorStrategy;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class ParserErrorStrategy extends DefaultErrorStrategy {

  private static final ParserErrorStrategy INSTANCE = new ParserErrorStrategy();

  public static ParserErrorStrategy getParserErrorStrategy() {
    return INSTANCE;
  }
}
