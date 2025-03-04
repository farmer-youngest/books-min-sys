package com.marubi.security.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.ConfigEntity;
import com.marubi.security.system.mapper.ConfigMapper;
import com.marubi.security.system.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-26
 */
@Service
@Slf4j
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigEntity> implements IConfigService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveConfigInfo(List<ConfigEntity> param) {
        Result<Object> result = Result.build(ErrorCode.B0001);
        try{
            //根据name查询是否存在记录，是否有多条配置记录
            for (int i = 0; i<param.size();i++) {
                LambdaQueryWrapper<ConfigEntity> queryWrapper = Wrappers.lambdaQuery(ConfigEntity.class)
                        .eq(ConfigEntity::getName, param.get(i).getName());
                List<ConfigEntity> configEntities = list(queryWrapper);
                if (configEntities.size()>0){
                    if (configEntities.size()==1){
                        LambdaUpdateWrapper<ConfigEntity> lambdaUpdate = Wrappers.lambdaUpdate(ConfigEntity.class)
                                .eq(ConfigEntity::getId,configEntities.get(0).getId())
                                .set(ConfigEntity::getValue,param.get(i).getValue());
                        boolean sqlRes = update(lambdaUpdate);
                        if (sqlRes && i == param.size()-1) {
                            return Result.SUCCESS;
                        }
                    }else {
                        result = Result.build(ErrorCode.C0103);
                    }
                }else {
                    result = Result.build(ErrorCode.C0102);
                }
            }
        }catch (Exception e){
            log.error("春纪配置项保存失败->{}",e.getMessage());
            throw e;
        }

        return result;
    }
}
