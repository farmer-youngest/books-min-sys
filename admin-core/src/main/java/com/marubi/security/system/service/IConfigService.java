package com.marubi.security.system.service;

import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.ConfigEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tmz
 * @since 2021-08-26
 */
public interface IConfigService extends IService<ConfigEntity> {

    /**
     * 保存春纪配置信息
     *
     * @param param
     * @return
     */
    Result saveConfigInfo(List<ConfigEntity> param);

}
