Feature: Function year
  It is necessary that expression-resolver perform year.

  Scenario Outline: 01. Perform function year.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression               | expression-result |
      | return year(1984/07/20); | 1984              |
      | return year(2017/09/06); | 2017              |
