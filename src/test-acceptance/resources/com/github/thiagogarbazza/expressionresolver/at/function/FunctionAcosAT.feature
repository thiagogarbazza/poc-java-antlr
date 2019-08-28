Feature: Function acos
  It is necessary that expression-resolver perform math acos.

  Background:
    Given the $N variable is in context with the value 0.9.
    And the $OTHER_VALUE_N variable is in context with the value 0.5.

  Scenario Outline: 01. Perform function cos.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                   | result              |
      | return acos(0.5);            | 1.0471975511965979  |
      | return acos(0.9);            | 0.45102681179626236 |
      | return acos($N);             | 0.45102681179626236 |
      | return acos($OTHER_VALUE_N); | 1.0471975511965979  |

  @Disabled
  Scenario: 99. Perform function cos.
    Given Send the expression return acos(0.5);.
    When I ask what the result is?
    Then I should have resulted 1.0471975511965979.

  @disabled
  Scenario: 98. Perform function cos.
    Given Send the expression return acos(0.5);.
    When I ask what the result is?
    Then I should have resulted 1.0471975511965979.

  @execute
  Scenario: 97. Perform function cos.
    Given Send the expression return acos(0.5);.
    When I ask what the result is?
    Then I should have resulted 1.0471975511965979.
