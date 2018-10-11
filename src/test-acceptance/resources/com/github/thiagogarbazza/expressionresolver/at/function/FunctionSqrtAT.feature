Feature: Function sqrt
  It is necessary that expression-resolver perform math sqrt.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 9     |
      | $OTHER_VALUE_N  | 16    |

  Scenario Outline: 01. Perform function sqrt.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                   | expression-result |
      | return sqrt(9);              | 3.0               |
      | return sqrt(16);             | 4.0               |
      | return sqrt($N);             | 3.0               |
      | return sqrt($OTHER_VALUE_N); | 4.0               |
