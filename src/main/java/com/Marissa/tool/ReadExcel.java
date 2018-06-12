package com.Marissa.tool;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {
	
	private FileInputStream fis = null;
	private Workbook workbook = null;
	private Sheet sheet = null;
	
	public ReadExcel(String filePath) {
		try{
			this.fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 通过工作簿获取所有行
	public List<Row> getAllRows() {
		List<Row> rows = new ArrayList<Row>();
		if(sheet != null) {
			for(Row row : sheet) {
				rows.add(row);
			}
		}
		return rows;
	}
		
	// 通过工作簿获取所有行
	public List<Row> getAllRows(Sheet sheet) {
		List<Row> rows = new ArrayList<Row>();
		if(sheet != null) {
			for(Row row : sheet) {
				rows.add(row);
			}
		}
		return rows;
	}
	
	// 获取特定行
	public List<Row> getRow(int[] index) {
		List<Row> rows = new ArrayList<Row>();
		if(sheet != null) {
			for(int i: index) {
				Row row = sheet.getRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
		
	// 获取特定行
	public List<Row> getRow(Sheet sheet, int[] index) {
		List<Row> rows = new ArrayList<Row>();
		if(sheet != null) {
			for(int i: index) {
				Row row = sheet.getRow(i);
				rows.add(row);
			}
		}
		return rows;
	}
	
	//获取每一行的单元格
	public Map<Integer, Object> getCellValue(Row row, int[] index) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		Cell cell = null;
		for(int i : index) {
			cell = row.getCell(i);
			if(cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if(cell.getStringCellValue()!= null) {
						map.put(i, cell.getStringCellValue());
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
				    if (DateUtil.isCellDateFormatted(cell)) {
				    	map.put(i, cell.getDateCellValue());
				    } else {
				    	map.put(i, cell.getDateCellValue());
				    }
				    break;
				case Cell.CELL_TYPE_BOOLEAN:
					if((Object)cell.getBooleanCellValue() != null) {
						map.put(i, cell.getBooleanCellValue());
					}
				    break;
				case Cell.CELL_TYPE_FORMULA:
					if(cell.getCellFormula() != null) {
						map.put(i, cell.getCellFormula());
//				        System.out.print(cell.getCellFormula() + " ");
					}
				    break;
				case Cell.CELL_TYPE_BLANK:
				    map.put(i, " ");
				    break;   
				default:
				    System.out.print("default ");
			}
		}
			
		}
		return map;
	}		
	
	//获取每一行所有的单元格
		public Map<Integer, Object> getAllCellValue(Row row) {
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			int i = 0;
			Object o = null;
			for(Cell cell : row) {
				cell = row.getCell(i);
				if(cell != null) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue()!= null) {
							o = cell.getStringCellValue();
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
					    if (DateUtil.isCellDateFormatted(cell)) {
					    	o = cell.getDateCellValue();
					    } else {
					    	o = cell.getNumericCellValue();
					    }
					    break;
					case Cell.CELL_TYPE_BOOLEAN:
						if((Object)cell.getBooleanCellValue() != null) {
							o = cell.getBooleanCellValue();
						}
					    break;
					case Cell.CELL_TYPE_FORMULA:
						if(cell.getCellFormula() != null) {
							o = cell.getCellFormula();
//					        System.out.print(cell.getCellFormula() + " ");
						}
					    break;
					case Cell.CELL_TYPE_BLANK:
					    o = " ";
					    break;   
					default:
					    System.out.print("default ");
					}
					map.put(i, o);
					System.out.print(o +  " ");
				}
				i++;
				System.out.println("一行读取完毕");
				
			}
			return map;
		}		
	
	public FileInputStream getFis() {
		return fis;
	}
	
	public void setFis(FileInputStream fis) {
		this.fis = fis;
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

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	// 根据名称获取工作簿
	public Sheet getSheet(String sheetName) {
		Sheet sheet = workbook.getSheet(sheetName);
		return sheet;
	}
	
	public Sheet getSheet(int index) {
		Sheet sheet = workbook.getSheetAt(index-1);
		return sheet;
	}
}



	





