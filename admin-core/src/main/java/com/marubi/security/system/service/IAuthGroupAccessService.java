package com.marubi.security.system.service;

import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.AuthGroupAccessEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2021-08-06
 */
public interface IAuthGroupAccessService extends IService<AuthGroupAccessEntity> {
    /**
     * 管理员信息-管理员组授权
     * @param param
     * @return
     */
    Result setUserAuthGroup( Map<String,Object> param);

    /**
     * 管理员信息-获取管理员组权限
     * @param uid
     * @return
     */
    Result getUserAuthGroup(Integer uid);

}
