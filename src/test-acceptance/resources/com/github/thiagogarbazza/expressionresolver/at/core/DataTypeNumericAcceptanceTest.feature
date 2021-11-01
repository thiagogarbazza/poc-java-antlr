Feature: Data type numeric

  Rule: Simple value of numeric

    Scenario Outline: 01 - Create a positive number
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return 5;                 | 5               |
        | return 7.123;             | 7.123           |
        | return 0;                 | 0               |
        | return 0.0;               | 0               |

    Scenario Outline: 02 - Create a negative number
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return - 3;               | -3              |
        | return - 9.123;           | -9.123          |
        | return - 0;               | 0               |
        | return - 0.0;             | 0               |

  Rule: Collection of numerics

    Scenario Outline: 01 - Create a positive numbers
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed  | result expected    |
        | return [5];                | [5]                |
        | return [7.123];            | [7.123]            |
        | return [1, 3, 5, 7, 8, 9]; | [1, 3, 5, 7, 8, 9] |
        | return [1.1, 3, 9.9];      | [1.1, 3, 9.9]      |

    Scenario Outline: 02 - Create a negative numbers
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected  |
        | return [ -5 ];            | [-5]             |
        | return [ -7.123 ];        | [-7.123]         |
        | return [ -1, -3, -5];     | [-1, -3, -5]     |
        | return [ -1.1, -3, -9.9]; | [-1.1, -3, -9.9] |
