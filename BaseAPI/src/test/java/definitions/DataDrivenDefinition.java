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

	@Given("Se obtienen los datos de prueba correctos {int}")
	public void obtenerDatosPruebaCorrectos(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, "EjemploExitoso");
	}

	@Given("Se obtienen los datos de prueba incorrectos {int}")
	public void obtenerDatosPruebaIncorrectos(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, "EjemploFallido");
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
