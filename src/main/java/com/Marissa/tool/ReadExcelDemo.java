package com.Marissa.tool;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ʹ��poi��ȡexcel
 * @author MarissaMan
 *
 */
public class ReadExcelDemo {
	public static void main(String[] args) {
		List<Object[]> li = new ArrayList<Object[]>();
		li  = test();

		for(Object[] o : li) {
			for(int i = 0; i < o.length; i++) {
				System.out.print("��" + i + "��Ԫ��" + o[i] + " ");
			}
			System.out.println();
		}
	}
	
	public static List<Object[]> test() {
		List<Object[]> li = new ArrayList<Object[]>();
		try {
			//����һ���ĵ�����
			FileInputStream fis = new FileInputStream("C:/Users/Administrator/Desktop/�����.xlsx");
			Workbook book = new XSSFWorkbook(fis);
			//��ȡ������
			Sheet sheet = book.getSheet("test");
			//ͨ����������ȡ������
			Iterator<Row> rows = sheet.iterator();
			while(rows.hasNext()) {
				Row row = rows.next();
				int len = (int)row.getLastCellNum();
				Object[] o = new Object[len];
				int i = 0;
				//��ȡÿһ�еĵ�Ԫ��
				Iterator<Cell> cells = row.iterator();
				while(cells.hasNext()){
					Cell cell = cells.next();
					switch (cell.getCellType()) {
	                	case Cell.CELL_TYPE_STRING:
	                		if(cell.getStringCellValue()!= null) {
	                			System.out.print(cell.getStringCellValue() + " ");
	                			o[i] = cell.getStringCellValue();
	                		}
	                		break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                    	o[i] = cell.getDateCellValue();
		                        System.out.print(cell.getDateCellValue() + " ");
		                    } else {
		                    	o[i] = cell.getNumericCellValue();
		                        System.out.print(cell.getNumericCellValue() + " ");
		                    }
		                    break;
		                case Cell.CELL_TYPE_BOOLEAN:
		                	if((Object)cell.getBooleanCellValue() != null) {
			                	o[i] = (Object)cell.getBooleanCellValue();
			                    System.out.print(cell.getBooleanCellValue() + " ");
		                    }
		                    break;
		                case Cell.CELL_TYPE_FORMULA:
		                	if(cell.getCellFormula() != null) {
			                	o[i] = (Object)cell.getCellFormula();
			                    System.out.print(cell.getCellFormula() + " ");
		                	}
		                    break;
		                case Cell.CELL_TYPE_BLANK:
		                    System.out.print(" ");
		                    break;
		                default:
		                	if(i < len) {
		                		i++;
		                	}
		                    System.out.print("default ");
					}
					if(i < len) {
                		i++;
                	}
//					cell.getStringCellValue();
//					cell.getAddress();
//					cell.getBooleanCellValue();
//					cell.getDateCellValue();
//					cell.getNumericCellValue();
				}
				System.out.println();
				li.add(o);
			}
				
			//ͨ����������ȡ�У���ȡ��һ��
//			Row row = sheet.getRow(0);
			//��ӡ��һ������
//			System.out.println("ֻ��ӡ��һ��");
//			for(int i = 0; i < 4 ; i++)
//			System.out.print(row.getCell(i).getStringCellValue() + "\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
}
