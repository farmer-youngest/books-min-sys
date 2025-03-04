package com.marubi.security.system.ann;


import java.lang.annotation.*;

/**
 * 忽略 token校验
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreCheckLogin {
}
