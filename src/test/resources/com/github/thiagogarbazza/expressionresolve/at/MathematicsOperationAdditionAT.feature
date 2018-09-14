Feature: Mathematics operation addition
  It is necessary that expression-resolver perform sum math operation.

  Scenario Outline: Perform sum math operation.
    Given Send the operation "<expression>".
    When I ask what the result is?
    Then I should be result "<expression-result>".
    Examples:
      | expression             | expression-result |
      | + 15 + 5               | 20                |
      | 5.6 + 4.4              | 10.0              |
      | 6 + 4                  | 10                |
      | +1 + 2 + 3 + 4 + 5 + 6 | 21                |
