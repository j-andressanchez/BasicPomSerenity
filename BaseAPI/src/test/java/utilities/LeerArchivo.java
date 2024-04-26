package utilities;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import net.thucydides.core.pages.PageObject;


public class LeerArchivo extends PageObject{
	
	Servicios servicios;

	/**
	 * Metodo que obiente URL de servicio apartir del ambiente indicado
	 * 
	 * @param strAmbiente String de ambiente a consultar
	 * @param strServicio String con detalle de servicio a ejecutar
	 */
	public String obtenerUrl(String strAmbiente, String strServicio) {
		try {			
			String strServiceUrl;
			String strPath = Constantes.RUTA_URL;
			String strKeyWord = strAmbiente + "-" + strServicio;			
			File file = new File(strPath);
			
			try (Scanner sc = new Scanner(file)) {
				while (sc.hasNextLine()) {
					String linea = sc.nextLine();
					if (linea.contains(strKeyWord)) {
						int encontrarIgual = linea.indexOf("=");
						strServiceUrl = linea.substring(encontrarIgual + 1).trim();
						return strServiceUrl;
					}
				}
			}
			
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		return null;		
	}
	
	/**
	 * Metodo que obtiene token de autorizacion para servicio a partir del ambiente indicado
	 * 
	 * @param strAmbiente String de ambiente a consultar
	 */
	public String obtenerToken(String strAmbiente) {
		try {			
			
			String strPath = Constantes.RUTA_TOKEN;
			String strToken;		
			File file = new File(strPath);
			
			try (Scanner sc = new Scanner(file)) {
				while (sc.hasNextLine()) {
					String linea = sc.nextLine();
					if (linea.contains(strAmbiente)) {	
						int encontrarIgual = linea.indexOf("_");
						strToken = linea.substring(encontrarIgual + 1).trim();
						return strToken;
					}
				}
			}
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return null;
	}
	
	/**
	 * Metodo que lee un archivo y retorna su contenido
	 * 
	 * @param strRuta String con ruta de archivo a leer
	 */
	public String leerArchivoGenerico(String strRuta) {
		try {			
					
			File file = new File(strRuta);
			
			try (Scanner sc = new Scanner(file)) {
				
				while (sc.hasNextLine()) {
					
					return sc.nextLine();
				}				
			}
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return null;
	}
	
	/**
	 * Metodo que obtiene body request desde un archivo
	 * 
	 * @param strRuta String con ruta donde se encuentra el archivo a leer
	 */	
	public String obtenerBodyRequest(String strRuta) {
		try {
			Servicios.setBodyRequest(Files.readString(Paths.get(strRuta), StandardCharsets.UTF_8));
			return servicios.getBodyRequest();
			
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return null;
	}
	
	/**
	 * Metodo que obtiene token de autorizacion para servicio a partir del ambiente indicado
	 * 
	 * @param strAmbiente String de ambiente a consultar
	 */
	public static List<String> obtenerArregloId(String strAmbiente, String strRuta) {
		
		List<String> list = new ArrayList<>();	
		
		try {			
							
			File file = new File(strRuta);
			
			try (Scanner sc = new Scanner(file)) {				
				while (sc.hasNextLine()) {
					String linea = sc.nextLine();
					if (linea.contains(strAmbiente)) {	
						int encontrarIgual = linea.indexOf("_");
						list.add(linea.substring(encontrarIgual + 1).trim());				
					}
				}
			}
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
		
		return list;
	}
}
