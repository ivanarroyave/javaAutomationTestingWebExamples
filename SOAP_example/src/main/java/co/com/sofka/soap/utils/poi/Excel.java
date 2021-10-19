package co.com.sofka.soap.utils.poi;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Excel {

	private static final Logger LOGGER = Logger.getLogger(Excel.class);

	public void createExcellBook(Map<String, Map<String, Map<String, List<String>>>> booksCollection) {
		// Creamos el libro de trabajo de Excel formato OOXML
		try(Workbook workbook = new XSSFWorkbook()) {
			//PARA CADA CLAVE DE LIBRO...
			for (Map.Entry<String, Map<String, Map<String, List<String>>>> booksEntry : booksCollection.entrySet()) {

				String booksCollectionKey = booksEntry.getKey();
				Map<String, Map<String, List<String>>> booksCollectionValue = booksEntry.getValue();

				//PARA CADA CLAVE DE HOJA...
				for (Map.Entry<String, Map<String, List<String>>> bookDataEntry : booksCollectionValue.entrySet()) {

					String bookDataKey = bookDataEntry.getKey();
					Map<String, List<String>> sheetData = bookDataEntry.getValue();

					//Se crea la hoja donde pondremos los datos
					workbook.createSheet(bookDataKey);
					Sheet sheet = workbook.getSheet(bookDataKey);

					//Se genera una lista con los títulos de cada columna a usar.
					ArrayList<ArrayList<String>> listaDeColumnas = columnList(sheetData);

					//Se insertan los datos en las celdas de Excel.
					insertDataInCells(sheet, listaDeColumnas);

					//Ahora se escribe el archivo.
					workbook.write(fileOutputStream(new File(booksCollectionKey)));
					LOGGER.info("\n\r" + "Archivo creado adecuadamente en " + booksCollectionKey + "\n\r");
				}

			}
		} catch(IOException ioException) {
			LOGGER.error(ioException.getMessage(), ioException);
		}
	}

	private FileOutputStream fileOutputStream(File file) throws FileNotFoundException {
		return new FileOutputStream(file);
	}

	private ArrayList<ArrayList<String>> columnList(Map<String, List<String>> sheetData){
		ArrayList<ArrayList<String>> listaDeColumnas = new ArrayList<>();

		// PARA CADA CLAVE DE COLUMNA DE LA HOJA...
		for (Map.Entry<String, List<String>> sheetDataEntry : sheetData.entrySet()) {

			String key = sheetDataEntry.getKey();
			ArrayList<String> sheetDataColumnValue = (ArrayList<String>) sheetDataEntry.getValue();

			// Agrega el título en el índice cero (0)
			sheetDataColumnValue.add(0, key);

			listaDeColumnas.add(sheetDataColumnValue);
		}

		return listaDeColumnas;
	}

	private void insertDataInCells(Sheet sheet, ArrayList<ArrayList<String>> listaDeColumnas) {

		// Recorre filas (se basa en el número de filas de la columna).
		for (int i = 0; i < listaDeColumnas.get(0).size(); i++) {
			// Ahora creamos una fila con su respectiva posición.
			Row fila = sheet.createRow((i));

			// Recorre columnas
			for (int j = 0; j < listaDeColumnas.size(); j++) {
				// Creamos una celda en esa fila, en la
				// posicion indicada por el contador del ciclo
				Cell celda = fila.createCell(j);
				celda.setCellValue(listaDeColumnas.get(j).get(i));
			}
		}
	}

}
