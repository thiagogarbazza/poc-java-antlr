Feature: Function tan
  It is necessary that expression-resolver perform math tan.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 45    |
      | $OTHER_VALUE_N  | 90    |

  Scenario Outline: 01. Perform function tan.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                  | expression-result  |
      | return tan(45);             | 1.6197751905438615 |
      | return tan(90);             | -1.995200412208242 |
      | return tan($N);             | 1.6197751905438615 |
      | return tan($OTHER_VALUE_N); | -1.995200412208242 |
