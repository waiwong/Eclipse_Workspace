package com.wol.model.util;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JTable;

public class MyTableItemListener implements ItemListener {
	private JTable table;

	public MyTableItemListener(JTable table) {
		this.table = table;
	}

	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if (source instanceof AbstractButton == false)
			return;
		boolean checked = e.getStateChange() == ItemEvent.SELECTED;
		for (int x = 0, y = table.getRowCount(); x < y; x++)
			table.setValueAt(new Boolean(checked), x, 0);
	}

}
