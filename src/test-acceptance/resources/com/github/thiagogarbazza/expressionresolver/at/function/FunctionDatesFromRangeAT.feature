Feature: Function dates from range
  It is necessary that expression-resolver perform date.

  Background:
    Given the $D_A variable is in context with the value 1900/12/31.
    And the $D_B variable is in context with the value 1901/01/02.

  Scenario Outline: 01. Perform function dates from range.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have the collection as resulted <result>.
    Examples:
      | expression                                     | result                             |
      | return datesFromRange(1900/12/31, 1900/12/31); | 1900/12/31                         |
      | return datesFromRange(1900/12/31, 1901/01/02); | 1900/12/31, 1901/01/01, 1901/01/02 |
      | return datesFromRange($D_A, $D_B);             | 1900/12/31, 1901/01/01, 1901/01/02 |
      | $D = datesFromRange($D_A, $D_B); return $D;    | 1900/12/31, 1901/01/01, 1901/01/02 |
