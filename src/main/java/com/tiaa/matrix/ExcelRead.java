package com.tiaa.matrix;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelRead {
	
	public ArrayList<String> readFile(String fileName) throws IOException, EncryptedDocumentException, InvalidFormatException{
		
		Workbook workbook = WorkbookFactory.create(new FileInputStream(fileName));
		
        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        ArrayList<String> cellValue= new ArrayList<String>();
        Row row;
        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
        	  row = sheet.getRow(rowIndex);
        	  if (row != null) {
        	    Cell cell = row.getCell(1);
        	    if (cell != null) {
        	      // Found column and there is value in the cell.
        	    	System.out.println(dataFormatter.formatCellValue(cell));
        	    	cellValue .add( dataFormatter.formatCellValue(cell));
//        	      cellValueMaybeNull = cell.getStringCellValue();
//        	      // Do something with the cellValueMaybeNull here ...
        	    }
        	  }
        	}
        	
        // Closing the workbook
        workbook.close();
        return cellValue;
	}

}
