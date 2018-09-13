package com.github.thiagogarbazza.expressionresolve.runnable;

import java.math.BigDecimal;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParserBaseVisitor;

import lombok.Getter;

abstract class AbstractVisitors extends ExpressionParserBaseVisitor<Object> {

    @Getter
    private final ExpressionContext executionContext;

    public AbstractVisitors(final ExpressionContext expressionContext) {
        this.executionContext = expressionContext;
    }

    protected final Object resultNormatize(final Integer result) {
        return resultNormatize(BigDecimal.valueOf(result));
    }

    protected final Object resultNormatize(final BigDecimal result) {
        return result;
    }
}