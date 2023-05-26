package com.fate.poitl.topdf;

import com.fate.poitl.utils.PathUtils;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lgb
 * @Description 图表不支持渲染.数据重叠， 且排版发生错位，页数也不对，效果极差
 * @Date 2023/5/25 13:50
 */
@SpringBootTest
public class XDocReportConvertPDFExample {

    /**
     * 此代码仅测试
     * @throws IOException
     */
    @Test
    public void testWord2Pdf() throws IOException {
        InputStream is = new FileInputStream(PathUtils.getWordTemplateTextUrl("testPdf.docx"));
        XWPFDocument docx = new XWPFDocument(is);
        FileOutputStream fileOutputStream = new FileOutputStream("target/opensagresPdf.pdf");
        PdfOptions pdfOptions = PdfOptions.create();
        pdfOptions.fontProvider((s, s1, v, i, color) -> {
            try {
                BaseFont stChinese = BaseFont.createFont("C:\\Windows\\Fonts\\msyh.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                Font stFontChinese = new Font(stChinese, v, i, color);
                if (s != null) {
                    stFontChinese.setFamily(s);
                }
                return stFontChinese;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        PdfConverter.getInstance().convert(docx, fileOutputStream, pdfOptions);
        is.close();
        fileOutputStream.close();
    }
}
