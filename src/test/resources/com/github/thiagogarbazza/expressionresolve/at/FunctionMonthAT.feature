Feature: Function month
  It is necessary that expression-resolver perform month.

  Scenario Outline: 01. Perform function month.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                | expression-result |
      | return month(1984/07/20); | 07                |
      | return month(2017/09/06); | 09                |
