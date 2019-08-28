package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.Result;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collection;

import static com.github.thiagogarbazza.expressionresolver.at.steps.ValueObjectUtil.stringToCollectionValueObject;
import static com.github.thiagogarbazza.expressionresolver.at.steps.ValueObjectUtil.stringToValueObject;
import static org.junit.Assert.assertEquals;

public class ExpressionInterpreterStep {

  private ExpressionContext context;
  private Expression expression;
  private Result expressionResult;
  private ExpressionInterpreter interpreter;

  @Before
  public void before() {
    interpreter = ExpressionInterpreter.getExpressionInterpreter();
    context = new ExpressionContext();
  }

  @Given("Send the expression (.*).")
  public void givenSendTheExpression(String expression) {
    this.expression = new Expression(expression);
  }

  @Given("the (\\$.*) variable is in context with the collection (.*).")
  public void givenTheVariableIsInContextWithTheCollection(String nameVariable, String value) {
    context.set(nameVariable, stringToCollectionValueObject(value));
  }

  @Given("the (\\$.*) variable is in context with the value (.*).")
  public void givenTheVariableIsInContextWithTheValue(String nameVariable, String value) {
    context.set(nameVariable, stringToValueObject(value));
  }

  @Then("I should have resulted (.*).")
  public void thenIShouldHaveResulted(String result) {
    assertEquals(new Result(stringToValueObject(result)), this.expressionResult);
  }

  @Then("I should have the collection as resulted (.*).")
  public void thenIShouldHaveResultedCollection(String result) {
    final Collection<Object> value = stringToCollectionValueObject(result);

    assertEquals(new Result(value), this.expressionResult);
  }

  @When("I ask what the result is?")
  public void whenIAskWhatTheResultIs() {
    this.expressionResult = interpreter.toInterpret(this.expression, this.context);
  }
}
