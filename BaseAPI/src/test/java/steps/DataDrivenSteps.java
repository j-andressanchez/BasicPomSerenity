package steps;

import net.thucydides.core.annotations.Step;
import utilities.DataDrivenExcel;


public class DataDrivenSteps {

	DataDrivenExcel dataDriven;

	@Step
	public void obtenerDatosPrueba(String strRuta, int intCaso, String strCaso) {
		dataDriven.cargarMapaDatos(strRuta, intCaso, strCaso);
	}	
}
