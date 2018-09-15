Feature: Function compare
  It is necessary that expression-resolver perform compare values.

  Scenario Outline: 01. Perform "compare number" values.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression            | expression-result |
      | compare(1, 9)         | -1                |
      | compare(3.3, 9.9)     | -1                |
      | compare(5, 5)         | 0                 |
      | compare(5.123, 5.123) | 0                 |
      | compare(9, 1)         | 1                 |
      | compare(9.12, 1.98)   | 1                 |
