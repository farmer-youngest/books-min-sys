package com.marubi.security.common.diy;

import java.lang.annotation.*;

/**
 * 说明当前方法是一个操作
 * @author tmz
 * @date 2021/8/30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operation {
    /**
     * 当前操作名称
     * {@link com.marubi.security.system.entity.SysMenuEntity&moduleName}
     * @return
     */
    String value() default "";

    /**
     * 当前操作url
     * @return
     */
    String url() default "";

    /**
     * 0 页面 不做权限控制 跳过权限设置
     * {@link com.marubi.security.system.entity.SysMenuEntity&id}
     * @return
     */
    int pageId() default 0;
}
