Feature: Function year
  It is necessary that expression-resolver perform year.

  Background:
    Given the variable named $D is in the expression's execution context with the value: "1900-12-31"

  Scenario Outline: 01. Perform function year.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed  | result expected |
      | return year('1984-07-20'); | 1984            |
      | return year('2017-09-06'); | 2017            |
      | return year($D);           | 1900            |
