package com.Marissa.tool;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class WriteExcel {
	private FileOutputStream fos = null;
	Workbook workbook = new XSSFWorkbook();
	Sheet sheet = null;

	public WriteExcel(String filePath) {
		try {
			//创建一个文件输出流
			this.fos = new FileOutputStream(filePath);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Sheet createSheet(String SheetName) {
		// 创建一个工作簿
		sheet = workbook.createSheet(SheetName);
		return sheet;
	}
	
	public Row createRow(int index) {
		Row row = null;
		// 通过一个工作簿创建一行
		if(sheet != null) {
		row = sheet.createRow(index);
		}
		return row;
	}
	
	public Row createRow(Sheet sheet, int index) {
		Row row = null;
		// 通过一个工作簿创建一行
		if(sheet != null) {
			row = sheet.createRow(index);
		}
		return row;
	}
	
	public List<Row> createRows(int num) {
		List<Row> rows = new ArrayList<Row>();
		Row row = null;
		if(sheet != null) {
			for(int i = 0; i < num; i++) {
				row = sheet.createRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
	
	public List<Row> createRows(Sheet sheet, int num) {
		List<Row> rows = new ArrayList<Row>();
		Row row = null;
		if(sheet != null) {
			for(int i = 0; i < num; i++) {
				row = sheet.createRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
	
	public List<Row> createRows(int[] index) {
		List<Row> rows = new ArrayList<Row>();
		Row row = null;
		if(sheet != null) {
			for(int i : index) {
				row = sheet.createRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
	
	public List<Row> createRows(Sheet sheet, int[] index) {
		List<Row> rows = new ArrayList<Row>();
		Row row = null;
		if(sheet != null) {
			for(int i : index) {
				row = sheet.createRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
	
	public void createCell(Row row, Map<Integer,Object> map) {
		Set<Integer> indexs = map.keySet();
		for(int index : indexs){
			Cell cell = row.createCell(index);
			Object content = map.get(index);
			System.out.print(content+ " ");
			if(content instanceof String) {
				cell.setCellValue((String) content);
			}
			if(content instanceof Integer || content instanceof Long
				|| content instanceof Double || content instanceof Float) {
				cell.setCellValue((Double)content);
			}
			if(content instanceof Date) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); 
					Date d=format.parse(content.toString());
					cell.setCellValue(d);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			if(content instanceof Boolean) {
				cell.setCellValue((Boolean)content);
			}
			if(content instanceof Calendar) {
				cell.setCellValue((Calendar)content);
			}
		}
	}
	
	public void writeFile() {
		try {
			//写出文件
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeStream(){
		try {
			//关闭文件流
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public FileOutputStream getFos() {
		return fos;
	}

	public void setFos(FileOutputStream fos) {
		this.fos = fos;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
	
	public Sheet getSheet() {
		return sheet;
	}

	public Sheet getSheet(int index) {
		return workbook.getSheetAt(index-1);
	}
	
	public Sheet getSheet(String SheetName) {
		return workbook.getSheet(SheetName);
	}
	
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
}
