package utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import net.serenitybdd.core.pages.PageObject;

public class DataDrivenExcel extends PageObject {

	static Map<String, String> mapaDatosExcel = new HashMap<>();
	static Map<String, String> mapaDatosExcelTarea = new HashMap<>();
	static Logger logger = Logger.getLogger(DataDrivenExcel.class.getName());

	/**
	 * Metodo que extrae datos de Excel y los carga al mapa de datos
	 * 
	 * @param strRuta String con ruta de archivo Excel a leer
	 * @param intCaso Entero con fila de Excel a cargar al mapa de datos
	 * @param strCaso String con hoja de Excel a cargar al mapa de datos
	 */
	public void cargarMapaDatos(String strRuta, int intCaso, String strCaso) {
		try {

			mapaDatosExcel.clear();
			List<Map<String, String>> excelData = new ExcelReader().getData(strRuta, strCaso);

			for (Entry<String, String> mapData : excelData.get(intCaso - 1).entrySet()) {
				setDataMap(mapData.getKey(), mapData.getValue());
			}

		} catch (Exception e) {
			logger.info("Error en metodo cargarMapaDatos de la clase DataDrivenExcel: " + e.getMessage());
		}
	}

	/**
	 * Metodo que extrae los datos de mapa
	 * 
	 * @param strDatoSolicitado String con valor de key a consultar en el mapa
	 */
	public String getDataMap(String strDatoSolicitado) {
		return mapaDatosExcel.get(strDatoSolicitado);
	}

	/**
	 * Metodo que agrega datos al mapa
	 * 
	 * @param strKey   String con valor de key a agregar en el mapa
	 * @param strValue String con valor de dato a agregar en el mapa
	 */
	public void setDataMap(String strKey, String strValue) {
		mapaDatosExcel.put(strKey, strValue);
	}

	/**
	 * Metodo que reemplaza datos al mapa
	 * 
	 * @param strKey   String con valor de key a reemplazar en el mapa
	 * @param strValue String con valor de dato a reemplazar en el mapa
	 */
	public void reemplazarDataMap(String strKey, String strValue) {
		mapaDatosExcel.replace(strKey, strValue);
	}	
	
	/**
	 * Metodo que extrae los datos de mapa de tareas
	 * 
	 * @param strDatoSolicitado String con valor de key a consultar en el mapa
	 */
	public String getDataMapTarea(String strDatoSolicitado) {
		return mapaDatosExcelTarea.get(strDatoSolicitado);
	}

	/**
	 * Metodo que agrega datos al mapa de tareas
	 * 
	 * @param strKey   String con valor de key a agregar en el mapa
	 * @param strValue String con valor de dato a agregar en el mapa
	 */
	public void setDataMapTarea(String strKey, String strValue) {
		mapaDatosExcelTarea.put(strKey, strValue);
	}
	
	/**
	 * Metodo que reemplaza datos al mapa de tareas
	 * 
	 * @param strKey   String con valor de key a reemplazar en el mapa
	 * @param strValue String con valor de dato a reemplazar en el mapa
	 */
	public void reemplazarDataMapTarea(String strKey, String strValue) {
		mapaDatosExcelTarea.replace(strKey, strValue);
	}
}
