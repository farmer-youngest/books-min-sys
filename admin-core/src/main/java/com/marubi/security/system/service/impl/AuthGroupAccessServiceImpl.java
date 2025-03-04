package com.marubi.security.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.AuthGroupAccessEntity;
import com.marubi.security.system.mapper.AuthGroupAccessMapper;
import com.marubi.security.system.service.IAuthGroupAccessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-06
 */
@Service
public class AuthGroupAccessServiceImpl extends ServiceImpl<AuthGroupAccessMapper, AuthGroupAccessEntity> implements IAuthGroupAccessService {
    public static final String ON_FLAG = "on";
    public static final String OFF_FLAG = "off";

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result setUserAuthGroup(Map<String, Object> param) {
        Result result = Result.build(ErrorCode.A0201);

        AuthGroupAccessEntity addEntity = new AuthGroupAccessEntity();
        addEntity.setUid((Integer) param.get("id"));
        try {
            boolean sqlRes = false ;
            //先把原来的删了，再重新插入
            sqlRes = remove(Wrappers.lambdaQuery(AuthGroupAccessEntity.class)
                    .eq(AuthGroupAccessEntity::getUid,param.get("id")));
            if (ON_FLAG.equals(param.get("system")) ) {
                addEntity.setGroupId(1);
                sqlRes=save(addEntity);
            }
            if (ON_FLAG.equals(param.get("chunji"))) {
                addEntity.setGroupId(2);
                sqlRes=save(addEntity);
            }
            if (ON_FLAG.equals(param.get("wanmei"))) {
                addEntity.setGroupId(3);
                sqlRes=save(addEntity);
            }
            if (sqlRes) {
                return Result.SUCCESS;
            }
        }catch (Exception e){
            result = Result.build("-1",e.getMessage());
            log.error("管理员授权失败->{}",e);
            throw e;
        }

        return result;
    }

    @Override
    public Result getUserAuthGroup(Integer uid) {
        LambdaQueryWrapper<AuthGroupAccessEntity> queryWrapper = Wrappers.lambdaQuery(AuthGroupAccessEntity.class).eq(AuthGroupAccessEntity::getUid, uid);
        List<AuthGroupAccessEntity> list = list(queryWrapper);
        return Result.build(list);
    }
}
