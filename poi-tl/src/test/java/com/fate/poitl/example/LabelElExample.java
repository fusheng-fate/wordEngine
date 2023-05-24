package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author lgb
 * @Description 使用el表达式与区块对结合起来
 * @Date 2023/5/23 19:03
 */
@SpringBootTest
public class LabelElExample {

    @Test
    public void testElLabel() throws IOException {
        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
        //数据不合法(为Null或者是一个错误的数据类型)，默认行为清空标签(ClearHandler) 如果希望对标签不作处理：
//        builder.setValidErrorHandler(new Configure.DiscardHandler());
        //执行严格的校验，直接抛出异常
        builder.setValidErrorHandler(new Configure.AbortHandler());
        HashMap<String, Object> map = new HashMap<>() {{
            put("desc", "");
            put("summary", "Find A Pet");
            put("produces", Collections.singletonList("application/xml"));
        }};
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("el_selection.docx"), builder.build())
                .render(map).writeToFile("target/output_elSelection.docx");
    }
}
