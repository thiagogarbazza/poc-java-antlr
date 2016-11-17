/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */
package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.parser.ExpressionLexer;

final class LexerBuilder {

    public ExpressionLexer build(final ExpressionInputStream in) {
        ExpressionLexer lexer = new ExpressionLexer(in);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new LexerErrorListener());
        return lexer;
    }
}
