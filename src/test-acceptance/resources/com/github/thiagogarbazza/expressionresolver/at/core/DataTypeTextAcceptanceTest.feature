Feature: Data type text

  Rule: Simple value of text

    Scenario Outline: 01 - Create a text
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return 'any-text';        | "any-text"      |
        | return 'Lorem ipsum';     | "Lorem ipsum"   |

  Rule: Collection of texts

    Scenario Outline: 01 - Create a texts
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed           | result expected             |
        | return ['any-text'];                | ["any-text"]                |
        | return ['Lorem ipsum'];             | ["Lorem ipsum"]             |
        | return ['any-text', 'Lorem ipsum']; | ["any-text", "Lorem ipsum"] |
