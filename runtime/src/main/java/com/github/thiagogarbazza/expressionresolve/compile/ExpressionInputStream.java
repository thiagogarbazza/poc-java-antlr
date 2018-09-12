package com.github.thiagogarbazza.expressionresolve.compile;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

final class ExpressionInputStream extends ANTLRInputStream {

    public ExpressionInputStream(Expression expression) {
        super(expression.getValue());
    }
}
