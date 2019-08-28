Feature: Function tan
  It is necessary that expression-resolver perform math tan.

  Background:
    Given the $N variable is in context with the value 45.
    And the $OTHER_VALUE_N variable is in context with the value 90.

  Scenario Outline: 01. Perform function tan.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                  | result             |
      | return tan(45);             | 1.6197751905438615 |
      | return tan(90);             | -1.995200412208242 |
      | return tan($N);             | 1.6197751905438615 |
      | return tan($OTHER_VALUE_N); | -1.995200412208242 |
