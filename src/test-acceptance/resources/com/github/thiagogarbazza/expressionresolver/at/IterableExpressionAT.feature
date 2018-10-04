Feature: Iterable expression
  It is necessary that expression-resolver perform iterable in collections.

  Background:
    Given the following numbers collections in the context
      | name-in-context | value         |
      | $numbers        | 5 :: 13 :: 17 |
      | $numbers_empty  |               |
    And the following dates collections in the context
      | name-in-context | value                                  |
      | $dates          | 1900/12/31 :: 1901/01/01 :: 1901/01/02 |
      | $dates_empty    |                                        |

  Scenario Outline: 01. Perform iterable in number collections .
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                                                              | expression-result |
      | $sum = 0; for ($n in $numbers) { $sum = $sum + $n; } return $sum;       | 35                |
      | $sum = 0; for ($n in $numbers_empty) { $sum = $sum + $n; } return $sum; | 0                 |

  Scenario Outline: 02. Perform iterable in date collections .
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                                                                 | expression-result |
      | $sum = 0; for ($d in $dates) { $sum = $sum + day($d); } return $sum;       | 34                |
      | $sum = 0; for ($d in $dates_empty) { $sum = $sum + day($d); } return $sum; | 0                 |
