package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.ParagraphRenderData;
import com.deepoove.poi.data.Paragraphs;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.data.style.ParagraphStyle;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.ParagraphRenderPolicy;
import com.deepoove.poi.xwpf.XWPFShadingPattern;
import com.fate.poitl.utils.PathUtils;
import com.fate.poitl.utils.ReportColumnUtil;
import org.apache.commons.collections4.KeyValue;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/31 11:16
 */
@SpringBootTest
public class ParagraphExample {

    @Test
    public void testParagraphRender() throws IOException {
        ParagraphStyle paragraphStyle = ParagraphStyle.builder().withAlign(ParagraphAlignment.CENTER).withSpacing(1.5f)
                .withIndentLeftChars(1.5f).withIndentRightChars(1.5f).withIndentHangingChars(1.0f).build();

        ParagraphRenderData para = Paragraphs.of().addText("\t段落标签不是一种默认支持的策略,所以需要")
                .addText(Texts.of("手动").color("0000FF").bold().create())
                .addText(Texts.of("配置 ").color("FF0000").sup().create())
                .addPicture(Pictures.ofLocal(PathUtils.getWordTemplateImageUrl("earth.png")).size(40, 40).create())
                .addText(Texts.of(" poi-tl").link("http://deepoove.com/poi-tl").create()).addText(".\n end!")
                .paraStyle(paragraphStyle).create();

        BorderStyle leftBorder = new BorderStyle();
        leftBorder.setColor("70AD47");
        leftBorder.setSize(48); // 6*8
        leftBorder.setType(XWPFTable.XWPFBorderType.SINGLE);

        ParagraphRenderData stypePara = Paragraphs.of().addText("带样式的段落 居中 绿色 阴影 左边框")
                .paraStyle(ParagraphStyle.builder().withAlign(ParagraphAlignment.CENTER).withBackgroundColor("70AD47")
                        .withShadingPattern(XWPFShadingPattern.DIAG_STRIPE).withLeftBorder(leftBorder).build())
                .create();

        Map<String, Object> data = new HashMap<>();
        data.put("paragraph", para);
        data.put("styleParagraph", stypePara);

        Configure config = Configure.builder().bind("paragraph", new ParagraphRenderPolicy())
                .bind("styleParagraph", new ParagraphRenderPolicy()).build();
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("render_paragraph.docx"), config).render(data)
                .writeToFile("target/out_render_paragraph.docx");
    }

    @Test
    public void testParagraph() throws IOException {
        Configure config = Configure.builder()
                .bind("styleParagraph", new ParagraphRenderPolicy()).build();
        KeyValue<String, Object> styleParagraph1 = ReportColumnUtil.mergeParagraph("styleParagraph", "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。" +
                "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。");
        KeyValue<String, Object> styleParagraph = ReportColumnUtil.mergeParagraph("styleParagraph", "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。" +
                "测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。测试一下段落.段落标签不是一种默认支持的策略,所以需要手动配置插件。");

        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("render_paragraph.docx"), config)
                .render(new HashMap<>() {{
                    put(styleParagraph.getKey(), styleParagraph.getValue());
                    put(styleParagraph1.getKey(), styleParagraph1.getValue());
                }}).writeToFile("target/out_render_paragraph.docx");
    }

    @Test
    public void testTitle() throws IOException {
        Map<String, Object> data = new HashMap<>();
        Configure config = Configure.builder().bind("title", new ParagraphRenderPolicy()).build();
        ParagraphStyle paragraphStyle = new ParagraphStyle();
        paragraphStyle.setStyleId("2");
//        Style style = new Style();
//        style.setFontFamily("宋体");
//        style.setBold(true);
//        style.setFontSize(24);
//        paragraphStyle.setGlyphStyle(style);
        ParagraphRenderData renderData = Paragraphs.of().addText("测试标题").paraStyle(paragraphStyle).create();
        data.put("title", renderData);
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("aa.docx"), config).render(data).writeToFile("target/out_toc.docx");
    }
}
