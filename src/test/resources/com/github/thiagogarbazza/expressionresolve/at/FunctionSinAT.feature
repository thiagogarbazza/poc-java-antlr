Feature: Function sin
  It is necessary that expression-resolver perform math sin.

  Scenario Outline: 01. Perform function sin.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression      | expression-result  |
      | return sin(90); | 0.8939966636005579 |
      | return sin(45); | 0.8509035245341184 |
