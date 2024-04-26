package utilities;

import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import net.serenitybdd.core.pages.PageObject;

public class GestionLogs extends PageObject {

	/**
	 * Metodo que crea la carpeta donde se guardara el log de ejecucion
	 * 
	 * @param strCaso String con valor de caso ejecutado y relacionado a la carpeta
	 *                a crear
	 */
	public void crearRepoLogs(String strCaso) {

		try {

			if (!Files.exists(Path.of(Constantes.RUTA_LOGS))) {
				Files.createDirectories(Path.of(Constantes.RUTA_LOGS));
			}

			if (!Files.exists(Path.of(Constantes.RUTA_LOGS + strCaso))) {
				Files.createDirectories(Path.of(Constantes.RUTA_LOGS + strCaso));
			}

			crearArchivoLog(strCaso);

		} catch (Exception e) {
			ExcepcionesProyecto.lanzarExcepcion(e.toString());
		}
	}

	/**
	 * Metodo que crea el log de ejecucion
	 * 
	 * @param strCaso String con valor de caso ejecutado y relacionado al log a
	 *                crear
	 */
	public void crearArchivoLog(String strCaso) {

		try (FileWriter f = new FileWriter(Constantes.RUTA_LOGS + strCaso + "/" + strCaso + ".txt", true); BufferedWriter b = new BufferedWriter(f); PrintWriter p = new PrintWriter(b);) {
			p.println("\n" + strCaso + " = OK");
		} catch (Exception e) {
			fail("Error en la clase GestionLogs, y el metodo crearRepoLogs: " + e);
		}
	}
}
