Feature: Obtener Datos

  @ObtenerDatosCorrectos
  Scenario Outline: Obtener datos de prueba
  	Given Se obtienen los datos de prueba correctos <Caso>
		When realizar acciones
		Then validacion de resultados
    
  Examples:
      | Caso | 
      ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@EjemploExitoso
|1|
|2|
