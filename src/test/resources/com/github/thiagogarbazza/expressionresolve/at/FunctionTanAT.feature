Feature: Function tan
  It is necessary that expression-resolver perform math tan.

  Scenario Outline: 01. Perform function tan.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression      | expression-result  |
      | return tan(90); | -1.995200412208242 |
      | return tan(45); | 1.6197751905438615 |
