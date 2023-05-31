package com.fate.poitl.annotions;

import java.lang.annotation.*;

/**
 * @author lgb
 * @Description
 * @Date 2023/5/31 10:40
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableIgnore {

}
