Feature: Function acos
  It is necessary that expression-resolver perform math acos.

  Scenario Outline: 01. Perform function cos.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result   |
      | acos(0.9)  | 0.45102681179626236 |
      | acos(0.5)  | 1.0471975511965979  |
