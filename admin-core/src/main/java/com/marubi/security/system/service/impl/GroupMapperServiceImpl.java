package com.marubi.security.system.service.impl;

import com.marubi.security.system.entity.GroupMapperEntity;
import com.marubi.security.system.mapper.GroupMapperMapper;
import com.marubi.security.system.service.IGroupMapperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限组映射表 服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-23
 */
@Service
public class GroupMapperServiceImpl extends ServiceImpl<GroupMapperMapper, GroupMapperEntity> implements IGroupMapperService {

}
