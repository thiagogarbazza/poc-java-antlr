Feature: Function date
  It is necessary that expression-resolver perform date.

  Background:
    Given the variable named $DAY is in the expression's execution context with the value: 1
    And the variable named $MONTH is in the expression's execution context with the value: 12
    And the variable named $YEAR is in the expression's execution context with the value: 2018

  Scenario Outline: 01. Perform function date.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed         | result expected |
      | return date(1984, 07, 20);        | "1984-07-20"    |
      | return date(2018, 12, 31);        | "2018-12-31"    |
      | return date($YEAR, $MONTH, $DAY); | "2018-12-01"    |
