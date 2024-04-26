package utilities;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.response.Response;
import net.thucydides.core.pages.PageObject;


public class ManejoJson extends PageObject{
	
	DataDrivenExcel dataDriven;	
	
	private Logger logger = LoggerFactory.getLogger(ManejoJson.class);	
	
	/**
	 * Metodo que extrae valor de un response, lo carga en el mapa de datos y lo retorna
	 * 
	 * @param response Response del cual se extraeran los datos
	 * @param strKey String con el valor de identificador a extraer del response
	 * @param strTipo String con el tipo de variable a extraer. Puede ser string, int o boolean
	 */
	public String extraerValorResponse(Response response, String strKey, String strTipo) {		
		
		try {
			
			String strValor = "";			
			JSONObject obj = new JSONObject(response.asString());	
			
			switch(strTipo) {
			
				case "string":
					strValor = 	(String) obj.get(strKey);
					break;
				case "int":
					int intValor = (int) obj.get(strKey);
					strValor = String.valueOf(intValor);
					break;
				case "boolean":
					boolean blValor = (boolean) obj.get(strKey);
					strValor = String.valueOf(blValor);
					break;
				default:
					break;
				}
			
			if(dataDriven.getDataMap(strKey) == null) {
				dataDriven.setDataMap(strKey, strValor);
			} else {
				dataDriven.reemplazarDataMap(strKey, strValor);
			}			
			
			logger.info(strValor);
			return strValor;
			
		}catch(Exception e) {
			logger.info("Se presento un error al intentar extraer el valor deseado del response suministrado");
		}
		
		return null;
	}
}
