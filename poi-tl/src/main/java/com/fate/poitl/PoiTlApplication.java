package com.fate.poitl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoiTlApplication {

    private static final Logger logger = LoggerFactory.getLogger(PoiTlApplication.class);

    public static void main(String[] args) {
        logger.info("hello poi-tl");
        SpringApplication.run(PoiTlApplication.class, args);
    }

}
