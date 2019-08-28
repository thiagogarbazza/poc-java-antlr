Feature: Function log
  It is necessary that expression-resolver perform math log.

  Background:
    Given the $N variable is in context with the value 4.
    And the $OTHER_VALUE_N variable is in context with the value 5.

  Scenario Outline: 01. Perform function log.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                  | result             |
      | return log(4);              | 0.6020599913279624 |
      | return log(5);              | 0.6989700043360189 |
      | return log($N);             | 0.6020599913279624 |
      | return log($OTHER_VALUE_N); | 0.6989700043360189 |
