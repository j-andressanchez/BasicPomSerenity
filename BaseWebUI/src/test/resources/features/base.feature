Feature: Obtener Datos

  @ObtenerDatos
  Scenario Outline: Obtener datos de prueba
  	Given Se obtienen los datos de prueba correcto <Caso>
		When Se realizan las acciones
		Then Se validan los resultados
    
  Examples:
      | Caso | 
      ##@externaldata@src/test/resources/datadriven/BaseDataDriven.xlsx@Exitoso
|1|
|2|
