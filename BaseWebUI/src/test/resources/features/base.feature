Feature: Obtener Datos

  @ObtenerDatos
  Scenario Outline: Obtener datos de prueba
  	Given obtener datos de prueba <Caso>
		When realizar acciones
		Then validacion de resultados
    
  Examples:
      | Caso | 
      ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@Exitoso
|1|
|2|
