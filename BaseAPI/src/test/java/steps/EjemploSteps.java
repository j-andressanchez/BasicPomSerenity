package steps;

import static org.junit.Assert.fail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import utilities.Constantes;
import utilities.DataDrivenExcel;
import utilities.GestionLogs;
import utilities.LeerArchivo;
import utilities.ManejoJson;
import utilities.Servicios;

public class EjemploSteps {

	DataDrivenExcel dataDriven;
	Servicios servicios;
	LeerArchivo leerArchivo;
	ManejoJson manejoJson;
	GestionLogs gestionLogs;

	private Logger logger = LoggerFactory.getLogger(EjemploSteps.class);

	@Step
	public void ConstruirRequestEjemplo() {
		leerArchivo.obtenerBodyRequest(Constantes.BODY_EMAIL_JSON);
		Servicios.modificarBodyRequest(Constantes.VAR_EMAIL, dataDriven.getDataMap("email"));
		Servicios.modificarBodyRequest(Constantes.VAR_PASSWORD, dataDriven.getDataMap("password"));

		// Se agrega Detalle de solicitud al Reporte de Serenity
		Serenity.recordReportData().withTitle("Request").andContents(servicios.getBodyRequest());
	}

	@Step
	public void EjecutarServicioEjemplo() {
		String strUrl = leerArchivo.obtenerUrl(dataDriven.getDataMap("Ambiente"), "TestEjemplo");
		// servicios.agregarHeader("Content-Type", "application/json");

		Servicios.setResponse(servicios.ejecutarPOSTServicioREST(servicios.getBodyRequest(), strUrl, servicios.getHeader()));

		// Se agrega Detalle de solicitud al Reporte de Serenity
		Serenity.recordReportData().withTitle("URL").andContents(strUrl);
		Serenity.recordReportData().withTitle("Status response").andContents(String.valueOf(servicios.getResponse().getStatusCode()));
	}

	@Step
	public void validarResponseEjemplo() {

		logger.info("Codigo response servicio de ejemplo con formato Json: " + servicios.getResponse().getStatusCode());

		if (servicios.getResponse().getStatusCode() != 200) {

			Serenity.recordReportData().withTitle("Response").andContents(servicios.getResponse().asString());
			fail("Fallo al consumir servicio REQRES ejemplo. Se espera respuesta 200 y se recibe " + servicios.getResponse().getStatusCode());

		} else {

			logger.info("Mensaje response servicio REQRES ejemplo: " + servicios.getResponse().asString());
			Serenity.recordReportData().withTitle("Response").andContents(servicios.getResponse().asString());
			manejoJson.extraerValorResponse(servicios.getResponse(), "isSuccess", "boolean");

			gestionLogs.crearRepoLogs(dataDriven.getDataMap(Constantes.PERFIL));
		}
	}

	@Step
	public void configurarHeader() {
		servicios.getHeader().clear();
		servicios.agregarHeader("Content-Type", "application/json");
		servicios.agregarHeader("Accept", "*/*");
		servicios.agregarHeader("Connection", "keep-alive");
	}
}
