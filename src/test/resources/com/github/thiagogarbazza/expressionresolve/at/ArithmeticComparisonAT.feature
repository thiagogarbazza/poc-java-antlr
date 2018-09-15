Feature: Arithmetic comparison
  It is necessary that expression-resolver perform arithmetic comparison expression.

  Scenario Outline: 01. perform "equals" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression       | expression-result |
      | 1 == 1           | true              |
      | 33.333 == 33.333 | true              |
      | 7 == 8           | false             |
      | 3.3 == 3         | false             |

  Scenario Outline: 02. perform "not equals" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression  | expression-result |
      | 7 != 8      | true              |
      | 3.3 != 77.8 | true              |
      | 3.3 != 3.33 | true              |
      | 7 != 7      | false             |
      | 3.3 != 3.3  | false             |

  Scenario Outline: 03. perform "greater than" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression | expression-result |
      | 9 > 5      | true              |
      | 9.9 > 5.5  | true              |
      | 9.9 > 9    | true              |
      | 7 > 7      | false             |
      | 3 > 3.3    | false             |
      | 9.9 > 9.9  | false             |

  Scenario Outline: 04. perform "greater than or Equal" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression | expression-result |
      | 9 >= 5     | true              |
      | 9.9 >= 5.5 | true              |
      | 9.9 >= 9.9 | true              |
      | 9.9 >= 9   | true              |
      | 7 >= 7     | true              |
      | 3 >= 3.3   | false             |

  Scenario Outline: 05. perform "less than" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression | expression-result |
      | 5 < 9      | true              |
      | 5.5 < 9.9  | true              |
      | 9 < 9.9    | true              |
      | 7 < 7      | false             |
      | 3.3 < 3    | false             |
      | 5.5 < 5.5  | false             |
