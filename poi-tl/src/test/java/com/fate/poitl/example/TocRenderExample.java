package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.toc.TOCRenderPolicy;
import com.fate.poitl.utils.TocUpdateUtil;
import com.fate.poitl.utils.PathUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgb
 * @Description 测试目录生成
 * @Date 2023/6/5 13:06
 */
@SpringBootTest
public class TocRenderExample {

    /**
     * poi-tl中自带的更新目录插件，插入一个目录域，但是合并后的word不会自动更新域，如果用word打开，打开的时候会强制你更新域，如果用wps打开，不会有提示，必须手动更新域才会显示目录
     * 是poi中提供的api实现的
     * @throws IOException
     */
    @Test
    public void testToc() throws IOException {
        Map<String, Object> data = new HashMap<>();
        Configure config = Configure.builder().bind("Toc", new TOCRenderPolicy()).build();
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("test_all.docx"), config).render(data).writeToFile("target/out_toc.docx");
    }

    /**
     * 一种方案，获取所有的段落，找到所有的标题，然后在模板中的标记处手动写入，获取段落所在的页码(不准，和实际有偏差)。
     * 另一种方案。和poi-tl插件中 TocRenderPolicy中的方式一样。
     * @throws IOException
     */
    @Test
    public void testTocV1() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(PathUtils.getWordTemplateTextUrl("aa.docx"));
        XWPFDocument doc = new XWPFDocument(fileInputStream);
//        TocUpdateUtil.automaticGenerateTOC(4, "Toc", doc, 1);
        TocUpdateUtil.handGenerateTOC(doc, "Toc");
        OutputStream out = new FileOutputStream("target/out_toc.docx");
        doc.write(out);
        out.close();
    }
}
