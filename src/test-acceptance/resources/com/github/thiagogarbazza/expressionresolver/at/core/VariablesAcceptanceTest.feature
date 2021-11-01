Feature: Variables
  Variables are containers for storing data (values).

  Scenario Outline: 01 - Storage of boolean
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                | result expected     |
      | $var = true; return $var;                | true                |
      | $var = [true, false, true]; return $var; | [true, false, true] |

  Scenario Outline: 02 - Storage of date
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                       | result expected                            |
      | $var = '2000-01-01'; return $var;                               | "2000-01-01"                               |
      | $var = ['2000-01-01', '2000-01-31', '2000-01-01']; return $var; | ["2000-01-01", "2000-01-31", "2000-01-01"] |

  Scenario Outline: 03 - Storage of number
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed              | result expected   |
      | $var = 1; return $var;                 | 1                 |
      | $var = [1, 2, 3]; return $var;         | [1, 2, 3]         |
      | $var = 1.1; return $var;               | 1.1               |
      | $var = [1.9, 2.8, 3.978]; return $var; | [1.9, 2.8, 3.978] |

  Scenario Outline: 04 - Storage of object
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                    | result expected                                         |
      | $var = {}; return $var;                                                      | {}                                                      |
      | $var = {'anyKey': "value"}; return $var;                                     | {"anyKey": "value"}                                     |
      | $var = [{'anyKey': false}, {"anyKey": 1}, {"anyKey": "value"}]; return $var; | [{"anyKey": false}, {"anyKey": 1}, {"anyKey": "value"}] |

  Scenario Outline: 05 - Storage of text
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed               | result expected    |
      | $var = ""; return $var;                 | ""                 |
      | $var = "Lorem ipsum"; return $var;      | "Lorem ipsum"      |
      | $var = ["A1", "A2", "A3"]; return $var; | ["A1", "A2", "A3"] |

  Scenario Outline: 06 - Storage of empty collection
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | $var = []; return $var;   | []              |

  Scenario Outline: 07 - Variável informada no contexto de execução da expressão
    Given send the expression: <expression to be executed>
    And the variable named $var is in the expression's execution context with the value: "Lorem ipsum"
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | return $var;              | "Lorem ipsum"   |

  Scenario Outline: 08 - Variável sendo criado como null
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected |
      | $var = null; return $var; | null            |

  Scenario Outline: 09 - Variável não está presente no contexto.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the error message: <result expected>
    Examples:
      | expression to be executed | result expected                                                              |
      | return $var;              | The variable $var is not present in the execution context of the expression. |
