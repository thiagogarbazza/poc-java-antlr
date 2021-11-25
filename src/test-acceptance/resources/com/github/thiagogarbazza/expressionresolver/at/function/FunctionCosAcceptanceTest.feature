Feature: Function cos
  It is necessary that expression-resolver perform math cos.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 45

  Scenario Outline: 01. Perform function cos.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected     |
      | return cos(45);           | 0.5253219888177297  |
      | return cos(90);           | -0.4480736161291701 |
      | return cos($N);           | 0.5253219888177297  |
