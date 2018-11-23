package com.example.boot.util;

import com.example.boot.exception.FileFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *excel导入
 * Administrator zhoulk
 * date: 2018/10/27 0027
 */
public class ImportUtil {
    //2003
    private static final String EXTENSION_XLS = "xls";
    //2007
    private static final String EXTENSION_XLSX = "xlsx";

    /***
     * <pre>
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     *   xls:HSSFWorkbook
     *   xlsx：XSSFWorkbook
     * @param file 文件
     * @return Workbook 对象
     * @throws IOException 异常
     * </pre>
     */
    private static Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
       // InputStream is = new FileInputStream(filePath);
        String fileName = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        if (fileName.endsWith(EXTENSION_XLS)) {
            workbook = new HSSFWorkbook(in);
        } else if (fileName.endsWith(EXTENSION_XLSX)) {
            workbook = new XSSFWorkbook(in);
        }
        in.close();
        return workbook;
    }

    /**
     * 文件检查
     * @param file 文件
     * @throws FileNotFoundException 异常
     * @throws FileFormatException 异常
     */
    private static void preReadCheck(MultipartFile file) throws FileNotFoundException, FileFormatException {
        // 常规检查
        //File file = new File(filePath);
        if (file.isEmpty()) {
            throw new FileNotFoundException("传入的文件为空，请重新上传。");
        }else{
            String fileName =file.getOriginalFilename();
            //String fileFormat= fileName.substring(fileName.lastIndexOf("."));
            if (!(fileName.endsWith(EXTENSION_XLS) || fileName.endsWith(EXTENSION_XLSX))) {
                throw new FileFormatException("文件不是excel");
            }
        }

    }

    /**
     * 读取excel文件内容
     * @param file 文件
     * @throws FileNotFoundException 异常
     * @throws FileFormatException 异常
     */
    public static List<Object[]> readExcel(MultipartFile file) throws FileNotFoundException, FileFormatException {
        //存放一行数据
        Object[] row;
        //返回结果，row
        List<Object[]> result = new ArrayList<>();
        // 检查
        preReadCheck(file);
        // 获取workbook对象
        Workbook workbook ;

        try {
            workbook = getWorkbook(file);
            // 读文件 一个sheet一个sheet地读取
            for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workbook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
               // sheet.getSheetName() ,一个sheet开始
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();

                // 读取首行 即,表头
                Row firstRow = sheet.getRow(firstRowIndex);
                //保证当前sheet不为null
                if(firstRow!=null){
                    for (int i = firstRow.getFirstCellNum(); i < firstRow.getLastCellNum(); i++) {
                        Cell cell = firstRow.getCell(i);
                        String cellValue = getCellValue(cell, true);
                        System.out.print(" " + cellValue + "\t");
                    }

                    // 读取数据行,第二行正式开始，不要表头，遍历行
                    for (int rowIndex = firstRowIndex + 1; rowIndex <= lastRowIndex; rowIndex++) {
                        // 当前行
                        Row currentRow = sheet.getRow(rowIndex);
                        // 首列
                        int firstColumnIndex = currentRow.getFirstCellNum();
                        // 最后一列
                        int lastColumnIndex = currentRow.getLastCellNum();
                        row = new Object[lastColumnIndex];
                        //序号在第一，遍历列，获取当前行的值
                        for (int columnIndex = firstColumnIndex; columnIndex < lastColumnIndex; columnIndex++) {
                            // 当前单元格
                            Cell currentCell = currentRow.getCell(columnIndex);
                            // 当前单元格的值
                            String currentCellValue = getCellValue(currentCell, true);
                            row[columnIndex]= currentCellValue;
                        }
                        result.add(row);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           /* if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        return result;
    }

    /**
     * 取单元格的值
     * @param cell 单元格对象
     * @param treatAsStr 为true时，当做文本来取值 (取到的是文本，不会把“1”取成“1.0”)
     * @return 单元格值
     */
    private static String getCellValue(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }

        if (treatAsStr) {
            // 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
            // 加上下面这句，临时把它当做文本来读取
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

}
