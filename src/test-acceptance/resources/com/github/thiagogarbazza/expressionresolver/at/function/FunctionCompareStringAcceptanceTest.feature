Feature: Function compare string
  It is necessary that expression-resolver perform compare string values.

  Background:
    Given the variable named $S is in the expression's execution context with the value: "Any text"
    And the variable named $OTHER_VALUE_S is in the expression's execution context with the value: "Other any text"

  Scenario Outline: 01. Perform "compare strings".
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                  | result expected |
      | return compareString("a", "z");            | -1              |
      | return compareString('A', 'Z');            | -1              |
      | return compareString("A", "A");            | 0               |
      | return compareString('a', 'a');            | 0               |
      | return compareString("z", "a");            | 1               |
      | return compareString('Z', 'A');            | 1               |
      | return compareString($S, 'A');             | 1               |
      | return compareString($OTHER_VALUE_S, 'z'); | -1              |
      | return compareString($OTHER_VALUE_S, $S);  | 1               |

