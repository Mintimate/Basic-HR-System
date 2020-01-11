package com.Mintimate.tool.ImportExcel;


import java.util.Arrays;
import java.util.List;


public class Result {
    private int row;//行数
    private int col;//列数
    private String[][] t_array;//二维数组
    private String[] o_array;//一维数组
    private List<?> list;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String[][] getT_array() {
        return t_array;
    }

    public void setT_array(String[][] t_array) {
        this.t_array = t_array;
    }

    public String[] getO_array() {
        return o_array;
    }

    public void setO_array(String[] o_array) {
        this.o_array = o_array;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Result{" +
                "row=" + row +
                ", col=" + col +
                ", t_array=" + Arrays.toString(t_array) +
                ", o_array=" + Arrays.toString(o_array) +
                ", list=" + list +
                '}';
    }

    public Result(int row, int col, String[][] t_array) {
        this.row = row;
        this.col = col;
        this.t_array = t_array;
    }

    public Result(int row, int col, String[] o_array) {
        this.row = row;
        this.col = col;
        this.o_array = o_array;
    }

    public Result(int row, String[] o_array) {
        this.row = row;
        this.o_array = o_array;
    }

    public Result(int row, List<?> list) {
        this.row = row;
        this.list = list;
    }

    public Result() {
    }
}
