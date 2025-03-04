package com.marubi.security.common.diy;

import java.lang.annotation.*;

/**
 * 标记在类上 说明当前类是 一个操作组
 * @author tmz
 * @date 2021/8/30
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationGroup {
    /**
     * 说明详情
     *  TODO: 2021/8/30  只是一个注释
     * @return
     */
    String value() default "";
}
