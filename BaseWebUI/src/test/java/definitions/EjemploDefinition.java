package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.EjemploSteps;

public class EjemploDefinition {

	@Steps
	EjemploSteps ejemploSteps;
	
	@Given("El usuario está en la aplicacion")
	public void abrirAplicacion() {
		ejemploSteps.abrirAplicacion();
	}
	
	@When("El usuario ingresa con credenciales correctas")
	public void iniciarSesionCorrecto() {
		ejemploSteps.iniciarSesion();
	}

	@When("El usuario ingresa con credenciales incorrectas")
	public void iniciarSesionIncorrecto() {
		ejemploSteps.iniciarSesion();
	}

	@Then("El usuario debería acceder al catálogo")
	public void validarLogin() {
		ejemploSteps.validarLogin();
	}

	@Then("El usuario no debería acceder al catálogo")
	public void validarLoginFail() {
		ejemploSteps.validarLogin();
	}

}
