package com.Marissa.tool;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;
import java.util.Map;

public class OperateDemo {
	public static void main(String[] main) {
		ReadExcel re = new ReadExcel("C:/Users/Administrator/Desktop/正式规格 - 副本.xlsx");
		WriteExcel out = new WriteExcel("C:/Users/Administrator/Desktop/out2.xlsx");
		Sheet s = re.getSheet("txt");
		Sheet outs = out.createSheet("text");
		List<Row> rows = re.getAllRows(s);
		Map<Integer, Object> map = null;
		Row row = null;
		for(int i = 0; i < rows.size(); i++) {
			row = rows.get(i);
			map = re.getAllCellValue(row);
			out.createCell(out.createRow(outs,i), map);
		}
		out.writeFile();
		out.closeStream();
	}
	
}
