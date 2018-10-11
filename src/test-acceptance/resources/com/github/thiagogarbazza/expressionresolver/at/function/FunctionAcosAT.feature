Feature: Function acos
  It is necessary that expression-resolver perform math acos.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 0.9   |
      | $OTHER_VALUE_N  | 0.5   |

  Scenario Outline: 01. Perform function cos.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                   | expression-result   |
      | return acos(0.5);            | 1.0471975511965979  |
      | return acos(0.9);            | 0.45102681179626236 |
      | return acos($N);             | 0.45102681179626236 |
      | return acos($OTHER_VALUE_N); | 1.0471975511965979  |
