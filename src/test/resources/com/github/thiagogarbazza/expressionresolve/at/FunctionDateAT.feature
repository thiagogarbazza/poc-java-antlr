Feature: Function date
  It is necessary that expression-resolver perform date.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $DAY            | 1     |
      | $MONTH          | 12    |
      | $YEAR           | 2018  |

  Scenario Outline: 01. Perform function date.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the date: "<expression-result>".
    Examples:
      | expression                        | expression-result |
      | return date(1984, 07, 20);        | 1984/07/20        |
      | return date(2018, 12, 31);        | 2018/12/31        |
      | return date($YEAR, $MONTH, $DAY); | 2018/12/01        |
