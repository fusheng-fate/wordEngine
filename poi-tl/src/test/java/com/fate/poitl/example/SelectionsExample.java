package com.fate.poitl.example;

import com.alibaba.fastjson2.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Charts;
import com.fate.poitl.utils.PathUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * selections非空集合实践(作者赞赞赞) 对于需要循环多次渲染的段落、文本标签、表格、图表来说很方便
     * @throws IOException
     */
    @Test
    public void circulateSelections() throws IOException {
        HashMap<String, Object> map = new HashMap<>() {{
            put("circulateChart", List.of(
                    new JSONObject() {{
                        put("name", "张三");
                        put("age", 18);
                        put("pieChart", Charts.ofPie("简单饼状图测试", new String[]{"a", "b", "c", "d", "e"})
                                .series("SPP", new Number[]{10, 20, 50, 52, 15})
                                .create()
                        );
                    }},
                    new JSONObject() {{
                        put("name", "李四");
                        put("age", 20);
                        put("pieChart", Charts.ofPie("简单饼状图测试", new String[]{"a", "b", "c", "d", "e"})
                                .series("SPP", new Number[]{80, 20, 50, 52, 15})
                                .create()
                        );
                    }}
            ));
        }};
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("chartSelections.docx"))
                .render(map).writeToFile("target/output_chartSelections.docx");
    }
}
