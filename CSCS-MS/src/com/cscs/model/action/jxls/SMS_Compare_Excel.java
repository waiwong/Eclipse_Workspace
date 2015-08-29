package com.cscs.model.action.jxls;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TreeSet;

import com.cscs.model.util.XlsMethod;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class SMS_Compare_Excel {
	private TreeSet<String> hashSet1, hashSet2;
	private WritableWorkbook writableWorkBook;
	private XlsMethod method;
	private String fileDate = "";

	public void create(String sourcePath1, String sourcePath2, String destPath)
			throws IOException, WriteException {
		hashSet1 = new TreeSet<String>();
		hashSet2 = new TreeSet<String>();
		readXls(sourcePath1, hashSet1);
		fileDate += " - ";
		readXls(sourcePath2, hashSet2);
		// compare
		@SuppressWarnings("unchecked")
		TreeSet<String> clone = (TreeSet<String>) hashSet1.clone();
		hashSet1.removeAll(hashSet2);
		hashSet2.removeAll(clone);

		// Gen Excel
		method = new XlsMethod();
		String fileName = destPath + File.separator + "SMS_Compare_"
				+ method.getCurrentTime() + ".xls";
		writableWorkBook = Workbook.createWorkbook(new File(fileName));

		int[] columnWidth = { 50 };
		String[] columnName = { "Software Name" };
		hashSet1.comparator(); // sort
		hashSet2.comparator();
		excelStructure("Less", "Program List Comparative Report - Less\n",
				"Total records : " + hashSet1.size(), 1, columnWidth, columnName,
				hashSet1);

		excelStructure("More", "Program List Comparative Report - More\n",
				"Total records : " + hashSet2.size(), 2, columnWidth, columnName,
				hashSet2);
		writableWorkBook.write();
		writableWorkBook.close();
	}

	private void readXls(String path, TreeSet<String> hashSet) {
		try {
			File file = new File(path);
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
			fileDate += matter.format(file.lastModified());
			Workbook book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows(); // total rows
			for (int i = 1; i < rows; i++) {
				String softwareName = sheet.getCell(0, i).getContents();
				hashSet.add(softwareName);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void excelStructure(String sheetName, String header, String title,
			int sheetIndex, int[] columnWidth, String[] columnName,
			TreeSet<String> hashSet) {
		try {
			WritableSheet sheet = writableWorkBook.createSheet(sheetName, sheetIndex);
			method.set(sheet, header, title, fileDate, columnWidth, columnName); // sheet
																																						// setting
			// begin the content
			int index = 3;
			Iterator<String> iterator = hashSet.iterator();
			while (iterator.hasNext()) {
				String softwareName = iterator.next();
				method.addNormalFont(0, index, softwareName);
				index++;
			}
			method.addUnderlineBorder(index + 2);
			// end the content
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}