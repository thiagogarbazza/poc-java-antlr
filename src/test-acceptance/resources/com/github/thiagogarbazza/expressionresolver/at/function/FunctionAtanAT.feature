Feature: Function atan
  It is necessary that expression-resolver perform math atan.

  Background:
    Given the $N variable is in context with the value 0.5.
    And the $OTHER_VALUE_N variable is in context with the value 0.8.

  Scenario Outline: 01. Perform function atan.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                   | result             |
      | return atan(0.5);            | 0.4636476090008061 |
      | return atan(0.8);            | 0.6747409422235527 |
      | return atan($N);             | 0.4636476090008061 |
      | return atan($OTHER_VALUE_N); | 0.6747409422235527 |
