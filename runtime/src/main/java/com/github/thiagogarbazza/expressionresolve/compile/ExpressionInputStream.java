/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software é confidencial e propriedade do Banco Central do Brasil.
 * Não é permitida sua distribuição ou divulgação do seu conteúdo sem
 * expressa autorização do Banco Central.
 * Este arquivo contém informações proprietárias.
 */
package com.github.thiagogarbazza.expressionresolve.compile;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

final class ExpressionInputStream extends ANTLRInputStream {

    public ExpressionInputStream(Expression expression) {
        super(expression.getValue());
    }
}
