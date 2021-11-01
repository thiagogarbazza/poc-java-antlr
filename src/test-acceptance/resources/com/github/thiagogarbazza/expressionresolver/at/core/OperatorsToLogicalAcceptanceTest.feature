Feature: Logical operators
  It is necessary that expression-resolver perform boolean expression.

  Background:
    Given the variable named $B is in the expression's execution context with the value: true
    And the variable named $OTHER_VALUE_B is in the expression's execution context with the value: false

  Scenario Outline: 01 - perform "and" expression
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed     | result expected |
      | return true && true;          | true            |
      | return true && false;         | false           |
      | return false && false;        | false           |
      | return true && false && true; | false           |
      | return true && true && true;  | true            |
      | return true && $B;            | true            |
      | return $B && $OTHER_VALUE_B;  | false           |

  Scenario Outline: 02 - perform "or" expression
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed          | result expected |
      | return true \|\|  true;            | true            |
      | return true \|\| false;            | true            |
      | return false \|\| false;           | false           |
      | return true \|\| false \|\| false; | true            |
      | return true \|\|  $B;              | true            |
      | return $B \|\| $OTHER_VALUE_B;     | true            |

  Scenario Outline: 03 - perform "negation" expression
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return !true;             | false           |
      | return !false;            | true            |
      | return !$B;               | false           |

  Scenario Outline: 04 - perform "combinations" expression
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                | result expected |
      | return true \|\| false && true;          | true            |
      | return false \|\| false && true;         | false           |
      | return false \|\| !false && true;        | true            |
      | return false \|\| !$OTHER_VALUE_B && $B; | true            |

  Scenario Outline: 05 - perform "grouped" expression
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                  | result expected |
      | return (true \|\| false) && true;          | true            |
      | return (false \|\| false) && true;         | false           |
      | return (false \|\| !false) && true;        | true            |
      | return false \|\| (!false && true);        | true            |
      | return false \|\| (!$OTHER_VALUE_B && $B); | true            |
