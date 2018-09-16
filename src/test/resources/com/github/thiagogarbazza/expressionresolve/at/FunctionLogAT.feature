Feature: Function log
  It is necessary that expression-resolver perform math log.

  Scenario Outline: 01. Perform function log.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result  |
      | log(5)     | 0.6989700043360189 |
      | log(4)     | 0.6020599913279624 |
