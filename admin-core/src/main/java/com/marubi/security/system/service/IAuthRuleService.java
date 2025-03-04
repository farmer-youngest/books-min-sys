package com.marubi.security.system.service;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthRuleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限认证：规则表 服务类
 * </p>
 *
 * @author tmz
 * @since 2021-08-18
 */
public interface IAuthRuleService extends IService<AuthRuleEntity> {
    List<AuthMapperUserDto> getAuthDtoListById(Integer uid);
}
