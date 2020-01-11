package com.Mintimate.UI;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.Mintimate.Database.EmpDao;
import com.Mintimate.model.Employee;
import com.Mintimate.tool.ExportExcel;

import java.awt.Font;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SQLselect2 {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String[] title = { "empNo", "empName", "empSex", "empEdu", "empAge", "depName", "position", "salary" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SQLselect2 window = new SQLselect2();
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
	public SQLselect2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1008, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("员工资料管理");
		label.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 25));
		label.setBounds(414, 13, 159, 45);
		frame.getContentPane().add(label);

		JButton button = new JButton("添加");
		button.setBounds(253, 74, 92, 27);
//		frame.getContentPane().add(button);

		JButton button_2 = new JButton("删除");
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
							return;
						}
					} else {
						JOptionPane.showMessageDialog(null, "没有编号为" + empNo + "的员工!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的员工资料!");
				}
			}
		});
		button_2.setBounds(405, 74, 92, 27);
//		frame.getContentPane().add(button_2);

		JButton button_3 = new JButton("导出");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExportExcel excel = new ExportExcel(table);
				if (excel.getTable() != null) {
					excel.export();
				}
			}
		});
		button_3.setBounds(574, 74, 92, 27);
		frame.getContentPane().add(button_3);

		JButton button_5 = new JButton("退出");
		button_5.setBounds(729, 74, 97, 27);
		button_5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}

		});
		frame.getContentPane().add(button_5);

		JLabel label_1 = new JLabel("员工信息模糊查询：");
		label_1.setBounds(284, 125, 135, 18);
		frame.getContentPane().add(label_1);

		textField = new JTextField();
		textField.setBounds(446, 122, 164, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton button_6 = new JButton("查询");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyStr = textField.getText();
				List<Employee> list = EmpDao.selectAll(keyStr);
				newTable(list);
			}
		});
		button_6.setBounds(643, 121, 92, 27);
		frame.getContentPane().add(button_6);

		JPanel panel = new JPanel();
		panel.setBounds(14, 156, 962, 342);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 934, 316);
		panel.add(scrollPane);

		table = new JTable();
		List<Employee> list = EmpDao.selectAll("");
		newTable(list);
		scrollPane.setViewportView(table);

		JLabel bgLabel = new JLabel("");
		bgLabel.setIcon(new ImageIcon(RegisterFrame.class.getResource("/image/bg2.png")));
		bgLabel.setBounds(0, 0, 1100, 650);
		panel.add(bgLabel);

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
		table.setFont(new Font("楷体", Font.BOLD, 18));
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
			data[i][7] = list.get(i).getSalary() + "元";
		}
		return data;
	}
}
