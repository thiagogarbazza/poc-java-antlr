Feature: Function asin
  It is necessary that expression-resolver perform math asin.

  Background:
    Given the following numbers in the context
      | name-in-context | value |
      | $N              | 0.5   |
      | $OTHER_VALUE_N  | 0.8   |

  Scenario Outline: 01. Perform function asin.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the number: "<expression-result>".
    Examples:
      | expression                   | expression-result  |
      | return asin(0.5);            | 0.5235987755982989 |
      | return asin(0.8);            | 0.9272952180016123 |
      | return asin($N);             | 0.5235987755982989 |
      | return asin($OTHER_VALUE_N); | 0.9272952180016123 |
