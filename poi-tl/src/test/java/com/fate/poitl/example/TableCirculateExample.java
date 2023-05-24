package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.LoopColumnTableRenderPolicy;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.fate.poitl.bean.Goods;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author lgb
 * @Description 表格循环  LoopRowTableRenderPolicy  LoopColumnTableRenderPolicy
 * @Date 2023/5/24 10:39
 */
@SpringBootTest
public class TableCirculateExample {

    @Test
    public void testTableRowCirculate() throws IOException {

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("goods", policy).build();

        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("table_circulate.docx"), config).render(
                new HashMap<String, Object>() {{
                    put("goods", Goods.randomLists());
                }}
        ).writeToFile("target/output_circulate.docx");

    }

    @Test
    public void testTableColumnCirculate() throws IOException {

        LoopColumnTableRenderPolicy policy = new LoopColumnTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("goods1", policy).build();

        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("table_circulate.docx"), config).render(
                new HashMap<String, Object>() {{
                    put("goods1", Goods.randomLists());
                }}
        ).writeToFile("target/output_circulate.docx");

    }
}
