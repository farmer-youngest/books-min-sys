package com.marubi.security.core.service;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public interface ViewService {
    /**
     * 动态添加视图
     * @author tmz
     * @date 2021/8/23
     * @param url
     * @param viewUrl
     * @return void
     */
    void addView(String url,String viewUrl);

    void setRegistry(ViewControllerRegistry registry);
}
