Feature: Function sin
  It is necessary that expression-resolver perform math sin.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 45

  Scenario Outline: 01. Perform function sin.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return sin(45);           | 0.8509035245341184 |
      | return sin(90);           | 0.8939966636005579 |
      | return sin($N);           | 0.8509035245341184 |
