package com.marubi.security.core.service.impl;

import com.marubi.security.core.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

/**
 * @author tmz
 */
@Service
@Slf4j
public class ViewServiceImpl  implements ViewService {

    private ViewControllerRegistry registry;



    @Override
    public void addView(String url, String viewUrl) {
        registry.addViewController(url).setViewName(viewUrl);
    }

    @Override
    public void setRegistry(ViewControllerRegistry registry) {
        this.registry = registry;
    }
}
