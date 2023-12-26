Feature: Prueba ejemplo con ReqRes

  @Exitoso
  Scenario Outline: Consulta exitosa de API
    Given Se obtienen los datos de prueba correctos <Caso>
    When Se construye request de consulta
    And Se ejecuta la consulta
    Then Se valida la respuesta obtenida

    Examples: 
     | Caso | 
     ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@EjemploExitoso
|1|
|2|

  @Fallido
  Scenario Outline: Consulta fallida de API
    Given Se obtienen los datos de prueba incorrectos <Caso>
    When Se construye request de consulta
    And Se ejecuta la consulta
    Then Se valida la respuesta obtenida

    Examples: 
     | Caso | 
     ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@EjemploFallido
|1|
