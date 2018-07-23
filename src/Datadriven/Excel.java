package Datadriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel {
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		FileInputStream fis=new FileInputStream("./Excel/data.xls");
		
	    Workbook wb = WorkbookFactory.create(fis);
	    
	    Sheet sh = wb.getSheet("Sheet1");
	    
	    Row r=sh.getRow(0);
	    
	    Cell c=r.getCell(0);
	    
	    
	    
	    
	    
	    String v = c.toString();
	   
	    System.out.println(v);
	    
	    
	    
	    
	    
		
		
	}

}
