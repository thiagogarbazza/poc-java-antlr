Feature: Function dates from range
  It is necessary that expression-resolver perform date.

  Background:
    Given the following dates in the context
      | name-in-context | value      |
      | $D_A            | 1900/12/31 |
      | $D_B            | 1901/01/02 |

  Scenario Outline: 01. Perform function dates from range.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the dates: "<expression-result>".
    Examples:
      | expression                                      | expression-result                      |
      | return datesFromRange(1900/12/31, 1900/12/31 ); | 1900/12/31                             |
      | return datesFromRange(1900/12/31, 1901/01/02 ); | 1900/12/31 :: 1901/01/01 :: 1901/01/02 |
      | return datesFromRange($D_A, $D_B);              | 1900/12/31 :: 1901/01/01 :: 1901/01/02 |
      | $D = datesFromRange($D_A, $D_B); return $D;     | 1900/12/31 :: 1901/01/01 :: 1901/01/02 |
