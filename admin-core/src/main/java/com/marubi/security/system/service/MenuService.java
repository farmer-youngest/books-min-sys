package com.marubi.security.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.SysMenuEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 菜单操作相关服务
 * @author hhw
 * @date 2021/8/23
 */
public interface MenuService {
    /**
     * 获取所有视图映射用于初始化
     * @author hhw
     * @date 2021/8/23
     * @return com.marubi.security.common.dto.Result<java.util.List<com.marubi.security.system.entity.SysMenuEntity>>
     */
    Result<List<SysMenuEntity>> getViewAll();
    /**
     * 获取菜单和模块相关集合
     * @author hhw
     * @date 2021/8/23
     * @return com.marubi.security.common.dto.Result<java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>>
     */
    Result<List<Tree<Integer>>> viewMenuList();
    /**
     * 根据userID获取菜单集合
     * @author hhw
     * @date 2021/8/23
     * @param [id]
     * @return com.marubi.security.common.dto.Result<java.util.List<cn.hutool.core.lang.tree.Tree<java.lang.String>>>
     */
    Result<List<Tree<Integer>>> viewMenuListByGroupId(HttpServletRequest req,Integer uid);

    Result<List<String>> getRequestUrlByUserId(Integer uid);
    Result<List<AuthMapperUserDto>> getRequestDtoByUserId(Integer uid);

    Result<List<SysMenuEntity>> getMenuViewList();
}
