package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.exception.SyntaxException;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import static com.github.thiagogarbazza.expressionresolver.domain.PropertieUtil.getPropertie;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
final class LexerErrorListener extends BaseErrorListener {

  private static final LexerErrorListener INSTANCE = new LexerErrorListener();

  @Override
  public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
    final int charPositionInLine, final String message, final RecognitionException e) {
    throw new SyntaxException(format(getPropertie("syntax.error"), line, charPositionInLine, message), e);
  }

  public static LexerErrorListener getLexerErrorListener() {
    return INSTANCE;
  }
}
