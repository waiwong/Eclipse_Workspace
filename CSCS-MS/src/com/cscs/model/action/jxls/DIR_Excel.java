package com.cscs.model.action.jxls;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.cscs.model.bo.DIR_DISPLAY;

public class DIR_Excel {

	public List<DIR_DISPLAY> read(String filePath) {
		List<DIR_DISPLAY> list = new LinkedList<DIR_DISPLAY>();
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = book.getSheet(3); // the second sheet
			int rows = sheet.getRows(); // total rows
			for (int i = 1; i < rows; i++) {
				String folder = sheet.getCell(0, i).getContents();
				if(folder != "") {
					String displayName = sheet.getCell(1, i).getContents();
					DIR_DISPLAY dir = new DIR_DISPLAY(folder, displayName);
					list.add(dir);
				}				
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
