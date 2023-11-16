package utilities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;

import net.serenitybdd.core.pages.PageObject;

public class EscrituraFeature extends PageObject{

	static final Logger logger = LoggerFactory.getLogger(EscrituraFeature.class);

	private EscrituraFeature() {

		throw new IllegalStateException(Constantes.ESCRITURA_FEATURE);
	}

	private static List<String> escribirDatosExcelFeature(File featureFile) throws InvalidFormatException, IOException {

		List<String> fileData = new ArrayList<>();

		try (BufferedReader buffReader = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), Charsets.UTF_8))) {

			String data;
			boolean featureData = false;

			while ((data = buffReader.readLine()) != null) {

				if (data.trim().contains(Constantes.EXTERNAL_DATA)) {

					fileData = configurarDatosExcel(data, fileData);
					featureData = true;

				} else {

					if (data.startsWith("|") || data.endsWith("|")) {

						if (!featureData) {

							fileData.add(data);
						}
					} else {

						fileData.add(data);
						featureData = false;
					}
				}
			}
		}

		return fileData;
	}

	private static List<String> configurarDatosExcel(String data, List<String> fileData)
			throws InvalidFormatException, IOException {

		String excelFilePath = data.substring(StringUtils.ordinalIndexOf(data, "@", 2) + 1, data.lastIndexOf('@'));
		fileData.add(data);
		return iteracionArraysData(
				new ExcelReader().getData(excelFilePath, data.substring(data.lastIndexOf('@') + 1, data.length())),
				fileData);
	}

	private static List<File> listarArchivosFeature(File folder) {

		List<File> featureFiles = new ArrayList<>();

		for (File fileEntry : folder.listFiles()) {

			if (fileEntry.isDirectory()) {

				featureFiles.addAll(listarArchivosFeature(fileEntry));

			} else {

				if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {

					featureFiles.add(fileEntry);
				}
			}

		}

		return featureFiles;
	}

	public static void sobreescribirArchivosFeature(String featuresDirectoryPath)
			throws IOException, InvalidFormatException {

		List<File> listOfFeatureFiles = listarArchivosFeature(new File(featuresDirectoryPath));

		for (File featureFile : listOfFeatureFiles) {

			if (!featureFile.setWritable(true)) {

				logger.info("No escribe el feature {} ", featureFile);
			}

			List<String> featureWithExcelData = escribirDatosExcelFeature(featureFile);

			try (BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(featureFile), Charsets.UTF_8));) {

				for (String string : featureWithExcelData) {
					writer.write(string);
					writer.write("\n");
				}
			}

		}
	}

	private static List<String> iteracionArraysData(List<Map<String, String>> excelData, List<String> fileData) {

		try {

			int cont = 1;
			boolean flag = true;

			for (int rowNumber = 0; rowNumber < excelData.size() - 1; rowNumber++) {

				StringBuilder cellData = new StringBuilder();

				for (Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {

					if (mapData.getValue().equalsIgnoreCase(String.valueOf(cont))
							&& mapData.getKey().equalsIgnoreCase(Constantes.CASO)) {

						cellData.append("|" + mapData.getValue());
						cont++;

					} else if (mapData.getValue().isBlank()) {
						flag = false;
					}
				}

				if (flag) {
					fileData.add(cellData.toString() + "|");
				} else {
					break;
				}
			}

			logger.info("Escritura correcta del feature");

		} catch (Exception e) {

			logger.info("Error en metodo iteracionArraysData de la clase EscrituraFeature");
		}

		return fileData;
	}
}