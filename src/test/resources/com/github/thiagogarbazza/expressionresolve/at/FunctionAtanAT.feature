Feature: Function atan
  It is necessary that expression-resolver perform math atan.

  Scenario Outline: 01. Perform function atan.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result  |
      | atan(0.5)  | 0.4636476090008061 |
      | atan(0.8)  | 0.6747409422235527 |
