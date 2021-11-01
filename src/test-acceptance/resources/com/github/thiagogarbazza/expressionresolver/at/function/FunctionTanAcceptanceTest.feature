Feature: Function tan
  It is necessary that expression-resolver perform math tan.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 45

  Scenario Outline: 01. Perform function tan.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return tan(45);           | 1.6197751905438615 |
      | return tan(90);           | -1.995200412208242 |
      | return tan($N);           | 1.6197751905438615 |
