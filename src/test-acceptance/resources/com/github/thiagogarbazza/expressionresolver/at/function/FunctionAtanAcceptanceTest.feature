Feature: Function atan
  It is necessary that expression-resolver perform math atan.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 0.5

  Scenario Outline: 01. Perform function atan.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return atan(0.5);         | 0.4636476090008061 |
      | return atan(0.8);         | 0.6747409422235527 |
      | return atan($N);          | 0.4636476090008061 |
