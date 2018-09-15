package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class IfExpressionTest extends AbstractFunctionsTest {

  @Test
  public void testComplexIfConditionFalse() {
    final Expression expression = new Expression("if(month(2015/03/20)==20){A=10;} else {A=15;} A");
    assertExpression(expression, RESULT_15);
  }

  @Test
  public void testComplexIfConditionTrue() {
    final Expression expression = new Expression("if(month(2015/03/20)==3){A=10;} else {A=15;} A");
    assertExpression(expression, RESULT_10);
  }

  @Test
  public void testIfElseConditionFalse() {
    final Expression expression = new Expression("A=100; if(false){A=20;} else {A=50;} A");
    assertExpression(expression, RESULT_50);
  }

  @Test
  public void testIfElseConditionTrue() {
    final Expression expression = new Expression("A=100; if(true){A=20;} else {A=10;} A");
    assertExpression(expression, RESULT_20);
  }

  @Test
  public void testIfElseIfConditionFalse() {
    final Expression expression = new Expression("A=100; if(false){A=10;} else if (false) {A=50;} A");
    assertExpression(expression, RESULT_100);
  }

  @Test
  public void testIfElseIfConditionTrue() {
    final Expression expression = new Expression("A=100; if(false){A=10;} else if (true) {A=50;} A");
    assertExpression(expression, RESULT_50);
  }

  @Test
  public void testIfElseIfElseConditionFalse() {
    final Expression expression = new Expression("A=100; if(false){A=10;} else if (false) {A=50;} else {A=15;} A");
    assertExpression(expression, RESULT_15);
  }

  @Test
  public void testIfElseIfElseConditionTrue() {
    final Expression expression = new Expression("A=100; if(false){A=10;} else if (true) {A=50;} else {A=15;} A");
    assertExpression(expression, RESULT_50);
  }

  @Test
  public void testIfNotInformBlock() {
    final Expression expression = new Expression("if(true)");
    assertException(expression, "In position 1:8 no viable alternative at input '<EOF>'.");
  }

  @Test
  public void testIfNotInformBlock2() {
    final Expression expression = new Expression("if(true){}");
    assertException(expression, "In position 1:9 no viable alternative at input '{}'.");
  }

  @Test
  public void testIfNotInformConditionAndBlock() {
    final Expression expression = new Expression("if");
    assertException(expression, "In position 1:2 no viable alternative at input '<EOF>'.");
  }

  @Test
  public void testIfNotInformConditionAndBlock2() {
    final Expression expression = new Expression("if()");
    assertException(expression, "In position 1:3 no viable alternative at input '()'.");
  }

  @Test
  public void testSimpleIfConditionFalse() {
    final Expression expression = new Expression("A=100; if(false){A=20;} A");
    assertExpression(expression, RESULT_100);
  }

  @Test
  public void testSimpleIfConditionTrue() {
    final Expression expression = new Expression("A=100; if(true){A=20;} A");
    assertExpression(expression, RESULT_20);
  }
}
