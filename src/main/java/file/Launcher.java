package file;

import java.io.File;
import java.util.List;

import file.edit.ModifyXlsxFiles;
import file.reader.ReadFiles;

public class Launcher {

	public static void main(String[] args) throws Exception {
		List<File> files = ReadFiles.getAllExcelFiles();
		ModifyXlsxFiles modifyFiles = new ModifyXlsxFiles(files);
		modifyFiles.saveFilesAsCSV(args[0]);
	}
}
