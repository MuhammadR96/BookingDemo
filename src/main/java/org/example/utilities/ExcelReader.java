package org.example.utilities;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class ExcelReader {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<String, Integer> columnHeaders;


    public ExcelReader(String fileName) throws FileNotFoundException, IOException {
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
}
