package com.cscs.model.action.jpdf.consolidation;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import com.cscs.model.action.GaNoSelector;
import com.cscs.model.bo.Department;
import com.cscs.model.bo.MyObject;
import com.cscs.model.service.CSCSService;
import com.cscs.model.service.RecordService;
import com.cscs.model.template.GenReport;
import com.cscs.model.util.MyMethod;

public class GaNoPdf {

	public void create(ApplicationContext context, List<Department> deptList,
			String filePath) {
		int count = 0, listSize = deptList.size();
		MyMethod method = new MyMethod();
		CSCSService cscsService = (CSCSService) context.getBean("cscsService");
		RecordService recordService = (RecordService) context
				.getBean("recordService");
		Date update = recordService.findByTableName("sms").getLastUpdate();

		try {
			for (Department dept : deptList) {
				List<Object[]> myObjectList = new LinkedList<Object[]>();
				String deptName = dept.getName();
				String pdfFile = filePath + File.separator + deptName + "_2.pdf";
				GaNoSelector selector = new GaNoSelector();
				List<MyObject> objectList = selector.getPdfContentList(cscsService,
						dept);
				Object[] myObjectArray = { dept, objectList };
				myObjectList.add(myObjectArray);
				Document document = new Document(PageSize.A4);
				PdfWriter writer = PdfWriter.getInstance(document,
						new FileOutputStream(pdfFile));
				document.open();
				GenReport.progressText.setText(deptName);
				new PdfContent(document, writer, update, myObjectList);
				document.close();

				count++;
				GenReport.completeText.setText(count + " / " + listSize);
			}
		} catch (Exception e) {
			method.showMsg("\n" + e.toString());
		}
	}

}