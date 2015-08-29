package com.cscs.model.action.jpdf.inventory;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Computer;
import com.cscs.model.bo.Department;
import com.cscs.model.bo.MyObject;
import com.cscs.model.bo.SMS;
import com.cscs.model.service.CSCSService;
import com.cscs.model.service.ComputerService;
import com.cscs.model.service.RecordService;
import com.cscs.model.service.SMSService;
import com.cscs.model.template.GenReport;
import com.cscs.model.util.MyMethod;

public class SMSPdf {
	private LinkedList<MyObject> list;

	public void create(ApplicationContext context, List<Department> deptList,
			String filePath) {
		int count = 0, listSize = deptList.size();
		MyMethod method = new MyMethod();
		ComputerService computerService = (ComputerService) context
				.getBean("computerService");
		CSCSService cscsService = (CSCSService) context.getBean("cscsService");
		SMSService smsService = (SMSService) context.getBean("smsService");
		RecordService recordService = (RecordService) context
				.getBean("recordService");
		Date update = recordService.findByTableName("sms").getLastUpdate();

		try {
			for (Department dept : deptList) {
				list = new LinkedList<MyObject>();
				String deptName = dept.getName(), pdfFile = filePath + File.separator
						+ deptName + "_1.pdf";
				List<Computer> computerList = computerService.findByDept(dept);
				GenReport.progressText.setText(deptName);
				count++;
				GenReport.completeText.setText(count + " / " + listSize);

				if (computerList.size() != 0) {
					// get computer and folder
					for (Computer computer : computerList) {
						List<SMS> smsList = smsService.findByComputer(computer);
						List<TB_CSCS> cscsList = cscsService.findStdPC(computer.getGaNo());

						// sms list extract folder
						if (smsList.size() != 0) {
							LinkedHashSet<String> hashSet = new LinkedHashSet<String>();
							for (SMS sms : smsList)
								hashSet.add(sms.getFolder());
							Iterator<String> folderNameList = hashSet.iterator();
							MyObject object = new MyObject(folderNameList, computer, 0);
							list.add(object);
						}

						// cscs list extract folder
						else if (cscsList.size() != 0) {
							LinkedHashSet<String> hashSet = new LinkedHashSet<String>();
							for (TB_CSCS cscs : cscsList)
								hashSet.add(cscs.getSoftwareName());
							Iterator<String> folderNameList = hashSet.iterator();
							MyObject object = new MyObject(folderNameList, computer, 0);
							list.add(object);
						}
					}
					Document document = new Document(PageSize.A4);
					PdfWriter writer = PdfWriter.getInstance(document,
							new FileOutputStream(pdfFile));
					document.open();
					new PdfContent(document, writer, update, computerList, list);
					document.close();
				}
			}
		} catch (Exception e) {
			method.showMsg("\n" + e.toString());
		}
	}

}