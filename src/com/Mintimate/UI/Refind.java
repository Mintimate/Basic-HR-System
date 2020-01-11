package com.Mintimate.UI;

import com.Mintimate.Database.UserDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Refind {
    public static Log log = LogFactory.getLog(Refind.class);
    private JFrame frame;
    private JTextField textField; //账号
    private JTextField textField2;//密保
    private JTextField textField3;//新密码
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Refind window = new Refind();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
public Refind() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 547, 383);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //人事管理系统
        JLabel label = new JLabel("找回密码(｡ì _ í｡)");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("幼圆", Font.BOLD, 20));
        label.setBounds(135, 33, 250, 31);
        frame.getContentPane().add(label);
        //用户名：
        JLabel label_1 = new JLabel("用户名");
        label_1.setFont(new Font("楷体", Font.PLAIN, 18));
        label_1.setBounds(117, 92, 73, 18);
        frame.getContentPane().add(label_1);
        //密保
        JLabel label_2 = new JLabel("密保");
        label_2.setFont(new Font("楷体", Font.PLAIN, 18));
        label_2.setBounds(127, 140, 54, 18);
        frame.getContentPane().add(label_2);
        //密码
        JLabel label_3 = new JLabel("新密码");
        label_3.setFont(new Font("楷体", Font.PLAIN, 18));
        label_3.setBounds(117, 185, 54, 18);
        frame.getContentPane().add(label_3);

        //输入账号
        textField = new JTextField();
        textField.setBounds(181, 91, 203, 24);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        //输入密保
        textField2 = new JPasswordField();
        textField2.setBounds(181, 138, 202, 24);
        frame.getContentPane().add(textField2);

        //输入密码
        textField3 = new JPasswordField();
        textField3.setBounds(181, 178, 202, 24);
        frame.getContentPane().add(textField3);

        JButton button = new JButton("找回密码");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameString = textField.getText();
                String pinString =textField2.getText();
                String pwdString =textField3.getText();

                if (nameString.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "用户名为空");
                } else if (pinString.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码为空");
                } else {
                    int rs = UserDao.UpUser(nameString,pinString,pwdString);
                    if (rs == 0) {
                        JOptionPane.showMessageDialog(null, "找回密码失败");
                        log.info("用户\""+nameString+"\"找回密码失败");
                    } else {
                        JOptionPane.showMessageDialog(null, "重置密码成功");
                        log.info("用户\""+nameString+"\"重置密码成功");
                    }
                }

            }
        });
        button.setBounds(117, 289, 113, 27);
        frame.add(button);

        JButton button_1 = new JButton("返回");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Refind.this.frame.dispose();
                MainFrame.main(null);
            }
        });
        button_1.setBounds(302, 289, 113, 27);
        frame.getContentPane().add(button_1);


        JLabel bgLabel = new JLabel("");
        bgLabel.setBounds(-50, -10, 597, 483);
        bgLabel.setIcon(new ImageIcon(MainFrame.class.getResource("/image/bg.gif")));
        bgLabel.setOpaque(false);
        frame.getContentPane().add(bgLabel);
    }
}
