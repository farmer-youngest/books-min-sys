package com.marubi.security.system.service.impl;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthEntity;
import com.marubi.security.system.mapper.AuthMapper;
import com.marubi.security.system.service.IAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-23
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, AuthEntity> implements IAuthService {
    @Override
    public List<AuthMapperUserDto> getAuthByUId(Integer uid) {
        return getBaseMapper().getAuthByUId(uid);
    }
}
