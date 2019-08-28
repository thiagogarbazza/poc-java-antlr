Feature: Function day
  It is necessary that expression-resolver perform day.

  Background:
    Given the $D variable is in context with the value 1900/12/31.
    And the $OTHER_VALUE_D variable is in context with the value 2021/01/01.

  Scenario Outline: 01. Perform function day.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                  | result |
      | return day(1984/07/20);     | 20     |
      | return day(2017/09/06);     | 06     |
      | return day($D);             | 31     |
      | return day($OTHER_VALUE_D); | 01     |
