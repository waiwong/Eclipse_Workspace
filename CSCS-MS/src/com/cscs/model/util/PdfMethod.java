package com.cscs.model.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class PdfMethod {
	private Document document;
	private Paragraph p;
	private PdfPTable table;

	public PdfMethod(Document document) {
		this.document = document;
	}

	public void setPdfTable(PdfPTable table) {
		this.table = table;
	}

	public void loadImage(PdfWriter writer) throws DocumentException, IOException {
		String imgPath = System.getProperty("user.dir") + File.separator
				+ "logo.png";
		Image img = Image.getInstance(imgPath);
		img.setAbsolutePosition(25, 785);
		img.scaleToFit(250, 35);
		writer.getDirectContent().addImage(img);
	}

	public void addElementToDoc(String str, int locate, float space)
			throws DocumentException {
		p = new Paragraph(str);
		p.setAlignment(locate);
		if (space != 0) {
			p.add(new Chunk(new VerticalPositionMark(), space, true));
			p.add(": ");
		}
		document.add(p);
	}

	public void addLineToDoc(float lineWidth, float percentage,
			BaseColor lineColor) throws DocumentException {
		p = new Paragraph();
		LineSeparator line = new LineSeparator(lineWidth, percentage, lineColor,
				Element.ALIGN_CENTER, 0);
		p.add(line);
		document.add(p);
	}

	public void addBoldFontToDoc(String str) throws DocumentException {
		p = new Paragraph(str, new Font(FontFamily.HELVETICA, 16, Font.BOLD));
		document.add(p);
	}

	public String setReportNo(int reportNo) {
		String str = "0", reportNoStr = Integer.toString(reportNo);
		while (reportNoStr.length() < 6)
			reportNoStr = str.concat(reportNoStr);
		return reportNoStr;
	}

	public String formatTime(Date date) {
		SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
		return matter.format(date);
	}

	public void addTableHeader(String headerStr) {
		PdfPCell cell = new PdfPCell(new Phrase(headerStr, new Font(
				FontFamily.HELVETICA, 16, Font.BOLD)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	}

}