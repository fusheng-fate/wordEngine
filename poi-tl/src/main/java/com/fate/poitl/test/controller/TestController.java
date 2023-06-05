package com.fate.poitl.test.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import com.fate.poitl.test.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author lgb
 * @Description
 * @Date 2023/6/5 11:20
 */
@RestController
@RequestMapping("/v1/poi-tl")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public void testPoi_tl(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment;filename=\""+"out_template.docx"+"\"");
        OutputStream out = response.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(out);
        XWPFTemplate xwpfTemplate = testService.generateTestWord();
        xwpfTemplate.write(bos);
        bos.flush();
        out.flush();
        PoitlIOUtils.closeQuietlyMulti(xwpfTemplate, bos, out);
    }
}
