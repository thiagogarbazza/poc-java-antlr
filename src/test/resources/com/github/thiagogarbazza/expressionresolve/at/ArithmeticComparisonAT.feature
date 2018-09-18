Feature: Arithmetic comparison
  It is necessary that expression-resolver perform arithmetic comparison expression.

  Scenario Outline: 01. perform "equals" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression               | expression-result |
      | return 1 == 1;           | true              |
      | return 33.333 == 33.333; | true              |
      | return 7 == 8;           | false             |
      | return 3.3 == 3;         | false             |

  Scenario Outline: 02. perform "not equals" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression          | expression-result |
      | return 7 != 8;      | true              |
      | return 3.3 != 77.8; | true              |
      | return 3.3 != 3.33; | true              |
      | return 7 != 7;      | false             |
      | return 3.3 != 3.3;  | false             |

  Scenario Outline: 03. perform "greater than" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression        | expression-result |
      | return 9 > 5;     | true              |
      | return 9.9 > 5.5; | true              |
      | return 9.9 > 9;   | true              |
      | return 7 > 7;     | false             |
      | return 3 > 3.3;   | false             |
      | return 9.9 > 9.9; | false             |

  Scenario Outline: 04. perform "greater than or equal" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression         | expression-result |
      | return 9 >= 5;     | true              |
      | return 9.9 >= 5.5; | true              |
      | return 9.9 >= 9.9; | true              |
      | return 9.9 >= 9;   | true              |
      | return 7 >= 7;     | true              |
      | return 1 >= 8;     | false             |
      | return 3 >= 3.3;   | false             |

  Scenario Outline: 05. perform "less than" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression        | expression-result |
      | return 5 < 9;     | true              |
      | return 5.5 < 9.9; | true              |
      | return 9 < 9.9;   | true              |
      | return 7 < 7;     | false             |
      | return 3.3 < 3;   | false             |
      | return 5.5 < 5.5; | false             |

  Scenario Outline: 06. perform "less than or equal" expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the boolean: "<expression-result>".
    Examples:
      | expression         | expression-result |
      | return 5 <= 9;     | true              |
      | return 5.5 <= 9.9; | true              |
      | return 9.9 <= 9.9; | true              |
      | return 9 <= 9.9;   | true              |
      | return 7 <= 7;     | true              |
      | return 8 <= 1;     | false             |
      | return 3.3 <= 3;   | false             |
