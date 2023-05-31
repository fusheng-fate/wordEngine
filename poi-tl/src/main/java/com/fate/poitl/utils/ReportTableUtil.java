package com.fate.poitl.utils;

import com.deepoove.poi.data.*;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.keyvalue.DefaultKeyValue;
import java.util.List;

/**
 * @author lgb
 * @Description 表格分为 手绘表格 、邮件合并循环表格行表格、邮件合并循环表格列、动态表格 @see TableCirculateExample
 * @Date 2023/5/30 16:02
 */
public class ReportTableUtil {

    public static KeyValue<String, Object> createTable(String label, List<String> headers, List<List<Object>> values) {
       return createTable(label, headers, values, false);
    }

    /**
     *
     * @param label 标签
     * @param headers 表格头
     * @param values 表格数据
     * @param autoWidth 自动列宽
     * @return
     */
   public static KeyValue<String, Object> createTable(String label, List<String> headers, List<List<Object>> values, boolean autoWidth) {
       Tables.TableBuilder tableBuilder = autoWidth ? Tables.ofAutoWidth() : Tables.ofPercentWidth("100%");
       if (headers != null && !headers.isEmpty()) {
           Rows.RowBuilder of = Rows.of();
           headers.stream().map(x -> Cells.of(String.valueOf(x)).create()).forEach(of::addCell);
           RowRenderData rowRenderData = of.textColor("000000").bgColor("696969").center().create();
           tableBuilder.addRow(rowRenderData);
       }
       for (List<Object> rowList : values) {
           Rows.RowBuilder rowBuilder = Rows.of();
           rowList.stream().map(text ->
                   Cells.of(String.valueOf(text)).create())
                   .forEach(rowBuilder::addCell);
           tableBuilder.addRow(rowBuilder.center().create());
       }
       return new DefaultKeyValue<>(label, tableBuilder.create());
   }
}
