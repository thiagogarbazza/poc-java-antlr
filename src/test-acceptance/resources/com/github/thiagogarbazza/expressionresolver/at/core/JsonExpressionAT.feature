Feature: JSON expression
  It is necessary that expression-resolver perform json expression.

  Scenario Outline: 01. Perform json expression.
    Given Send the expression "<expression>".
    When I ask what the result is?
    Then I should have resulted the json: "<expression-result>".
    Examples:
      | expression                                     | expression-result                       |
      | return { };                                    | {}                                      |
      | return {"any-key" : 5};                        | {"any-key":5}                           |
      | return {"any-key" : [5, 7]};                   | {"any-key":[5,7]}                       |
      | return {"any-key" : "valor"};                  | {"any-key":"valor"}                     |
      | return {"any-key" : ["valor-1", "valor-2"]};   | {"any-key":["valor-1","valor-2"]}       |
      | return {"any-key" : true};                     | {"any-key":true}                        |
      | return {"any-key" : [true, false]};            | {"any-key":[true,false]}                |
      | return {"any-key" : 1900/01/01};               | {"any-key":"1900/01/01"}                |
      | return {"any-key" : [1900/01/01, 1900/01/02]}; | {"any-key":["1900/01/01","1900/01/02"]} |
      | return {"any-key" : {"any-sub-key": 5} };      | {"any-key":{"any-sub-key":5}}           |
