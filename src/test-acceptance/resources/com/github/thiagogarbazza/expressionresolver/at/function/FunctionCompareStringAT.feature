Feature: Function compare string
  It is necessary that expression-resolver perform compare string values.

  Background:
    Given the $S variable is in context with the value Any text.
    And the $OTHER_VALUE_S variable is in context with the value Other any text.

  Scenario Outline: 01. Perform "compare strings".
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                 | result |
      | return compareString("a", "z");            | -1     |
      | return compareString('A', 'Z');            | -1     |
      | return compareString("A", "A");            | 0      |
      | return compareString('a', 'a');            | 0      |
      | return compareString("z", "a");            | 1      |
      | return compareString('Z', 'A');            | 1      |
      | return compareString($S, 'A');             | 1      |
      | return compareString($OTHER_VALUE_S, 'z'); | -1     |
      | return compareString($OTHER_VALUE_S, $S);  | 1      |

