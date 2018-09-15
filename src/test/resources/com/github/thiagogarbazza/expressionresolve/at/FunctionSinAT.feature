Feature: Function sin
  It is necessary that expression-resolver perform math sin.

  Scenario Outline: 01. Perform function sin.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result   |
      | cos(90)    | -0.4480736161291702 |
      | cos(45)    | 0.5253219888177297  |
