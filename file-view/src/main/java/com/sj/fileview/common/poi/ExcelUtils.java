package com.sj.fileview.common.poi;

import com.sj.fileview.annotation.Excel;
import com.sj.fileview.annotation.ExcelList;
import com.sj.fileview.config.SjConfig;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExcelUtils {
    public static Workbook createWorkbook(){
        return new SXSSFWorkbook(800);
    }
    /**
     * 创建工作表
     */
    public static Sheet createSheet(Workbook workbook)
    {
       return workbook.createSheet();
    }

    /**
     * 编码文件名
     */
    public static String encodingFilename(String filename)
    {
        filename = System.currentTimeMillis() + "_" + filename + ".xlsx";
        return filename;
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public static String getAbsoluteFile(String filename)
    {
        String downloadPath = SjConfig.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists())
        {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 编码文件名
     */
    public static void createHeader(Sheet sheet,Class clazz)
    {
        try {
            List<String> header = new ArrayList<String>();
            Row row = sheet.createRow(0);
            Field[] f = clazz.getDeclaredFields();
            for (Field field : f) {
                ExcelList excelList = field.getAnnotation(ExcelList.class);
                if(excelList != null){
                    ParameterizedType pt = (ParameterizedType)field.getGenericType();
                    Class u = Class.forName(pt.getActualTypeArguments()[0].toString().replace("class","").trim());
                    Field[] fields = u.getDeclaredFields();
                    for (Field d : fields) {
                        Excel e = d.getAnnotation(Excel.class);
                        if(e != null){
                            header.add(e.name());
                        }
                    }
                }
            }
            Cell cell =null;
            int i = 0;
            for (String s : header) {
                cell = row.createCell(i);
                cell.setCellValue(s);
                i++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
