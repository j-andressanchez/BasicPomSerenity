package utilities;

import static org.junit.Assert.fail;


public class ExcepcionesProyecto {	
	
	private ExcepcionesProyecto() {}
	private static final String ELEMENT_CLICK_INTERCEPTED_COMENTARIO = "No se pudo clickear el elemento porque estaba oculto  ";
	private static final String ELEMENT_NOT_SELECTABLE_COMENTARIO = "El elemento está deshabilitado, no se puede hacer clic o seleccionar  ";
	private static final String ELEMENT_NOT_VISIBLE_COMENTARIO = "El elemento no es visible y, por lo tanto, no se puede interactuar con él.  ";
	private static final String ELEMENT_NOT_INTERACTABLE_COMENTARIO = "El elemento estaba presente pero se encuentra en un estado en el que no se puede interactuar con el  ";
	private static final String INVALID_ELEMENT_STATE_COMENTARIO = "El elemento está oculto por otro elemento o no es visible en la ventana actual  ";
	private static final String STATE_ELEMENT_REFERENCE_COMENTARIO = "El elemento pertenece a un frame diferente al actual  ";
	private static final String TIME_OUT_COMENTARIO = "El elemento no se mostró en el tiempo establecido  ";
	private static final String SCRIPT_TIMEOUT_EXCEPTION_COMENTARIO = "El comando no se pudo ejecutar en el tiempo establecido ";
	private static final String NO_SUNCH_FRAME_COMENTARIO = "Se presentó un problema al intentar cambiar de frame";
	private static final String NO_SUCH_ELEMENT = "El webdriver no pudo determinar el elemento durante el tiempo de ejecución ";
	private static final String NO_ALERT_PRESENT_EXCEPTION_COMENTARIO = "Se esta intentando acceder a una alerta que no se encuentra disponible";
	private static final String ELEMENT_SHOULD_BE_ENABLED_EXCEPTION = "El elemento debería estar habilitado para poder interactuar con el";	
	private static final String STR_ELEMENT_CLICK_INTERCEPTED_EXCEPTION = "ElementClickInterceptedException";
	private static final String STR_ELEMENT_NOT_SELECTABLE_EXCEPTION = "ElementNotSelectableException";
	private static final String STR_ELEMENT_NOT_VISIBLE_EXCEPTION = "ElementNotVisibleException";
	private static final String STR_ELEMENT_NOT_INTERACTABLE_EXCEPTION = "ElementNotInteractableException";
	private static final String STR_INVALID_STATE_EXCEPTION = "InvalidElementStateException";
	private static final String STR_STALE_ELEMENT_REFERENCE_EXCEPTION = "StaleElementReferenceException";
	private static final String STR_TIMEOUT_EXCEPTION = "TimeoutException";
	private static final String STR_SCRIPT_TIMEOUT_EXCEPTION = "ScriptTimeoutException";
	private static final String STR_NO_SUCH_FRAME_EXCEPTION = "NoSuchFrameException";
	private static final String STR_NO_SUCH_ELEMENT_EXCEPTION = "NoSuchElementException";
	private static final String STR_NO_ALERT_PRESENT_EXCEPTION = "NoAlertPresentException";
	private static final String STR_ELEMENT_SHOULD_BE_ENABLED_EXCEPTION = "ElementShouldBeEnabledException";
	
	
	/**
	 * Método que valida que mensaje mostrar a partir de una excepcion
	 * 
	 * @param strExcepcion String con exception de sistema
	 */
	public static void validaExcepcion(String strExcepcion) {
		switch(strExcepcion) {
			case  STR_ELEMENT_CLICK_INTERCEPTED_EXCEPTION:
				lanzarExcepcion(ELEMENT_CLICK_INTERCEPTED_COMENTARIO);
				break;
			case STR_ELEMENT_NOT_SELECTABLE_EXCEPTION:
				lanzarExcepcion(ELEMENT_NOT_SELECTABLE_COMENTARIO);
				break;
			case STR_ELEMENT_NOT_VISIBLE_EXCEPTION:
				lanzarExcepcion(ELEMENT_NOT_VISIBLE_COMENTARIO);
				break;
			case STR_ELEMENT_NOT_INTERACTABLE_EXCEPTION:
				lanzarExcepcion(ELEMENT_NOT_INTERACTABLE_COMENTARIO);
				break;
			case STR_INVALID_STATE_EXCEPTION:
				lanzarExcepcion(INVALID_ELEMENT_STATE_COMENTARIO);
				break;
			case STR_STALE_ELEMENT_REFERENCE_EXCEPTION:
				lanzarExcepcion(STATE_ELEMENT_REFERENCE_COMENTARIO);
				break;
			case STR_TIMEOUT_EXCEPTION:
				lanzarExcepcion(TIME_OUT_COMENTARIO);
				break;
			case STR_SCRIPT_TIMEOUT_EXCEPTION:
				lanzarExcepcion(SCRIPT_TIMEOUT_EXCEPTION_COMENTARIO);
				break;
			case STR_NO_SUCH_FRAME_EXCEPTION:
				lanzarExcepcion(NO_SUNCH_FRAME_COMENTARIO);
				break;
			case STR_NO_SUCH_ELEMENT_EXCEPTION:
				lanzarExcepcion(NO_SUCH_ELEMENT);
				break;
			case STR_NO_ALERT_PRESENT_EXCEPTION:
				lanzarExcepcion(NO_ALERT_PRESENT_EXCEPTION_COMENTARIO);
				break;
			case STR_ELEMENT_SHOULD_BE_ENABLED_EXCEPTION:
				lanzarExcepcion(ELEMENT_SHOULD_BE_ENABLED_EXCEPTION);
				break;
			default:
				fail(strExcepcion + " Se presento un problema inesperado: ");
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
