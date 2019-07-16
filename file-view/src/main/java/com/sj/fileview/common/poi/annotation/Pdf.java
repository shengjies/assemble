package com.sj.fileview.common.poi.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Pdf {
    /**
     * 表格标题
     * @return
     */
    String name() default "";

    /**
     * 夸列数
     * @return
     */
    int colspan() default 1;
}
