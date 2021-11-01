Feature: Data type boolean

  Rule: Simple value of boolean

    Scenario Outline: 01 - Create a boolean
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return true;              | true            |
        | return false;             | false           |

  Rule: Collection of booleans

    Scenario Outline: 01 - Create a booleans
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed    | result expected      |
        | return [true];               | [true]               |
        | return [false];              | [false]              |
        | return [true, false, true];  | [true, false, true]  |
        | return [false, true, false]; | [false, true, false] |
