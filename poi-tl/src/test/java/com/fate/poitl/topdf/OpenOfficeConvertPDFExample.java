package com.fate.poitl.topdf;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.ConnectException;

/**
 * @author lgb
 * @Description  不支持图表 使用libreOffice 支持图表
 * @Date 2023/5/25 15:51
 */
@SpringBootTest
public class OpenOfficeConvertPDFExample {

    @Test
    public void testConvertPdf() throws ConnectException {
        wordToPdf(PathUtils.getWordTemplateTextUrl("testPdf.docx"), "target/output_openOfficePdf.pdf");
    }

    public static void wordToPdf(String docFile,String pdfFile) throws ConnectException {
        // 源文件目录
        File inputFile = new File(docFile);
        // 输出文件目录
        File outputFile = new File(pdfFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        // 连接openoffice服务
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                "127.0.0.1", 8100);
        connection.connect();
        // 转换word到pdf
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);
        // 关闭连接
        connection.disconnect();
    }
}
