Feature:  Inicio de sesión en Swag Labs

  @Exitoso
  Scenario Outline: Iniciar sesión con credenciales válidas
    Given Se obtienen los datos de prueba correcto <Caso>
    And El usuario está en la aplicacion
    When El usuario ingresa con credenciales correctas
    Then El usuario debería acceder al catálogo

    Examples: 
     | Caso | 
     ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@Exitoso
|1|
|2|

  @Fallido
  Scenario Outline: Iniciar sesión con credenciales inválidas
    Given Se obtienen los datos de prueba incorrecto <Caso>
    And El usuario está en la aplicacion
    When El usuario ingresa con credenciales incorrectas
    Then El usuario no debería acceder al catálogo

    Examples: 
     | Caso | 
     ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@Fallido
|1|
