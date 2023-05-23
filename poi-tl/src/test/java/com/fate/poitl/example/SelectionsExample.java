package com.fate.poitl.example;

import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.fate.poitl.utils.PathUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author lgb
 * @Description 区块对 循环
 * @Date 2023/5/22 17:46
 */
@SpringBootTest
public class SelectionsExample {

    @Test
    public void testSelections() throws IOException {
        //非空集合
        ArrayList<JSONObject> arrays = Lists.newArrayList(
                new JSONObject() {{
                    put("name", "张三");
                }},
                new JSONObject() {{
                    put("name", "李四");
                }}
        );

        //非false 非集合
        JSONObject object = new JSONObject() {{
            put("name", "张三");
            put("age", 22);
        }};

        //false
        JSONObject announce = new JSONObject() {{
            put("announce", false);
            put("songs", arrays);
            put("person", object);
        }};

        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("selections.docx")).render(announce).writeToFile("target/output_selections.docx");
    }

    //特殊标签 {{#=this}} {
    //  "produces": [
    //    "application/json",
    //    "application/xml"
    //  ]
    //}

    //    {{?produces}}
    //    {{=#this}}
    //    {{/produces}}



}
