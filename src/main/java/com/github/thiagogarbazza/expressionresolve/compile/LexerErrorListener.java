package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import static com.github.thiagogarbazza.expressionresolve.domain.PropertieUtil.getPropertie;
import static java.lang.String.format;

final class LexerErrorListener extends BaseErrorListener {

  @Override
  public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
    final int charPositionInLine, final String message, final RecognitionException e) {
    throw new SyntaxException(format(getPropertie("syntax.error"), line, charPositionInLine, message), e);
  }
}
