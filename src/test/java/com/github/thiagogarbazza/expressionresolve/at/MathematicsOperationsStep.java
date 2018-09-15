package com.github.thiagogarbazza.expressionresolve.at;

import com.github.thiagogarbazza.expressionresolve.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.domain.Result;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MathematicsOperationsStep {

  private ExpressionContext context;
  private Expression expression;
  private Result expressionResult;
  private ExpressionInterpreter interpreter;

  @Before
  public void before() {
    interpreter = new ExpressionInterpreter();
    context = new ExpressionContext();
  }

  @Given("^Send the expression \"([^\"]*)\".$")
  public void givenSendTheExpression(String expression) throws Throwable {
    this.expression = new Expression(expression);
  }

  @Then("^I should have resulted the number: \"([^\"]*)\".$")
  public void thenIShouldHaveResultedTheNumber(String expressionResult) throws Throwable {
    Result expected = new Result(new BigDecimal(expressionResult));
    assertEquals(expected, this.expressionResult);
  }

  @When("^I ask what the result is\\?$")
  public void whenIAskWhatTheResultIs() throws Throwable {
    this.expressionResult = interpreter.toInterpret(this.expression, this.context);
  }
}
