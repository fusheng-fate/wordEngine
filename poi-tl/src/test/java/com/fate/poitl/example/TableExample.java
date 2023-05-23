package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.*;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author lgb
 * @Description 插入表格  TableRenderData  Tables Rows Cells
 * @Date 2023/5/22 15:33
 */
@SpringBootTest
public class TableExample {

    /**
     * 插入一个基础的表格
     * 一个2行2列的表格
     */
    @Test
    public void testBasicTable() throws IOException {
        HashMap<String, TableRenderData> map = new HashMap<>() {{
            put("table0", Tables.of(new String[][]{
                    new String[]{"00", "01"},
                    new String[]{"张三", "11"}})
                    .border(BorderStyle.DEFAULT).create());
        }};
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(map).writeToFile("target/out_table_basic.docx");
    }

    @Test
    public void testStyleTable() throws IOException {
        // 第0行居中且背景为蓝色的表格
        RowRenderData row0 = Rows.of("姓名", "学历").textColor("FFFFFF")
                .bgColor("4472C4").center().create();
        RowRenderData row1 = Rows.create("李四", "博士");
        HashMap<String, TableRenderData> map = new HashMap<>() {
            {
                put("table1", Tables.create(row0, row1));
            }
        };
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(map).writeToFile("target/out_table_basic.docx");
    }

    @Test
    public void testStyle() throws IOException {
//        Rows.RowBuilder rowBuilder = Rows.of();
//        rowBuilder.center();
//        rowBuilder.textColor("");
//        rowBuilder.bgColor("");
//        rowBuilder.addCell(new CellRenderData().addParagraph(new ParagraphRenderData().addText("")));
//        rowBuilder.rowHeight(14.0);
//        rowBuilder.rowStyle(new RowStyle());
//        RowStyle rowStyle = new RowStyle();
//        CellStyle style = rowStyle.getDefaultCellStyle();
//        ParagraphStyle defaultParagraphStyle = style.getDefaultParagraphStyle();
//        if (null == defaultParagraphStyle) {
//            defaultParagraphStyle = ParagraphStyle.builder().build();
//            style.setDefaultParagraphStyle(defaultParagraphStyle);
//        }
//        Style defaultTextStyle = defaultParagraphStyle.getDefaultTextStyle();
//        if (null == defaultTextStyle) {
//            defaultTextStyle = Style.builder().build();
//            defaultParagraphStyle.setDefaultTextStyle(defaultTextStyle);
//        }
//        defaultTextStyle.setColor("FFFFFF");
//        style.setBackgroundColor("4472C4");
        //表头
        RowRenderData rowHeader = Rows.of("姓名", "年龄", "专业").textColor("FFFFFF").bgColor("4472C4").center().create();
        //第一行
        RowRenderData row1 = Rows.create("张三", "23", "Bug工程师");
        //第二行
        RowRenderData row2 = Rows.create("李四", "24", "Bug工程师");

        HashMap<String, TableRenderData> map = new HashMap<>() {
            {
                put("table1", Tables.of(rowHeader, row1, row2).autoWidth().create());
            }
        };
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(map).writeToFile("target/out_table_basic.docx");
    }

    @Test
    public void testTableStyle() throws IOException {
        //表头
        RowRenderData rowHeader = Rows.of("姓名", "年龄", "专业").textColor("FFFFFF").bgColor("4472C4").center().create();
        //第一行
        RowRenderData row1 = Rows.create("张三xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "23", "Bug工程师");
        //第二行
        RowRenderData row2 = Rows.create("李四", "24", "Bug工程师");

        Tables.TableBuilder tableBuilder = Tables.of(rowHeader, row1, row2);
        //自定义表格宽度
//        tableBuilder.width(15d, null);
        //自定义表格列宽
//        tableBuilder.width(15d, new double[]{3,6,6});
        //自动列宽
//        tableBuilder.autoWidth();
        tableBuilder.center();
        //百分比列宽
        tableBuilder.percentWidth("100%", new int[]{50,40,10});

        HashMap<String, TableRenderData> map = new HashMap<>() {
            {
                put("table1", tableBuilder.create());
            }
        };
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(map).writeToFile("target/out_table_basic.docx");
    }

}
