package com.wol.model.template;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Filter;
import org.jdesktop.swingx.decorator.FilterPipeline;
import org.jdesktop.swingx.decorator.PatternFilter;

import com.wol.model.bo.Computer;
import com.wol.model.jxls.ReadXls;
import com.wol.model.util.MyCommand;
import com.wol.model.util.MyTableItemListener;
import com.wol.model.util.MyMethod;
import com.wol.model.util.Wakeup;
import com.wol.model.util.Shutdown;

public class ConsolePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton wakeupBtn, shutdownBtn;
	private JTextField keywordField;
	private DefaultTableModel model;
	private MyMethod method;
	private Object[] columnNames = { "", "Host Name", "IP Address", "MAC Address" };
	private Object[][] data;
	private List<Computer> selectedList;
	private MyCommand cmd;
	private int count = 0;
	public static JXTable table;

	@SuppressWarnings("serial")
	public ConsolePanel() {
		setLayout(new BorderLayout());
		cmd = new MyCommand();
		method = new MyMethod();

		// the filter of JTable
		JPanel searchPanel = new JPanel(new BorderLayout()),
					 panel1 = new JPanel(), panel2 = new JPanel();
		wakeupBtn = new JButton("Wake Up");
		shutdownBtn = new JButton("Remote Shutdown");
		keywordField = new JTextField(40);
		keywordField.setFont(method.setMyFont());
		wakeupBtn.addActionListener(this);
		shutdownBtn.addActionListener(this);
		keywordField.addActionListener(this);
		method.setMyLabel("Keyword", panel1);
		panel1.add(keywordField);
		method.setMyButton(wakeupBtn, panel2);
		method.setMyButton(shutdownBtn, panel2);
		searchPanel.add(panel1, BorderLayout.NORTH);
		searchPanel.add(panel2, BorderLayout.CENTER);
		add(searchPanel, BorderLayout.NORTH);

		// create JTable
		ReadXls read = new ReadXls();
		data = read.array;
		model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		table = new JXTable(model);
		table.setSortable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setCheckBoxColumn();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		// filter component
		if (e.getSource() == keywordField) {
			String keyword = keywordField.getText();
			Filter[] filterArray = { new PatternFilter(keyword.toUpperCase(), 1, 1) };
			FilterPipeline filters = new FilterPipeline(filterArray);
			table.setFilters(filters);
		}

		else if (e.getSource() == wakeupBtn)
			wakeup();
		
		else if (e.getSource() == shutdownBtn)
			shutdown();
	}

	private void wakeup() {
		count = 0;
		this.selectedItemHandler();
		if (selectedList.size() == 0) {
			JOptionPane.showMessageDialog(this, "Please select computer !",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		System.out.println("Begin [ Wakeup ] operation ...");
		System.out.println("Total selected : " + count);
		List<Thread> threads = new ArrayList<Thread>();
		for (Computer computer : selectedList) {
			Runnable task = new Wakeup(computer, cmd);
			Thread thread = new Thread(task);
			threads.add(thread);
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				// starting from the first wait for each one to finish.
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this, count + " thread(s) were finished !",
				"System Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void shutdown() {
		count = 0;
		this.selectedItemHandler();
		if (selectedList.size() == 0) {
			JOptionPane.showMessageDialog(this, "Please select computer !",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		System.out.println("Begin [ Shutdown ] operation ...");
		System.out.println("Total selected : " + count);
		List<Thread> threads = new ArrayList<Thread>();
		for (Computer computer : selectedList) {
			Runnable task = new Shutdown(computer.getHostName());
			Thread thread = new Thread(task);
			threads.add(thread);
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				// starting from the first wait for each one to finish.
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(this, count + " thread(s) were finished !",
				"System Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setCheckBoxColumn() {
		TableColumn checkBoxColumn = table.getColumnModel().getColumn(0);
		checkBoxColumn.setMaxWidth(100);
		checkBoxColumn.setHeaderRenderer(new MyCheckBoxHeader(
				new MyTableItemListener(table)));
	}

	// begin the internal class
	class MyCheckBoxHeader extends JCheckBox implements TableCellRenderer {
		private static final long serialVersionUID = 1L;
		protected MyCheckBoxHeader rendererComponent;
		protected int column;
		protected boolean mousePressed = false;

		public MyCheckBoxHeader(ItemListener itemListener) {
			rendererComponent = this;
			rendererComponent.addItemListener(itemListener);
		}

		public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					rendererComponent.setFont(header.getFont());
					header.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							handleClickEvent(e);
							((JTableHeader) e.getSource()).repaint();
						}

						public void mousePressed(MouseEvent e) {
							mousePressed = true;
						}
					});
				}
			}
			setColumn(column);
			rendererComponent.setText("Select All");
			return rendererComponent;
		}

		protected void handleClickEvent(MouseEvent e) {
			if (mousePressed) {
				mousePressed = false;
				JTableHeader header = (JTableHeader) (e.getSource());
				JTable tableView = header.getTable();
				TableColumnModel columnModel = tableView.getColumnModel();
				int viewColumn = columnModel.getColumnIndexAtX(e.getX());
				int column = tableView.convertColumnIndexToModel(viewColumn);
				if (viewColumn == this.column && e.getClickCount() == 1 && column != -1)
					doClick();
			}
		}

		protected void setColumn(int column) {
			this.column = column;
		}

		public int getColumn() {
			return column;
		}
	} // end the internal class

	private void selectedItemHandler() {
		selectedList = new LinkedList<Computer>();
		for (int i = 0; i < table.getRowCount(); i++) {
			String hostName = (String) table.getValueAt(i, 1), macAddr = (String) table
					.getValueAt(i, 3);
			if (table.getValueAt(i, 0).equals(true)) {
				Computer computer = new Computer();
				computer.setHostName(hostName);
				computer.setMacAddr(macAddr);
				computer.setIndex(i);
				selectedList.add(computer);
				count++;
			}
		}
	}

}