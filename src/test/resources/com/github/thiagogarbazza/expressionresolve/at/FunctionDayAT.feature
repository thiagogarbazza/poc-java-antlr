Feature: Function day
  It is necessary that expression-resolver perform day.

  Scenario Outline: 01. Perform function day.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression      | expression-result |
      | day(1984/07/20) | 20                |
      | day(2017/09/06) | 06                |
