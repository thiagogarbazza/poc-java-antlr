Feature: Boolean expression
  It is necessary that expression-resolver perform boolean expression.

  Background:
    Given the $B variable is in context with the value true.
    And the $OTHER_VALUE_B variable is in context with the value false.

  Scenario Outline: 01. perform "and" expression.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                    | result |
      | return true && true;          | true   |
      | return true && false;         | false  |
      | return false && false;        | false  |
      | return true && false && true; | false  |
      | return true && true && true;  | true   |
      | return true && $B;            | true   |
      | return $B && $OTHER_VALUE_B;  | false  |

  Scenario Outline: 02. perform "or" expression.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                         | result |
      | return true \|\|  true;            | true   |
      | return true \|\| false;            | true   |
      | return false \|\| false;           | false  |
      | return true \|\| false \|\| false; | true   |
      | return true \|\|  $B;              | true   |
      | return $B \|\| $OTHER_VALUE_B;     | true   |

  Scenario Outline: 03. perform "negation" expression.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression     | result |
      | return !true;  | false  |
      | return !false; | true   |
      | return !$B;    | false  |

  Scenario Outline: 04. perform "combinations" expression.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                               | result |
      | return true \|\| false && true;          | true   |
      | return false \|\| false && true;         | false  |
      | return false \|\| !false && true;        | true   |
      | return false \|\| !$OTHER_VALUE_B && $B; | true   |

  Scenario Outline: 05. perform "grouped" expression.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                 | result |
      | return (true \|\| false) && true;          | true   |
      | return (false \|\| false) && true;         | false  |
      | return (false \|\| !false) && true;        | true   |
      | return false \|\| (!false && true);        | true   |
      | return false \|\| (!$OTHER_VALUE_B && $B); | true   |
