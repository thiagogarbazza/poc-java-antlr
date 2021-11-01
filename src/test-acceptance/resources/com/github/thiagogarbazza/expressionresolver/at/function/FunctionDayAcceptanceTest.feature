Feature: Function day
  It is necessary that expression-resolver perform day.

  Background:
    Given the variable named $D is in the expression's execution context with the value: "1900-12-31"

  Scenario Outline: 01. Perform function day.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return day('1984-07-20'); | 20              |
      | return day('2017-09-06'); | 6               |
      | return day($D);           | 31              |
