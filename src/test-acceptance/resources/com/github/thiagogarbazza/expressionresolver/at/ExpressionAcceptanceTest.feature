Feature: Expression interpreter

  Rule: Validação

    Scenario: 01 - Validação da expressão válida
      Given send the expression: return 5 * 5;
      When to request expression validation
      Then will should not to get error message

    Scenario: 02 - Validação da expressão que contenha quebra de linha
      Given send the expression:
        """
        return
          5
          *
          5
        ;
        """
      When to request expression validation
      Then will should not to get error message

    Scenario: 03 - Validação da expressão vazia
      When to request expression validation
      Then will should to get the error message: Expression can not be null or empty.

    Scenario: 04 - Validação da expressão inválida
      Given send the expression: return ABC(5 * 5);
      When to request expression validation
      Then will should to get the error message: In position 1:7 token recognition error at: 'A'.

  Rule: Execução

    Scenario: 01 - Executando expressão
      Given send the expression: return 5 * 5;
      When to request expression execution
      Then will should to get the result: 25

    Scenario: 02 - Executando expressão passando dados no contexto
      Given send the expression: return 5 * $valor;
      And the variable named $valor is in the expression's execution context with the value: 5
      When to request expression execution
      Then will should to get the result: 25

    Scenario: 03 - Executando expressão com quebra de linha
      Given send the expression:
        """
        return
          5
          *
          5
        ;
        """
      When to request expression execution
      Then will should to get the result: 25

    Scenario: 04 - Validação da expressão vazia
      When to request expression execution
      Then will should to get the error message: Expression can not be null or empty.

    Scenario: 05 - Executando expressão inválida
      Given send the expression: return ABC(5 * 5);
      When to request expression execution
      Then will should to get the error message: In position 1:7 token recognition error at: 'A'.
