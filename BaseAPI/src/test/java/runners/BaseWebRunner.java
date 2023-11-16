package runners;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import utilities.Constantes;
import utilities.EscrituraFeature;
import utilities.Logs;


public class BaseWebRunner {	
	
	@Before
	public void before() throws IOException, InvalidFormatException {
		EscrituraFeature.sobreescribirArchivosFeature(Constantes.RUTA_FEATURES);
		Logs.guardarInfoEjecucion();
	}

	@Test
	public void testR() {
		JUnitCore.runClasses(TestRunner.class);
	}

	@RunWith(CucumberWithSerenity.class)
	@CucumberOptions( 
			features = Constantes.RUTA_FEATURES,
			snippets = CucumberOptions.SnippetType.CAMELCASE,
			glue = { "definitions" } )
	public class TestRunner {
	}

}
