/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */
package com.github.thiagogarbazza.expressionresolve.compile;

import org.antlr.v4.runtime.CommonTokenStream;

import com.github.thiagogarbazza.expressionresolve.parser.ExpressionLexer;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;

final class ParserBuilder {

    public ExpressionParser build(final ExpressionLexer lexer) {
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        ExpressionParser parser = new ExpressionParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new LexerErrorListener());
        parser.setErrorHandler(new ParserErrorStrategy());
        return parser;
    }
}
