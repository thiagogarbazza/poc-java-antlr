Feature: Function month
  It is necessary that expression-resolver perform month.

  Background:
    Given the following dates in the context
      | name-in-context | value      |
      | $D              | 1900/12/31 |
      | $OTHER_VALUE_D  | 2021/01/01 |

  Scenario Outline: 01. Perform function month.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                    | expression-result |
      | return month(1984/07/20);     | 07                |
      | return month(2017/09/06);     | 09                |
      | return month($D);             | 12                |
      | return month($OTHER_VALUE_D); | 01                |
