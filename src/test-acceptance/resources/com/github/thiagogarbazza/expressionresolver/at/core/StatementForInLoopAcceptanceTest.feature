Feature: Iterable expression
  It is necessary that expression-resolver perform iterable in collections.

  Scenario:
    Given send the expression:
      """
       $sum = 0;
       for ($n in [5, 13, 17]) {
         $sum = $sum + $n;
       }
       return $sum;
      """
    When to request expression execution
    Then will should to get the result: 35

  Scenario Outline: 01. Perform iterable in number collections .
    Given send the expression: <expression to be executed>
    And the variable named $numbers is in the expression's execution context with the value: [5, 13, 17]
    And the variable named $numbers_empty is in the expression's execution context with the value: []
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                          | result expected |
      | $sum = 0; for ($n in $numbers) { $sum = $sum + $n; } return $sum;                  | 35              |
      | $sum = 0; for ($n in $numbers_empty) { $sum = $sum + $n; } return $sum;            | 0               |
      | $ns = [5, 7, 13, 17]; $sum = 0; for ($n in $ns) { $sum = $sum + $n; } return $sum; | 42              |

  Scenario Outline: 02. Perform iterable in date collections
    Given send the expression: <expression to be executed>
    And the variable named $dates is in the expression's execution context with the value: ["1900-12-31", "1901-01-01", "1901-01-02"]
    And the variable named $dates_empty is in the expression's execution context with the value: []
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                                             | result expected |
      | $sum = 0; for ($d in $dates) { $sum = $sum + day($d); } return $sum;                                  | 34              |
      | $sum = 0; for ($d in $dates_empty) { $sum = $sum + day($d); } return $sum;                            | 0               |
      | $ds = ['1900-12-05', '1901-01-01']; $sum = 0; for ($d in $ds) { $sum = $sum + day($d); } return $sum; | 6               |
