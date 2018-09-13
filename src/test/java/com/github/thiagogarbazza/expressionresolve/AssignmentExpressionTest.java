package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class AssignmentExpressionTest extends AbstractFunctionsTest {

  @Test
  public void test2AssignmentsAndUseInMathematicsOperation() {
    final Expression expression = new Expression("A=96;B=4; A+B");
    assertExpression(expression, RESULT_100);
  }

  @Test
  public void testAssignmentBoolean() {
    final Expression expression = new Expression("A=true;");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testAssignmentNumberAndUseInMathematicsFunction() {
    final Expression expression = new Expression("A=90; cos(A)");
    assertExpression(expression, RESULT_COS_90);
  }

  @Test
  public void testAssignmentNumberAndUseInMathematicsOperation() {
    final Expression expression = new Expression("A=5; A^2");
    assertExpression(expression, RESULT_25);
  }

  @Test
  public void testAssignmentNumberDoubleNegative() {
    final Expression expression = new Expression("A=-0.4480736161291702;");
    assertExpression(expression, RESULT_COS_90);
  }

  @Test
  public void testAssignmentNumberDoublePositive() {
    final Expression expression = new Expression("A=0.8939966636005579;");
    assertExpression(expression, RESULT_SIN_90);
  }

  @Test
  public void testAssignmentNumberInteger() {
    final Expression expression = new Expression("A=20;");
    assertExpression(expression, RESULT_20);
  }

  @Test
  public void testAssignmentResultMathematicsFunctionAndUseInMathematicsOperation() {
    final Expression expression = new Expression("A=day(2015/05/20); 100/A");
    assertExpression(expression, RESULT_5);
  }
}
