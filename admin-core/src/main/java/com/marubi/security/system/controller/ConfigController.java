package com.marubi.security.system.controller;

import com.marubi.security.common.dto.Result;
import com.marubi.security.core.service.impl.FileServiceImpl;
import com.marubi.security.system.entity.ConfigEntity;
import com.marubi.security.system.service.IConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author whh
 */
@RestController
@Slf4j
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    IConfigService iConfigService;
    @Autowired
    FileServiceImpl fileServiceImpl;

    /**
     * 获取配置信息
     *
     * @param id
     * @return
     */
    @PostMapping("/gerConfigGroup")
    public Result gerConfigGroup(Integer id) {
        List<ConfigEntity> list = iConfigService.list();
        list.forEach(item->{
            if (item.getValue().endsWith(".jpg")||item.getValue().endsWith(".png")){
                item.setValue(fileServiceImpl.getAbs(item.getValue()));
            }
        });
        return Result.build(list);
    }

    /**
     * 保存春纪配置信息
     *
     * @param param
     * @return
     */
    @PostMapping("/saveConfigInfo")
    public Result saveConfigInfo(@RequestBody List<ConfigEntity> param) {
        return iConfigService.saveConfigInfo(param);
    }
}

