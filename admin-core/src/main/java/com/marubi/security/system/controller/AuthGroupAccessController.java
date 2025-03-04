package com.marubi.security.system.controller;

import com.marubi.security.common.dto.Result;
import com.marubi.security.system.service.IAuthGroupAccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author tmz
 */
@RestController
@Slf4j
@RequestMapping("/authGroupAccess")
public class AuthGroupAccessController {
    @Autowired
    IAuthGroupAccessService iAuthGroupAccessService;

    /**
     * 管理员信息-设置管理员组权限
     * @param param
     * @param param
     * @return
     */
    @PostMapping("/setUserAuthGroup")
    public Result setUserAuthGroup(@RequestBody Map<String,Object> param){
        return iAuthGroupAccessService.setUserAuthGroup(param);
    }
    /**
     * 管理员信息-获取管理员组权限
     * @param param
     * @return
     */
    @PostMapping("/getUserAuthGroup")
    public Result getUserAuthGroup(@RequestBody Map<String,Integer> param){
        return iAuthGroupAccessService.getUserAuthGroup(param.get("id"));
    }
}
