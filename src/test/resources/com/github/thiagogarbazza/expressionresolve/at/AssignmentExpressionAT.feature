Feature: Assignment expression
  It is necessary that expression-resolver perform assignment values.

  Scenario Outline: 01. Perform assignment number values.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression             | expression-result |
      | $A = 21;    return $A; | 21                |
      | $a = 105;   return $a; | 105               |
      | $B = 33.77; return $B; | 33.77             |
      | $b = 11.99; return $b; | 11.99             |

  Scenario Outline: 02. Perform assignment boolean values.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression             | expression-result |
      | $A = true;  return $A; | true              |
      | $a = false; return $a; | false             |

  Scenario Outline: 03. Perform assignment date values.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the date: "<expression-result>".
    Examples:
      | expression                  | expression-result |
      | $A = 1984/07/20; return $A; | 1984/07/20        |
      | $a = 2018/12/31; return $a; | 2018/12/31        |

  Scenario Outline: 04. Perform assignment string values.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the string: "<expression-result>".
    Examples:
      | expression                           | expression-result |
      | $A = "Lorem ipsum";       return $A; | Lorem ipsum       |
      | $a = 'Other lorem ipsum'; return $a; | Other lorem ipsum |
