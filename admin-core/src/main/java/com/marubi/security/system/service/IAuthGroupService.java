package com.marubi.security.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AuthParamDto;
import com.marubi.security.system.entity.AuthGroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员组明细表 服务类
 * </p>
 *
 * @author whh
 * @since 2021-08-06
 */
public interface IAuthGroupService extends IService<AuthGroupEntity> {

    /**
     * 获取用户组列表
     *
     * @param param
     * @return
     */
    Result getAuthGroupList(AuthParamDto param);

    /**
     * 添加用户组
     *
     * @param groupEntity
     * @return
     */
    Result authAdd(AuthGroupEntity groupEntity);

    /**
     * 删除用户组
     *
     * @param groupEntity
     * @return
     */
    Result authDelete(AuthGroupEntity groupEntity);

    /**
     * 编辑用户组
     *
     * @param groupEntity
     * @return
     */
    Result authEdit(AuthGroupEntity groupEntity);

    /**
     * 用户组访问授权
     *
     * @param
     * @return
     */
    Result<List<Tree<String>>> getAuthAccessTree(Integer id);

    /**
     * 保存用户授权
     *
     * @param param
     * @return
     */
    Result saveAuthAccess(Map<String,Object> param);
}
