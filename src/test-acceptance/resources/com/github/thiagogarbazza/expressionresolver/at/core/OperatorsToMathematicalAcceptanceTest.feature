Feature: Mathematical operations
  It is necessary that expression-resolver perform math operations.

  Background:
    Given the variable named $A is in the expression's execution context with the value: 4
    And the variable named $B is in the expression's execution context with the value: 5
    And the variable named $C is in the expression's execution context with the value: -5

  Scenario Outline: 01 - Perform "addition" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed      | result expected |
      | return +7;                     | 7               |
      | return 1 + 1;                  | 2               |
      | return 6 + 4;                  | 10              |
      | return 5.6 + 4.5;              | 10.1            |
      | return + 15 + 5;               | 20              |
      | return +1 + 2 + 3 + 4 + 5 + 6; | 21              |
      | return +1 + $A;                | 5               |
      | return $B + $A;                | 9               |

  Scenario Outline: 02 - Perform "subtraction" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return -7;                | -7              |
      | return 1 - 1;             | 0               |
      | return 15 - 5;            | 10              |
      | return 15.5 - 5.2;        | 10.3            |
      | return - 15 - 5;          | -20             |
      | return 20 - 15 + 3;       | 8               |
      | return 1 - $A;            | -3              |
      | return $B - $A;           | 1               |

  Scenario Outline: 03 - Perform "multiplication" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                       | result expected                     |
      | return 1 * 1;                                   | 1                                   |
      | return 5 * 5;                                   | 25                                  |
      | return 5.5 * 5;                                 | 27.5                                |
      | return 33.33333333333333333333333333333333 * 3; | 99.99999999999999999999999999999999 |
      | return 5 * 4 * 3 * 2 * 1;                       | 120                                 |
      | return 2 * $A;                                  | 8                                   |
      | return $B * $A;                                 | 20                                  |

  Scenario Outline: 04 - Perform "division" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed       | result expected                     |
      | return 1 / 1;                   | 1                                   |
      | return 5 / 5;                   | 1                                   |
      | return 15 / 5;                  | 3                                   |
      | return 99 / 3;                  | 33                                  |
      | return 99.99 / 3;               | 33.33                               |
      | return 27.5 / 5;                | 5.5                                 |
      | return 100 / 3;                 | 33.33333333333333333333333333333333 |
      | return 120 / 5 / 4 / 3 / 2 / 1; | 1                                   |
      | return 12 / $A;                 | 3                                   |
      | return $B / $A;                 | 1.25                                |

  Scenario Outline: 05 - Perform "exponentiation" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return 1 ^ 1;             | 1               |
      | return 15 ^ 0;            | 1               |
      | return 5 ^ 3;             | 125             |
      | return 12 ^ 2;            | 144             |
      | return 8 ^ 3;             | 512             |
      | return 3.3 ^ 3;           | 35.937          |
      | return 2 ^ $A;            | 16              |
      | return $B ^ $A;           | 625             |

  Scenario Outline: 06 - Perform "modulo" mathematical operation
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return 1 % 1;             | 0               |
      | return 10 % 3;            | 1               |
      | return 33 % 3;            | 0               |
      | return 24.4 % 5;          | 4.4             |
      | return 21 % $A;           | 1               |
      | return $B % $A;           | 1               |

  Scenario Outline: 07 - Numeric Expressions with Brackets, Brackets, and Braces
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return (6/2);             | 3               |
      | return (6/2) * (1+2);     | 9               |
      | return (3 + 2) * (6 - 4); | 10              |
      | return -(7 - 14);         | 7               |
