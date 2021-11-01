Feature: Function dates from range
  It is necessary that expression-resolver perform date.

  Background:
    Given the variable named $D_A is in the expression's execution context with the value: "1900-12-31"
    And the variable named $D_B is in the expression's execution context with the value: "1901-01-02"

  Scenario Outline: 01. Perform function dates from range.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                          | result expected                            |
      | return datesFromRange('1900-12-31', '1900-12-31'); | ["1900-12-31"]                             |
      | return datesFromRange('1900-12-31', '1901-01-02'); | ["1900-12-31", "1901-01-01", "1901-01-02"] |
      | return datesFromRange($D_A, $D_B);                 | ["1900-12-31", "1901-01-01", "1901-01-02"] |
      | $D = datesFromRange($D_A, $D_B); return $D;        | ["1900-12-31", "1901-01-01", "1901-01-02"] |
