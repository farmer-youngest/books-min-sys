package com.marubi.security.system.service;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2021-08-23
 */
public interface IAuthService extends IService<AuthEntity> {
    List<AuthMapperUserDto> getAuthByUId(Integer uid);
}
