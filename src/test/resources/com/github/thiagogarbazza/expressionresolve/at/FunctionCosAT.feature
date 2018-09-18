Feature: Function cos
  It is necessary that expression-resolver perform math cos.

  Scenario Outline: 01. Perform function cos.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression      | expression-result   |
      | return cos(90); | -0.4480736161291702 |
      | return cos(45); | 0.5253219888177297  |
