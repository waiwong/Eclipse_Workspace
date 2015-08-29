package com.cscs.model.template;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

//import com.cscs.model.action.jxls.CSCS_GAD_Excel;
import com.cscs.model.action.jxls.CSCS_GAD_Excel;
import com.cscs.model.action.jxls.SMS_Compare_Excel;
import com.cscs.model.util.ExtensionFileFilter;
import com.cscs.model.util.MyMethod;
import com.cscs.model.util.ParagraphLayout;

public class Comparator extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton browseSourceBtn1, browseSourceBtn2, browseDestBtn, runBtn,
			resetBtn;
	private JComboBox typeBox;
	private JTextField sourceText1, sourceText2, destText;
	private MyMethod method;
	private GoThread thread = null;
	private Runnable run = null;
	private String[] typeArray = { "GAD - CSCS", "SMS - SMS" };

	public Comparator() {
		setLayout(new BorderLayout());
		method = new MyMethod();
		JPanel panel1 = new JPanel(new ParagraphLayout()), panel2 = new JPanel();

		typeBox = new JComboBox(typeArray);
		typeBox.setSelectedIndex(-1);
		typeBox.setFont(method.setMyFont());
		sourceText1 = new JTextField(32);
		sourceText2 = new JTextField(32);
		destText = new JTextField(32);
		browseSourceBtn1 = new JButton("Browse");
		browseSourceBtn2 = new JButton("Browse");
		browseDestBtn = new JButton("Browse");
		method.setMyLabel(panel1, "Type", true);
		panel1.add(typeBox);
		method.setMyLabel(panel1, "Source Excel 1", true);
		method.setMyTextField(panel1, sourceText1, null, false);
		method.setMyButton(panel1, browseSourceBtn1);
		method.setMyLabel(panel1, "Source Excel 2", true);
		method.setMyTextField(panel1, sourceText2, null, false);
		method.setMyButton(panel1, browseSourceBtn2);
		method.setMyLabel(panel1, "Destination Path", true);
		method.setMyTextField(panel1, destText, null, false);
		method.setMyButton(panel1, browseDestBtn);

		runBtn = new JButton("Run");
		resetBtn = new JButton("Reset");
		method.setMyButton(panel2, runBtn);
		method.setMyButton(panel2, resetBtn);
		typeBox.addActionListener(this);
		browseSourceBtn1.addActionListener(this);
		browseSourceBtn2.addActionListener(this);
		browseDestBtn.addActionListener(this);
		runBtn.addActionListener(this);
		resetBtn.addActionListener(this);

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);

		run = new Runnable() {
			public void run() {
				// do something...
			}
		};
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == browseSourceBtn1)
			fileChooser(sourceText1, false);
		else if (e.getSource() == browseSourceBtn2)
			fileChooser(sourceText2, false);
		else if (e.getSource() == browseDestBtn)
			fileChooser(destText, true);
		else if (e.getSource() == runBtn) {
			if (thread == null) {
				thread = new GoThread();
				thread.start();
			}
			thread = null;
		}
	}

	private void fileChooser(JTextField textField, boolean isDir) {
		JFileChooser fileChooser = new JFileChooser();
		if (isDir)
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		else {
			FileFilter filter = new ExtensionFileFilter("*.xls", "XLS");
			fileChooser.setFileFilter(filter);
		}
		int status = fileChooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			textField.setText(selectedFile.toString());
		}
	}

	private void go() {
		int index = typeBox.getSelectedIndex();
		String sourcePath1 = sourceText1.getText(), sourcePath2 = sourceText2
				.getText(), destPath = destText.getText();
		try {
			method.showMsg("Comparator is running...Please wait !!!\n");
			if (index == 0) {
				CSCS_GAD_Excel excel = new CSCS_GAD_Excel();
				excel.create(sourcePath1, sourcePath2, destPath);
			} else {
				SMS_Compare_Excel excel = new SMS_Compare_Excel();
				excel.create(sourcePath1, sourcePath2, destPath);
			}
			method.showMsg("Gen excel file is completed !!!\n");
		} catch (Exception exp) {
			method.showMsg("Programe fail :\n" + exp.toString() + "\n");
		}
		SwingUtilities.invokeLater(run);
	}

	class GoThread extends Thread {
		public void run() {
			// do something...
			go();
		}
	}

}