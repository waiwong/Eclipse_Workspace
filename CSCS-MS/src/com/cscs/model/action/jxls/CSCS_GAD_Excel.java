package com.cscs.model.action.jxls;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;

import com.cscs.model.util.XlsMethod;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class CSCS_GAD_Excel {
	private HashMap<String, String[]> gadHash = new HashMap<String, String[]>();
	private HashMap<String, String[]> cscsHash = new HashMap<String, String[]>();
	private LinkedList<String[]> deptNotMatchList;
	private LinkedList<String[]> cscsMoreList;
	private LinkedList<String[]> cscsNotExistList;
	private WritableWorkbook writableWorkBook;
	private XlsMethod method;
	private String fileDate = "";

	public void create(String sourcePath1, String sourcePath2, String destPath)
			throws IOException, WriteException {
		// GAD Excel
		int[] columnIndex1 = { 0, 2, 1 };
		readXls(gadHash, columnIndex1, sourcePath1);

		// CSCS Excel
		fileDate += " To ";
		int[] columnIndex2 = { 3, 0, 4 };
		readXls(cscsHash, columnIndex2, sourcePath2);
		comparator();

		// Gen Excel
		method = new XlsMethod();
		String header = "CSCS - GAD Comparative Report\n", fileName = destPath
				+ File.separator + "CSCS_GAD_" + method.getCurrentTime() + ".xls";
		writableWorkBook = Workbook.createWorkbook(new File(fileName));
		// first sheet
		int[] columnWidth_1 = { 16, 18, 12, 40 };
		String[] columnName_1 = { "GAD-Dept.", "CSCS-Dept.", "GA-NO.",
				"Software Name" };
		excelStructure("Not Match", header, "Department does't match : ", 0,
				columnWidth_1, columnName_1, deptNotMatchList);
		// second sheet
		int[] columnWidth_2 = { 18, 16, 40 };
		String[] columnName_2 = { "GA-NO.", "CSCS_Dept.", "Software Name" };
		excelStructure("More than", header, "GAD does't exist these records : ", 1,
				columnWidth_2, columnName_2, cscsMoreList);
		// third sheet
		int[] columnWidth_3 = { 18, 16, 40 };
		String[] columnName_3 = { "GA-NO.", "GAD_Dept.", "Software Name" };
		excelStructure("Not Exist", header, "CSCS does't exist these records : ",
				2, columnWidth_3, columnName_3, cscsNotExistList);

		writableWorkBook.write();
		writableWorkBook.close();
	}

	private void readXls(HashMap<String, String[]> hashMap, int[] columnIndex,
			String path) {
		try {
			File file = new File(path);
			SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
			fileDate += matter.format(file.lastModified());
			Workbook book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows(); // total rows
			for (int i = 1; i < rows; i++) {
				String gaNo = sheet.getCell(columnIndex[0], i).getContents();
				if (gaNo.contains("3-")) {
					String deptName = sheet.getCell(columnIndex[1], i).getContents(), software = sheet
							.getCell(columnIndex[2], i).getContents();
					deptName = splitStr(deptName);
					String[] strArr = { deptName, software };
					hashMap.put(gaNo, strArr);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void excelStructure(String sheetName, String header, String title,
			int sheetIndex, int[] columnWidth, String[] columnName,
			LinkedList<String[]> list) {
		try {
			WritableSheet sheet = writableWorkBook.createSheet(sheetName, sheetIndex);
			method.set(sheet, header, title + list.size(), fileDate, columnWidth,
					columnName); // sheet setting
			// begin the content
			int startColumn = 3, column = 0;
			for (int i = 0; i < list.size(); i++) {
				column = startColumn + i;
				String[] dataValues = list.get(i);
				for (int j = 0; j < dataValues.length; j++)
					method.addNormalFont(j, column, dataValues[j]);
			}
			// end the content
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private void comparator() {
		deptNotMatchList = new LinkedList<String[]>();
		cscsMoreList = new LinkedList<String[]>();
		cscsNotExistList = new LinkedList<String[]>();
		LinkedList<String> cscsKeyList = new LinkedList<String>(cscsHash.keySet()), gadKeyList = new LinkedList<String>(
				gadHash.keySet());
		for (int i = 0; i < gadKeyList.size(); i++) {
			String gadKey = gadKeyList.get(i);
			String[] gadValues = gadHash.get(gadKey);
			if (!cscsHash.containsKey(gadKey)) {
				String[] dataArr = { gadKey, gadValues[0], gadValues[1] };
				cscsNotExistList.add(dataArr);
			}
		}
		for (int i = 0; i < cscsKeyList.size(); i++) {
			String cscsKey = cscsKeyList.get(i); // GA-NO.
			String[] cscsValues = cscsHash.get(cscsKey);
			String cscsDept = cscsValues[0];
			if (gadHash.containsKey(cscsKey)) {
				String[] gadValues = gadHash.get(cscsKey);
				String gadDept = gadValues[0];
				if (!cscsDept.equals(gadDept)) {
					String[] dataArr = { gadDept, cscsDept, cscsKey, cscsValues[1] };
					deptNotMatchList.add(dataArr);
				}
			} else {
				String[] dataArr = { cscsKey, cscsDept, cscsValues[1] };
				cscsMoreList.add(dataArr);
			}
		}
	}

	// department name subString
	private String splitStr(String deptName) {
		if (deptName.equals("IAD"))
			deptName = "AUD";
		else if (deptName.equals("GMO"))
			deptName = "MNG";
		else if (deptName.contains(" BR")) {
			deptName = deptName.replace(" ", "");
			deptName = (deptName.length() > 5) ? deptName.substring(0, 4) : deptName
					.substring(0, 3);
		} else if (deptName.contains("-") || deptName.contains("_")) {
			deptName = deptName.replace("_", "-");
			int lastIndex = deptName.lastIndexOf("-");
			deptName = deptName.substring(0, lastIndex);
		}
		return deptName;
	}

}