package com.cscs.model.action.jpdf.consolidation;

import java.io.IOException;
import java.util.Date;

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
				"Reprot Name : Departmental Software Consolidation List", 0, 0);
		pdfMethod.addElementToDoc("Department Name", 0, 105.5f);
		pdfMethod.addElementToDoc(" ", 0, 0);
	}

}