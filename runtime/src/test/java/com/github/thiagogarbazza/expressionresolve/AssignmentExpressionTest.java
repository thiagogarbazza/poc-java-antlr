package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class AssignmentExpressionTest extends AbstractFunctionsTest {

    @Test
    public void testAssignmentNumberInteger() {
        final Expression expression = new Expression("A=20;");
        assertExpression(expression, V_20);
    }

    @Test
    public void testAssignmentNumberDouble() {
        final Expression expression = new Expression("A=29.99;");
        assertExpression(expression, new Result(BigDecimal.valueOf(29.99)));
    }

    @Test
    public void testAssignmentBoolean() {
        final Expression expression = new Expression("A=true;");
        assertExpression(expression, TRUE);

    }

    @Test
    public void testAssignmentNumberAndUseInMathematicsOperation() {
        final Expression expression = new Expression("A=5; A^2");
        assertExpression(expression, V_25);
    }

    @Test
    public void testAssignmentNumberAndUseInMathematicsFunction() {
        final Expression expression = new Expression("A=90; cos(A)");
        assertExpression(expression, COS_90);
    }

    @Test
    public void testAssignmentResultMathematicsFunctionAndUseInMathematicsOperation() {
        final Expression expression = new Expression("A=day(2015/05/20); 100/A");
        assertExpression(expression, new Result(BigDecimal.valueOf(5)));
    }

    @Test
    public void test2AssignmentsAndUseInMathematicsOperation() {
        final Expression expression = new Expression("A=96;B=4; A+B");
        assertExpression(expression, V_100);
    }
}