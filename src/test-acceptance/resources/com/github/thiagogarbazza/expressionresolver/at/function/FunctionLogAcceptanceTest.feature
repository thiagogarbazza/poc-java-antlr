Feature: Function log
  It is necessary that expression-resolver perform math log.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 4

  Scenario Outline: 01. Perform function log.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return log(4);            | 0.6020599913279624 |
      | return log(5);            | 0.6989700043360189 |
      | return log($N);           | 0.6020599913279624 |
