package com.marubi.security.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.BackendMenuEntity;
import com.marubi.security.system.mapper.BackendMenuMapper;
import com.marubi.security.system.service.IBackendMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 后台系统菜单 服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-18
 */
@Service
public class BackendMenuServiceImpl extends ServiceImpl<BackendMenuMapper, BackendMenuEntity> implements IBackendMenuService {

    @Override
    public Result<List<Tree<String>>> getMenuTree() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        List<BackendMenuEntity> menuEntities = list();
        //前端属性匹配
        treeNodeConfig.setChildrenKey("children")
                .setNameKey("label")
                .setIdKey("id")
                .setDeep(3);
        List<Tree<String>> treeNodes = TreeUtil.build(menuEntities, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setName(treeNode.getTitle());

                });
        return Result.build(treeNodes);
    }
}
