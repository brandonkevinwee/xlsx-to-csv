package file.edit;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ModifyXlsxFiles {
	List<File> files;

	public ModifyXlsxFiles(List<File> files) {
		this.files = files;
	}
	
	public void saveFilesAsCSV(String separator) throws Exception {
		for(File file : files) {
			convertXlsxToCSV(file, separator);
		}
	}
	
	private void convertXlsxToCSV(File file, String separator) throws Exception {
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet workSheet =  workBook.getSheetAt(0);
		DataFormatter cellFormatter = new DataFormatter();
		Iterator<Row> rowIterator = workSheet.rowIterator();
		Row row;
		StringBuilder stringData = new StringBuilder();
		while(rowIterator.hasNext()) {
			row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell;
			while(cellIterator.hasNext()) {
				cell = cellIterator.next();
				stringData.append(cellFormatter.formatCellValue(cell));
				if(cellIterator.hasNext()) stringData.append(separator);
			}
			stringData.append(System.lineSeparator());
		}
		File outputFile = new File(FilenameUtils.getBaseName(file.getName()) + ".csv");
		FileUtils.write(outputFile, stringData, "UTF-8");
		workBook.close();
	}
}