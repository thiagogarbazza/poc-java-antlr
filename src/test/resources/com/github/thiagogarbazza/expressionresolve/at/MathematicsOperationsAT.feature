Feature: Mathematics operations
  It is necessary that expression-resolver perform math operations.

  Scenario Outline: 01. Perform addition mathematical operation.
    Given Send the operation "<expression>".
    When I ask what the result is?
    Then I should be result "<expression-result>".
    Examples:
      | expression             | expression-result |
      | 1 + 1                  | 2                 |
      | 6 + 4                  | 10                |
      | 5.6 + 4.5              | 10.1              |
      | + 15 + 5               | 20                |
      | +1 + 2 + 3 + 4 + 5 + 6 | 21                |

  Scenario Outline: 02. Perform subtraction mathematical operation.
    Given Send the operation "<expression>".
    When I ask what the result is?
    Then I should be result "<expression-result>".
    Examples:
      | expression | expression-result |
      | 1 - 1      | 0                 |
      | 15 - 5     | 10                |
      | 15.5 - 5.2 | 10.3              |
      | - 15 - 5   | -20               |
