package com.cscs.model.action.jxls;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.SMS;

public class SMS_Excel {

	public List<SMS> read(String filePath, List<Computer> computerList) {
		List<SMS> list = new LinkedList<SMS>();
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = book.getSheet(0);
			String hostName = null, softwareName = null;
			int rows = sheet.getRows();

			for (int i = 1; i < rows; i++) {
				softwareName = sheet.getCell(0, i).getContents();
				hostName = sheet.getCell(1, i).getContents();
				if (!softwareName.equals("")) {
					// find computer by hostName
					Computer computer = selectFirst(computerList,
							having(on(Computer.class).getName(), equalTo(hostName)));
					if (computer == null)
						continue;
					SMS sms = new SMS(i, softwareName, computer, null);
					list.add(sms);
				}
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

}