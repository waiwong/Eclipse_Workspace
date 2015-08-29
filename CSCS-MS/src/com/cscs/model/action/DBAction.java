package com.cscs.model.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cscs.model.action.jxls.ComputerExcel;
import com.cscs.model.action.jxls.CSCS_Excel;
import com.cscs.model.action.jxls.DIR_Excel;
import com.cscs.model.action.jxls.SYS_DEFAULT_Excel;
import com.cscs.model.action.jxls.GAD_Excel;
import com.cscs.model.action.jxls.SMS_Excel;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.DIR_DISPLAY;
import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Department;
import com.cscs.model.bo.GAD_SOFT;
import com.cscs.model.bo.TB_LOG;
import com.cscs.model.bo.SMS;
import com.cscs.model.bo.SYS_DEFAULT;
import com.cscs.model.service.ComputerService;
import com.cscs.model.service.CSCSService;
import com.cscs.model.service.DepartmentService;
import com.cscs.model.service.DirService;
import com.cscs.model.service.GadSoftwareService;
import com.cscs.model.service.RecordService;
import com.cscs.model.service.SMSService;
import com.cscs.model.service.SystemDefaultService;
import com.cscs.model.util.MyMethod;

public class DBAction {

	public void confirmImport(ApplicationContext context, int index,
			TB_LOG record, String fileName, String filePath) {
		// initialize service
		ComputerService computerService = (ComputerService) context
				.getBean("computerService");
		CSCSService cscsService = (CSCSService) context.getBean("cscsService");
		DepartmentService departmentService = (DepartmentService) context
				.getBean("departmentService");
		GadSoftwareService gadSoftwareService = (GadSoftwareService) context
				.getBean("gadSoftwareService");
		RecordService recordService = (RecordService) context
				.getBean("recordService");
		SMSService smsService = (SMSService) context.getBean("smsService");
		SystemDefaultService defaultService = (SystemDefaultService) context
				.getBean("defaultService");
		DirService dirService = (DirService) context.getBean("dirService");
		List<Department> departmentList = departmentService
				.findAllDept("id", false);
		List<Computer> computerList = computerService.findAll();
		MyMethod method = new MyMethod();

		try {
			int listLen = 0;
			switch (index) {
			case 0:
				ComputerExcel excel_0 = new ComputerExcel();
				List<Computer> list_0 = excel_0.read(context, filePath,
						cscsService, departmentList);
				listLen = list_0.size();
				computerService.saveAll(list_0);
				break;
			case 1:
				CSCS_Excel excel_1 = new CSCS_Excel();
				List<TB_CSCS> list_1 = excel_1.read(filePath, departmentList);
				listLen = list_1.size();
				cscsService.saveAll(list_1);
				break;
			case 2:
				GAD_Excel excel_2 = new GAD_Excel();
				List<GAD_SOFT> list_2 = excel_2.read(filePath, departmentList);
				listLen = list_2.size();
				gadSoftwareService.saveAll(list_2);
				break;
			case 3:
				SMS_Excel excel_3 = new SMS_Excel();
				List<SMS> list_3 = excel_3.read(filePath, computerList);
				// folder mapping, find all folder is not null values
				FolderMapper mapper = new FolderMapper();
				List<SMS> temp = mapper.softwareMapToFolder(context, list_3);
				listLen = temp.size();
				smsService.saveAll(temp);
				break;
			case 4:
				SYS_DEFAULT_Excel excel_4 = new SYS_DEFAULT_Excel();
				List<SYS_DEFAULT> list_4 = excel_4.read(filePath);
				listLen = list_4.size();
				defaultService.saveAll(list_4);
				break;
			case 5:
				DIR_Excel excel_5 = new DIR_Excel();
				List<DIR_DISPLAY> list_5 = excel_5.read(filePath);
				listLen = list_5.size();
				dirService.saveAll(list_5);
			}

			method.showMsg("Save or Update " + listLen
					+ " records to database successfully !" + "\n");
			record.setLastUpdate(new Date());
			record.setCurrentFileName(fileName);
			recordService.update(record);
			method.showMsg("Update database config !" + "\n\n");
		} catch (Exception e) {
			System.out.println(e);
			method.showMsg("\nOccurred Time : " + new Date() + "\n"
					+ "SQL Exception Name : " + e.getClass().getName() + "\n"
					+ "Description : " + e.getCause().getMessage());
		}
	}

}