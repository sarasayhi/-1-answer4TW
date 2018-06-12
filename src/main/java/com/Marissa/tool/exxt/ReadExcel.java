package com.Marissa.tool.exxt;

/**
 * ʹ��poi��ȡexcel ��װ�ɶ�������
 * @author MarissaMan
 *
 */

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

public class ReadExcel {
	
	Workbook book = null;
	Sheet sheet = null;
	
    //	����excel�ļ����ƻ�ȡexcel�ļ�
	public Workbook getWorkbook(String filepath) {
		
		try{
			FileInputStream fis = new FileInputStream(filepath); 
			book = new XSSFWorkbook(fis);
//			System.out.println("��ȡexcel�ļ��ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			if(book == null) {
				System.out.println("û�д�excel�ļ�");
			} else {
				System.out.println("��ȡexcel�ļ����ɹ�");
			}
		}
		return book;
	}
	
    //	���ݹ�������Ż�ȡ����������Ŵ�1��ʼ
	public Sheet getSheetByIndex(Workbook wb, int index) {
		
		try{
			sheet = wb.getSheetAt(index-1);
//			System.out.println("������Ż�ȡ�������ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			if(sheet == null) {
				System.out.println("�����ڴ˹�����");
			} else {
				System.out.println("��ȡ���������ɹ�");
			}
		}
		
		return sheet;
	}
	//	���ݹ��������ƻ�ȡ������
	public Sheet getSheetByName(Workbook wb, String sheetName) {
		
		try{
			sheet = wb.getSheet(sheetName);
//			System.out.println("�������ƻ�ȡ�������ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			if(sheet == null) {
				System.out.println("�����ڴ˹�����");
			} else {
				System.out.println("��ȡ���������ɹ�");
			}
		}
		return sheet;
	}
	
	
	//	��ȡ��������һ�б�ͷ��Ϣ
	public String[] getHeadRow(Sheet s) {
		Row row = s.getRow(0);
		int len = row.getLastCellNum();
		String[] HeadMsg = new String[len];
		for(int i = 0; i < len; i++){
//			System.out.println( row.getCell(i).getCellStyle().toString() + " type:" + );
			if(row.getCell(i).getCellType() == Cell.CELL_TYPE_STRING){
				HeadMsg[i] = row.getCell(i).getStringCellValue();
			} else {
				HeadMsg[i] = null;
//				System.out.println("��" + i + "����Ԫ����String����");
			} 
		}
//		System.out.println();
//		System.out.println("һ��һ����"+ len + "��");
		return HeadMsg;
	}
	
	//	��ȡ�����������У���һ�г��⣩
	public List<Object[]> getAllRows(Sheet s) {
		List<Object[]> list = null;
		
		int i = 0;
		Iterator<Row> rows = s.iterator();
		Row row = rows.next();
		int len = row.getLastCellNum();
		Object[] o = new Object[len];
		    while(rows.hasNext()){
		    	o = null;
		    	i = 0;
		    	row = rows.next();
		        for (Cell cell : row) {
		            switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING:
		                	if(cell.getStringCellValue()!= null)
		                    System.out.print(cell.getStringCellValue() + " ");
		                	o[i++] = cell.getStringCellValue();
		                    break;
//		                case Cell.CELL_TYPE_NUMERIC:
//		                    if (DateUtil.isCellDateFormatted(cell)) {
//		                    	o[i++] = cell.getDateCellValue();
////		                        System.out.print(cell.getDateCellValue() + " ");
//		                    } else {
////		                    	o[i++] = cell.getNumericCellValue();
//		                        System.out.print(cell.getNumericCellValue() + " ");
//		                    }
//		                    break;
		                case Cell.CELL_TYPE_BOOLEAN:
		                	if((Object)cell.getBooleanCellValue() != null)
		                	o[i++] = (Object)cell.getBooleanCellValue();
//		                    System.out.print(cell.getBooleanCellValue() + " ");
		                    break;
		                case Cell.CELL_TYPE_FORMULA:
		                	if(cell.getCellFormula() != null)
		                	o[i++] = (Object)cell.getCellFormula();
//		                    System.out.print(cell.getCellFormula() + " ");
		                    break;
//		                case Cell.CELL_TYPE_BLANK:
//		                    System.out.print(" ");
//		                    break;
		                default:
		                	i++;
//		                    System.out.print(" ");
		            }
		        }
		        list.add(o);
//		        System.out.println();
		    }
		    return list;
	}
}


