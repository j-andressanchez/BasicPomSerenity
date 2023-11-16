package utilities;

import static org.junit.Assert.fail;


public class ExcepcionesProyecto {	
	
	private ExcepcionesProyecto() {}

	private static final String STR_CONNECT_EXCEPTION = "ConnectException";
	private static final String STR_SOCKET_TIMEOUT_EXCEPTION = "SocketTimeoutException";
	private static final String STR_HTTP_RESPONSE_EXCEPTION = "HttpResponseException";
	private static final String STR_JSON_PARSE_EXCEPTION = "JsonParseException";
	private static final String STR_IO_EXCEPTION = "IOException";
	private static final String STR_SSL_HANDSHAKE_EXCEPTION = "SSLHandshakeException";
	private static final String STR_UNKNOWN_HOST_EXCEPTION = "UnknownHostException";
	private static final String STR_ILEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";
	private static final String STR_SOCKET_EXCEPTION = "SocketException";
	
	private static final String CONNECT_EXCEPTION_COMENTARIO = "No se puede establecer una conexión con el servidor API";
	private static final String SOCKET_TIMEOUT_EXCEPTION_COMENTARIO = "La respuesta del servidor no se recibió dentro del tiempo límite especificado";
	private static final String HTTP_RESPONSE_EXCEPTION_COMENTARIO = "El servidor API retornó una respuesta que no está en el rango esperado";
	private static final String JSON_PARSE_EXCEPTION_COMENTARIO = "Error al analizar el formato JSON de la respuesta obtenida";
	private static final String IO_EXCEPTION_COMENTARIO = "Error de entrada/salida al realizar la solicitud o recibir la respuesta";
	private static final String SSL_HANDSHAKE_EXCEPTION_COMENTARIO = "Error en el proceso de handshake SSL/TLS al establecer la conexión segura con el servidor";
	private static final String UNKNOWN_HOST_EXCEPTION_COMENTARIO = "No se puede resolver la dirección IP del servidor API a partir de su nombre de host";
	private static final String ILEGAL_ARGUMENT_EXCEPTION_COMENTARIO = "Hay argumentos inválidos en la solicitud API";
	private static final String SOCKET_EXCEPTION_COMENTARIO = "Error con el socket de conexión, pudo perderse la conexión durante la solicitud";
	
	/**
	 * Método que valida que mensaje mostrar a partir de una excepcion
	 * 
	 * @param strExcepcion String con exception de sistema
	 */
	public static void validaExcepcion(String strExcepcion) {
		switch(strExcepcion) {
		case STR_CONNECT_EXCEPTION:
			lanzarExcepcion(CONNECT_EXCEPTION_COMENTARIO);
				break;
		case STR_SOCKET_TIMEOUT_EXCEPTION:
			lanzarExcepcion(SOCKET_TIMEOUT_EXCEPTION_COMENTARIO);
				break;
		case STR_HTTP_RESPONSE_EXCEPTION:
			lanzarExcepcion(HTTP_RESPONSE_EXCEPTION_COMENTARIO);
				break;
		case STR_JSON_PARSE_EXCEPTION:
			lanzarExcepcion(JSON_PARSE_EXCEPTION_COMENTARIO);
				break;
		case STR_IO_EXCEPTION:
			lanzarExcepcion(IO_EXCEPTION_COMENTARIO);
				break;
		case STR_SSL_HANDSHAKE_EXCEPTION:
			lanzarExcepcion(SSL_HANDSHAKE_EXCEPTION_COMENTARIO);
				break;
		case STR_UNKNOWN_HOST_EXCEPTION:
			lanzarExcepcion(UNKNOWN_HOST_EXCEPTION_COMENTARIO);
				break;
		case STR_ILEGAL_ARGUMENT_EXCEPTION:
			lanzarExcepcion(ILEGAL_ARGUMENT_EXCEPTION_COMENTARIO);
				break;
		case STR_SOCKET_EXCEPTION:
			lanzarExcepcion(SOCKET_EXCEPTION_COMENTARIO);
				break;
			default:
				fail(strExcepcion + " Se presento un problema inesperado");
				break;
		}
	}
	
	/**
	 * Método trigger que lanza excepciones
	 * 
	 * @param strMensaje String con mensaje a mostrar
	 */
	public static void lanzarExcepcion(String strMensaje) {
		fail(strMensaje);
	}    
    
}
