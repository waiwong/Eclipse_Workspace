package com.wol.model.jxls;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class ReadXls {
	public Object[][] array;

	public ReadXls() {
		try {
			Workbook book = Workbook.getWorkbook(new File("bwh_pc.xls"));
			Sheet sheet = book.getSheet(0);
			int index = 1, rows = sheet.getRows(), length = rows - 1;
			array = new Object[length][4];
			for (int i = 0; i < length; i++) {
				array[i][0] = false;
				array[i][1] = sheet.getCell(0, index).getContents();
				array[i][2] = sheet.getCell(1, index).getContents();
				array[i][3] = sheet.getCell(2, index).getContents();
				index++;
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
