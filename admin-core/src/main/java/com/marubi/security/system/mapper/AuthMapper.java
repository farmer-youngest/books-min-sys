package com.marubi.security.system.mapper;

import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.AuthEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tmz
 * @since 2021-08-23
 */
public interface AuthMapper extends BaseMapper<AuthEntity> {

    List<AuthMapperUserDto> getAuthByUId(@Param("uid")Integer uid);
}
