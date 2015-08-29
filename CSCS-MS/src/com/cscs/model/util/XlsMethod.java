package com.cscs.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.HeaderFooter;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class XlsMethod {
	private WritableSheet sheet;
	private WritableCellFormat normalFormat, boldFormat, underLineFormat;

	public void set(WritableSheet sheet, String header, String title,
			String fileDate, int[] columnWidth, String[] columnName)
			throws RowsExceededException, WriteException {
		// sheet font setting
		this.sheet = sheet;
		WritableFont normalFont = new WritableFont(
				WritableFont.createFont("Times New Roman"), 12), boldFont = new WritableFont(
				WritableFont.createFont("Times New Roman"), 12, WritableFont.BOLD), underLineFont = new WritableFont(
				WritableFont.createFont("Times New Roman"), 12, WritableFont.BOLD);
		underLineFont.setUnderlineStyle(UnderlineStyle.SINGLE);
		normalFormat = new WritableCellFormat(normalFont);
		boldFormat = new WritableCellFormat(boldFont);
		underLineFormat = new WritableCellFormat(underLineFont);

		// header
		sheet.getSettings().setHeader(new HeaderFooter(header + fileDate));
		// title
		addBoldFont(0, 0, title);
		// table header
		int columnLength = columnWidth.length;
		for (int i = 0; i < columnLength; i++) {
			if (columnLength == 1) {
				underLineFormat.setAlignment(Alignment.CENTRE);
				sheet.mergeCells(0, 2, 2, 2);
			}
			sheet.setColumnView(i, columnWidth[i]);
			addUnderLineFont(i, 2, columnName[i]);
		}
	}

	public void addBoldFont(int row, int column, String str)
			throws RowsExceededException, WriteException {
		Label label = new Label(row, column, str, boldFormat);
		sheet.addCell(label);
	}

	public void addNormalFont(int row, int column, String str)
			throws RowsExceededException, WriteException {
		Label label = new Label(row, column, str, normalFormat);
		sheet.addCell(label);
	}

	public void addUnderLineFont(int row, int column, String str)
			throws RowsExceededException, WriteException {
		Label label = new Label(row, column, str, underLineFormat);
		sheet.addCell(label);
	}

	public void addUnderlineBorder(int index) {
		WritableCellFormat wcf = new WritableCellFormat(boldFormat);
		try {
			addBoldFont(1, index, "Checker :");
			sheet.setColumnView(1, 13);
			wcf.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
			sheet.setColumnView(2, 20);
			Label label = new Label(2, index, null, wcf);
			sheet.addCell(label);
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public String getCurrentTime() {
		Date printTime = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyyMMdd");
		return matter.format(printTime);
	}

}