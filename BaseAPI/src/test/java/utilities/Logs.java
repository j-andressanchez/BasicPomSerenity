package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.pages.PageObject;

public class Logs extends PageObject {

	DataDrivenExcel dataDriven;
	ExcepcionesProyecto excepciones;
	GeneradorDatos generarDatos;

	static final Logger logger = LoggerFactory.getLogger(Logs.class);
	
	
	/**
	 * Método que guarda los datos de las excepciones creadas
	 * 
	 * @param strId String con ID de excepcion a guardar
	 */
	public void guardarInfoExcepcion(String strId) {
		
		if(!Files.exists(Path.of(Constantes.RUTA_LOG))) {		
			
			try {
				Files.createFile(Path.of(Constantes.RUTA_LOG));
			} catch (Exception e) {
				ExcepcionesProyecto.validaExcepcion(e.toString());
			}
		} 
			
		try (FileWriter f = new FileWriter(Constantes.RUTA_LOG, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);) {
			p.println("\n" + strId);				
		} catch (Exception ex) {
			ExcepcionesProyecto.validaExcepcion(ex.toString());
		}				
	}
	
	/**
	 * Método que guarda la fecha de las ejecucione
	 * 
	 */
	public static void guardarInfoEjecucion() {
		
		if(!Files.exists(Path.of(Constantes.RUTA_LOG_EJECUCION))) {		
			
			try {
				Files.createFile(Path.of(Constantes.RUTA_LOG_EJECUCION));
			} catch (Exception e) {
				ExcepcionesProyecto.validaExcepcion(e.toString());
			}
		} 
			
		try (FileWriter f = new FileWriter(Constantes.RUTA_LOG_EJECUCION, true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);) {
			p.println("\n" + GeneradorDatos.obtenerFechaHoraFull());				
		} catch (Exception ex) {
			ExcepcionesProyecto.validaExcepcion(ex.toString());
		}				
	}
}
