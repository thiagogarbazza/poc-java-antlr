package com.github.thiagogarbazza.expressionresolve.runnable;

import java.math.BigDecimal;

import org.apache.commons.lang3.BooleanUtils;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanANDContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanArithmeticComparisonContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanGroupedByContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanNegationContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanORContext;

abstract class BooleanVisitors extends CalendarVisitors {

    public BooleanVisitors(final ExpressionContext expressionContext) {
        super(expressionContext);
    }

    @Override
    public final Object visitBooleanArithmeticComparison(final BooleanArithmeticComparisonContext ctx) {
        final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
        final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
        Comparison comparison = Comparison.findByOperator(ctx.op.getText());
        return comparison.compare(left, right);
    }

    @Override
    public final Object visitBooleanNegation(final BooleanNegationContext ctx) {
        Boolean value = (Boolean) visit(ctx.booleanExpression());
        return BooleanUtils.negate(value);
    }

    @Override
    public Object visitBooleanGroupedBy(final BooleanGroupedByContext ctx) {
        Boolean value = (Boolean) visit(ctx.booleanExpression());
        return value;
    }

    @Override
    public Object visitBooleanAND(final BooleanANDContext ctx) {
        Boolean left = (Boolean) visit(ctx.booleanExpression(0));
        Boolean right = (Boolean) visit(ctx.booleanExpression(1));
        return left && right;

    }

    @Override
    public Object visitBooleanOR(final BooleanORContext ctx) {
        Boolean left = (Boolean) visit(ctx.booleanExpression(0));
        Boolean right = (Boolean) visit(ctx.booleanExpression(1));
        return left || right;
    }
}
