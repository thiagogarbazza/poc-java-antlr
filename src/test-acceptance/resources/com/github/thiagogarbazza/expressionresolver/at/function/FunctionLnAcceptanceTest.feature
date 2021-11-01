Feature: Function ln
  It is necessary that expression-resolver perform math ln.

  Background:
    Given the variable named $N is in the expression's execution context with the value: 4

  Scenario Outline: 01. Perform function ln.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected    |
      | return ln(4);             | 1.3862943611198906 |
      | return ln(5);             | 1.6094379124341003 |
      | return ln($N);            | 1.3862943611198906 |
