/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */
package com.github.thiagogarbazza.expressionresolve.compile;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;

import static com.github.thiagogarbazza.expressionresolve.domain.PropertieUtil.getPropertie;
import static java.lang.String.format;

final class LexerErrorListener extends BaseErrorListener {

    @Override
    public void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
        final int charPositionInLine, final String message, final RecognitionException e) {
        throw new SyntaxException(format(getPropertie("syntax.error"), line, charPositionInLine, message), e);
    }
}