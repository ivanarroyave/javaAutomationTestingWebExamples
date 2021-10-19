package co.com.sofka.soap.utils.poi;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 25/04/2018
 * @author carmarlo
 *
 */
public class LectorExcel {

	private static final Logger LOGGER = Logger.getLogger(LectorExcel.class);

	/**
	 * Obtiene los datos de un archivo de excel, teniendo en cuenta el nombre de la
	 * hoja
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param excelFilePath Ruta del libro de excel
	 * @param sheetName     Nombre de la hoja que contiene los datos
	 */
	public List<Map<String, String>> getData(String excelFilePath, String sheetName) {
		Sheet sheet = getSheetByName(excelFilePath, sheetName);
		return readSheet(sheet);
	}

	/**
	 * Obtiene la hoja de trabajo donde se encuentran los datos de acuerdo a la ruta
	 * del archivo
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param excelFilePath Ruta del libro de excel
	 * @param sheetName     Nombre de la hoja que contiene los datos
	 */
	private Sheet getSheetByName(String excelFilePath, String sheetName) {
		return getWorkBook(excelFilePath).getSheet(sheetName);
	}

	/**
	 * Devuelve el libro correspondiente a la hoja determinada con antelación
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param excelFilePath Ruta del archivo de excel
	 */
	private Workbook getWorkBook(String excelFilePath) {
		try {
			return WorkbookFactory.create(new File(excelFilePath));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new XSSFWorkbook();
		}
	}

	/**
	 * Retorna la lista en forma de Map de todas las filas que contiene la hoja de
	 * excel, teniendo en cuenta la primera fila como los nombres de la columna
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param sheet
	 * 			Objeto que representa una hoja de Excel.
	 */
	private List<Map<String, String>> readSheet(Sheet sheet) {
		Row row;
		int totalRow = sheet.getPhysicalNumberOfRows();
		List<Map<String, String>> excelRows = new ArrayList<>();
		int headerRowNumber = getHeaderRowNumber(sheet);
		if (headerRowNumber != -1) {
			int totalColumn = sheet.getRow(headerRowNumber).getLastCellNum();
			int setCurrentRow = 1;
			for (int currentRow = setCurrentRow; currentRow <= totalRow; currentRow++) {
				row = getRow(sheet, sheet.getFirstRowNum() + currentRow);
				LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					columnMapdata.putAll(getCellValue(sheet, row, currentColumn));
				}
				excelRows.add(columnMapdata);
			}
		}
		return excelRows;
	}

	/**
	 * Obtiene el numero de filas conceniernte a encabezado de la hoja
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param sheet
	 * 			Objeto que representa una hoja de Excel.
	 */
	private int getHeaderRowNumber(Sheet sheet) {
		Row row;
		int totalRow = sheet.getLastRowNum();
		for (int currentRow = 0; currentRow <= totalRow + 1; currentRow++) {
			row = getRow(sheet, currentRow);
			if (row != null) {
				int totalColumn = row.getLastCellNum();
				for (int currentColumn = 0; currentColumn < totalColumn; currentColumn++) {
					Cell cell;
					cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if (cell.getCellTypeEnum() == CellType.STRING || cell.getCellTypeEnum() == CellType.NUMERIC
							|| cell.getCellTypeEnum() == CellType.BOOLEAN || cell.getCellTypeEnum() == CellType.ERROR) {
						return row.getRowNum();
					}
				}
			}
		}
		return (-1);
	}

	/**
	 * Obtiene la fila de acuerdo a la hoja y el numero de ésta
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param sheet
	 * 			Objeto que representa una hoja de Excel.
	 * @param rowNumber
	 * 			Indice que representa el numero de fila en una hoja de Excel.
	 */
	private Row getRow(Sheet sheet, int rowNumber) {
		return sheet.getRow(rowNumber);
	}

	/**
	 * Obtiene el valor de cada una de las celdas -------> reevaluar y dejar como
	 * texto todos los valores
	 * 
	 * @since 25/04/2018
	 * @author carmarlo
	 * @param sheet
	 * 			Objeto que representa una hoja de Excel.
	 * @param row
	 * 			Objeto que representa una fila de hoja en Excel.
	 * @param currentColumn
	 * 			Indice que representa la columna actual en una hoja de Excel.
	 */
	private LinkedHashMap<String, String> getCellValue(Sheet sheet, Row row, int currentColumn) {
		LinkedHashMap<String, String> columnMapdata = new LinkedHashMap<>();
		Cell cell;
		if (row != null) {
			cell = row.getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

			if (sheet.getRow(sheet.getFirstRowNum())
					.getCell(cell.getColumnIndex(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.getCellTypeEnum() != CellType.BLANK) {
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(cell.getColumnIndex())
						.getStringCellValue();
				if (cell.getCellTypeEnum() == CellType.STRING) {
					columnMapdata.put(columnHeaderName, cell.getStringCellValue());
				} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
					columnMapdata.put(columnHeaderName, NumberToTextConverter.toText(cell.getNumericCellValue()));
				} else if (cell.getCellTypeEnum() == CellType.BLANK) {
					columnMapdata.put(columnHeaderName, "");
				} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
					columnMapdata.put(columnHeaderName, Boolean.toString(cell.getBooleanCellValue()));
				} else if (cell.getCellTypeEnum() == CellType.ERROR) {
					columnMapdata.put(columnHeaderName, Byte.toString(cell.getErrorCellValue()));
				}
			}
		} else {
			if (sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					.getCellTypeEnum() != CellType.BLANK) {
				String columnHeaderName = sheet.getRow(sheet.getFirstRowNum()).getCell(currentColumn)
						.getStringCellValue();
				columnMapdata.put(columnHeaderName, "");
			}
		}
		return columnMapdata;
	}
}
