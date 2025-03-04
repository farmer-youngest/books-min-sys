package com.marubi.security.system.mapper;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthRuleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限认证：规则表 Mapper 接口
 * </p>
 *
 * @author tmz
 * @since 2021-08-18
 */
public interface AuthRuleMapper extends BaseMapper<AuthRuleEntity> {
    List<AuthMapperUserDto> selectAuthByUid(@Param("uid") Integer uid);

}
