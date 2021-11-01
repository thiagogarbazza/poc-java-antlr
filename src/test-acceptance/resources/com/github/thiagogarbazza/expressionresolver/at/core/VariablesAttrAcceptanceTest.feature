Feature: Uso de variáveis para acessar uma attr de um objeto nas expressões
  Variables are containers for storing data (values).

  Scenario Outline:  01 - Storage of boolean
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                          | result expected               |
      | $var.attr = true; return $var;                     | {"attr": true}                |
      | $var.attr = true; return $var.attr;                | true                          |
      | $var.attr = [true, false, true]; return $var;      | {"attr": [true, false, true]} |
      | $var.attr = [true, false, true]; return $var.attr; | [true, false, true]           |

  Scenario Outline: 02 - Storage of date
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                 | result expected                                      |
      | $var.attr = '2000-01-01'; return $var;                                    | {"attr": "2000-01-01"}                               |
      | $var.attr = '2000-01-01'; return $var.attr;                               | "2000-01-01"                                         |
      | $var.attr = ['2000-01-01', '2000-01-15', '2000-01-31']; return $var;      | {"attr": ["2000-01-01", "2000-01-15", "2000-01-31"]} |
      | $var.attr = ['2000-01-01', '2000-01-15', '2000-01-31']; return $var.attr; | ["2000-01-01", "2000-01-15", "2000-01-31"]           |

  Scenario Outline: 03 - Storage of number
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                          | result expected                 |
      | $var.attr = 1; return $var;                        | { "attr": 1}                    |
      | $var.attr = 1; return $var.attr;                   | 1                               |
      | $var.attr = 1.987; return $var;                    | { "attr": 1.987}                |
      | $var.attr = 1.987; return $var.attr;               | 1.987                           |
      | $var.attr = [1, 2, 3]; return $var;                | { "attr": [1, 2, 3] }           |
      | $var.attr = [1, 2, 3]; return $var.attr;           | [1, 2, 3]                       |
      | $var.attr = [1.987, 2.0, 3.123]; return $var;      | { "attr": [1.987, 2.0, 3.123] } |
      | $var.attr = [1.987, 2.0, 3.123]; return $var.attr; | [1.987, 2.0, 3.123]             |

  Scenario Outline: 04 - Storage of object
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                              | result expected                                                     |
      | $var.attr = {}; return $var;                                                           | { "attr": {  } }                                                    |
      | $var.attr = {}; return $var.attr;                                                      | {  }                                                                |
      | $var.attr = {'anyKey': 'value'}; return $var;                                          | { "attr": { "anyKey": "value" } }                                   |
      | $var.attr = {'anyKey': 'value'}; return $var.attr;                                     | { "anyKey": "value" }                                               |
      | $var.attr = [{'anyKey': false}, {'anyKey': 1}, {'anyKey': 'value'}]; return $var;      | { "attr": [{"anyKey": false}, {"anyKey": 1}, {"anyKey": "value"}] } |
      | $var.attr = [{'anyKey': false}, {'anyKey': 1}, {'anyKey': 'value'}]; return $var.attr; | [{"anyKey": false}, {"anyKey": 1}, {"anyKey": "value"}]             |

  Scenario Outline: 05 - Storage of text
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                         | result expected                |
      | $var.attr = ''; return $var;                      | { "attr": "" }                 |
      | $var.attr = ''; return $var.attr;                 | ""                             |
      | $var.attr = 'Any text'; return $var;              | { "attr": "Any text" }         |
      | $var.attr = 'Any text'; return $var.attr;         | "Any text"                     |
      | $var.attr = ['A1', 'A2', 'A3']; return $var;      | { "attr": ["A1", "A2", "A3"] } |
      | $var.attr = ['A1', 'A2', 'A3']; return $var.attr; | ["A1", "A2", "A3"]             |

  Scenario Outline: 06 - Storage of empty collection
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed         | result expected |
      | $var.attr = []; return $var;      | { "attr": [] }  |
      | $var.attr = []; return $var.attr; | []              |

  Scenario Outline: 07 - Variável informada no contexto de execução da expressão
    Given send the expression: <expression to be executed>
    And the variable named $var is in the expression's execution context with the value: { "attr": "Any text" }
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed | result expected        |
      | return $var;              | { "attr": "Any text" } |
      | return $var.attr;         | "Any text"             |

  Scenario Outline: 08 - Variável com valor em sub nível
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                            | result expected                                           |
      | $var.attr.sub1 = 1; return $var;                     | { "attr": { "sub1": 1} }                                  |
      | $var.attr.sub1.sub2 = 2; return $var;                | { "attr": { "sub1": { "sub2": 2}} }                       |
      | $var.attr.sub1.sub2.sub3 = 3; return $var;           | { "attr": { "sub1": { "sub2": { "sub3": 3}}} }            |
      | $var.attr.sub1.sub2.sub3.sub4 = 4; return $var;      | { "attr": { "sub1": { "sub2": { "sub3": { "sub4": 4}}}} } |
      | $var.attr.sub1.sub2.sub3.sub4 = 4; return $var.attr; | { "sub1": { "sub2": { "sub3": { "sub4": 4}}}}             |

  Scenario Outline: 09 - Variável sendo criado como null
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                | result expected          |
      | $var = {'attr': null}; return $var;      | {"attr": null}           |
      | $var.attr = null; return $var;           | {"attr": null}           |
      | $var.attr.sub1 = null; return $var;      | {"attr": {"sub1": null}} |
      | $var.attr.sub1 = null; return $var.attr; | {"sub1": null}           |

  Scenario Outline: 10 - Variável não está presente no contexto.
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the error message: <result expected>
    Examples:
      | expression to be executed    | result expected                                                                   |
      | return $var.attr;            | The variable $var is not present in the execution context of the expression.      |
      | $var = {}; return $var.attr; | The variable $var.attr is not present in the execution context of the expression. |

  Scenario Outline: 11 - Variable is not a data type of object
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the error message: <result expected>
    Examples:
      | expression to be executed      | result expected                                 |
      | $var = ''; return $var.attr;   | The variable $var is not a data type of object. |
      | $var = null; return $var.attr; | The variable $var is not a data type of object. |
