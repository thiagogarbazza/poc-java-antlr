Feature: Function asin
  It is necessary that expression-resolver perform math asin.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 0.5

  Scenario Outline: 01. Perform function asin.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return asin(0.5);         | 0.5235987755982989 |
      | return asin(0.8);         | 0.9272952180016123 |
      | return asin($N);          | 0.5235987755982989 |
