package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExeclUtilsss {
	
	static String excelPath="C:\\Users\\Msys\\eclipse-workspace\\sleniumTestNGDemo\\excel\\data.xlsx";
	//static String sheetName="registerInfo";
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	public static void main(String[] args) {
		getRowCount();
		getColCount();
	}
	public ExeclUtilsss() {
		
	}

	
	public ExeclUtilsss(String sheetName) {
		try {
			workbook=new XSSFWorkbook(excelPath);
			sheet=workbook.getSheet(sheetName);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public static int getRowCount() {
		int rowCount=0;
		try {
			rowCount=sheet.getPhysicalNumberOfRows();
			System.out.println("No. of rows: "+rowCount);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return rowCount;
	}
	
	public static int getColCount() {
		int colCount=0;
		try {
			colCount=sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No. of col: "+colCount);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
		return colCount;
	}
	
    public static String getCellDataString(int rowNum, int colNum) {
        String cellData = null;
        try {
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            if (cell != null) {
            	if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            	    // Convert numeric value to string
            	    cellData = String.valueOf((int) cell.getNumericCellValue());
            	}else if (cell.getCellTypeEnum() == CellType.STRING) {
                    // Get string value directly
                    cellData = cell.getStringCellValue();
                } else {
                    // Handle other cell types if needed
                    // For example: BOOLEAN, FORMULA, BLANK, ERROR
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred while reading cell data:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cellData;
    }
    public void writeBagPageToExcel(String[][] valueToWrite) throws Exception {
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a new sheet
        XSSFSheet sheet = workbook.createSheet("bagPage");

        // Iterate over the 2D array
        for (int i = 0; i < valueToWrite.length; i++) {
            // Create a new row
            XSSFRow row = sheet.createRow(i);
            // Iterate over each element in the inner array
            for (int j = 0; j < valueToWrite[i].length; j++) {
                // Create a new cell and set its value
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(valueToWrite[i][j]);
            }
        }
        

        // Specify the file path
        String filePath = "C:\\Users\\Msys\\eclipse-workspace\\sleniumTestNGDemo\\excel\\data2.xlsx";
        // Write the workbook to the file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
        }

        // Close the workbook
        workbook.close();
    }

    
    public void writeDoneToExcel(int rowIndex) throws Exception {
        // Load existing workbook
        File file = new File("C:\\Users\\Msys\\eclipse-workspace\\sleniumTestNGDemo\\excel\\data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("registerInfo");

        // Write "X" to the "done" column of the specified row index
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(15); 
        cell.setCellValue("X");

        // Write changes to the workbook
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);

        // Close streams and workbook
        fos.close();
        fis.close();
        workbook.close();
    }
    
    public void writeStatusToExcel(int rowIndex,String sheetName,String Status) throws Exception {
        // Load existing workbook
        File file = new File("C:\\Users\\Msys\\eclipse-workspace\\sleniumTestNGDemo\\excel\\data.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Write "Passed or Failed" to the "status" column of the specified row index
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.createCell(1); 
        cell.setCellValue(Status);

        // Write changes to the workbook
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);

        // Close streams and workbook
        fos.close();
        fis.close();
        workbook.close();
    }

}
