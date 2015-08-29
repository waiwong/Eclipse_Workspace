package com.cscs.model.action.jxls;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import jxl.*;

import com.cscs.model.bo.GAD_SOFT;
import com.cscs.model.bo.Department;

public class GAD_Excel {
	private List<Department> deptList;

	public List<GAD_SOFT> read(String filePath, List<Department> deptList) {
		this.deptList = deptList;
		List<GAD_SOFT> list = new LinkedList<GAD_SOFT>();
		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			// get the first sheet
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();

			for (int i = 1; i < rows; i++) {
				String gaNo = sheet.getCell(0, i).getContents(), softwareName = sheet
						.getCell(1, i).getContents(), deptName = sheet.getCell(2, i)
						.getContents();
				if (gaNo.contains("3-")) {
					GAD_SOFT gadSoftware = new GAD_SOFT(gaNo, softwareName, null);
					deptConfig(gadSoftware, deptName);
					list.add(gadSoftware);
				}
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void deptConfig(GAD_SOFT gadSoftware, String name) {
		name = name.replaceAll(" BR", "");
		for (Department dept : deptList) {
			String deptName = dept.getName(), alias = dept.getAlias();
			deptName = deptName.replaceAll(" BR", "");
			if (name.equals(deptName) || name.equals(alias)) {
				gadSoftware.setDept(dept);
				break;
			}
		}
	}

}