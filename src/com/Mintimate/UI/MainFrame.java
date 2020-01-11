package com.Mintimate.UI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;

import com.Mintimate.Database.UserDao;
import com.Mintimate.model.User;
import com.Mintimate.tool.Md5;
import com.Mintimate.tool.Check_Image;
import com.Mintimate.tool.browser;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.Mintimate.tool.Print_Error.getTrace;

public class MainFrame {

	public static Log log = LogFactory.getLog(MainFrame.class);

	private JFrame frame;
	private JTextField textField; // 用户名
	private JTextField textField_m;//密保
	private JPasswordField passwordField;
	private JTextField textField_1;
	private String rString;
	private BufferedImage image;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(getTrace(e));
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
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

		// 验证码

		//标题：人事管理系统
		ImageIcon login=null;
		JLabel label = new JLabel("\u4EBA\u4E8B\u7BA1\u7406\u7CFB\u7EDF");
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/image/login.png")));
		label.setBounds(201, 24, 250, 70);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("幼圆", Font.BOLD, 20));
		frame.getContentPane().add(label);
		//用户名：
		JLabel label_1 = new JLabel(new ImageIcon(MainFrame.class.getResource("/image/用户.png")));
		label_1.setBounds(130, 93, 95, 32);
		label_1.setFont(new Font("幼圆", Font.BOLD, 18));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label_1);
		//密码图标：
		JLabel label_2 = new JLabel(new ImageIcon(MainFrame.class.getResource("/image/密码.png")));
		label_2.setBounds(130, 157, 95, 31);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(label_2);

		//验证码
		GenerateRandom();
		textField = new JTextField();
		textField.setBounds(205, 93, 155, 31);
		textField.setFont(new Font("幼圆", Font.PLAIN, 18));
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		// 登录
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setBounds(80, 247, 105, 40);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameString = textField.getText();
				String pwdString = new String (passwordField.getPassword());
				String YZ = textField_1.getText();
//				String st = new String(YZM.st);
				User user = new User(nameString, pwdString);
				User user2 = UserDao.selectUser(user);
				if (nameString.equals("")) {
					JOptionPane.showMessageDialog(null, "用户名为空");
				} else if (pwdString.equals("")) {
					JOptionPane.showMessageDialog(null, "密码为空");
				} else if (YZ.equals("")) {
					JOptionPane.showMessageDialog(null, "验证码为空");
				}
				// 判断验证码是否正确
				else if (!YZ.equalsIgnoreCase(rString)) {//忽略大小写
					JOptionPane.showMessageDialog(null, "验证码错误");
					GenerateRandom();
					MainFrame.this.lblNewLabel.setIcon(new ImageIcon(image));
					MainFrame.this.textField_1.setText("");
				} else {
					// 用户是否存在
					if (user2 != null) {
						// 密码
						if (!Md5.Md5(user.getPwd()).equals(user2.getPwd())) {
							JOptionPane.showMessageDialog(null, "密码错误");
							log.info("001-用户\'"+user2.getName()+"\'：密码错误");
						} else {
							MainFrame.this.frame.dispose();
							log.info(user2.getName()+"登陆成功");
							if (user2.getLimit() == 0) { // 普通用户
								JOptionPane.showMessageDialog(null, "普通用户:" + user2.getName() +"登录成功");
								SQLselect2.main(null);


							} else { // 管理员
								JOptionPane.showMessageDialog(null, "管理员:" + user2.getName() +"登录成功");
								SQLselect.main(null);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户不存在");
					}
				}

			}
		});
		btnNewButton.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(btnNewButton);

		//找回密码
		JButton FindButton = new JButton("找回密码");
		FindButton.setBounds(80, 297, 105, 40);
		FindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.error("用户尝试进行找回密码");
				MainFrame.this.frame.setVisible(false);
				Refind.main(null);
			}
		});
		FindButton.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(FindButton);

		//帮助
		JButton button_help = new JButton("帮助");
		button_help.setBounds(218, 297, 105, 40);
		button_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("访问帮助页面");
				browser.show("https://www.mintimate.cn/2020/01/03/java%E9%A1%B9%E7%9B%AE/");
			}
		});
		button_help.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(button_help);

		// 注册
		JButton button = new JButton("\u6CE8\u518C");
		button.setBounds(218, 247, 105, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.frame.setVisible(false);
				RegisterFrame.main(null);
			}
		});
		button.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(button);

		// 取消
		JButton button_1 = new JButton("退出");
		button_1.setBounds(348, 247, 105, 40);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.frame.dispose();
				log.info("用户正常退出");
				frame.dispose();
			}
		});
		button_1.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(button_1);


		//验证码图标
		JLabel label_3 = new JLabel(new ImageIcon(MainFrame.class.getResource("/image/验证码.png")));
		label_3.setBounds(130, 201, 95, 31);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("幼圆", Font.BOLD, 18));
		frame.getContentPane().add(label_3);

		passwordField = new JPasswordField();
		passwordField.setBounds(205, 157, 155, 32);
		frame.getContentPane().add(passwordField);

		textField_1 = new JTextField();
		textField_1.setBounds(203, 205, 86, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GenerateRandom();
				lblNewLabel.setIcon(new ImageIcon(image));
			}

		});
		lblNewLabel.setBounds(333, 192, 80, 50);
		lblNewLabel.setIcon(new ImageIcon(image));

		frame.getContentPane().add(lblNewLabel);

		JLabel bgLabel = new JLabel("");
		bgLabel.setBounds(0, 0, 547, 383);
		//背景图加载
		bgLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/image/bg0.gif")));
//		设置图片为透明
		bgLabel.setOpaque(false);
		//加入容器
		frame.getContentPane().add(bgLabel);

	}

	private void GenerateRandom() {
		Object[] objs = Check_Image.createImage();
		rString = (String) objs[0];
		image = (BufferedImage) objs[1];
	}
}
