Feature: Function acos
  It is necessary that expression-resolver perform math acos.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 0.9

  Scenario Outline: 01. Perform function cos.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected     |
      | return acos(0.5);         | 1.0471975511965979  |
      | return acos(0.9);         | 0.45102681179626236 |
      | return acos($N);          | 0.45102681179626236 |
