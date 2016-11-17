package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class BooleanExpressionTest extends AbstractFunctionsTest {

    @Test
    public void testPrimitiveBooleanTrue() {
        final Expression expression = new Expression("true");
        assertExpression(expression, TRUE);
    }

    @Test
    public void testPrimitiveBooleanFalse() {
        final Expression expression = new Expression("false");
        assertExpression(expression, FALSE);
    }

    @Test
    public void testConjunctionANDResultTrue(){
        final Expression expression = new Expression("true && true && true");
        assertExpression(expression, TRUE);
    }

    @Test
    public void testConjunctionANDResultFalse(){
        final Expression expression = new Expression("true && false && true");
        assertExpression(expression, FALSE);
    }

    @Test
    public void testConjunctionORResultTrue(){
        final Expression expression = new Expression("true || false || false");
        assertExpression(expression, TRUE);
    }

    @Test
    public void testConjunctionORResultFalse(){
        final Expression expression = new Expression("false || false");
        assertExpression(expression, FALSE);
    }
}