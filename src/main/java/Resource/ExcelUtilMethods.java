/*
 * @autor : Chaitanya 
 * 
 * * 
 */
package Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtilMethods {
	File f;
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook wb;
	XSSFSheet ws;
	XSSFRow row;
	XSSFCell cell;
	XSSFCellStyle style;	
//it is constructor
public ExcelUtilMethods(String inputpath) throws IOException
	{
f=new File(inputpath);
fi=new FileInputStream(f);
wb=new XSSFWorkbook(fi);
	}
//count no of rows
public int getRowCount(String sheetname) throws IOException
	{		
	int rowcount;
	ws=wb.getSheet(sheetname);
	rowcount=ws.getLastRowNum();
	return rowcount;		
	}
//count no of columns
public int getCellCount(String sheetname) throws IOException
	{		
	int cellcount;
	ws=wb.getSheet(sheetname);
	row=ws.getRow(0);
	cellcount=row.getLastCellNum();
	return cellcount;		
	}
//get columns from sheet	
	public String getCellData(int i, int rownum,int colnum) throws IOException
	{
		ws=wb.getSheetAt(i);
		row=ws.getRow(0);
		String data=ws.getRow(rownum).getCell(colnum).getStringCellValue();
		if (data == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
	        return "empty";
	    }

	    if (data == "") {
	        return "empty";
	    }

	    if (data.trim().isEmpty()) {
	        return "empty";
	    }
		return data;
	}
//writing into new File	
	public void setCellDatanewfile(String sheetname, int rownum,int colnum,String data,String outputxlfile) throws IOException
	{
		ws=wb.getSheet(sheetname);
		row=ws.getRow(0);
		ws.getRow(rownum).createCell(colnum).setCellValue(data);
		fo=new FileOutputStream(new File(outputxlfile));
		wb.write(fo);
		
	}
	
public void fillGreenColor(String sheetname, int rownum,int colnum, String excelpath) throws IOException
	{
		ws=wb.getSheet(sheetname);
		row=ws.getRow(0);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		ws.getRow(rownum).getCell(colnum).setCellStyle(style);
		fo=new FileOutputStream(excelpath);
		wb.write(fo);
	}
public void fillRedColor(String sheetname, int rownum,int colnum, String excelpath) throws IOException
	{
		ws=wb.getSheet(sheetname);
		row=ws.getRow(0);
		style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		ws.getRow(rownum).getCell(colnum).setCellStyle(style);
		fo=new FileOutputStream(excelpath);
		wb.write(fo);
		}
public void closefiles() throws IOException
	{
fi.close();
fo.close();
wb.close();
	}

}