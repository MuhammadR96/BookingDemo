//package org.example.utilities;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class ExcelReader {
//    public static void main(String[] args) {
//        // Set the path to the Excel file
//        String filePath = "D:/INTERVIEWS TASKS/SUMERGE/myData.xlsx";
//        // Set the sheet name
//        String sheetName = "Sheet1";
//
//        // Create an instance of ChromeDriver
//        WebDriver driver = new ChromeDriver();
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
//    }
//}
