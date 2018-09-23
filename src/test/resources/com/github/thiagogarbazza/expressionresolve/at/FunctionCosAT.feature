Feature: Function cos
  It is necessary that expression-resolver perform math cos.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 45    |
      | $OTHER_VALUE_N  | 90    |

  Scenario Outline: 01. Perform function cos.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                  | expression-result   |
      | return cos(45);             | 0.5253219888177297  |
      | return cos(90);             | -0.4480736161291702 |
      | return cos($N);             | 0.5253219888177297  |
      | return cos($OTHER_VALUE_N); | -0.4480736161291702 |
