package utilities;

import java.util.logging.Logger;

import net.serenitybdd.core.pages.PageObject;

public class GestionAmbientes extends PageObject{
	
	Logger logger = Logger.getLogger(GestionAmbientes.class.getName());	
	private static String environment = "";
	
	
	/**
	 * Método que configura valor de ambiente para uso recurrente
	 * 
	 * @param strAmbiente String con valor de ambiente tomado de variable de entorno 
	 */
	public static void configurarAmbiente(String strAmbiente) {
		environment = strAmbiente;
	}
	
	/**
	 * Método que devuelve valor de ambiente previamente configurado
	 * 
	 */
	public static String obtenerAmbiente() {
		return environment;
	}

}
