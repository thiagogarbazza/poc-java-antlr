Feature: Function today
  It is necessary that expression-resolver perform today.

  Scenario Outline: 01. Perform function today.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return today;             | is today        |
