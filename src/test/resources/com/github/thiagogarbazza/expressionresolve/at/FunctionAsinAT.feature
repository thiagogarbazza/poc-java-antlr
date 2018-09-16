Feature: Function asin
  It is necessary that expression-resolver perform math asin.

  Scenario Outline: 01. Perform function asin.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result  |
      | asin(0.5)  | 0.5235987755982989 |
      | asin(0.8)  | 0.9272952180016123 |
