package com.Marissa.tool;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 使用poi操作excel
 * @author MarissaMan
 *
 *
 */
public class ReadAndWriteDemo {
		public static void main(String[] args) {
			String[] str = {
			};
			write("test.xlsx", str);
		}
		
		public Map<Integer, String> Read(String SheetName) {
			Map<Integer, String> map = new HashMap<Integer, String>();
			
			try {
				FileInputStream fis = new FileInputStream("C:/Users/Administrator/Desktop/锟斤拷锟� - 锟斤拷锟斤拷.xlsx");
				Workbook book = new XSSFWorkbook(fis);
				Sheet sheet = book.getSheet(SheetName);
				Iterator<Row> rows = sheet.iterator();
				while(rows.hasNext()) {
					Row row = rows.next();
					String o = new String();
					Iterator<Cell> cells = row.iterator();
						Cell cell = cells.next();
						if(cell.getStringCellValue()!= null) {
							String sp = new String();
//	            			System.out.print(cell.getStringCellValue() + " ");
	            			o = cell.getStringCellValue();
//	            			int x = Integer.parseUnsignedInt(o.substring(0,5).trim());
	            			if(o.length() > 5) {
	            				int x = Integer.parseInt(o.substring(0, 5));
//	            			System.out.println(x);
	            				sp = o.substring(6);
	            				map.put(x, sp);
	            			}
	            		}
				}
					System.out.println();
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
		
		public static void write(String fileName, String[] str) {
			ReadAndWriteDemo r = new ReadAndWriteDemo();
			try {
				FileOutputStream fos = null;
				Workbook wb = new XSSFWorkbook();
				fos = new FileOutputStream("C:/Users/Administrator/Desktop/" + fileName);
				for(String s : str) {
					Sheet sheet = wb.createSheet(s);
					Map<Integer, String> map = r.Read(s);
					int i = 0;
					for(int each : map.keySet()) {
						Row row = sheet.createRow(i);
						Cell c11 = row.createCell(0);
						c11.setCellValue(each);
						Cell c12 = row.createCell(1);
						c12.setCellValue(map.get(each));
						System.out.println(each + " " + map.get(each));
						i++;
					}
				}
				wb.write(fos);
				fos.close();
				System.out.println("create excel file sucess!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
