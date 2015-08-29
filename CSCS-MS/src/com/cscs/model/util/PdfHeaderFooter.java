package com.cscs.model.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfHeaderFooter extends PdfPageEventHelper {
	/** Current page number (will be reset for every chapter). */
	int pagenumber = 1;

	public void onOpenDocument(PdfWriter writer, Document document) {
	}

	public void onChapter(PdfWriter writer, Document document,
			float paragraphPosition, Paragraph title) {
	}

	public void onStartPage(PdfWriter writer, Document document) {
		pagenumber++;
	}

	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
				new Phrase(String.format("Page %d", pagenumber), new Font(
						FontFamily.HELVETICA, 8)), 580, 20, 0);
	}

}