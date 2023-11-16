package utilities;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import net.thucydides.core.pages.PageObject;

public class GeneradorDatos extends PageObject {

	DataDrivenExcel dataDriven;
	ExcepcionesProyecto excepciones;
	SecureRandom random;

	/**
	 * Metodo para obtener fecha y hora en formato DDMMAAhhmm
	 */
	public String obtenerFechaHora() {

		try {
			LocalDateTime ahora = LocalDateTime.now();
			String dia = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("dd")));
			String mes = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("MM")));
			String anio = String.valueOf(ahora.getYear() % 100);
			String hora = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("HH")));
			String minutos = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("mm")));
			return dia + mes + anio + hora + minutos;

		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return null;
	}
	
	/**
	 * Metodo para obtener fecha y hora en formato DDMMAAhhmmss
	 */
	public static String obtenerFechaHoraFull() {

		try {
			LocalDateTime ahora = LocalDateTime.now();
			String dia = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("dd")));
			String mes = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("MM")));
			String anio = String.valueOf(ahora.getYear() % 100);
			String hora = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("HH")));
			String minutos = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("mm")));
			String segundos = String.valueOf(ahora.format(DateTimeFormatter.ofPattern("ss")));
			return dia + mes + anio + hora + minutos + segundos;

		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return null;
	}	

	/**
	 * Metodo que retorna un valor entero aleatorio entre dos valores límite
	 * @param intLimiteInferior entero con valor de limite inferior
	 * @param intLimiteSuperior entero con valor de limite superior
	 * 
	 * @return
	 **/
	public String obtenerValorEnteroAleatorio(int intLimiteInferior, int intLimiteSuperior) {
		random = new SecureRandom();
		int valor = random.nextInt(intLimiteSuperior - intLimiteInferior + 1) + intLimiteInferior;
		return String.valueOf(valor);
	}
	
	/**
	 * Metodo que retorna un valor decimal aleatorio entre dos valores límite
	 * @param dbLimiteInferior entero con valor de limite inferior
	 * @param dbLimiteSuperior entero con valor de limite superior
	 * 
	 * @return
	 **/
	public double obtenerValorDecimalAleatorio(double dbLimiteInferior, double dbLimiteSuperior) {
		random = new SecureRandom();
		double rand = dbLimiteInferior + ( dbLimiteSuperior - dbLimiteInferior ) * random.nextDouble();			
		return Math.round(rand * 100d) / 100d;
	}
	
	/**
	 * Metodo para cambia formato de fecha de dd//MM//yyyy a MM//dd/yyyy
	 * 
	 * @param strFecha String con valor de fecha a modificar
	 */
	public String modificarFormatoFecha(String strFecha) {
		String[] strFechaModificada = strFecha.split("/");
		return strFechaModificada[1] + "/" + strFechaModificada[0] + "/" + strFechaModificada[2];		
	}
}