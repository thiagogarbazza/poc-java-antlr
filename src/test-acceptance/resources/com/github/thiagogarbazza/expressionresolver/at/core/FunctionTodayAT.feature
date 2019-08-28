Feature: Function today
  It is necessary that expression-resolver perform today.

  Scenario Outline: 01. Perform function today.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression    | result   |
      | return today; | is today |
