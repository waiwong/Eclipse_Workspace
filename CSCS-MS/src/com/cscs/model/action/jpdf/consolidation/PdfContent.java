package com.cscs.model.action.jpdf.consolidation;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.cscs.model.bo.Department;
import com.cscs.model.bo.MyObject;
import com.cscs.model.util.PdfHeaderFooter;
import com.cscs.model.util.PdfMethod;

public class PdfContent {
	PdfMethod pdfMethod;
	List<Object[]> myObjectList;

	public PdfContent(Document document, PdfWriter writer, Date update,
			List<Object[]> myObjectList) throws DocumentException, IOException {
		this.myObjectList = myObjectList;
		pdfMethod = new PdfMethod(document);
		PdfContentByte pdfContent = writer.getDirectContent();
		PdfHeaderFooter event = new PdfHeaderFooter();
		Font font = new Font(FontFamily.HELVETICA, 12);
		BaseFont baseFont = font.getCalculatedBaseFont(false);
		writer.setPageEvent(event);

		for (Object[] myObject : myObjectList) {
			Department dept = (Department) myObject[0];
			String deptName = dept.getName();
			new PdfLayout(document, writer, update); // set pdf layout
			pdfContent.beginText();
			pdfContent.setFontAndSize(baseFont, 12);
			pdfContent.showTextAligned(0, pdfMethod.setReportNo(dept.getId()), 118,
					733, 0);
			pdfContent.showTextAligned(0, deptName, 150, 698, 0);
			pdfContent.endText();
			document.add(createTable(myObject));
			document.newPage();
		}
	}

	public PdfPTable createTable(Object[] myObject) {
		float[] colsWidth = { 3f, 1f };
		PdfPTable table = new PdfPTable(colsWidth);
		table.setWidthPercentage(100);
		pdfMethod.setPdfTable(table);
		pdfMethod.addTableHeader("Software Name");
		pdfMethod.addTableHeader("GA-NO.");

		@SuppressWarnings("unchecked")
		List<MyObject> objectList = (List<MyObject>) myObject[1];
		for (MyObject object : objectList) {
			PdfPCell cell = new PdfPCell(new Phrase((String) object.getObject()));
			cell.setRowspan(object.getLength());
			table.addCell(cell);
			Iterator<?> gaNoIter = object.getObjList();
			while (gaNoIter.hasNext())
				table.addCell((String) gaNoIter.next());
		}
		return table;
	}

}