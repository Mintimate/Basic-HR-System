package com.Mintimate.tool.ImportExcel;

import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Read_Excel {
    public Result readExcel(File e_fileName, Integer sheet_Num){
        /**
         * @param: [e_fileName,文件 sheet_Num 文件页数]
         * @return: java.lang.String[][]
         * @auther: Htc
         * @date: 2019/2/17 21:33
         * @Description:将excel数据读为二元数组
         */
        Workbook workbook = null;
        String[][] excelList=null;
        try {
            //创建Workbook
            workbook=Workbook.getWorkbook(e_fileName);
            //获取第一个sheet
            Sheet sheet=workbook.getSheet(sheet_Num);
            excelList=new String[sheet.getColumns()][sheet.getRows()];
            //获取数据
            for(int i=0;i<sheet.getColumns();i++){
                for(int j=0;j<sheet.getRows();j++){
                    Cell cell=sheet.getCell(i,j);
                    if(j<1){
                        excelList[i][j]=cell.getContents();
                    }else{
                        excelList[i][j]="'"+cell.getContents()+"'";
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }finally {
            workbook.close();
        }
        //封装
        Result result=new Result(excelList.length,excelList[0].length,excelMode(excelList));

        return result;
    }
    public String[] excelMode(String[][] excelData){
        /**
         * @param: [excelData]
         * @return: com.plug.excel_MySql.Result
         * @auther: Htc
         * @date: 2019/2/19 9:24
         * @Description:将元数据整理成sql可以使用的数据，第一行是数据项，其他的是数据
         */
        String[] list=new String[excelData[0].length];
        String var="";

        //获得数据项
        for(int i=0;i<excelData[0].length;i++){
            for(int j=0;j<excelData.length;j++){
                var+=excelData[j][i]+",";
            }
            var=var.substring(0,var.length()-1);
            list[i]=var;
            var="";
        }
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }
        return list;
    }
}
