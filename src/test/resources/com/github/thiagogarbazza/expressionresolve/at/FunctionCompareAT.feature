Feature: Function compare
  It is necessary that expression-resolver perform compare values.

  Scenario Outline: 01. Perform "compare numbers".
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

  Scenario Outline: 02. Perform "compare strings".
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression        | expression-result |
      | compare('a', 'z') | -1                |
      | compare('A', 'Z') | -1                |
      | compare('A', 'A') | 0                 |
      | compare('a', 'a') | 0                 |
      | compare('z', 'a') | 1                 |
      | compare('Z', 'A') | 1                 |

  Scenario Outline: 03. Perform "compare dates".
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                      | expression-result |
      | compare(1900/12/31, 2021/01/01) | -1                |
      | compare(2018/12/31, 2018/12/31) | 0                 |
      | compare(2021/01/01, 1900/12/31) | 1                 |
