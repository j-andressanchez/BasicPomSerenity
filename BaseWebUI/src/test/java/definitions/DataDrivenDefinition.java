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

	@Given("Se obtienen los datos de prueba {int}")
	public void obtenerDatosPrueba(int intCaso) {
		dataSteps.obtenerDatosPrueba(Constantes.RUTA_DATADRIVEN, intCaso, "Exitoso");
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
