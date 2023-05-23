package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.NumberingRenderData;
import com.deepoove.poi.data.Numberings;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

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
}
