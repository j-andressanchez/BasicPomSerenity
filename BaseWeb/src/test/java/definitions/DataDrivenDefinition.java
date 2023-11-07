package definitions;

import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;
import steps.DataDrivenSteps;
import utilities.Constantes;

public class DataDrivenDefinition {

	@Steps
	DataDrivenSteps dataSteps;

	@Given("obtener datos de prueba {int}")
	public void obtenerDatosPrueba(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, Constantes.CREAR_EXCEPCION);
	}
	
}
