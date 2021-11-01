package com.github.thiagogarbazza.expressionresolver.at.steps;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.Result;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.github.thiagogarbazza.expressionresolver.AssertionsCustom.assertNotThrowException;
import static com.github.thiagogarbazza.expressionresolver.AssertionsCustom.assertResultEquals;
import static com.github.thiagogarbazza.expressionresolver.AssertionsCustom.assertThrowException;
import static com.github.thiagogarbazza.expressionresolver.at.steps.ConverterData.stringToValue;

public class ExpressionInterpreterStep {

  private Exception exception;
  private String expression;
  private ExpressionContext expressionContext;
  private ExpressionInterpreter expressionInterpreter;
  private Result result;

  @Before
  public void before() {
    this.exception = null;
    this.expression = null;
    this.result = null;
    this.expressionInterpreter = ExpressionInterpreter.getExpressionInterpreter();
    this.expressionContext = new ExpressionContext();
  }

  @Given("send the expression: {}")
  public void givenSendTheExpression(final String expressao) {
    this.expression = expressao;
  }

  @Given("send the expression:")
  public void givenSendTheExpressionDocString(final String expressao) {
    this.expression = expressao;
  }

  @Given("the variable named {variable} is in the expression's execution context with the value: {}")
  public void givenTheVariableNamedIsInTheExpressionSExecutionContextWithTheValue(final String variable, final String value) {
    expressionContext.set(variable, stringToValue(value));
  }

  @Given("the variable named {variable} is in the expression's execution context with the value:")
  public void givenTheVariableNamedIsInTheExpressionSExecutionContextWithTheValueDocString(final String variable, final String value) {
    expressionContext.set(variable, stringToValue(value));
  }

  @Then("will should not to get error message")
  public void thenWillShouldNotToGetErrorMessage() {
    assertNotThrowException(this.exception);
  }

  @Then("will should to get the error message: {}")
  public void thenWillShouldToGetTheErrorMessage(final String mensagem) {
    assertThrowException(mensagem, this.exception);
  }

  @Then("will should to get the result: {}")
  public void thenWillShouldToGetTheResult(final String expected) {
    assertNotThrowException(this.exception);
    assertResultEquals(new Result(stringToValue(expected)), this.result);
  }

  @Then("will should to get the result:")
  public void thenWillShouldToGetTheResultDocString(final String expected) {
    assertNotThrowException(this.exception);
    assertResultEquals(new Result(stringToValue(expected)), this.result);
  }

  @When("to request expression validation")
  public void whenToRequestExpressionValidation() {
    try {
      expressionInterpreter.toValid(this.expression);
    } catch (Exception e) {
      this.exception = e;
    }
  }

  @When("to request expression execution")
  public void whenToRunTheExpression() {
    try {
      this.result = expressionInterpreter.toInterpret(this.expression, this.expressionContext);
    } catch (Exception e) {
      this.exception = e;
    }
  }
}
