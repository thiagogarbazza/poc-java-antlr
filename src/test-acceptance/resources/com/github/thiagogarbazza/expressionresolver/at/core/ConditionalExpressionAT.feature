Feature: IF condition expression
  It is necessary that expression-resolver perform condition IF.

  Background:
    Given the $B_YES variable is in context with the value true.
    And the $B_NO variable is in context with the value false.
    And the $A variable is in context with the value 99.
    And the $B_17 variable is in context with the value 17.
    And the $B_13 variable is in context with the value 13.

  Scenario Outline: 01. Perform condition IF.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                                                      | result |
      | if (true)   { return 1; } return 2;                                             | 1      |
      | if (false)  { return 1; } return 2;                                             | 2      |
      | if (true)   { return 1; } else { return 2; }                                    | 1      |
      | if (false)  { return 1; } else { return 2; }                                    | 2      |
      | if (true)   { return 1; } else if (true)   { return 2; } else { return 3; }     | 1      |
      | if (false)  { return 1; } else if (true)   { return 2; } else { return 3; }     | 2      |
      | if (false)  { return 1; } else if (false)  { return 2; } else { return 3; }     | 3      |
      | if ($B_YES) { return 1; } else if ($B_YES) { return 2; } else { return 3; }     | 1      |
      | if ($B_NO)  { return 1; } else if ($B_YES) { return 2; } else { return 3; }     | 2      |
      | if ($B_NO)  { return 1; } else if ($B_NO)  { return 2; } else { return 3; }     | 3      |
      | if (true)   { $A = 1; } return $A;                                              | 1      |
      | if (false)  { $A = 1; } return $A;                                              | 99     |
      | if (true)   { $A = 1; } else { $A = 2; } return $A;                             | 1      |
      | if (false)  { $A = 1; } else { $A = 2; } return $A;                             | 2      |
      | if (false)  { $A = 1; } else { $B = 2; } return $A;                             | 99     |
      | if (true)   { $A = 1; } else if (true)  { $A = 2; } else { $A = 3; } return $A; | 1      |
      | if (false)  { $A = 1; } else if (true)  { $A = 2; } else { $A = 3; } return $A; | 2      |
      | if (false)  { $A = 1; } else if (false) { $A = 2; } else { $A = 3; } return $A; | 3      |
      | if (true)  { return $B_17; } else { return $B_13; }                             | 17     |
      | if (false) { return $B_17; } else { return $B_13; }                             | 13     |

