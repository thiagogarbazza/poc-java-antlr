Feature: IF condition expression

  Background:
    Given the variable named $B_YES is in the expression's execution context with the value: true
    And the variable named $B_NO is in the expression's execution context with the value: false
    And the variable named $A is in the expression's execution context with the value: 99
    And the variable named $B_17 is in the expression's execution context with the value: 17
    And the variable named $B_13 is in the expression's execution context with the value: 13

  Scenario Outline: 01 - Perform condition IF
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed           | result expected |
      | if (true)   { return 1; } return 2; | 1               |
      | if (false)  { return 1; } return 2; | 2               |
      | if (true)   { $A = 1; } return $A;  | 1               |
      | if (false)  { $A = 1; } return $A;  | 99              |

  Scenario Outline: 02 - Perform condition IF ... ELSE
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                           | result expected |
      | if (true)   { return 1; } else { return 2; }        | 1               |
      | if (false)  { return 1; } else { return 2; }        | 2               |
      | if (true)   { $A = 1; } else { $A = 2; } return $A; | 1               |
      | if (false)  { $A = 1; } else { $A = 2; } return $A; | 2               |
      | if (false)  { $A = 1; } else { $B = 2; } return $A; | 99              |
      | if (true)  { return $B_17; } else { return $B_13; } | 17              |
      | if (false) { return $B_17; } else { return $B_13; } | 13              |

  Scenario Outline: 03 - Perform condition IF ... ELSE ... IF
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                       | result expected |
      | if (true)   { return 1; } else if (true)   { return 2; } else { return 3; }     | 1               |
      | if (false)  { return 1; } else if (true)   { return 2; } else { return 3; }     | 2               |
      | if (false)  { return 1; } else if (false)  { return 2; } else { return 3; }     | 3               |
      | if ($B_YES) { return 1; } else if ($B_YES) { return 2; } else { return 3; }     | 1               |
      | if ($B_NO)  { return 1; } else if ($B_YES) { return 2; } else { return 3; }     | 2               |
      | if ($B_NO)  { return 1; } else if ($B_NO)  { return 2; } else { return 3; }     | 3               |
      | if (true)   { $A = 1; } else if (true)  { $A = 2; } else { $A = 3; } return $A; | 1               |
      | if (false)  { $A = 1; } else if (true)  { $A = 2; } else { $A = 3; } return $A; | 2               |
      | if (false)  { $A = 1; } else if (false) { $A = 2; } else { $A = 3; } return $A; | 3               |