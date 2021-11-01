Feature: Function compare
  It is necessary that expression-resolver perform compare values.

  Background:
    Given the variable named $D is in the expression's execution context with the value: "1900-12-31"
    And the variable named $OTHER_VALUE_D is in the expression's execution context with the value: "2021-01-01"

  Scenario Outline: 01. Perform "compare dates".
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                         | result expected |
      | return compareDate('1900-12-31', '2021-01-01');   | -1              |
      | return compareDate('2018-12-31', '2018-12-31');   | 0               |
      | return compareDate('2021-01-01', '1900-12-31');   | 1               |
      | return compareDate($D, '2021-01-01');             | -1              |
      | return compareDate($OTHER_VALUE_D, '1900-12-31'); | 1               |
      | return compareDate($OTHER_VALUE_D, $D);           | 1               |
