package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/19 14:17
 */
@SpringBootTest
public class TextExample {

    /**
     * compile: 编译模板
     * render: 渲染数据
     * write: 输出到流
     * @throws IOException
     */
    @Test
    public void testTitle() throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx")).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});
        template.writeAndClose(new FileOutputStream("target/output.docx"));
    }


}
