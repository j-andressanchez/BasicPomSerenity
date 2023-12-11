Feature:  Inicio de sesión en Swag Labs

  @Exitoso
  Scenario Outline: Iniciar sesión con credenciales válidas
    Given Se obtienen los datos de prueba <Caso>
    And El usuario está en la aplicacion
    When El usuario ingresa con credenciales correctas
    Then El usuario debería acceder al catálogo

    Examples: 
     | Caso | 
     ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@Exitoso
|1|
|2|
