package com.Mintimate.UI;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Mintimate.Database.EmpDao;
import com.Mintimate.model.Employee;
import com.Mintimate.tool.DBOut;
import com.Mintimate.tool.ExportExcel;
import com.Mintimate.tool.ImportExcel.InExcel;

import java.awt.Font;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;

public class SQLselect {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String[] title = { "empNo", "empName", "empSex", "empEdu", "empAge", "depName", "position", "salary" };
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private Employee employee = new Employee();
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQLselect window = new SQLselect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SQLselect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1080, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1080, 580);
		frame.getContentPane().add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("查询", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);

		JButton button_in = new JButton("导入");
		panel_2.add(button_in);
		button_in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InExcel.insertSql();
			}
		});

		//导出
		JButton button_3 = new JButton("导出本页");
		panel_2.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportExcel excel = new ExportExcel(table);
				if (excel.getTable() != null) {
					excel.export();
					String keyStr = textField.getText();
					List<Employee> list = EmpDao.selectAll(keyStr);
					newTable(list);
				}
			}
		});

		JButton button_3ALL = new JButton("导出全表");
		panel_2.add(button_3ALL);
		button_3ALL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBOut.PrintExcel("emp");
			}
		});


		JButton button_2 = new JButton("删除");
		panel_2.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int iRow = table.getSelectedRow();

				if (iRow != -1) { // 如果有选中
					String empNo = (String) table.getValueAt(iRow, 0);
					String empName = (String) table.getValueAt(iRow, 1);
					if (empNo != null) { // 选中行不为空
						int result = JOptionPane.showConfirmDialog(null, "确定删除该条信息?", "提示", JOptionPane.YES_NO_OPTION);
						if (result == 0) {
							EmpDao.deleteEmp(empNo);
							JOptionPane.showMessageDialog(null, "已成功删除员工" + empName + "的信息!");
							String keyStr = textField.getText();
							List<Employee> list = EmpDao.selectAll(keyStr);
							newTable(list);
						}
					} else {
						JOptionPane.showMessageDialog(null, "没有编号为" + empNo + "的员工!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的员工资料!");
				}
			}
		});

		JButton button_5 = new JButton("退出");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "确定退出系统?", "提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					SQLselect.this.frame.dispose();
				}
			}
		});
		panel_2.add(button_5);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);

