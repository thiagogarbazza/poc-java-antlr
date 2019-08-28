Feature: Function ln
  It is necessary that expression-resolver perform math ln.

  Background:
    Given the $N variable is in context with the value 4.
    And the $OTHER_VALUE_N variable is in context with the value 5.

  Scenario Outline: 01. Perform function ln.
    Given Send the expression <expression>.
    When I ask what the result is?
    Then I should have resulted <result>.
    Examples:
      | expression                 | result             |
      | return ln(4);              | 1.3862943611198906 |
      | return ln(5);              | 1.6094379124341003 |
      | return ln($N);             | 1.3862943611198906 |
      | return ln($OTHER_VALUE_N); | 1.6094379124341003 |
