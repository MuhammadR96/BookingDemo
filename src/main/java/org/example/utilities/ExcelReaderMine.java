package org.example.utilities;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ExcelReaderMine {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<String, Integer> columnHeaders;


    public ExcelReaderMine(String fileName) throws FileNotFoundException, IOException {
        String filePath = System.getProperty("user.dir") + "\\input\\" + fileName;
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Test file not found" + e.getMessage());
            System.exit(0);
        }
    }


    public String getCellData(String sheetName, int row, String columnName){
        sheet = workbook.getSheet(sheetName);
        getAllHeaderColumnValues();
        String data = null;
        try {
            data = sheet.getRow(row).getCell(columnHeaders.get(columnName)).getStringCellValue();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        data = (data == null) ? "" : data;
        return data;
    }

    private void getAllHeaderColumnValues() {
        columnHeaders = new HashedMap<>();
        int totalNumOfColumns = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i < totalNumOfColumns; i++){
            columnHeaders.put(sheet.getRow(0).getCell(i).getStringCellValue(), i);
        }
    }







//    // Set the path to the Excel file
//        String filePath = "D:/INTERVIEWS TASKS/SUMERGE/myData.xlsx";
//        // Set the sheet name
//        String sheetName = "Sheet1";
//
//        // Create an instance of ChromeDriver
//        WebDriver driver;
//
//        try {
//            // Instantiate a Workbook object and open the Excel file
//            Workbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
//
//            // Get the desired sheet from the workbook
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            // Iterate over the rows and columns to read the data
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    String cellValue = "";
//
//                    // Check the cell type and read the value accordingly
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            cellValue = cell.getStringCellValue();
//                            break;
//                        case NUMERIC:
//                            cellValue = String.valueOf(cell.getNumericCellValue());
//                            break;
//                        case BOOLEAN:
//                            cellValue = String.valueOf(cell.getBooleanCellValue());
//                            break;
//                    }
//
//                    System.out.println("Cell Value: " + cellValue);
//                }
//            }
//
//            // Close the workbook
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // Quit the driver
//            driver.quit();
//        }
}
