package com.Mintimate.tool.ImportExcel;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Read_Mysql {
    public int insertExcel(Result dataSource,String tableName,Connection con){
        int num=0;
        String[] source=dataSource.getO_array();
        //字段拼接做插入，需要表名（后期传入），表字段，表数据

        String sql="insert into "+tableName+"("+source[0]+") values";
        for(int i=1;i<source.length;i++){
            sql+="("+source[i]+"),";
        }
        //将最后的一个逗号处理成分号
        sql=sql.substring(0,sql.length()-1)+";";
        System.out.println(sql);
        //进行数据插入
        Statement statement=null;
        try {
            statement =con.createStatement();
            num=(int) statement.executeLargeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                statement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return num;
    }

}
