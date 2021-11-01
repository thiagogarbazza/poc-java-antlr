Feature: Data type date

  Rule: Simple value of date

    Scenario Outline: 01 - Create a date
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return '2020-01-01';      | "2020-01-01"    |
        | return '2004-02-29';      | "2004-02-29"    |
        | return '1984-12-31';      | "1984-12-31"    |

    Scenario Outline: 02 - Create a date use name of month
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return '2020-JAN-01';     | "2020-01-01"    |
        | return '2020-FEB-01';     | "2020-02-01"    |
        | return '2020-MAR-01';     | "2020-03-01"    |
        | return '2020-APR-01';     | "2020-04-01"    |
        | return '2020-MAY-01';     | "2020-05-01"    |
        | return '2020-JUN-01';     | "2020-06-01"    |
        | return '2020-JUL-01';     | "2020-07-01"    |
        | return '2020-AUG-01';     | "2020-08-01"    |
        | return '2020-SEP-01';     | "2020-09-01"    |
        | return '2020-OCT-01';     | "2020-10-01"    |
        | return '2020-NOV-01';     | "2020-11-01"    |
        | return '2020-DEC-01';     | "2020-12-01"    |

  Rule: Collection of dates

    Scenario Outline: 01 - Create a dates
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed                           | result expected                            |
        | return ['2020-01-01'];                              | ["2020-01-01"]                             |
        | return ['2020-01-01', '2004-02-29', '1984-DEC-31']; | ["2020-01-01", "2004-02-29", "1984-12-31"] |

