Feature: Operación aritmética - Suma
  Como un usuario de una calculadora
  necesito validar que la funcionalidad de suma funcione adecuadamente
  para poder tener seguridad en los operaciones diarias de contaduría.

  Scenario: Sumar dos números
    Given que el usuario de la calculadaora ha definido como sumandos el 5 y el 20
    When el usuario de la calculadora ejecuta el cálculo
    Then el usuario debería obtener el resultado de 25