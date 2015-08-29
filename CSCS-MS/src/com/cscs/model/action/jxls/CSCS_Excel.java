package com.cscs.model.action.jxls;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jxl.*;
import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Department;
import com.cscs.model.util.MyMethod;

public class CSCS_Excel {

	public List<TB_CSCS> read(String filePath, List<Department> deptList) {
		List<TB_CSCS> list = new LinkedList<TB_CSCS>();
		MyMethod method = new MyMethod();

		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// get the first sheet
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			for (int i = 1; i < rows; i++) {
				String deptName = sheet.getCell(0, i).getContents(),
							 gaNo = sheet.getCell(1, i).getContents(),
							 codeType = sheet.getCell(2, i).getContents(),
							 codeNo = sheet.getCell(3, i).getContents(),
							 softwareName = sheet.getCell(4, i).getContents();
				Department dept = method.deptConfig(deptName, deptList);
				TB_CSCS cscs = new TB_CSCS(i, dept, gaNo, codeType, codeNo, softwareName,
						false);
				if (deptName.contains("BCP"))
					cscs.setBcp(true);
				list.add(cscs);
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}