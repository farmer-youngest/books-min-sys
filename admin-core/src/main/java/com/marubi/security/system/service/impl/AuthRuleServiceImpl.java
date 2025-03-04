package com.marubi.security.system.service.impl;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthRuleEntity;
import com.marubi.security.system.mapper.AuthRuleMapper;
import com.marubi.security.system.service.IAuthRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限认证：规则表 服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-18
 */
@Service
public class AuthRuleServiceImpl extends ServiceImpl<AuthRuleMapper, AuthRuleEntity> implements IAuthRuleService {

    @Override
    public List<AuthMapperUserDto> getAuthDtoListById(Integer uid) {
        return getBaseMapper().selectAuthByUid(uid);
    }
}
