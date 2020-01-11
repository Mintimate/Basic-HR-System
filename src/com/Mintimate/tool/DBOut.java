package com.Mintimate.tool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;

import static com.Mintimate.tool.Print_Error.getTrace;

public class DBOut {
    public static Log log = LogFactory.getLog(DBOut.class);
    public final static String url = "jdbc:mysql://www.mintimate.ml:3306/intimate?"; // 数据库URL
    public final static String user = "intimate"; // 数据库用户名
    public final static String password = "20000215"; // 数据库密码
    private static String printfdata;


    public static void PrintExcel(String Excel){
        printfdata=Excel;
        try {
            DBOut.printfdata();
            log.info("用户导出成功(全部表格)");
            JOptionPane.showMessageDialog(null, "导出全部数据成功");
        } catch (Exception e) {
            log.error("导入失败"+getTrace(e));
            JOptionPane.showMessageDialog(null, "导出数据失败");
        }

    }
    // 把数据库单张表信息导入到Excel表中

    private static void  printfdata() throws Exception {
        // 架子啊数据库驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection con = (Connection) DriverManager.getConnection(url, user,
                password);
        // 创建Excel表。
        Workbook book = new HSSFWorkbook();
        String Table_Name = printfdata;//表名
        // 在当前Excel创建一个子表
        Sheet sheet = book.createSheet(Table_Name);

        Statement st = (Statement) con.createStatement();
        // 创建sql语句，对team进行查询所有数据
        String sql = "select * from intimate." + Table_Name;
        ResultSet rs = st.executeQuery(sql);
        // 设置表头信息（写入Excel左上角是从(0,0)开始的）
        Row row1 = sheet.createRow(0);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colnum = rsmd.getColumnCount();
        for (int i = 1; i <= colnum; i++) {
            String name = rsmd.getColumnName(i);
            // 单元格
            Cell cell = row1.createCell(i - 1);
            // 写入数据
            cell.setCellValue(name);
        }
        // 设置表格信息
        int idx = 1;
        while (rs.next()) {
            // 行
            Row row = sheet.createRow(idx++);
            for (int i = 1; i <= colnum; i++) {
                String str = rs.getString(i);
                // 单元格
                Cell cell = row.createCell(i - 1);
                // 写入数据
                cell.setCellValue(str);
            }
        }
        // 保存
        File file = new File(printfdata+".xls");
        book.write(new FileOutputStream(file));

    }
}