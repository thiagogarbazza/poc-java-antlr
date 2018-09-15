Feature: Boolean expression
  It is necessary that expression-resolver perform boolean expression.

  Scenario Outline: 01. perform "and" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression            | expression-result |
      | true && true          | true              |
      | true && false         | false             |
      | false && false        | false             |
      | true && false && true | false             |
      | true && true && true  | true              |

  Scenario Outline: 02. perform "or" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression                 | expression-result |
      | true \|\|  true            | true              |
      | true \|\| false            | true              |
      | false \|\| false           | false             |
      | true \|\| false \|\| false | true              |

  Scenario Outline: 03. perform "negation" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression | expression-result |
      | !true      | false             |
      | !false     | true              |

  Scenario Outline: 04. perform "combinations" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression                | expression-result |
      | true \|\| false && true   | true              |
      | false \|\| false && true  | false             |
      | false \|\| !false && true | true              |

  Scenario Outline: 05. perform "grouped" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression                  | expression-result |
      | (true \|\| false) && true   | true              |
      | (false \|\| false) && true  | false             |
      | (false \|\| !false) && true | true              |
      | false \|\| (!false && true) | true              |
