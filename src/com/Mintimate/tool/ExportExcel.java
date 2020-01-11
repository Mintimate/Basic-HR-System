package com.Mintimate.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableModel;

import com.Mintimate.UI.MainFrame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import static com.Mintimate.tool.Print_Error.getTrace;

public class ExportExcel {
	public static Log log = LogFactory.getLog(ExportExcel.class);
	JTable table;
	FileOutputStream fos;
	File file;
	JFileChooser jfc = new JFileChooser();

	public ExportExcel(JTable table) {
		if (table.getRowCount() == 0) {
			JOptionPane.showMessageDialog(table, "表格内容不能为空");
			return;

		}
		this.table = table;
		jfc.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {

				return "Excel";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().indexOf("xls") != -1;
			}
		});

		jfc.showSaveDialog(null);
		file = jfc.getSelectedFile();

		try {
			this.fos = new FileOutputStream(file + ".xls");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public JTable getTable() {
		return table;
	}

	public void export() {
		// 判断有没有选择文件
		if (file == null) {
			JOptionPane.showMessageDialog(null, "没有选择要导出的文件路径");
			return;
		}
		// 判断文件有没有被别的程序占用
//		if (!file.renameTo(file)) {
//			JOptionPane.showMessageDialog(null, "该表单号文件正在被别的程序占用，请先关闭该文件解除占用");
//			return;
//		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet hs = wb.createSheet();
		TableModel tm = table.getModel();
		int row = table.getRowCount();
		int column = table.getColumnCount();
		// System.out.println("行："+row+" 列："+column);
		HSSFCellStyle style = wb.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		style.setFont(font);

		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setFillForegroundColor(HSSFColor.ORANGE.index);
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 15);
		font1.setBoldweight((short) 700);
		style1.setFont(font);

		for (int i = 0; i < row + 1; i++) {
			HSSFRow hr = hs.createRow(i);
			for (int j = 0; j < column; j++) {
				if (i == 0) {
					String value = tm.getColumnName(j);
					// System.out.println("value " + value);
					int len = value.length();
					hs.setColumnWidth(j, len * 800);
					HSSFRichTextString strs = new HSSFRichTextString(value);
					HSSFCell hc = hr.createCell(j);
					// hc.setEncoding((short) 1);
					hc.setCellStyle(style1);
					hc.setCellValue(strs);
				} else {
					// System.out.println("vlue " + tm.getValueAt(i - 1, j));
					if (tm.getValueAt(i - 1, j) != null) {
						String value = tm.getValueAt(i - 1, j).toString();
						HSSFRichTextString strs = new HSSFRichTextString(value);
						HSSFCell hc = hr.createCell(j);
						hc.setCellStyle(style);
						if (value.equals("") || value == null) {
							hc.setCellValue(new HSSFRichTextString(""));
						} else {
							hc.setCellValue(strs);
						}
					}
				}
			}
		}
		try {
			wb.write(fos);
			log.info("用户导出成功"+"导出路径："+file);
			JOptionPane.showMessageDialog(null, "导出成功");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "导出失败");
			log.error(getTrace(e));
			// 阻止下面的代码执行
			throw new RuntimeException(e);

		} finally {
			try {
				if (fos != null) {
					fos.close();
					log.info("资源关闭成功。。。");
//					System.out.println("资源关闭成功。。。");
				}
			} catch (IOException e) {
				log.info("资源关闭失败。。。");
//				System.out.println("资源关闭失败。。。");
				throw new RuntimeException(e);
			}
		}
	}
	
}