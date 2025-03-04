package com.marubi.security.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.utils.UploadCommonUtils;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单相关数据接口
 * 不返回视图
 *
 * @author tmz
 * @date 2021/8/23
 */
@RestController
@RequestMapping(value = "/sysMenu",produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class SysMenuController {
    @Autowired
    ISysMenuService iSysMenuService;

    @GetMapping("/icon")
    public Result<List<String>>  icon(){
        return Result.build(UploadCommonUtils.getInstance().iconAll());
    }

    /**
     * 获取菜单列表
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public List<SysMenuEntity> getMenuList(){
        List<SysMenuEntity> menuEntities = iSysMenuService.list(Wrappers.lambdaQuery(SysMenuEntity.class)
                .eq(SysMenuEntity::getDelStatus,0)
                .in(SysMenuEntity::getType, 0, 1, 3)
                .orderByAsc(SysMenuEntity::getSort));
        return menuEntities;
    }

    /**
     * 获取菜单树形下拉选项
     * @return
     */
    @GetMapping("/getMenuTreeSelect")
    public List<Tree<String>> getMenuTreeSelect () {
        return iSysMenuService.getMenuTreeSelect();
    }
    /**
     * 获取指定菜单详情
     *
     * @param sysMenuEntity
     * @return
     */
    @PostMapping("/getMenuDetail")
    public Result getMenuDetail (@RequestBody SysMenuEntity sysMenuEntity){
        return iSysMenuService.getMenuDetail(sysMenuEntity.getId());
    }

    /**
     * 保存菜单信息
     * @param sysMenuEntity
     * @return
     */
    @PostMapping("/saveMenuInfo")
    public Result saveMenuInfo(@RequestBody SysMenuEntity sysMenuEntity, HttpServletRequest request) {
        return  iSysMenuService.saveMenuInfo(sysMenuEntity,request);
    }
    /**
     * 逻辑删除菜单信息
     * @param sysMenuEntity-id
     * @param request
     * @return
     */
    @PostMapping("/deleteMenu")
    public Result deleteMenu(@RequestBody SysMenuEntity sysMenuEntity, HttpServletRequest request) {
        return  iSysMenuService.deleteMenu(sysMenuEntity.getId(),request);
    }
}
