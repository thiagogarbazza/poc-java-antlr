Feature: Boolean expression
  It is necessary that expression-resolver perform boolean expression.

  Scenario Outline: 01. perform "and" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression     | expression-result |
      | true && true   | true              |
      | true && false  | false             |
      | false && false | false             |

  Scenario Outline: 02. perform "or" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression       | expression-result |
      | true \|\|  true  | true              |
      | true \|\| false  | true              |
      | false \|\| false | false             |

  Scenario Outline: 03. perform "negation" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression | expression-result |
      | !true      | false             |
      | !false     | true              |
