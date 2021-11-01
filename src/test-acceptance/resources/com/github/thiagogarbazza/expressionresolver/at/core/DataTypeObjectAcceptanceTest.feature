Feature: Data type object

  A collection of name/value pairs.
  For more information see https://www.json.org/.

  Rule: Simple value of object

    Scenario Outline: 01 - Create an empty object
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return {};                | {}              |
        | return { };               | {}              |

    Scenario Outline: 02 - Create object with numeric values
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed         | result expected          |
        | return {"anyKey" : 5};            | {"anyKey": 5}            |
        | return {"anyKey" : [5, 7]};       | {"anyKey": [5, 7]}       |
        | return {"anyKey" : 5.1};          | {"anyKey": 5.1}          |
        | return {"anyKey" : [5.1, 7.123]}; | {"anyKey": [5.1, 7.123]} |

    Scenario Outline: 03 - Create object with string values
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed                   | result expected                    |
        | return {"anyKey" : "value"};                | {"anyKey": "value"}                |
        | return {"anyKey" : ["value-1", "value-2"]}; | {"anyKey": ["value-1", "value-2"]} |

    Scenario Outline: 04 - Create object with boolean values
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed          | result expected           |
        | return {"anyKey" : true};          | {"anyKey": true}          |
        | return {"anyKey" : [true, false]}; | {"anyKey": [true, false]} |

    Scenario Outline: 05 - Create object with date values
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed                         | result expected                          |
        | return {"anyKey" : '1900-01-01'};                 | {"anyKey": "1900-01-01"}                 |
        | return {"anyKey" : ['1900-01-01', '1900-01-02']}; | {"anyKey": ["1900-01-01", "1900-01-02"]} |

    Scenario Outline: 06 - Create object with object values
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed                | result expected                |
        | return {"anyKey" : {"any-sub-key": 5} }; | {"anyKey": {"any-sub-key": 5}} |

    Scenario: 07 - Create object with many different type values
      Given send the expression:
        """
        return {
          'any-boolean': true,
          'any-date': '2018-02-28',
          'any-number': 13,
          'any-string': 'any-string-value',
          'any-json': {
            'any-sub-key': 5
          }
        };
        """
      When to request expression execution
      Then will should to get the result:
        """
        {
          "any-boolean": true,
          "any-date": "2018-02-28",
          "any-number": 13,
          "any-string": "any-string-value",
          "any-json": {
            "any-sub-key": 5
          }
        }
        """

  Rule: Collection of objects

    Scenario Outline: 01 - Create an empty object
      Given send the expression: <expression to be executed>
      When to request expression execution
      Then will should to get the result: <result expected>
      Examples:
        | expression to be executed | result expected |
        | return [{}];              | [{}]            |
        | return [ { } ];           | [{}]            |
