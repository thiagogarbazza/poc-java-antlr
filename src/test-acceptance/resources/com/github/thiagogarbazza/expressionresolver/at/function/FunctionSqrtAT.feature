Feature: Function sqrt
  It is necessary that expression-resolver perform math sqrt.

  Background:
    Given the $N variable is in context with the value 9.
    And the $OTHER_VALUE_N variable is in context with the value 16.

  Scenario Outline: 01. Perform function sqrt.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                   | result |
      | return sqrt(9);              | 3.0    |
      | return sqrt(16);             | 4.0    |
      | return sqrt($N);             | 3.0    |
      | return sqrt($OTHER_VALUE_N); | 4.0    |
