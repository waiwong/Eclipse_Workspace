package com.cscs.model.action.jpdf.inventory;

import java.io.IOException;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.cscs.model.bo.Computer;
import com.cscs.model.bo.MyObject;
import com.cscs.model.bo.OS_VER;
import com.cscs.model.util.PdfHeaderFooter;
import com.cscs.model.util.PdfMethod;

public class PdfContent {
	private PdfPTable table;

	public PdfContent(Document document, PdfWriter writer, Date update,
			List<Computer> computerList, LinkedList<MyObject> objList)
			throws DocumentException, IOException {
		PdfMethod pdfMethod = new PdfMethod(document);
		PdfContentByte pdfContent = writer.getDirectContent();
		PdfHeaderFooter event = new PdfHeaderFooter();
		Font font = new Font(FontFamily.HELVETICA, 12);
		BaseFont baseFont = font.getCalculatedBaseFont(false);
		writer.setPageEvent(event);
		tableContent(computerList);
		document.add(table);
		pdfMethod.addElementToDoc("W - Workstation", 0, 0);
		pdfMethod.addElementToDoc("P - Production", 0, 0);
		pdfMethod.addElementToDoc("SVR - Server", 0, 0);
		pdfMethod.addElementToDoc("STD - Standalone", 0, 0);
		document.newPage();

		for (MyObject object : objList) {
			Computer computer = (Computer) object.getObject();
			int id = computer.getId();
			String computerName = computer.getName().toUpperCase(), deptName = computer
					.getDept().getName(), gaNo = computer.getGaNo(), os = "N/A";
			OS_VER osVersion = computer.getOs();
			if (osVersion != null)
				os = osVersion.getAbbreviation();
			Iterator<?> folderNameIter = object.getObjList();
			// pdf layout
			new PdfLayout(document, writer, update);
			pdfContent.beginText();
			pdfContent.setFontAndSize(baseFont, 12);
			// pdf header
			pdfContent.showTextAligned(0, pdfMethod.setReportNo(id), 118, 733, 0);
			pdfContent.showTextAligned(0, deptName, 150, 679, 0);
			pdfContent.showTextAligned(0, computerName, 150, 661, 0);
			pdfContent.showTextAligned(0, gaNo, 150, 643, 0);
			pdfContent.showTextAligned(0, os, 150, 625, 0);
			pdfContent.endText();
			pdfMethod.addElementToDoc(" ", 0, 0);

			// pdf content
			while (folderNameIter.hasNext())
				pdfMethod.addElementToDoc((String) folderNameIter.next(), 0, 0);

			// pdf remark
			pdfContent.beginText();
			pdfContent.setFontAndSize(baseFont, 7);
			pdfContent.setColorFill(BaseColor.DARK_GRAY);
			pdfContent
					.showTextAligned(
							0,
							"The above program list are for those programs that "
									+ "have to be installed additionally, i.e. Not belonging to default programs / drivers "
									+ "of Windows system.", 15, 30, 0);
			pdfContent
					.showTextAligned(
							0,
							"What belongs to default program / driver of Windows system ? "
									+ "It can be searched from the following URL < http://bwhprdrpt:8080/CSCS >",
							15, 22, 0);
			pdfContent.endText();
			pdfContent.setColorFill(BaseColor.BLACK);
			document.newPage();
		}
	}

	private void tableContent(List<Computer> computerList) {
		float[] colsWidth = { 1f, 1f, 1f };
		table = new PdfPTable(colsWidth);
		table.setWidthPercentage(100);
		// pdf header
		String[] headerArr = { "Computer Name", "GA-NO.", "Catalog" };
		for (String header : headerArr)
			addTableHeader(header);

		// pdf content
		PdfPCell cell = new PdfPCell();
		for (Computer computer : computerList) {
			String catalogStr = null, name = computer.getName();
			tableAddCell(cell, name);
			tableAddCell(cell, computer.getGaNo());
			int catalog = computer.getCatalog();
			switch (catalog) {
			case 0:
				catalogStr = name.contains("BCP") ? "W & BCP" : "W & P";
				break;
			case 1:
				catalogStr = "SVR & P";
				break;
			case 2:
				catalogStr = "STD";
			}
			tableAddCell(cell, catalogStr);
		}
	}

	private void tableAddCell(PdfPCell cell, String str) {
		cell = new PdfPCell(new Phrase(str, new Font(FontFamily.HELVETICA, 8,
				Font.NORMAL)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

	private void addTableHeader(String header) {
		PdfPCell cell = new PdfPCell(new Phrase(header, new Font(
				FontFamily.HELVETICA, 10, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

}