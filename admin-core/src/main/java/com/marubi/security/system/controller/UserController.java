package com.marubi.security.system.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.marubi.security.common.utils.UploadCommonUtils;
import com.marubi.security.system.service.IAuthGroupAccessService;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AdminParamDto;
import com.marubi.security.system.dto.LoginDto;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.marubi.security.system.service.IBackendAdminService;
import com.marubi.security.system.service.MenuService;
import com.marubi.security.system.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    IBackendAdminService iBackendAdminService;
    @Autowired
    IAuthGroupAccessService iAuthGroupAccessService;
    @Autowired
    private MenuService menuService;


    /**
     * 登陆成功返回 token（令牌）
     *
     * @param dto
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginDto dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("登陆传参->{}", dto);
        Result result = iBackendAdminService.login(dto, request);
        if(result.isSuccess()){
            Result<List<Tree<Integer>>> listResult = menuService.viewMenuListByGroupId(request,
                    NumberUtil.parseInt(StrUtil.toString(request.getSession()
                            .getAttribute(UserHelper.Contants.USERID))));
            HttpSession session = request.getSession();
            if (listResult.isSuccess()&&session!=null&&listResult!=null&& CollUtil.isNotEmpty(listResult.getData())) {
                List<String> icons = UploadCommonUtils.getInstance().iconAll();
                session.setAttribute("sysMenu",listResult.getData());
                session.setAttribute("icons",icons);
            }else{
                return listResult;
            }
        }
        return result;

    }

    /**
     * 登出--移除用户登录的token信息
     *
     * @param req
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest req) {
        try {
            req.getSession().removeAttribute(UserHelper.Contants.TOKEN);
            req.getSession().removeAttribute(UserHelper.Contants.USERNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("login");
    }

    /**
     * 修改个人资料
     *
     * @param backendAdminEntity
     * @return
     */
    @PostMapping("/updCurrInfo")
    public Result updCurrInfo(@Valid @RequestBody BackendAdminEntity backendAdminEntity, HttpServletRequest request) {
        return iBackendAdminService.updCurrInfo(backendAdminEntity, request);
    }

    /**
     * 获取用户列表
     *
     * @param param
     * @return
     */
    @PostMapping("/getUsers")
    public Result<IPage<BackendAdminEntity>> getUsers(@RequestBody AdminParamDto param) {
        return iBackendAdminService.getUsers(param);
    }

    /**
     * 查看管理员详情
     *
     * @param id
     * @return
     */
    @PostMapping("/userDetail")
    public Result userDetail(@RequestBody Integer id) {
        return iBackendAdminService.userDetail(id);
    }

    /**
     * 修改管理员信息
     *
     * @param param
     * @return
     */
    @PostMapping("/userEdit")
    public Result userEdit(@RequestBody BackendAdminEntity param) {
        return iBackendAdminService.userEdit(param);
    }

    /**
     * 删除管理员（关联删除）
     *
     * @param param
     * @return
     */
    @PostMapping("/userDelete")
    public Result userDelete(@RequestBody BackendAdminEntity param) {
        return iBackendAdminService.userDelete(param);
    }

    /**
     * 添加用户
     * @param param
     * @return
     */
    @PostMapping("/userAdd")
    public Result userAdd(@RequestBody BackendAdminEntity param) {
        return iBackendAdminService.userAdd(param);
    }
}
