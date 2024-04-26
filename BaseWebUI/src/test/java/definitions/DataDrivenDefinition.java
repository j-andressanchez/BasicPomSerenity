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

	@Given("Se obtienen los datos de prueba correcto {int}")
	public void obtenerDatosPruebaCorrecto(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, "Exitoso");
	}

	@Given("Se obtienen los datos de prueba incorrecto {int}")
	public void obtenerDatosPruebaIncorrecto(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, "Fallido");
	}

	@When("Se realizan las acciones")
	public void realizarAcciones() {
		// Llamar métodos que realicen las acciones de las pruebas
	}

	@Then("Se validan los resultados")
	public void validarResultados() {
		// Llamar métodos que haga las aserciones y validaciones
	}
}
