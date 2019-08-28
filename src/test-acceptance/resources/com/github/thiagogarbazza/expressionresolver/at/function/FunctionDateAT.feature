Feature: Function date
  It is necessary that expression-resolver perform date.

  Background:
    Given the $DAY variable is in context with the value 1.
    And the $MONTH variable is in context with the value 12.
    And the $YEAR variable is in context with the value 2018.

  Scenario Outline: 01. Perform function date.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                        | result     |
      | return date(1984, 07, 20);        | 1984/07/20 |
      | return date(2018, 12, 31);        | 2018/12/31 |
      | return date($YEAR, $MONTH, $DAY); | 2018/12/01 |
