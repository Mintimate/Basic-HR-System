package com.Mintimate.tool.ImportExcel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.Mintimate.tool.Print_Error.getTrace;

public class InExcel {
    public static Log log = LogFactory.getLog(InExcel.class);
    //   public FXMLTest application;
        public static boolean insertSql () {
            int num = 0;
            JFileChooser jfc = new JFileChooser();
            Read_Excel read_excel = new Read_Excel();
            Read_Mysql read_mysql = new Read_Mysql();
            String sql = "truncate table emp";//清空表
            Connection conn = null;
            try {
                //加载MySQL JDBC 驱动程序名称
                Class.forName("com.mysql.cj.jdbc.Driver");
                //数据库连接参数。
                String serverName = "www.mintimate.cn"; // 数据库主机名称
                String mydatabase = "intimate";   // 数据库名称
                String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?characterEncoding=utf-8" + "&useSSL=true&serverTimezone=GMT";
                String username = "intimate";    //MySql用户名
                String password = "20000215";  //MySql密码
                conn = DriverManager.getConnection(url, username, password);//建立连接
                Statement stmt = conn.createStatement();//创建Statement对象
                stmt.executeUpdate(sql);//执行sql语句
            } catch (ClassNotFoundException e) {
                e.printStackTrace();//找不到MySql驱动程序类时，打印异常
            } catch (SQLException e) {
                e.printStackTrace();//获得数据库连接发生异常
            }
            //读出文件
            File file = new File("/Users/mintimate/1.xls");
            jfc.showOpenDialog(null);
            file=jfc.getSelectedFile();
            Result result = read_excel.readExcel(file, 0);
            //插入数据
            try {
                num = read_mysql.insertExcel(result, "emp", conn);
            } catch (java.lang.NullPointerException e) {
//            application.popUp("");
                log.error("插入失败"+getTrace(e));
                return false;
            }
            if (num > 0) {
                log.info("用户执行导入操作成功");
                JOptionPane.showMessageDialog(null, "导出成功");

            } else {
                log.error("用户执行导入操作失败");
                JOptionPane.showMessageDialog(null, "导出失败，请核对文件");

            }
            return true;
        }

    }
