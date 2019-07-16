package com.sj.fileview.common.poi;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sj.fileview.common.poi.annotation.Pdf;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf
 */
public class PdfUtils<T> {
    private Document document;

    private PdfWriter writer;

    private List<T> list;
    /**
     * 创建表格
     */
    private PdfPTable table;
    /**
     * 表格头部名称
     */
    private String headerName;
    /**
     * 表格单元格
     */
    private PdfPCell cell;

    private Font font;
    /**
     * 表格总行数
     */
    private int numColumns =0;
    /**
     * 注解列表
     */
    private List<Field> fields;

    public Class<T> clazz;

    public PdfUtils(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Document createDocument() {
        if (this.document == null) {
            this.document = new Document();
        }
        return this.document;
    }

    public PdfWriter createPdfWriter(HttpServletResponse response) throws IOException, DocumentException {
        if (this.document == null) {
            createDocument();
        }
        if (this.writer == null) {
            this.writer = PdfWriter.getInstance(this.document, response.getOutputStream());
        }
        return this.writer;
    }

    public void createPdfField() {
        this.fields = new ArrayList<Field>();
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            Pdf pdf = field.getAnnotation(Pdf.class);
            if(pdf != null){
                fields.add(field);
                numColumns += pdf.colspan();
            }
        }
    }
    /**
     * 创建单元格
     * @param value 对应数值
     * @param font 对应样式
     * @return
     */
    public  PdfPCell createCell(String value, Font font, int span, boolean color){
        cell = new PdfPCell(new Paragraph(value,font));
        cell.setHorizontalAlignment(1);
        cell.setColspan(span);
        if(color){
            cell.setBackgroundColor(BaseColor.PINK);
        }
        return cell;
    }
    /**
     * 创建表格
     * @param headerName
     * @return
     */
    public PdfPTable createTable(String headerName) throws IOException, DocumentException {
        this.headerName = headerName;
        font = new Font(BaseFont.createFont("/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        font.setSize(10);
        table = new PdfPTable(numColumns);
        return table;
    }

    /**
     * 创建表格头部信息
     */
    public void createTableHeader(){
        table.addCell(createCell(headerName,font,numColumns,false));
    }

    public void createTableTitle(){
        for (Field field : fields) {
            Pdf pdf = field.getAnnotation(Pdf.class);
            if(pdf != null){
                table.addCell(createCell(pdf.name(),font,pdf.colspan(),true));
            }
        }
    }
    public void init(HttpServletResponse response,String headerName) throws IOException, DocumentException {
        createPdfWriter(response);
        createPdfField();
        document.open();
        writer.open();
        PdfPTable table = createTable(headerName);
        createTableHeader();
        createTableTitle();
        document.add(table);
        if(writer != null){
            writer.close();
        }
        if(document != null){
            document.close();
        }
    }
}
