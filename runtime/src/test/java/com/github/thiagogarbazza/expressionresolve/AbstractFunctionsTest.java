package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public abstract class AbstractFunctionsTest {

    protected static final Result FALSE = new Result(Boolean.FALSE);
    protected static final Result TRUE = new Result(Boolean.TRUE);
    protected static final Result V_8_NEGATIVE = new Result(BigDecimal.valueOf(-8));
    protected static final Result V_9 = new Result(BigDecimal.valueOf(9));
    protected static final Result V_10 = new Result(BigDecimal.valueOf(10));
    protected static final Result V_15 = new Result(BigDecimal.valueOf(15));
    protected static final Result V_20 = new Result(BigDecimal.valueOf(20));
    protected static final Result V_25 = new Result(BigDecimal.valueOf(25));
    protected static final Result V_50 = new Result(BigDecimal.valueOf(50));
    protected static final Result V_100 = new Result(BigDecimal.valueOf(100));
    protected static final Calendar CURRENT = Calendar.getInstance();

    private final ExpressionInterpreter interpreter = new ExpressionInterpreter();
    protected ExpressionContext EXPRESSION_CONTEXT;

    protected static final Result COS_90 = new Result(BigDecimal.valueOf(-0.4480736161291702));

    @Before
    public void setUpAbstract() {
        EXPRESSION_CONTEXT = new ExpressionContext();
    }

    protected void assertException(final Expression expression, final String message) {
        try {
            interpreter.toInterpret(expression, EXPRESSION_CONTEXT);
            Assert.fail("Exception expected!");
        } catch (Exception e) {
            Assert.assertEquals("Exception message invalid.", message, e.getMessage());
        }
    }

    protected void assertExpression(final Expression expression, final Result expected) {
        final Result result = interpreter.toInterpret(expression, EXPRESSION_CONTEXT);
        Assert.assertEquals("Result value invalid.", expected, result);
    }


}