Feature: Operación aritmética - Suma
  Como usuario de una calculadora
  necesito validar que la funcionalidad de suma trabaje adecuadamente
  para poder tener seguridad en las operaciones de contaduría.

  Scenario: Sumar dos números
    Given que el usuario de la calculadora ha definido como sumandos el 5 y el 20
    When el usuario de la calculadora ejecuta el cálculo
    Then el ususario debería obtener el resultado 25