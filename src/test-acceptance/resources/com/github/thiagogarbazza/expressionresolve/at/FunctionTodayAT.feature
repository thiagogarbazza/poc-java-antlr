Feature: Function today
  It is necessary that expression-resolver perform today.

  Scenario Outline: 01. Perform function today.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the date: "<expression-result>".
    Examples:
      | expression    | expression-result |
      | return today; | is today          |
