package definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.EjemploSteps;

public class EjemploDefinition {
	
	@Steps
	EjemploSteps ejemploStep;
	
	@When("Se construye request de consulta")
	public void ConstruirRequestEjemplo() {
		ejemploStep.ConstruirRequestEjemplo();
		ejemploStep.configurarHeader();
	}

	@When("Se ejecuta la consulta")
	public void EjecutarRequestEjemplo() {
		ejemploStep.EjecutarServicioEjemplo();
	}

	@Then("Se valida la respuesta obtenida")
	public void ValidarResponseEjemplo() {
		ejemploStep.validarResponseEjemplo();
	}

}
