package com.marubi.security.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.marubi.security.system.dto.AuthParamDto;
import com.marubi.security.system.entity.AuthGroupEntity;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.marubi.security.system.service.IAuthGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tmz
 */
@RestController
@Slf4j
@RequestMapping("/authGroup")
public class AuthGroupController {
    @Autowired
    IAuthGroupService iAuthGroupService;

    /**
     * 获取用户组列表
     *
     * @param param
     * @return
     */
    @PostMapping("/getAuthGroupList")
    public Result<IPage<AuthGroupEntity>> getAuthGroupList(@RequestBody AuthParamDto param) {
        return iAuthGroupService.getAuthGroupList(param);
    }

    /**
     * 添加管理员组
     *
     * @param groupEntity
     * @return
     */
    @PostMapping("/authAdd")
    public Result authAdd(@RequestBody AuthGroupEntity groupEntity) {
        return iAuthGroupService.authAdd(groupEntity);
    }

    /**
     * 删除用户组
     *
     * @param groupEntity
     * @return
     */
    @PostMapping("/authDelete")
    public Result authDelete(@RequestBody AuthGroupEntity groupEntity) {
        return iAuthGroupService.authDelete(groupEntity);
    }

    /**
     * 编辑用户组
     *
     * @param groupEntity
     * @return
     */
    @PostMapping("/authEdit")
    public Result authEdit(@RequestBody AuthGroupEntity groupEntity) {
        return iAuthGroupService.authEdit(groupEntity);
    }

    /**
     * 用户组访问授权
     *
     * @param
     * @return
     */
    @PostMapping("/getAuthAccessTree")
    public Result<List<Tree<String>>> getAuthAccessTree(Integer id) {
        return iAuthGroupService.getAuthAccessTree(id);
    }

    /**
     * 保存用户授权
     *
     * @param param
     * @return
     */
    @PostMapping("/saveAuthAccess")
    public Result saveAuthAccess(@RequestBody Map<String,Object> param){
        return iAuthGroupService.saveAuthAccess(param);
    }

}
