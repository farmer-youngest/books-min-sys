package com.marubi.security.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.BackendMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 后台系统菜单 服务类
 * </p>
 *
 * @author whh
 * @since 2021-08-18
 */
public interface IBackendMenuService extends IService<BackendMenuEntity> {

    /**
     * 获取菜单树
     *
     * @return
     */
    Result<List<Tree<String>>> getMenuTree();
}
