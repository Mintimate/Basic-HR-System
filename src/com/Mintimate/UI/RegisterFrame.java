package com.Mintimate.UI;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.Font;

import com.Mintimate.Database.UserDao;
import com.Mintimate.tool.Md5;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RegisterFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField2;
	static int Au=0; //用户权限
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame window = new RegisterFrame();
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
	public RegisterFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 547, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//人事管理系统
		JLabel label = new JLabel("\u4EBA\u4E8B\u7BA1\u7406\u7CFB\u7EDF");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("幼圆", Font.BOLD, 20));
		label.setBounds(185, 33, 144, 31);
		frame.getContentPane().add(label);
		//用户名：
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setFont(new Font("楷体", Font.PLAIN, 18));
		label_1.setBounds(98, 92, 73, 18);
		frame.getContentPane().add(label_1);
		//密码
		JLabel label_2 = new JLabel("\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("楷体", Font.PLAIN, 18));
		label_2.setBounds(117, 140, 54, 18);
		frame.getContentPane().add(label_2);
		//确定密码
		JLabel label_3 = new JLabel("确定密码");
		label_3.setFont(new Font("楷体", Font.PLAIN, 18));
		label_3.setBounds(81, 183, 90, 18);
		frame.getContentPane().add(label_3);

		//密保密匙
		JLabel label_Pin = new JLabel("密保密匙");
		label_Pin.setFont(new Font("楷体", Font.PLAIN, 18));
		label_Pin.setBounds(81, 220, 90, 18);
		frame.getContentPane().add(label_Pin);

		//用户权限
		JLabel label_Au= new JLabel("用户权限");
		label_Au.setFont(new Font("楷体", Font.PLAIN, 18));
		label_Au.setBounds(81, 257, 90, 18);
		frame.getContentPane().add(label_Au);

		textField = new JTextField();
		textField.setBounds(181, 91, 203, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(181, 138, 202, 24);
		frame.getContentPane().add(passwordField_1);

		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(181, 181, 202, 24);
		frame.getContentPane().add(passwordField_2);

		textField2 = new JTextField();
		textField2.setBounds(181, 220, 203, 24);
		frame.getContentPane().add(textField2);
		textField2.setColumns(10);
		frame.getContentPane().add(textField2);

		JComboBox Authority =null;
		Authority=new JComboBox();
		Authority.addItem("普通用户");
		Authority.addItem("管理员");
		String select=null;
		Authority.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
					case ItemEvent.SELECTED:
						if(String.valueOf(event.getItem()).equals("管理员")) {
							System.out.println(String.valueOf(event.getItem()).equals("管理员"));
							Au = 1;
						}
						else
							Au=0;
						break;
					case ItemEvent.DESELECTED:
						System.out.println("取消选中" + event.getItem());
						break;
				}

			}
		});
		Authority.setOpaque(false);
		Authority.setBounds(181, 257, 203, 24);
		frame.add(Authority);


		// 注册
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = textField.getText();
				String pwdString1 = new String(passwordField_1.getPassword());
				String pwdString2 = new String(passwordField_2.getPassword());
				String pinString =textField2.getText();
//				if(nameString==null) {
//					System.out.println("输入为空");
//				}
				if (nameString.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名为空");
				} else if (pwdString1.equals("") || pwdString2.equals("")) {
					JOptionPane.showMessageDialog(null, "密码为空");
				} else if (!pwdString1.equals(pwdString2)) {
					JOptionPane.showMessageDialog(null, "密码不一致");
				} else {
					int rs = UserDao.addUser(nameString, Md5.Md5(pwdString1), Au,pinString);
					if (rs == 1) {
						JOptionPane.showMessageDialog(null, "注册成功");
					} else {
						JOptionPane.showMessageDialog(null, "注册失败");
					}
				}

			}
		});

		button.setBounds(117, 309, 113, 27);
		frame.getContentPane().add(button);

		// 取消
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame.this.frame.dispose();
				MainFrame.main(null);
			}
		});
		button_1.setBounds(302, 309, 113, 27);
		frame.getContentPane().add(button_1);

		JLabel bgLabel = new JLabel("");
		bgLabel.setIcon(new ImageIcon(RegisterFrame.class.getResource("/image/bg.jpg")));
		bgLabel.setBounds(0, 0, 547, 383);
		frame.getContentPane().add(bgLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(463, 217, 6, 24);
		frame.getContentPane().add(passwordField);
	}
}
