Feature: Function log
  It is necessary that expression-resolver perform math log.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 4     |
      | $OTHER_VALUE_N  | 5     |

  Scenario Outline: 01. Perform function log.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                  | expression-result  |
      | return log(4);              | 0.6020599913279624 |
      | return log(5);              | 0.6989700043360189 |
      | return log($N);             | 0.6020599913279624 |
      | return log($OTHER_VALUE_N); | 0.6989700043360189 |
