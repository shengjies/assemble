package com.sj.fileview.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sj.fileview.common.poi.PdfUtils;
import com.sj.fileview.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PdfController {

    private static PdfPCell cell;

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        String titleHeader[]={"总入库数量","总报废数量","总投入数量","标准总工时","出勤总工时","生产总工时","平均达成率(%)",
                "平均直通率(%)","加班X1.5","加班X2.0","加班X3.0"};
        String workHeader[]={"工单号","产品编码","工单数量","入库数量","报废数量","投入数量","达成率","直通率","出勤工时","生产工时","加班X1.5","加班X2.0","加班X3.0"};
        String eHeader[]={"工单号","异常分类","发生时间","异常描述","处理状态","责任人","解决方案","处理时间"};
        String fileName = "test";
        response.setHeader("content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        writer.open();
        // 字体，定义为蓝色加粗
        Font title = new Font(BaseFont.createFont("/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        title.setSize(14);
        Paragraph company = new Paragraph("深圳市聚特易技术有限公司", title);
        company.setAlignment(1);
        document.add(company);
        title.setSize(12);
        Paragraph text = new Paragraph("生产报表", title);
        text.setAlignment(1);
        document.add(text);
        //数据来源
        title.setSize(10);
        Phrase phrase  = new Phrase ("数据来源:全局;  报表时间:2019-07-15至2019-07-15",title);
        document.add(phrase);
        Font body =  new Font(BaseFont.createFont("/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        body.setSize(8);
        // 表格，3列
        PdfPTable table = new PdfPTable(titleHeader.length);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        for (String header : titleHeader) {
            table.addCell(createCell(header,body,1,false));
        }
        for (String va : titleHeader) {
            table.addCell(createCell("100",body,1,false));
        }
        document.add(table);
        //工单详情
        document.add(new Chunk("\n"));
        table = new PdfPTable(workHeader.length+2);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        PdfPCell titleCell = createCell("工单详情",title,workHeader.length+2,false);
        titleCell.setVerticalAlignment(1);
        table.addCell(titleCell);
        int i=0;
        for (;i<workHeader.length;i++) {
            if(i == 0 || i == 1){
                table.addCell(createCell(workHeader[i],body,2,true));
            }else{
                table.addCell(createCell(workHeader[i],body,1,true));
            }

        }
        i=0;
        for(;i<100;i++){
            table.addCell(createCell("GD190715236541256",body,2,false));
            table.addCell(createCell("CP1907152365412564545454554545",body,2,false));
            table.addCell(createCell("100",body,1,false));
            table.addCell(createCell("500",body,1,false));
            table.addCell(createCell("200",body,1,false));
            table.addCell(createCell("0",body,1,false));
            table.addCell(createCell("100%",body,1,false));
            table.addCell(createCell("100%",body,1,false));
            table.addCell(createCell("8",body,1,false));
            table.addCell(createCell("2",body,1,false));
            table.addCell(createCell("0",body,1,false));
            table.addCell(createCell("0",body,1,false));
            table.addCell(createCell("0",body,1,false));
        }
        document.add(table);
        //工单异常
        document.add(new Chunk("\n"));
        table = new PdfPTable(eHeader.length+7);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        PdfPCell eCell = createCell("异常事件",title,eHeader.length+7,false);
        eCell.setVerticalAlignment(1);
        table.addCell(eCell);
        i=0;
        for (; i<eHeader.length ; i++) {
            if(i == 0 || i== 2 || i==eHeader.length-1){
                table.addCell(createCell(eHeader[i],body,2,true));
            }else if(i == 3 || i == 6){
                table.addCell(createCell(eHeader[i],body,3,true));
            }else{
                table.addCell(createCell(eHeader[i],body,1,true));
            }
        }
        i=0;
        for(;i<10;i++){
            table.addCell(createCell("GD190715236541256",body,2,false));
            table.addCell(createCell("物料不足",body,1,false));
            table.addCell(createCell("2019-07-15 00:00:00",body,2,false));
            table.addCell(createCell("库存不足",body,3,false));
            table.addCell(createCell("已处理",body,1,false));
            table.addCell(createCell("拉长",body,1,false));
            table.addCell(createCell("使用储备物料",body,3,false));
            table.addCell(createCell("2019-07-15 00:00:00",body,2,false));
        }
        document.add(table);
        writer.close();
        document.close();
    }

    /**
     * 创建单元格
     * @param value 对应数值
     * @param font 对应样式
     * @return
     */
    public static PdfPCell createCell(String value,Font font,int span,boolean color){
        cell = new PdfPCell(new Paragraph(value,font));
        cell.setHorizontalAlignment(1);
        cell.setColspan(span);
        if(color){
            cell.setBackgroundColor(BaseColor.PINK);
        }
        return cell;
    }

    @RequestMapping("/single")
    public void singlePdf(HttpServletResponse response) throws IOException, DocumentException {
        String titleHeader[]={"总入库数量","总报废数量","总投入数量","标准总工时","生产总工时"};
        String workHeader[]={"工单号","产品编码","工单数量","入库数量","报废数量","投入数量","工价","总价","总机台数"};
        String eHeader[]={"工单号","异常分类","发生时间","异常描述","处理状态","责任人","解决方案","处理时间"};
        String fileName = "single";
        response.setHeader("content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        writer.open();
        // 字体，定义为蓝色加粗
        Font title = new Font(BaseFont.createFont("/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        title.setSize(14);

        Paragraph company = new Paragraph("深圳市聚特易技术有限公司", title);
        company.setAlignment(1);
        document.add(company);
        title.setSize(12);
        Paragraph text = new Paragraph("生产报表", title);
        text.setAlignment(1);
        document.add(text);
        //数据来源
        title.setSize(10);
        Phrase phrase  = new Phrase ("数据来源:A车间;  报表时间:2019-07-15至2019-07-15",title);
        document.add(phrase);
        Font body =  new Font(BaseFont.createFont("/fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        body.setSize(8);
        // 表格，3列
        PdfPTable table = new PdfPTable(titleHeader.length);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        for (String header : titleHeader) {
            table.addCell(createCell(header,body,1,false));
        }
        for (String va : titleHeader) {
            table.addCell(createCell("100",body,1,false));
        }
        document.add(table);
        //工单详情
        document.add(new Chunk("\n"));
        table = new PdfPTable(workHeader.length+2);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        PdfPCell titleCell = createCell("工单详情",title,workHeader.length+2,false);
        titleCell.setVerticalAlignment(1);
        table.addCell(titleCell);
        int i=0;
        for (;i<workHeader.length;i++) {
            if(i == 0 || i == 1){
                table.addCell(createCell(workHeader[i],body,2,true));
            }else{
                table.addCell(createCell(workHeader[i],body,1,true));
            }

        }
        i = 0;
        for (;i<workHeader.length;i++){
            table.addCell(createCell("GD201907153625413652",body,2,false));
            table.addCell(createCell("CP201907153625413652",body,2,false));
            table.addCell(createCell("500",body,1,false));
            table.addCell(createCell("450",body,1,false));
            table.addCell(createCell("10",body,1,false));
            table.addCell(createCell("500",body,1,false));
            table.addCell(createCell("0.5",body,1,false));
            table.addCell(createCell("250",body,1,false));
            table.addCell(createCell("3",body,1,false));
        }
        document.add(table);
        //工单异常
        document.add(new Chunk("\n"));
        table = new PdfPTable(eHeader.length+7);
        table.setTotalWidth(580);
        table.setLockedWidth(true);
        PdfPCell eCell = createCell("异常事件",title,eHeader.length+7,false);
        eCell.setVerticalAlignment(1);
        table.addCell(eCell);
        i=0;
        for (; i<eHeader.length ; i++) {
            if(i == 0 || i== 2 || i==eHeader.length-1){
                table.addCell(createCell(eHeader[i],body,2,true));
            }else if(i == 3 || i == 6){
                table.addCell(createCell(eHeader[i],body,3,true));
            }else{
                table.addCell(createCell(eHeader[i],body,1,true));
            }
        }
        i=0;
        for(;i<10;i++){
            table.addCell(createCell("GD190715236541256",body,2,false));
            table.addCell(createCell("物料不足",body,1,false));
            table.addCell(createCell("2019-07-15 00:00:00",body,2,false));
            table.addCell(createCell("库存不足",body,3,false));
            table.addCell(createCell("已处理",body,1,false));
            table.addCell(createCell("拉长",body,1,false));
            table.addCell(createCell("使用储备物料",body,3,false));
            table.addCell(createCell("2019-07-15 00:00:00",body,2,false));
        }
        document.add(table);
        writer.close();
        document.close();
    }

    @RequestMapping("/user")
    public void userPdf(HttpServletResponse response) throws IOException, DocumentException {
        String fileName = "user";
        response.setHeader("content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".pdf");
        PdfUtils utils = new PdfUtils(User.class);
        utils.init(response,"用户详情");
    }
}
