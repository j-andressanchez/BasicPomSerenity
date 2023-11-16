package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
	
	@When("realizar acciones")
	public void realizarAcciones() {
		// acciones a realizar
	}

	@Then("validacion de resultados")
	public void validarResultados() {
		// Realizar las aserciones necesarias
	}

}
