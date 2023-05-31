package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.NumberingFormat;
import com.deepoove.poi.data.NumberingRenderData;
import com.deepoove.poi.data.Numberings;
import com.fate.poitl.utils.PathUtils;
import com.fate.poitl.utils.ReportColumnUtil;
import org.apache.commons.collections4.KeyValue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author lgb
 * @Description 列表  NumberingRenderData 列表标签 *
 * @Date 2023/5/22 17:43
 */
@SpringBootTest
public class NumberingExample {

    @Test
    public void testNumbering() throws IOException {
        HashMap<String, NumberingRenderData> list = new HashMap<>() {
            {
                put("list", Numberings.create("Plug-in grammar",
                        "Supports word text, pictures, table...",
                        "Template, not just template, but also style template"));
            }
        };
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(list).writeToFile("target/output_numbering.docx");

    }

    @Test
    public void testNumberingUtil() throws IOException {
        List<Object> list = List.of("Plug-in grammar",
                "Supports word text, pictures, table...",
                "Template, not just template, but also style template", 18);
        KeyValue<String, Object> list1 = ReportColumnUtil.createNumbering("list", list);
        HashMap<Object, Object> map = new HashMap<>() {{
            put(list1.getKey(), list1.getValue());
        }};

        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx"))
                .render(map).writeToFile("target/output_numbering.docx");
    }
}
