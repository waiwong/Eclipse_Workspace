package com.cscs.model.action.jxls;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.cscs.model.bo.SYS_DEFAULT;

import jxl.Sheet;
import jxl.Workbook;

public class SYS_DEFAULT_Excel {

	public List<SYS_DEFAULT> read(String filePath) {
		HashSet<String> hashSet = new HashSet<String>();
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = book.getSheet(1); // the second sheet
			int rows = sheet.getRows(); // total rows
			for (int i = 1; i < rows; i++) {
				String softwareName = sheet.getCell(0, i).getContents();
				hashSet.add(softwareName);
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<SYS_DEFAULT> list = new ArrayList<SYS_DEFAULT>();
		Iterator<String> iterator = hashSet.iterator();
		while (iterator.hasNext())
			list.add(new SYS_DEFAULT(iterator.next()));
		return list;
	}

}