package utilities;

import static org.junit.Assert.fail;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccionesWeb extends PageObject {
	
	ExcepcionesProyecto excepciones;

	ArrayList<String> arrayTabs = null;
	String strIDElementoWeb;
	boolean flagPopUp = false;
	private Logger logger = LoggerFactory.getLogger(AccionesWeb.class);

	/**
	 * Metodo para escribir Texto
	 * 
	 * @param element  elemento donde escribir
	 * @param strTexto texto a escribir
	 */
	public void escribirTexto(WebElementFacade element, String strTexto) {
		try {
			waitFor(element).isPresent();
			element.clear();
			element.sendKeys(strTexto);
		} catch (Exception e) {
			logger.error("Error en la clase AccionesWeb en el metodo escribirTexto" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo para dar click a un boton
	 * 
	 * @param element tipo WebElementFacade el elemento a interactuar
	 */
	public void clickElemento(WebElementFacade element) {
		try {
			element.waitUntilClickable();
			element(element).click();
		} catch (Exception e) {
			logger.error("Error en la clase AccionesWeb en el metodo clickBoton " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo para dar click a un boton
	 * 
	 * @param element tipo By el elemento a interactuar
	 */
	public void clickElementoBy(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), obtenerTimeOutSerenity());
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element(element).click();
		} catch (Exception e) {
			logger.error("Error en la clase AccionesWeb en el metodo clickBotonElementoBy " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que cambia al frame por defecto
	 */
	public void switchFrameDefault() {
		try {
			Set<String> allWindows = getDriver().getWindowHandles();
			for (String currentWindow : allWindows) {
				getDriver().switchTo().window(currentWindow);
			}
			esperaCargaPagina();
		} catch (Exception e) {
			logger.error("Error en la clase AccionesWeb en el metodo switchFrame " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Espera a que carge todos los objetos de la pagina
	 */
	public void esperaCargaPagina() {
		int intTimer = 30;
		try {
			new WebDriverWait(getDriver(), intTimer).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		} catch (Exception e) {
			logger.error("en la clase AccionesWeb en el metodo esperaCargaPagina " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo encargado de abrir la url
	 * 
	 * @param strURL elemento de tipo String la cual pueder ser una IP o URL para
	 *               abrirla en el navegador
	 */
	public void abrirURL(String strURL) {			
		openAt(strURL);
		getDriver().manage().window().maximize();		
	}

	/**
	 * Metodo que espera a que desaparezca el elemento
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se espera que
	 *                desaparezca
	 */
	public void esperaDesaparezcaElemento(By element) {
		int cont = 0;
		boolean boolBandera = true;
		logger.info("Elemento que se valida que desaparezca: " + element);
		editarTiempoImpilicitoSerenity(3);
		do {
			try {				
				WebDriverWait wait = new WebDriverWait(getDriver(), obtenerTimeOutSerenity());
				wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(element)));
				List<WebElement> numElement = getDriver().findElements(element);

				if (numElement.size() > 0) {
					boolBandera = true;
				} else {
					boolBandera = false;
				}

			} catch (Exception e) {
				logger.info("Clase : AccionesWeb, Metodo: esperaDesaparezcaElemento");
				ExcepcionesProyecto.validaExcepcion(e.toString());
			}

			logger.info("Elemento todavía visible");
			cont++;

		} while ((boolBandera) && (cont <= 150));

		if ((boolBandera)) {
			fail("Elemento no desaparecio de pantalla: " + element);
		} else {
			logger.info("Elemento desaparecio de pantalla: " + element);
			restaurarTiempoDefectoSerenity();
		}
	}

	/**
	 * Este metodo espera a que un elemento este habilitado
	 * 
	 * @param xpath elemento que se requiere esperar a que este habilitado
	 * @return retorna true o false si el elemento esta o no habilitado
	 */
	public boolean esperaElementoHabilitado(WebElementFacade element) {

		try {
			if (element(element).isEnabled()) {
				return true;
			}
		} catch (Exception e) {
			logger.info("Error en la clase AccionesWeb en el metodo esperaElementoHabilitado" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		fail("Elemento esperado no se habilito: " + element);
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este presente
	 * 
	 * @param xpath elemento que se requiere esperar a que este presente
	 * @return retorna true o false si el elemento esta o no presente
	 */
	public boolean esperaElementoPresente(WebElementFacade element) {

		try {
			if (element(element).isPresent()) {
				return true;
			}
		} catch (Exception e) {
			logger.info("Error en la clase AccionesWeb en el metodo esperaElementoPresente" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		fail("Elemento esperado no esta presente: " + element);
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este presente
	 * 
	 * @param xpath elemento tipo By que se requiere esperar a que este presente
	 * @return retorna true o false si el elemento esta o no presente
	 */
	public boolean esperaElementoPresenteBy(By elementBy) {

		try {
			if (element(elementBy).isPresent()) {
				return true;
			}
		} catch (Exception e) {
			logger.info("Error en la clase AccionesWeb en el metodo esperaElementoPresenteBy" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		fail("Elemento esperado no esta presente: " + elementBy);
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este habilitado
	 * 
	 * @param xpath elemento que se requiere esperar a que sea visible modificando
	 *              TimeOut de Serenity
	 * @return retorna true o false si el elemento esta o no habilitado
	 */
	public boolean esperaElementoHabilitadoCondicional(WebElementFacade element) {

		try {
			editarTiempoImpilicitoSerenity(5);
			if (element(element).isEnabled()) {
				restaurarTiempoDefectoSerenity();
				return true;
			}
		} catch (Exception e) {
			logger.info("en la clase AccionesWeb en el metodo esperaElementoHabilitadoCondicional" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		restaurarTiempoDefectoSerenity();
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este presente
	 * 
	 * @param xpath elemento By que se requiere esperar a que este presente
	 * @return retorna true o false si el elemento esta o no presente
	 */
	public boolean esperaElementoPresenteCondicionalBy(By elementBy) {

		try {
			editarTiempoImpilicitoSerenity(5);
			if (element(elementBy).isPresent()) {
				restaurarTiempoDefectoSerenity();
				return true;
			}
		} catch (Exception e) {
			logger.info("en la clase AccionesWeb en el metodo esperaElementoPresenteCondicionalBy" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		restaurarTiempoDefectoSerenity();
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este presente
	 * 
	 * @param xpath elemento WebElement que se requiere esperar a que este presente
	 * @return retorna true o false si el elemento esta o no presente
	 */
	public boolean esperaElementoPresenteCondicional(WebElementFacade element) {

		try {
			editarTiempoImpilicitoSerenity(5);
			if (element(element).isPresent()) {
				restaurarTiempoDefectoSerenity();
				return true;
			}
		} catch (Exception e) {
			logger.info("en la clase AccionesWeb en el metodo esperaElementoPresenteCondicional" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		restaurarTiempoDefectoSerenity();
		return false;
	}

	/**
	 * Metodo para obtener el tiempo del serenity properties
	 * 
	 * @return retorna el tiempo en segundo del serenity
	 */
	public int obtenerTimeOutSerenity() {
		Duration tiempo = getImplicitWaitTimeout();
		return (int) tiempo.getSeconds();
	}

	/**
	 * Metodo que edita el tiempo implicito predeterminado
	 * 
	 * @param intTime tiempo
	 */
	public void editarTiempoImpilicitoSerenity(int intTime) {
		setImplicitTimeout(intTime, ChronoUnit.SECONDS);
	}

	/**
	 * restaura el tiempo cambiado con el metodo editaTiempoImplicito
	 */
	public void restaurarTiempoDefectoSerenity() {
		resetImplicitTimeout();
	}

	/**
	 * Metodo que verifica si elemento web no contiene valor
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se verifica
	 */
	public boolean verificarSiVacioValue(WebElementFacade element) {

		boolean flag = false;

		try {
			element.waitUntilEnabled();
			String strElementValue = element(element).getValue();

			if (strElementValue.isBlank()) {
				flag = true;
			}
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return flag;
	}
	
	/**
	 * Metodo que verifica si elemento web no contiene texto
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se verifica
	 */
	public boolean verificarSiVacioTexto(WebElementFacade element) {

		boolean flag = false;

		try {
			element.waitUntilEnabled();
			String strElementTexto = element(element).getText();

			if (strElementTexto.isEmpty()) {
				flag = true;
			}
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return flag;
	}

	/**
	 * Metodo que extrae el texto de un elemento web
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se valida
	 */
	public String obtenerTextoElementoWeb(WebElementFacade element) {

		try {
			element.waitUntilPresent();
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return element(element).getText();
	}

	/**
	 * Metodo que extrae el texto de un elemento web
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se valida
	 */
	public String obtenerTextoElementoWebCondicional(WebElementFacade element) {

		String strText = "";

		try {

			if (esperaElementoHabilitadoCondicional(element)) {
				element.waitUntilPresent();
				strText = element(element).getText();
			} else {
				strText = "No se puede obtener texto de elemento web pues no esta habilitado";
			}

		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		return strText;
	}

	/**
	 * Metodo que abre una nueva pestana en el navegador web
	 * 
	 * @param intPestana entero con el valor de identificador de pestana
	 * @param strURL     String con url a abrir
	 */
	public void abrirNuevaPestana(int intPestana, String strURL) {

		try {
			JavascriptExecutor ex = (JavascriptExecutor) getDriver();
			ex.executeScript("window.open()");
			arrayTabs = new ArrayList<>(getDriver().getWindowHandles());
			getDriver().switchTo().window(arrayTabs.get(intPestana));
			getDriver().get(strURL);
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que cambia estre pestanas en el navegador web
	 * 
	 * @param intPestana entero con el valor de identificador de pestana
	 */
	public void switchPestana(int intPestana) {

		try {
			arrayTabs = new ArrayList<>(getDriver().getWindowHandles());
			getDriver().switchTo().window(arrayTabs.get(intPestana));
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que cierra una pestana en el navegador web
	 * 
	 * @param intPestana entero con el valor de identificador de pestana
	 */
	public void cerrarPestana(int intPestana) {

		try {
			JavascriptExecutor ex = (JavascriptExecutor) getDriver();
			arrayTabs = new ArrayList<>(getDriver().getWindowHandles());
			getDriver().switchTo().window(arrayTabs.get(intPestana));
			ex.executeScript("window.close()");
			getDriver().switchTo().window(arrayTabs.get(0));
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que toma evidencia y complementa la estrategia planteada en el
	 * serenity.properties
	 * 
	 */
	public void tomarEvidencia() {
		Serenity.takeScreenshot();
	}

	/**
	 * Metodo que da click a un elemento web usando JavaScript
	 * 
	 * @param element elemento de tipo WebElementFacade con el cual se interactua
	 */
	public void clickJavaScript(WebElementFacade element) {
		JavascriptExecutor ex = (JavascriptExecutor) getDriver();
		ex.executeScript("arguments[0].click()", element);
	}

	/**
	 * Metodo que elimina un atributo de un elemento web usando JavaScript
	 * 
	 * @param element     elemento de tipo WebElementFacade con el cual se
	 *                    interactua
	 * @param strAtributo String con valor de atributo a remover
	 */
	public void removerAtributoElementoWeb(WebElementFacade element, String strAtributo) {

		try {
			JavascriptExecutor ex = (JavascriptExecutor) getDriver();
			ex.executeScript("arguments[0].removeAttribute('" + strAtributo + "')", element);
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que da modifica un elemento web usando JavaScript
	 * 
	 * @param element elemento de tipo WebElementFacade con el cual se interactua,
	 *                para hacerlo visible o interactuable
	 */
	public void modificarEstiloElemento(WebElementFacade element) {

		try {
			String xpath = element.toString().replace("By.xpath: ", "");
			String strID = element(By.xpath(xpath)).getAttribute(Constantes.ID).toString();
			String jsConsole = "document.getElementById('";
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.display = 'block';");
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.position = 'unset';");
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.overflow = 'visible';");
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.opacity = 'unset';");
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.height = '10px';");
			((JavascriptExecutor) getDriver()).executeScript(jsConsole + strID + "').style.width = '10px';");
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que cambia a un frame especifico
	 * 
	 */
	public void switchFrameEspecifico(String strFrame) {

		try {
			getDriver().switchTo().frame(strFrame);
			esperaCargaPagina();
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que espera a que carge iFrame en pantalla
	 * 
	 * @param strID String con valor de ID de iFrame a validar
	 */
	public void esperaCargaFrame(String strID) {
		try {

			for (int i = 0; i < 12; i++) {

				if (!(esperaElementoPresenteCondicionalBy(By.xpath("//iframe[@id='" + strID + "']")))) {
					logger.info("iFrame " + strID + " aun no carga");
				} else {
					logger.info("iFrame presente en pantalla");
					break;
				}
			}
		} catch (Exception e) {
			logger.error("en la clase AccionesWeb en el metodo esperaCargaFrame " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que genera la accion scroll hasta un elemento web
	 * 
	 * @param element elemento de tipo WebElementFacade el cual se valida
	 */
	public void scroll(WebElementFacade element) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que bordea un elemento tipo By en pantalla para resaltarlo
	 * 
	 */
	public void bordearElemento(By webLocalizador) {

		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='2px dashed red'", element(webLocalizador));
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que recarga la pagina con que se interactua
	 * 
	 */
	public void recargarPagina() {
		try {
			getDriver().navigate().refresh();
		} catch (Exception e) {
			logger.error("en la clase AccionesWeb en el metodo recargarPagina " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que elimina todos los cookies de una pagina
	 * 
	 */
	public void borrarCookies() {
		try {
			getDriver().manage().deleteAllCookies();
		} catch (Exception e) {
			logger.error("en la clase AccionesWeb en el metodo recargarPagina " + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}
	
	/**
	 * Metodo que da click a elemento web utilizando libreria Action
	 * 
	 * @param element    elemento de tipo WebElementFacade con el cual se interactua
	 */
	public void clickElementoAction(WebElementFacade element) {
		Actions act = new Actions(getDriver());
		act.click(element).build().perform();
	}

	/**
	 * Metodo que convierte un elemento By a WebElementFacade
	 * 
	 * @param element elemento de tipo By con el cual se interactua
	 */
	public WebElementFacade convertirElementoWebBy(By xpath) {
		return element(xpath);
	}

	/**
	 * Este metodo espera a que un elemento este visible en pantalla
	 * 
	 * @param xpath elemento que se requiere esperar a que sea visible modificando
	 *              TimeOut de Serenity
	 * @return retorna true o false si el elemento esta o no visible
	 */
	public boolean esperaElementoVisibleCondicionalBy(By element) {

		try {
			editarTiempoImpilicitoSerenity(5);
			if (element(element).isVisible()) {
				restaurarTiempoDefectoSerenity();
				return true;
			}
		} catch (Exception e) {
			logger.info("en la clase AccionesWeb en el metodo esperaElementoVisibleCondicional" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		restaurarTiempoDefectoSerenity();
		return false;
	}

	/**
	 * Este metodo espera a que un elemento este presente una cantidad de tiempo
	 * variable
	 * 
	 * @param xpath       elemento que se requiere esperar a que este presente
	 * @param intSegundos Entero que representa la cantidad en segundos a esperar
	 * @return retorna true o false si el elemento esta o no presente
	 */
	public boolean esperaElementoCondicionalTimeOutVarible(WebElementFacade elementBy, int intSegundos) {

		try {
			editarTiempoImpilicitoSerenity(intSegundos);
			if (elementBy.isPresent()) {
				restaurarTiempoDefectoSerenity();
				return true;
			}
		} catch (Exception e) {
			logger.info("en la clase AccionesWeb en el metodo esperaElementoCondicionalTimeOutVarible" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}

		restaurarTiempoDefectoSerenity();
		return false;
	}

	/**
	 * Metodo que realiza scroll hasta el final o inicio de una pagina usando
	 * JavaScript
	 * 
	 * @param strOpcion String que indica si se ira al final o al inicio de la
	 *                  pagina
	 */
	public void scrollExtremoPagina(String strOpcion) {

		try {

			JavascriptExecutor ex = (JavascriptExecutor) getDriver();

			if (strOpcion.equalsIgnoreCase(Constantes.FINAL)) {
				ex.executeScript("document.documentElement.scrollTop = document.documentElement.scrollHeight");
			} else if (strOpcion.equalsIgnoreCase(Constantes.INICIO)) {
				ex.executeScript("document.body.scrollTop = document.documentElement.scrollTop = 0");
			}

		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 ** Metodo para escribir Texto    
	 ** 
	 * @param element  elemento a escribir     
	 ** @param strTexto texto que se va a escribir     
	 */
	public void escribirTextoElementoBy(By element, String strTexto) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), obtenerTimeOutSerenity());
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element(element).clear();
			element(element).sendKeys(strTexto);
		} catch (Exception e) {
			logger.error("Error en la clase AccionesWeb en el metodo escribirTexto" + e);
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	/**
	 ** Metodo que espera de manera explicita una cantidad de tiempo en segundos
	 ** 
	 ** @param strSegundos entero con valor de segundos a esperar
	 */
	public void esperaExplicita(int intSegundos) {
		try {
			Thread.sleep(intSegundos * 1000L);
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
			Thread.currentThread().interrupt();
		}
	}	
	
	/**
	 * Metodo que da modifica un elemento web usando JavaScript
	 * 
	 * @param element elemento de tipo WebElementFacade con el cual se interactua
	 * @param strTexto String con texto que se ingresara como valor
	 */
	public void modificarValorTextoJavascript(WebElementFacade element, String strTexto) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			String xpath = element.toString().replace("By.xpath: ", "");
			String strID = element(By.xpath(xpath)).getAttribute(Constantes.ID).toString();
			esperaExplicita(1);
			String jsConsole = "document.getElementById('";
			js.executeScript(jsConsole + strID + "').value = '" + strTexto + "';");
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}
}
