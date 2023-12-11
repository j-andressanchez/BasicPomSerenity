package steps;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.util.EnvironmentVariables;
import ui.EjemploUI;
import utilities.AccionesWeb;
import utilities.Constantes;
import utilities.DataDrivenExcel;
import utilities.ExcepcionesProyecto;
import utilities.GestionAmbientes;

public class EjemploSteps {

	AccionesWeb accionesWeb;
	DataDrivenExcel dataDriven;
	EjemploUI ejemploUi;

	private EnvironmentVariables environmentVariables;

	@Step
	public void abrirAplicacion() {
		try {
			GestionAmbientes.configurarAmbiente(EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(Constantes.WEBDRIVER_NAME));
			accionesWeb.abrirURL(Constantes.HOME_PAGE);
			accionesWeb.esperaCargaPagina();
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	@Step
	public void iniciarSesion() {
		try {
			accionesWeb.esperaCargaPagina();
			accionesWeb.escribirTexto(ejemploUi.getTextUser(), dataDriven.getDataMap("User"));
			accionesWeb.escribirTexto(ejemploUi.getTextPass(), dataDriven.getDataMap("Pass"));
			accionesWeb.clickElemento(ejemploUi.getBotonLogin());
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

	@Step
	public void validarLogin() {
		try {
			accionesWeb.esperaCargaPagina();
			accionesWeb.validarTexto(ejemploUi.getTextTittle(), dataDriven.getDataMap("AssertText"));
		} catch (Exception e) {
			ExcepcionesProyecto.validaExcepcion(e.toString());
		}
	}

}