//		JLabel label_1 = new JLabel("员工信息模糊查询：");
//		label_1.setBounds(246, 41, 135, 18);
//		panel_3.add(label_1);

		textField = new JTextField();
		textField.setBounds(395, 38, 146, 24);
		panel_3.add(textField);
		textField.setColumns(10);

		JButton button_6 = new JButton("关键字查询");
		button_6.setBounds(577, 37, 150, 27);
		panel_3.add(button_6);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 81, 900, 383);
		panel_3.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyStr = textField.getText();
				List<Employee> list = EmpDao.selectAll(keyStr);
				newTable(list);
			}
		});
		List<Employee> list = EmpDao.selectAll("");
		newTable(list);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				table.setSelectionBackground(Color.yellow);
				int iRow = table.getSelectedRow();
				if (iRow != -1) {

					String empNo = (String) table.getValueAt(iRow, 0);
					String empName = (String) table.getValueAt(iRow, 1);
					String empSex = (String) table.getValueAt(iRow, 2);
					String empEdu = (String) table.getValueAt(iRow, 3);
					String empAge = (String) table.getValueAt(iRow, 4);
					String depName = (String) table.getValueAt(iRow, 5);
					String position = (String) table.getValueAt(iRow, 6);
					String salary = ((String) table.getValueAt(iRow, 7));

					employee.setEmpNo(empNo);
					employee.setEmpName(empName);
					employee.setEmpSex(empSex);
					employee.setEmpEdu(empEdu);
					employee.setEmpAge(empAge);
					employee.setDepName(depName);
					employee.setPosition(position);
					employee.setSalary(salary);
					textField_1.setText(empNo);
					textField_2.setText(empName);
					textField_3.setText(empSex);
					textField_4.setText(empEdu);
					textField_5.setText(empAge);
					textField_6.setText(depName);
					textField_7.setText(position);
					textField_8.setText(salary);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JPanel panel = new JPanel();
		tabbedPane.addTab("修改", null, panel, null);
		panel.setLayout(null);

		JLabel lblEmpno = new JLabel("empNo:");
		lblEmpno.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblEmpno.setBounds(41, 60, 99, 34);
		panel.add(lblEmpno);

		JLabel lblEmpname = new JLabel("empName:");
		lblEmpname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblEmpname.setBounds(364, 60, 99, 34);
		panel.add(lblEmpname);

		JLabel lblEmpedu = new JLabel("empEdu:");
		lblEmpedu.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblEmpedu.setBounds(364, 154, 99, 34);
		panel.add(lblEmpedu);

		JLabel lblEmpsex = new JLabel("empSex:");
		lblEmpsex.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblEmpsex.setBounds(41, 154, 99, 34);
		panel.add(lblEmpsex);

		JLabel lblEmpage = new JLabel("empAge:");
		lblEmpage.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblEmpage.setBounds(41, 238, 99, 34);
		panel.add(lblEmpage);

		JLabel lblDeptname = new JLabel("depName:");
		lblDeptname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblDeptname.setBounds(364, 238, 99, 34);
		panel.add(lblDeptname);

		JLabel lblPosition = new JLabel("position:");
		lblPosition.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblPosition.setBounds(41, 331, 99, 34);
		panel.add(lblPosition);

		JLabel lblSalary = new JLabel("salary:");
		lblSalary.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblSalary.setBounds(364, 331, 99, 34);
		panel.add(lblSalary);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(120, 60, 160, 34);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(447, 60, 160, 34);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(120, 154, 160, 34);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(447, 154, 160, 34);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(120, 238, 160, 34);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(447, 238, 160, 34);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(120, 331, 160, 34);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(447, 331, 160, 34);
		panel.add(textField_8);

		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "确定修改该条信息?", "提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					employee.setEmpName(textField_2.getText());
					employee.setEmpSex(textField_3.getText());
					employee.setEmpEdu(textField_4.getText());
					employee.setEmpAge(textField_5.getText());
					employee.setDepName(textField_6.getText());
					employee.setPosition(textField_7.getText());
					employee.setSalary(textField_8.getText());
					EmpDao.updateEmp(employee);
					JOptionPane.showMessageDialog(null, "修改成功！");
					String keyStr = textField.getText();
					List<Employee> list = EmpDao.selectAll(keyStr);
					newTable(list);
				}

			}
		});
		btnNewButton.setBounds(226, 469, 113, 43);
		panel.add(btnNewButton);

		JButton button_1 = new JButton("重置");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
			}
		});
		button_1.setBounds(413, 469, 113, 43);
		panel.add(button_1);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		tabbedPane.addTab("添加", null, panel_4, null);

		JLabel label = new JLabel("empNo:");
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label.setBounds(41, 60, 99, 34);
		panel_4.add(label);

		JLabel label_2 = new JLabel("empName:");
		label_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_2.setBounds(364, 60, 99, 34);
		panel_4.add(label_2);

		JLabel label_3 = new JLabel("empEdu:");
		label_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_3.setBounds(364, 154, 99, 34);
		panel_4.add(label_3);

		JLabel label_4 = new JLabel("empSex:");
		label_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_4.setBounds(41, 154, 99, 34);
		panel_4.add(label_4);

		JLabel label_5 = new JLabel("empAge:");
		label_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_5.setBounds(41, 238, 99, 34);
		panel_4.add(label_5);

		JLabel label_6 = new JLabel("depName:");
		label_6.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_6.setBounds(364, 238, 99, 34);
		panel_4.add(label_6);

		JLabel label_7 = new JLabel("position:");
		label_7.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_7.setBounds(41, 331, 99, 34);
		panel_4.add(label_7);

		JLabel label_8 = new JLabel("salary:");
		label_8.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		label_8.setBounds(364, 331, 99, 34);
		panel_4.add(label_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(120, 60, 160, 34);
		panel_4.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(447, 60, 160, 34);
		panel_4.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(120, 154, 160, 34);
		panel_4.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(447, 154, 160, 34);
		panel_4.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(120, 238, 160, 34);
		panel_4.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(447, 238, 160, 34);
		panel_4.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(120, 331, 160, 34);
		panel_4.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(447, 331, 160, 34);
		panel_4.add(textField_16);

		JButton button_4 = new JButton("添加");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee.setEmpNo(textField_9.getText());
				employee.setEmpName(textField_10.getText());
				employee.setEmpSex(textField_11.getText());
				employee.setEmpEdu(textField_12.getText());
				employee.setEmpAge(textField_13.getText());
				employee.setDepName(textField_14.getText());
				employee.setPosition(textField_15.getText());
				employee.setSalary(textField_16.getText());
				EmpDao.addEmp(employee);
				JOptionPane.showMessageDialog(null, "添加成功！");
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");
				textField_14.setText("");
				textField_15.setText("");
				textField_16.setText("");
				String keyStr = textField.getText();
				List<Employee> list = EmpDao.selectAll(keyStr);
				newTable(list);
			}
		});
		button_4.setBounds(226, 469, 113, 43);
		panel_4.add(button_4);

		JButton button_7 = new JButton("重置");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_9.setText("");
				textField_10.setText("");
				textField_11.setText("");
				textField_12.setText("");
				textField_13.setText("");
				textField_14.setText("");
				textField_15.setText("");
				textField_16.setText("");
			}
		});
		button_7.setBounds(413, 469, 113, 43);
		panel_4.add(button_7);

		JLabel bgLabel = new JLabel("");
		bgLabel.setIcon(new ImageIcon(RegisterFrame.class.getResource("/image/bg2.png")));
		bgLabel.setBounds(0, 0, 1100, 650);
		panel_3.add(bgLabel);
	}

	private void newTable(List<Employee> list) {
		table.setModel(new DefaultTableModel(getSelect(list), title) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			// 设置表格不可编辑
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			};
		});
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.getColumnModel().getColumn(0).setPreferredWidth(119);
//		table.getColumnModel().getColumn(1).setPreferredWidth(120);
//		table.getColumnModel().getColumn(2).setPreferredWidth(80);
//		table.getColumnModel().getColumn(3).setPreferredWidth(79);
//		table.getColumnModel().getColumn(4).setPreferredWidth(140);
//		table.getColumnModel().getColumn(5).setPreferredWidth(107);
		table.setFont(new Font("宋体", Font.PLAIN, 15));
		// scrollPane.setViewportView(jTable);
	}

	private Object[][] getSelect(List<Employee> list) {
		Object[][] data = new Object[list.size()][8];
		for (int i = 0; i < data.length; i++) {
			data[i][0] = list.get(i).getEmpNo();
			data[i][1] = list.get(i).getEmpName();
			data[i][2] = list.get(i).getEmpSex();
			data[i][3] = list.get(i).getEmpEdu();
			data[i][4] = list.get(i).getEmpAge();
			data[i][5] = list.get(i).getDepName();
			data[i][6] = list.get(i).getPosition();
			data[i][7] = list.get(i).getSalary();
		}
		return data;
	}
}
