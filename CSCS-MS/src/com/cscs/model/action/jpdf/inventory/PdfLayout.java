package com.cscs.model.action.jpdf.inventory;

import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.cscs.model.util.PdfMethod;

public class PdfLayout {

	public PdfLayout(Document document, PdfWriter writer, Date update)
			throws DocumentException, IOException {
		PdfMethod pdfMethod = new PdfMethod(document);
		pdfMethod.loadImage(writer);
		pdfMethod.addElementToDoc(
				"Print Time : " + pdfMethod.formatTime(new Date()), 2, 0);
		pdfMethod.addElementToDoc("Update Time : " + pdfMethod.formatTime(update),
				2, 0);
		pdfMethod.addElementToDoc(" ", 0, 0);
		pdfMethod.addElementToDoc("Reprot NO.", 0, 75f);
		pdfMethod.addElementToDoc(
				"Reprot Name : Computeral Software Inventory List", 0, 0);
		pdfMethod.addLineToDoc(0.5f, 80, BaseColor.LIGHT_GRAY);
		pdfMethod.addElementToDoc(" ", 0, 0);

		pdfMethod.addElementToDoc("Department Name", 0, 101.5f);
		pdfMethod.addElementToDoc("Computer Name", 0, 101.5f);
		pdfMethod.addElementToDoc("GA-NO.", 0, 101.5f);
		pdfMethod.addElementToDoc("Operation System", 0, 101.5f);
		pdfMethod.addElementToDoc(" ", 0, 0);
		pdfMethod.addBoldFontToDoc("Program List");
		pdfMethod.addLineToDoc(1.5f, 100, BaseColor.BLACK);
	}

}