package file.reader;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFiles {

	private static List<File> getAllFiles() throws Exception {
		String jarFilePath = ReadFiles.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File jarFile = new File(jarFilePath);
		File[] jarDirectoryFiles = new File(URLDecoder.decode(jarFile.getParent(), "UTF-8")).listFiles();
		return Arrays.stream(jarDirectoryFiles).filter(file -> !file.getName().equals(jarFile.getName()))
		    .collect(Collectors.toList());
//		File[] jarDirectoryFiles = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).listFiles();
//		return Arrays.stream(jarDirectoryFiles).collect(Collectors.toList());
	}

	public static List<File> getAllExcelFiles() throws Exception {
		List<File> jarDirectoryFiles = getAllFiles();
		List<File> excelFiles = new ArrayList<File>();
		for (File file : jarDirectoryFiles) {
			String fileName = file.getName();
			String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (fileExtension.equals("xlsx"))
				excelFiles.add(file);
		}
		return excelFiles;
	}
}