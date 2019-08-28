Feature: Function compare
  It is necessary that expression-resolver perform compare values.

  Background:
    Given the $D variable is in context with the value 1900/12/31.
    And the $OTHER_VALUE_D variable is in context with the value 2021/01/01.

  Scenario Outline: 01. Perform "compare dates".
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                                      | result |
      | return compareDate(1900/12/31, 2021/01/01);     | -1     |
      | return compareDate(2018/12/31, 2018/12/31);     | 0      |
      | return compareDate(2021/01/01, 1900/12/31);     | 1      |
      | return compareDate($D, 2021/01/01);             | -1     |
      | return compareDate($OTHER_VALUE_D, 1900/12/31); | 1      |
      | return compareDate($OTHER_VALUE_D, $D);         | 1      |
