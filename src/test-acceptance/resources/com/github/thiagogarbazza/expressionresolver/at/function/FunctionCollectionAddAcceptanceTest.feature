Feature: Function atan
  It is necessary that expression-resolver perform math atan.

  Scenario Outline: 01. add
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                       | result expected |
      | $c = []; collectionAdd($c, 1); return $c;       | [1]             |
      | $c = []; collectionAdd($c, 123.321); return $c; | [123.321]       |


  Scenario Outline: 01 - Storage of boolean
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                         | result expected |
      | $c = []; collectionAdd($c, true); return $c;      | [true]          |
      | $c = []; collectionAdd($c, false); return $c;     | [false]         |
      | $c = [true]; collectionAdd($c, false); return $c; | [true, false]   |

  Scenario Outline: 02 - Storage of date
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                        | result expected              |
      | $c = []; collectionAdd($c, '2000-01-01'); return $c;             | ["2000-01-01"]               |
      | $c = ['2000-01-01']; collectionAdd($c, '2000-01-02'); return $c; | ["2000-01-01", "2000-01-02"] |

  Scenario Outline: 03 - Storage of number
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                    | result expected |
      | $c = []; collectionAdd($c, 1); return $c;    | [1]             |
      | $c = []; collectionAdd($c, 1.1); return $c;  | [1.1]           |
      | $c = [1]; collectionAdd($c, 1.1); return $c; | [1, 1.1]        |

  Scenario Outline: 04 - Storage of object
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                            | result expected                                  |
      | $c = []; collectionAdd($c, {'anyKey': 'anyValue'}); return $c;                       | [{"anyKey": "anyValue"}]                         |
      | $c = [{'anyKey': 'anyValue'}]; collectionAdd($c, {'anyKey': 'anyValue'}); return $c; | [{"anyKey": "anyValue"}, {"anyKey": "anyValue"}] |

  Scenario Outline: 05 - Storage of text
    Given send the expression: <expression to be executed>
    When to request expression execution
    Then will should to get the result: <result expected>
    Examples:
      | expression to be executed                                                | result expected                      |
      | $c = []; collectionAdd($c, ''); return $c;                               | [""]                                 |
      | $c = []; collectionAdd($c, 'Lorem ipsum'); return $c;                    | ["Lorem ipsum"]                      |
      | $c = ['Lorem ipsum 01']; collectionAdd($c, 'Lorem ipsum 02'); return $c; | ["Lorem ipsum 01", "Lorem ipsum 02"] |
