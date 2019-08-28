Feature: Iterable expression
  It is necessary that expression-resolver perform iterable in collections.

  Background:
    Given the $numbers variable is in context with the collection 5, 13, 17.
    And the $numbers_empty variable is in context with the collection .
    And the $dates variable is in context with the collection 1900/12/31, 1901/01/01, 1901/01/02.
    And the $dates_empty variable is in context with the collection .

  Scenario Outline: 01. Perform iterable in number collections .
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                                                         | result |
      | $sum = 0; for ($n in $numbers) { $sum = $sum + $n; } return $sum;                  | 35     |
      | $sum = 0; for ($n in $numbers_empty) { $sum = $sum + $n; } return $sum;            | 0      |
      | $ns = [5, 7, 13, 17]; $sum = 0; for ($n in $ns) { $sum = $sum + $n; } return $sum; | 42     |

  Scenario Outline: 02. Perform iterable in date collections .
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                                                                        | result |
      | $sum = 0; for ($d in $dates) { $sum = $sum + day($d); } return $sum;                              | 34     |
      | $sum = 0; for ($d in $dates_empty) { $sum = $sum + day($d); } return $sum;                        | 0      |
      | $ds = [1900/12/05, 1901/01/01]; $sum = 0; for ($d in $ds) { $sum = $sum + day($d); } return $sum; | 06     |
