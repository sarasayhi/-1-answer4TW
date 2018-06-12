package com.Marissa.tool.exxt;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;


public class ReadTest {
	static ReadExcel read  = new ReadExcel();
	
	public static void main(String[] args) {
		String[] head = null;
		Sheet s = null;
		List<Object[]> list = null;
		Workbook wb = read.getWorkbook("C:/Users/saras/Desktop/11.xlsx");
		if(wb != null){
			s = read.getSheetByIndex(wb, 2);
		}
		if(s != null) {
			head = read.getHeadRow(s);
			list = read.getAllRows(s);
		}
		
		int len = head.length;
		for(int i = 0; i < len; i++) {
			System.out.print(head[i] + " ");
		}
		System.out.println();
		for(int i = 0; i < list.size(); i++) {
			for(Object o : list.get(i)){
				System.out.print(o);
			}
			System.out.println();
		}
		
		read.getSheetByName(wb, "sheet2");
	}
}
