Feature: Function sqrt
  It is necessary that expression-resolver perform math sqrt.

  Scenario Outline: 01. Perform function sqrt.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result |
      | sqrt(9)    | 3.0               |
      | sqrt(16)   | 4.0               |
