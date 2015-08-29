package com.cscs.model.action.jxls;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import jxl.Sheet;
import jxl.Workbook;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Department;
import com.cscs.model.bo.OS_VER;
import com.cscs.model.service.CSCSService;
import com.cscs.model.service.OSVersionService;
import com.cscs.model.util.MyMethod;

public class ComputerExcel {
	private List<TB_CSCS> cscsList;
	private List<OS_VER> osVersionList;

	public List<Computer> read(ApplicationContext context, String filePath,
			CSCSService cscsService, List<Department> departmentList) {
		OSVersionService osVersionService = (OSVersionService) context.getBean("osVersionService");
		osVersionList = osVersionService.findAll();
		cscsList = cscsService.matchByComputer();
		List<Computer> list = new LinkedList<Computer>();
		List<Object[]> dataObjList = checkVersionAndLocate();
		MyMethod method = new MyMethod();

		try {
			Workbook book = Workbook.getWorkbook(new File(filePath));
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			for (int i = 1; i < rows; i++) {
				String deptName = sheet.getCell(0, i).getContents(),
						   hostName = sheet.getCell(1, i).getContents(),
						   gaNo = sheet.getCell(3, i).getContents(),
						   catalogStr = sheet.getCell(2, i).getContents();
				if (hostName.contains("BWH") || hostName.contains("MAC")) {
					Department dept = method.deptConfig(deptName, departmentList);
					int catalog = 0;
					if (catalogStr.equals("W"))
						catalog = 0;
					else if (catalogStr.equals("SVR"))
						catalog = 1;
					else
						catalog = 2;
					Computer computer = new Computer(i, dept, hostName, gaNo, null, catalog);

					for (Object[] object : dataObjList) {
						OS_VER os = (OS_VER) object[0];
						TB_CSCS cscs = (TB_CSCS) object[1];
						if (gaNo.equals(cscs.getPcGaNo())) {
							if (cscs.isBcp())
								computer.setDept(cscs.getDept());
							computer.setOs(os);
							break;
						}
					}
					list.add(computer);
				}
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<Object[]> checkVersionAndLocate() {
		List<Object[]> list = new LinkedList<Object[]>();
		for (TB_CSCS cscs : cscsList) {
			String name = cscs.getSoftwareName();
			OS_VER osVersion = selectFirst(osVersionList,
					having(on(OS_VER.class).getCscsName(), equalTo(name)));
			if (osVersion != null) {
				Object[] object = { osVersion, cscs };
				list.add(object);
			}
		}
		return list;
	}

}