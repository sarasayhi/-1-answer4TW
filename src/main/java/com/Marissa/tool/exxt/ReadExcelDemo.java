package com.Marissa.tool.exxt;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

/**
 * ʹ��poi��ȡexcel
 * @author MarissaMan
 *
 */
public class ReadExcelDemo {
	public static void main(String[] args) {
		try {
			//����һ���ĵ�����
			FileInputStream fis = new FileInputStream("first.xlsx");
			Workbook book = new XSSFWorkbook(fis);
			//��ȡ������
			Sheet sheet = book.getSheetAt(0);
			//ͨ����������ȡ������
			Iterator<Row> rows = sheet.iterator();
			while(rows.hasNext()) {
				Row row = rows.next();
				//��ȡÿһ�еĵ�Ԫ��
				Iterator<Cell> cells = row.iterator();
				System.out.println();
				while(cells.hasNext()){
					Cell cell = cells.next();
					System.out.print("type:" + cell.getCellTypeEnum() + "\t");
					//System.out.print(cell.getStringCellValue() + "\t");
					cell.getStringCellValue();
					cell.getAddress();
					cell.getBooleanCellValue();
					cell.getDateCellValue();
					cell.getNumericCellValue();
				}
				System.out.println();
			}
			//ͨ����������ȡ�У���ȡ��һ��
			Row row = sheet.getRow(0);
			//��ӡ��һ������
//			System.out.println("ֻ��ӡ��һ��");
//			for(int i = 0; i < 4 ; i++)
//			System.out.print(row.getCell(i).getStringCellValue() + "\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
