Feature: Function ln
  It is necessary that expression-resolver perform math ln.

  Scenario Outline: 01. Perform function ln.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression    | expression-result  |
      | return ln(5); | 1.6094379124341003 |
      | return ln(4); | 1.3862943611198906 |
