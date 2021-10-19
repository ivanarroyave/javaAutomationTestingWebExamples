package co.com.sofka.soap.utils.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Ingresa los datos obtenidos del archivo de Excel al archivo feature del cual
 * se está llamando
 * 
 * @since 25/04/2018
 * @author carmarlo
 *
 */
public class DataToFeature {

	private static String data;
	private static String sheetName;
	private static String excelFilePath;
	private static final Logger LOGGER = Logger.getLogger(DataToFeature.class);

	private DataToFeature() {
	}

	/**
	 * Ingresa los datos obtenidos de un excel al archivo .feature del cual se está
	 * llamando, hace que se genere la tabla en el escenario Outline como Data Table
	 *
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param featureFile Nombre del archivo .feature el cual se modificará, debe
	 *                    tener la ruta del archivo y la hoja ser usada
	 */
	private static List<String> setExcelDataToFeature(File featureFile) {
		List<String> fileData = new ArrayList<>();

		try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(featureFile)), UTF_8))) {

			boolean foundHashTag = false;
			boolean addData = true;

			//Mientras que el archivo tenga líneas.
			while ((data = buffReader.readLine()) != null) {
				sheetName = null;
				excelFilePath = null;

				//Si se cumple ... se inserta la data desde un archivo de Excel como nuevas líneas de la lista de archivo.
				if (data.trim().contains("##@externaldata")) {
					fileData.addAll(getDataExcel());
					foundHashTag = true;
					continue;
				}

				if(foundHashTag && (data.trim().startsWith("|") || data.trim().endsWith("|")))
					addData = false;
				else
				if (foundHashTag && data.trim().isEmpty())
					addData = false;
				else
				if (foundHashTag && (!data.trim().startsWith("|") || !data.trim().endsWith("|"))) {
					addData = true;
					foundHashTag = false;
				}

				//Agrega una línea a la lista de archivo por iteración.
				if(addData)
					fileData.add(data);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

		//Retorna la línea de archivo.
		return fileData;
	}

	private static List<String> getDataExcel() {

		List<String> fileData = new ArrayList<>();
		excelFilePath = data.substring(StringUtils.ordinalIndexOf(data, "@", 2) + 1, data.lastIndexOf('@'));
		int index = data.length();
		sheetName = data.substring((data.lastIndexOf('@') + 1), index);

		fileData.add(data);

		List<Map<String, String>> excelData = new LectorExcel().getData(excelFilePath, sheetName);

		boolean systemWroteData = false;
		for (int rowNumber = 0; rowNumber < excelData.size() - 1; rowNumber++) {
			StringBuilder cellData = new StringBuilder();
			cellData.append("      ");
			for (Entry<String, String> mapData : excelData.get(rowNumber).entrySet()) {
				cellData.append("|");
				cellData.append(mapData.getValue());
			}
			cellData.append("|");
			fileData.add(cellData.toString());
			systemWroteData = true;
		}

		if(!systemWroteData){
			for (Map<String, String> excelDatum : excelData) {
				StringBuilder cellData = new StringBuilder();
				cellData.append("      ");
				for (Entry<String, String> mapData : excelDatum.entrySet()) {
					cellData.append("|");
					cellData.append(mapData.getValue());
				}
				cellData.append("|");
				fileData.add(cellData.toString());
			}
		}

		return fileData;
	}

	/**
	 * Lista de todos los features con sus respectivos archivo de excel que se
	 * usarán en la prueba
	 *
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param folder Carpeta donde estarán los archivo .feature
	 */
	private static List<File> listOfFeatureFiles(File folder) {
		List<File> featureFiles = new ArrayList<>();

		for (File fileEntry : Objects.requireNonNull(folder.listFiles())) {
			if (fileEntry.isDirectory()) {
				featureFiles.addAll(listOfFeatureFiles(fileEntry));
			} else {
				if (fileEntry.isFile() && fileEntry.getName().endsWith(".feature")) {
					featureFiles.add(fileEntry);
				}
			}
		}

		return featureFiles;
	}

	/**
	 * Hace una lista con todos los features dependiendo de la ruta asignada
	 *
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param featurePath Ruta donde se encuentra un archivo features.
	 */
	public static void overrideFeatureFiles(String featurePath) {
		try {
			File featureFile = new File(featurePath);

			List<String> featureWithExcelData = setExcelDataToFeature(featureFile);

			try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(featureFile), UTF_8))) {

				for (String string : featureWithExcelData) {
					writer.write(string);
					writer.write("\n");
				}

			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
