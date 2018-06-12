package com.Marissa.tool.exxt;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * ����һ��xlsx�ĵ�
 * @author MarissaMan
 * 
 */
public class WriteTestDemo {
	public static void main(String[] args) {
		try {
			//����һ���ĵ�����
			Workbook wb = new XSSFWorkbook();
			//����һ���ļ������
			FileOutputStream fos = new FileOutputStream("first.xlsx");
			//1.����һ��������
			Sheet sheet = wb.createSheet("first sheet");
			//2.ͨ��һ������������һ��
			Row row1 = sheet.createRow(0);
			//3.ͨ������������
			Cell c1 = row1.createCell(0);
			c1.setCellValue("��Ʒ���");
			
			Cell c2 = row1.createCell(1);
			c2.setCellValue("��Ʒ����");
			
			Cell c3 = row1.createCell(2);
			c3.setCellValue("��Ʒ�۸�");
			
			Cell c4 = row1.createCell(3);
			c4.setCellValue("���տ��");
			
			Cell c5 = row1.createCell(4);
			c5.setCellValue("״̬");
			//д���ļ�
			wb.write(fos);
			//�ر��ļ���
			fos.close();
			System.out.println("create excel file sucess!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
