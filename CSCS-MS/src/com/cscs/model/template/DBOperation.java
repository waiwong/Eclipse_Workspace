package com.cscs.model.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.springframework.context.ApplicationContext;

import com.cscs.model.action.DBAction;
import com.cscs.model.bo.TB_LOG;
import com.cscs.model.service.RecordService;
import com.cscs.model.util.ExtensionFileFilter;
import com.cscs.model.util.MyMethod;
import com.cscs.model.util.ParagraphLayout;

public class DBOperation extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton browseBtn, runBtn, resetBtn;
	private JComboBox tableNameBox;
	private JTextField currentFileText, timeText, pathText;
	private MyMethod method;
	private String[] tableNameArr;
	private List<TB_LOG> recordList;
	private ApplicationContext context;
	private GoThread thread = null;
	private Runnable run = null;
	private TB_LOG record;
	private String fileName = null;
	private int selectedIndex = 0;

	public DBOperation(ApplicationContext context) {
		this.context = context;
		setLayout(new BorderLayout());
		record = new TB_LOG();
		method = new MyMethod();
		JPanel panel1 = new JPanel(new ParagraphLayout()), panel2 = new JPanel();
		currentFileText = new JTextField(30);
		timeText = new JTextField(30);
		pathText = new JTextField(30);
		browseBtn = new JButton("Browse");

		recordConfig();
		tableNameBox = new JComboBox(tableNameArr);
		tableNameBox.setSelectedIndex(-1);
		tableNameBox.setFont(method.setMyFont());
		method.setMyLabel(panel1, "Table Name", true);
		panel1.add(tableNameBox);
		method.setMyLabel(panel1, "Current File Name", true);
		method.setMyTextField(panel1, currentFileText, null, false);
		method.setMyLabel(panel1, "Last Updated Time", true);
		method.setMyTextField(panel1, timeText, null, false);
		method.setMyLabel(panel1, "Imported File Name", true);
		method.setMyTextField(panel1, pathText, null, false);
		method.setMyButton(panel1, browseBtn);

		runBtn = new JButton("Run");
		resetBtn = new JButton("Reset");
		method.setMyButton(panel2, runBtn);
		method.setMyButton(panel2, resetBtn);
		tableNameBox.addActionListener(this);
		browseBtn.addActionListener(this);
		runBtn.addActionListener(this);
		resetBtn.addActionListener(this);

		JTextArea remindArea = new JTextArea(3, 35);
		remindArea.setBackground(new Color(215, 215, 225));
		remindArea.setEditable(false);
		remindArea.setBorder(new TitledBorder("Reminder"));
		remindArea
				.setText("Please follow the order to initialize the whole database :\n"
						+ "1) CSCS 2) Computer 3) SMS / GadSoftware / DefaultSoftware.");
		remindArea.setFont(method.setMyFont());
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(remindArea, BorderLayout.SOUTH);

		run = new Runnable() {
			public void run() {
				// do something...
			}
		};
	}

	private void recordConfig() {
		RecordService recordService = (RecordService) context
				.getBean("recordService");
		recordList = recordService.findAll();
		int recordLength = recordList.size();
		tableNameArr = new String[recordLength];
		for (int i = 0; i < recordLength; i++)
			tableNameArr[i] = recordList.get(i).getTableName();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tableNameBox) {
			selectedIndex = tableNameBox.getSelectedIndex();
			record = recordList.get(selectedIndex);
			refreshValue();
		}

		else if (e.getSource() == browseBtn) {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter filter = new ExtensionFileFilter("*.xls", "XLS");
			fileChooser.setFileFilter(filter);
			int status = fileChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				fileName = selectedFile.getName();
				pathText.setText(selectedFile.toString());
			}
		}

		else if (e.getSource() == runBtn) {
			if (thread == null) {
				thread = new GoThread();
				thread.start();
			}
			thread = null;
		}

		else
			pathText.setText(null);
	}

	private void refreshValue() {
		currentFileText.setText(record.getCurrentFileName());
		timeText.setText(record.getLastUpdate().toString());
	}

	private void go() {
		method.showMsg("Programe running...\n");
		DBAction dbAction = new DBAction();
		dbAction.confirmImport(context, selectedIndex, record, fileName,
				pathText.getText());
		refreshValue();
		SwingUtilities.invokeLater(run);
	}

	class GoThread extends Thread {
		public void run() {
			// do something...
			go();
		}
	}

}