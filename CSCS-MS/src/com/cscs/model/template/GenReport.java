package com.cscs.model.template;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import org.springframework.context.ApplicationContext;

import com.cscs.model.action.jpdf.consolidation.GaNoPdf;
import com.cscs.model.action.jpdf.inventory.SMSPdf;
import com.cscs.model.bo.Department;
import com.cscs.model.service.DepartmentService;
import com.cscs.model.util.MyMethod;
import com.cscs.model.util.SpringUtilities;

public class GenReport extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	public static JTextField progressText, completeText;
	private JButton ltBtn, gtBtn, dltBtn, dgtBtn, browseBtn, submitBtn, resetBtn;
	private JList deptList, selectedList;
	private JTextField pathText;
	private JRadioButton btn_1, btn_2;
	private MyMethod method;
	private DefaultListModel deptModel, selectedModel;
	private GoThread thread = null;
	private Runnable run = null;
	private ApplicationContext context;
	private DepartmentService departmentService;
	private int total = 0, countOfSelected = 0;
	private List<Department> deptNameList;

	public GenReport(ApplicationContext context) {
		this.context = context;
		setLayout(new BorderLayout());
		method = new MyMethod();
		findAllDeptName();

		// north Panel
		JPanel listPanel = new JPanel(new SpringLayout()), btnPanel = new JPanel(
				new SpringLayout()), operatePanel = new JPanel(new BorderLayout()), infoPanel = new JPanel(
				new SpringLayout()), radioPanel = new JPanel(), destPanel = new JPanel(), actionPanel = new JPanel();
		ltBtn = new JButton("<");
		gtBtn = new JButton(">");
		dltBtn = new JButton("<<");
		dgtBtn = new JButton(">>");
		setMyButton(btnPanel, gtBtn);
		setMyButton(btnPanel, ltBtn);
		setMyButton(btnPanel, dgtBtn);
		setMyButton(btnPanel, dltBtn);
		SpringUtilities.makeCompactGrid(btnPanel, 4, 1, // rows, cols
				10, 50, // locateX, locateY
				20, 8); // componentX, componentY

		deptList = new JList(deptModel);
		selectedList = new JList(selectedModel);
		setMyJList(deptList, "Department List", listPanel);
		listPanel.add(btnPanel);
		setMyJList(selectedList, "Selected List", listPanel);
		SpringUtilities.makeCompactGrid(listPanel, 1, 3, // rows, cols
				10, 10, // locateX, locateY
				20, 8); // componentX, componentY

		// center Panel
		btn_1 = new JRadioButton("Inventory");
		btn_2 = new JRadioButton("Consolidation");
		browseBtn = new JButton("Browse");
		submitBtn = new JButton("Submit");
		resetBtn = new JButton("Reset");
		pathText = new JTextField(30);

		btn_1.setFont(method.setMyFont());
		btn_2.setFont(method.setMyFont());
		radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.LINE_AXIS));
		radioPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		method.setMyLabel(radioPanel, "PDF Type", false);
		radioPanel.add(Box.createRigidArea(new Dimension(65, 0)));
		radioPanel.add(btn_1);
		radioPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		radioPanel.add(btn_2);

		destPanel.setLayout(new BoxLayout(destPanel, BoxLayout.LINE_AXIS));
		destPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		method.setMyLabel(destPanel, "Destination Path", false);
		destPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		method.setMyTextField(destPanel, pathText, null, false);
		destPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		method.setMyButton(destPanel, browseBtn);

		method.setMyButton(actionPanel, submitBtn);
		method.setMyButton(actionPanel, resetBtn);
		browseBtn.addActionListener(this);
		submitBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		operatePanel.add(radioPanel, BorderLayout.NORTH);
		operatePanel.add(destPanel, BorderLayout.CENTER);
		operatePanel.add(actionPanel, BorderLayout.SOUTH);

		// south Panel
		progressText = new JTextField(10);
		completeText = new JTextField(10);
		method.setMyLabel(infoPanel, "Progressing dept.", false);
		method.setMyTextField(infoPanel, progressText, "N/A", true);
		method.setMyLabel(infoPanel, "Completed", false);
		method.setMyTextField(infoPanel, completeText, Integer.toString(0), true);
		SpringUtilities.makeCompactGrid(infoPanel, 2, 2, // rows, cols
				10, 10, // locateX, locateY
				20, 2); // componentX, componentY

		add(listPanel, BorderLayout.NORTH);
		add(operatePanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.SOUTH);

		run = new Runnable() {
			public void run() {
				// do something...
			}
		};
	}

	private void findAllDeptName() {
		departmentService = (DepartmentService) context
				.getBean("departmentService");
		deptNameList = departmentService.findAllDept("id", true);
		total = deptNameList.size();
		deptModel = new DefaultListModel();
		selectedModel = new DefaultListModel();
		addObjToDeptModel();
	}

	private void setMyJList(JList jList, String msg, JPanel panel) {
		JScrollPane scrollPanel = new JScrollPane(jList);
		scrollPanel.setBorder(new TitledBorder(msg));
		scrollPanel.setPreferredSize(new Dimension(320, 280));
		((JLabel) jList.getCellRenderer()).setOpaque(false);
		jList.setOpaque(false);
		jList.setFont(method.setMyFont());
		panel.add(scrollPanel);
	}

	private void setMyButton(JPanel panel, JButton btn) {
		btn.setFont(method.setMyFont());
		btn.addActionListener(this);
		panel.add(btn);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gtBtn) {
			int index = deptList.getSelectedIndex();
			if (index > -1) {
				selectedModel.addElement(deptList.getSelectedValue());
				deptModel.remove(index);
				updateTextField();
			}
		}

		else if (e.getSource() == ltBtn) {
			int index = selectedList.getSelectedIndex();
			if (index > -1) {
				deptModel.addElement(selectedList.getSelectedValue());
				selectedModel.remove(index);
				updateTextField();
			}
		}

		else if (e.getSource() == dgtBtn) {
			deptModel.removeAllElements();
			selectedModel.removeAllElements();
			for (int i = 0; i < total; i++)
				selectedModel.add(i, deptNameList.get(i).getName());
			updateTextField();
		}

		else if (e.getSource() == dltBtn) {
			addObjToDeptModel();
			updateTextField();
		}

		else if (e.getSource() == browseBtn) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int status = fileChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				pathText.setText(selectedFile.toString());
			}
		}

		else if (e.getSource() == submitBtn) {
			if (thread == null) {
				thread = new GoThread();
				thread.start();
			}
			thread = null;
		}

		else {
			deptModel.removeAllElements();
			addObjToDeptModel();
			pathText.setText(null);
			completeText.setText(Integer.toString(0));
			progressText.setText("N/A");
		}
	}

	private void addObjToDeptModel() {
		selectedModel.removeAllElements();
		deptModel.removeAllElements();
		for (int i = 0; i < total; i++)
			deptModel.add(i, deptNameList.get(i).getName());
	}

	private void updateTextField() {
		countOfSelected = selectedModel.getSize();
		completeText.setText("0 / " + countOfSelected);
	}

	private void go() {
		String filePath = pathText.getText();
		Enumeration<?> deptEnum = selectedModel.elements();
		List<String> deptNameList = new ArrayList<String>();
		while (deptEnum.hasMoreElements())
			deptNameList.add((String) deptEnum.nextElement());
		List<Department> deptList = departmentService
				.findByDeptNameList(deptNameList);

		// SMS PDF
		if (btn_1.isSelected()) {
			method.showMsg("Inventory PDF is generating...");
			SMSPdf smsPDF = new SMSPdf();
			smsPDF.create(context, deptList, filePath);
			method.showMsg("\nGenerate completely !!!\n");
		}

		// Consolidate PDF
		if (btn_2.isSelected()) {
			method.showMsg("\nConsolidation PDF is generating...");
			GaNoPdf gaNoPDF = new GaNoPdf();
			gaNoPDF.create(context, deptList, filePath);
			method.showMsg("\nGenerate completely !!!");
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