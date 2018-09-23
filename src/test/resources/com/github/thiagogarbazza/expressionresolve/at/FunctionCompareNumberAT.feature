Feature: Function compare number
  It is necessary that expression-resolver perform compare number values.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 45    |
      | $OTHER_VALUE_N  | 90    |

  Scenario Outline: 01. Perform "compare numbers".
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                                | expression-result |
      | return compareNumber(1, 9);               | -1                |
      | return compareNumber(3.3, 9.9);           | -1                |
      | return compareNumber(5, 5);               | 0                 |
      | return compareNumber(5.123, 5.123);       | 0                 |
      | return compareNumber(9, 1);               | 1                 |
      | return compareNumber(9.12, 1.98);         | 1                 |
      | return compareNumber(9.12, $N);           | -1                |
      | return compareNumber($OTHER_VALUE_N, $N); | 1                 |
