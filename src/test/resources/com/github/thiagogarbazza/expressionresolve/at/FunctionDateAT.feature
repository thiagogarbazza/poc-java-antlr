Feature: Function date
  It is necessary that expression-resolver perform date.

  Scenario Outline: 01. Perform function date.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the date: "<expression-result>".
    Examples:
      | expression         | expression-result |
      | date(1984, 07, 20) | 1984/07/20        |
      | date(2018, 12, 31) | 2018/12/31        |
