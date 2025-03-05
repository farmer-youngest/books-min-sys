package com.marubi.security.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.exceptions.BaseBusinessException;
import com.marubi.security.system.MenuType;
import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单相关服务
 * @author tmz
 * @date 2021/8/23
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private ISysMenuService sysMenuService;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IAuthGroupMapperService authGroupMapperService;
    @Autowired
    private IGroupMapperService groupMapperService;
    @Autowired
    private IAuthRuleService authRuleService;
    @Override
    public Result<List<SysMenuEntity>> getViewAll() {
        LambdaQueryWrapper<SysMenuEntity> query = Wrappers.lambdaQuery(SysMenuEntity.class);
        query.eq(SysMenuEntity::getDelStatus,0)
                .eq(SysMenuEntity::getEnableStatus,1)
                .in(SysMenuEntity::getType,
                        MenuType.MENU.ordinal(),MenuType.SEPARATE.ordinal());
        return Result.build(sysMenuService.list(query));
    }

    @Override
    public Result<List<Tree<Integer>>> viewMenuList() {
        //todo 根据用户id查用户权限，再根据权限查对应菜单显示
        LambdaQueryWrapper<SysMenuEntity> query = Wrappers.lambdaQuery(SysMenuEntity.class);
        query.eq(SysMenuEntity::getDelStatus,0)
                .eq(SysMenuEntity::getEnableStatus,1)
                .in(SysMenuEntity::getType, MenuType.MENU.ordinal(),MenuType.MODULE.ordinal())
                .orderByAsc(SysMenuEntity::getSort);
        List<SysMenuEntity> list = sysMenuService.list(query);
        TreeNodeConfig config = TreeNodeConfig.DEFAULT_CONFIG;
        config.setNameKey("name")
                .setParentIdKey("parentId")
                .setChildrenKey("children")
                .setIdKey("id")
                .setDeep(2);
        List<Tree<Integer>> build = TreeUtil.build(list, 0, (item, treeNode) -> {
            treeNode.setId(item.getId())
                    .setParentId(item.getParentId())
                    .setName(item.getModuleName())
                    .putExtra("url",item.getMapperUrl());
            treeNode.putExtra("icon",item.getIcon());
            treeNode.putExtra("uniq",item.getLabelUnq());
        });
        return Result.build(build);
    }

    @Override
    public Result<List<Tree<Integer>>> viewMenuListByGroupId(HttpServletRequest req,Integer uid) {
        LambdaQueryWrapper<SysMenuEntity> query = Wrappers.lambdaQuery(SysMenuEntity.class);
        query.eq(SysMenuEntity::getDelStatus,0)
                .eq(SysMenuEntity::getEnableStatus,1)
                .in(SysMenuEntity::getType, MenuType.MENU.ordinal(),MenuType.MODULE.ordinal())
                .orderByAsc(SysMenuEntity::getSort)
//                    .in(SysMenuEntity::getId,meunIds)
        ;

        List<SysMenuEntity> menu = sysMenuService.list(query);
        req.getSession().setAttribute("sysMenuList",menu);
        req.getSession().setAttribute("authUrlList",menu);
//        req.getSession().setAttribute("authforUser",mapperUserDto);
//        req.getSession().setAttribute("authId",mapperUserDto.getAuthIds());
        return Result.build(viewMenuList(menu));
    }

    @Override
    public Result<List<String>> getRequestUrlByUserId(Integer uid) {
        List<AuthMapperUserDto> authByUId = authService.getAuthByUId(uid);
        return Result.build(authByUId.stream().map(AuthMapperUserDto::getMapperUrl).collect(Collectors.toList()));
    }

    @Override
    public Result<List<AuthMapperUserDto>> getRequestDtoByUserId(Integer uid) {
        return Result.build( authService.getAuthByUId(uid));
    }

    @Override
    public Result<List<SysMenuEntity>> getMenuViewList() {
        LambdaQueryWrapper<SysMenuEntity> query = Wrappers.lambdaQuery(SysMenuEntity.class);
        query.eq(SysMenuEntity::getDelStatus,0)
                .eq(SysMenuEntity::getEnableStatus,0)
                .in(SysMenuEntity::getType, MenuType.MENU.ordinal(),MenuType.MODULE.ordinal())
                .orderByAsc(SysMenuEntity::getSort);
        List<SysMenuEntity> list = sysMenuService.list(query);
        return Result.build(list);
    }
    public List<Tree<Integer>> viewMenuList(List<SysMenuEntity> list){
        TreeNodeConfig config = TreeNodeConfig.DEFAULT_CONFIG;
        config.setNameKey("name")
                .setParentIdKey("parentId")
                .setChildrenKey("children")
                .setIdKey("id")
                .setDeep(2);
        List<Tree<Integer>> build = TreeUtil.build(list, 0, (item, treeNode) -> {
            treeNode.setId(item.getId())
                    .setParentId(item.getParentId())
                    .setName(item.getModuleName())
                    .putExtra("url",item.getMapperUrl());
            treeNode.putExtra("icon",item.getIcon());
            treeNode.putExtra("uniq",item.getLabelUnq());
        });
        return build;
    }
}
