Feature: Mathematics operations
  It is necessary that expression-resolver perform math operations.

  Scenario Outline: 01. Perform addition mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression             | expression-result |
      | +7                     | 7                 |
      | 1 + 1                  | 2                 |
      | 6 + 4                  | 10                |
      | 5.6 + 4.5              | 10.1              |
      | + 15 + 5               | 20                |
      | +1 + 2 + 3 + 4 + 5 + 6 | 21                |

  Scenario Outline: 02. Perform subtraction mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression  | expression-result |
      | -7          | -7                |
      | 1 - 1       | 0                 |
      | 15 - 5      | 10                |
      | 15.5 - 5.2  | 10.3              |
      | - 15 - 5    | -20               |
      | 20 - 15 + 3 | 8                 |

  Scenario Outline: 03. Perform multiplication mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                              | expression-result                   |
      | 1 * 1                                   | 1                                   |
      | 5 * 5                                   | 25                                  |
      | 5.5 * 5                                 | 27.5                                |
      | 33.33333333333333333333333333333333 * 3 | 99.99999999999999999999999999999999 |
      | 5 * 4 * 3 * 2 * 1                       | 120                                 |

  Scenario Outline: 04. Perform division mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression            | expression-result                   |
      | 1 / 1                 | 1                                   |
      | 5 / 5                 | 1                                   |
      | 15 / 5                | 3                                   |
      | 99 / 3                | 33                                  |
      | 99.99 / 3             | 33.33                               |
      | 27.5 / 5              | 5.5                                 |
      | 100 / 3               | 33.33333333333333333333333333333333 |
      | 120 / 5 / 4 / 3 / 2 1 | 1                                   |

  Scenario Outline: 05. Perform exponentiation mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result |
      | 1 ^ 1      | 1                 |
      | 15 ^ 0     | 1                 |
      | 5 ^ 3      | 125               |
      | 12 ^ 2     | 144               |
      | 8 ^ 3      | 512               |
      | 3.3 ^ 3    | 35.937            |

  Scenario Outline: 06. Perform modulo mathematical operation.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression | expression-result |
      | 1 % 1      | 0                 |
      | 10 % 3     | 1                 |
      | 33 % 3     | 0                 |
      | 24.4 % 5   | 4.4               |

  Scenario Outline: 07. Numeric Expressions with Brackets, Brackets, and Braces
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                                                 | expression-result |
      | (6/2)                                                      | 3                 |
      | (6/2) * (1+2)                                              | 9                 |
      | (3 + 2) * (6 - 4)                                          | 10                |
      | -(7 - 14)                                                  | 7                 |
      | [6/2]                                                      | 3                 |
      | [6/2] * [1+2]                                              | 9                 |
      | [(-6) + (-1) * (+2)]                                       | -8                |
      | [(3 + 2) * (6 - 4) + 2] * 4                                | 48                |
      | {6/2}                                                      | 3                 |
      | {6/2} * {1+2}                                              | 9                 |
      | {(-2) + [(-4-2) + (-1) * (+2)] + 20} + 15                  | 25                |
      | 25 - {3 * 17 - [10 + 6 * (8 - 4 * 2) + 2 + 3] - 4 * 4} / 5 | 21                |
      | 20 - [4 + (5 - 1) ^ 2 - 5] + 3                             | 8                 |
