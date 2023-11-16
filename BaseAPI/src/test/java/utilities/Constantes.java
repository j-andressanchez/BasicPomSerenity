package utilities;


public class Constantes {

	private Constantes() {
		throw new IllegalStateException("Clase utilitaria: Constantes");
	}

	public static final String RUTA_DATADRIVEN = "datadriven/BaseDataDriven.xlsx";
	public static final String RUTA_CREDENCIALES = "src/test/resources/configuration/credenciales.properties";
	public static final String RUTA_TOKEN = "src/test/resources/configuration/token.properties";
	public static final String RUTA_URL = "src/test/resources/configuration/url.properties";
	public static final String RUTA_LOG = "logs/log_excepciones.txt";
	public static final String RUTA_LOG_EJECUCION = "src/test/resources/ejecuciones/log_ejecuciones.txt";
	public static final String RUTA_FEATURES = "src/test/resources/features";
	public static final String WEBDRIVER_NAME = "webdriver.name";
	public static final String HOME_PAGE = "page:home.page";
	public static final String ESCRITURA_FEATURE = "EscrituraFeature";
	public static final String EXTERNAL_DATA = "##@externaldata";
	public static final String CASO = "Caso";
	public static final String ID = "id";
	public static final String FINAL = "final";
	public static final String INICIO = "inicio";
	public static final String PERFIL = "Perfil";
	public static final String CREAR_EXCEPCION = "CrearExcepcion";
	public static final String TAREAS_EXCEPCION = "TareasExcepcion";
	public static final String TIPO_EXCEPCION = "TipoExcepcion";
}
