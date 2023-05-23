package com.fate.poitl.example;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/23 19:03
 */
@SpringBootTest
public class LabelElExample {

    @Test
    public void testElLabel() {
        ConfigureBuilder builder = Configure.builder();
        builder.useSpringEL();
    }
}
