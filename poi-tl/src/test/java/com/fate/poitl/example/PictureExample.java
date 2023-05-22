package com.fate.poitl.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.fate.poitl.utils.PathUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author lgb
 * @Description 插入图片
 * 图像标签以@开头，例如，{{@logo}}将在数据模型中查找带有logo键的值，然后将标签替换为图像。与图像标记相对应的数据可以是简单的URL或Path字符串，也可以是包含图像宽度和高度的结构。
 * @Date 2023/5/19 17:24
 */
@SpringBootTest
public class PictureExample {

    @Test
    public void testCreatePicture() throws IOException {
        HashMap<String, Object> map = new HashMap<>() {{
            put("watermelon",PathUtils.getWordTemplateImageUrl("watermelon.png"));
            put("lemon",Pictures.ofLocal(PathUtils.getWordTemplateImageUrl("sob.jpeg")).size(24,24).create());
        }};
        XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("")).render(null).writeToFile(null);
    }
}
