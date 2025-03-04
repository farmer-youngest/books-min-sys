package com.marubi.security.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whh
 * @since 2021-08-23
 */
public interface ISysMenuService extends IService<SysMenuEntity> {


    /**
     * 获取菜单树形下拉选项
     * @return
     */
    List<Tree<String>> getMenuTreeSelect ();

    /**
     * 根据id获取菜单详情
     *
     * @param id
     * @return
     */
    Result getMenuDetail (Integer id);

    /**
     * 保存菜单信息
     * @param sysMenuEntity
     * @return
     */
    Result saveMenuInfo(SysMenuEntity sysMenuEntity, HttpServletRequest request);

    /**
     * 逻辑删除菜单信息
     * @param id
     * @param request
     * @return
     */
    Result deleteMenu(Integer id, HttpServletRequest request);

}
