Feature: Function month
  It is necessary that expression-resolver perform month.

  Background:
    Given the variable named $D is in the expression's execution context with the value: "1900-12-31"

  Scenario Outline: 01. Perform function month.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed   | result expected |
      | return month('1984-07-20'); | 7               |
      | return month('2017-09-06'); | 9               |
      | return month($D);           | 12              |
