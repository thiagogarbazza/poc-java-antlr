Feature: Function sqrt
  It is necessary that expression-resolver perform math sqrt.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 9

  Scenario Outline: 01. Perform function sqrt.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return sqrt(9);           | 3.0             |
      | return sqrt(16);          | 4.0             |
      | return sqrt($N);          | 3.0             |
