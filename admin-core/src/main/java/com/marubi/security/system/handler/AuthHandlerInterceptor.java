package com.marubi.security.system.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AuthMapperUserDto;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.service.IAuthRuleService;
import com.marubi.security.system.service.MenuService;
import com.marubi.security.system.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 校验 登录 拦截器 {@link HandlerInterceptor}
 *
 * @author tmz
 */
@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    UserHelper userHelper;
    @Autowired
    MenuService menuService;
    @Autowired
    IAuthRuleService authRuleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是资源的请求，则放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        String requestURI = request.getRequestURI();
        if(requestURI.indexOf("error")!=-1){
            return true;
        }
        //过滤上传请求  上传请求不需要登录  如果需要  请注释代码
        /***********start*****/
        if (handler instanceof HandlerMethod) {
            HandlerMethod h = (HandlerMethod) handler;
            for (MethodParameter parameter : h.getMethodParameters()) {
                if (parameter.getParameterType().equals(MultipartFile.class)) {
                    return true;
                }
            }
        }
        /*********end*********/
        //判断是否有登录token信息
        if (!userHelper.checkHandler(request)) {
            // 没有就重定向，重新登录
            response.sendRedirect("/login");
            return false;
        }
      /*  //鉴权
        AuthMapperUserDto auth = (AuthMapperUserDto) request.getSession().getAttribute("authforUser");

        if (auth!=null) {
            //管理员 不校验权限
            List<Integer> menusId = StrUtil.split(auth.getAuthIds(), ",", true, true)
                    .stream().map(NumberUtil::parseInt).collect(Collectors.toList());
            if (auth.getAuthIds().equals(1)) {
                return true;
            }
        } else {
            return false;
        }*/
        List<SysMenuEntity> reqUrl = (List<SysMenuEntity>) request.getSession()
                .getAttribute("authUrlList");
        if(CollUtil.isEmpty(reqUrl)){
            return false;
        }
        List<String> url = reqUrl.stream().map(SysMenuEntity::getMapperUrl).collect(Collectors.toList());

        if (CollUtil.isEmpty(url) || url.indexOf(requestURI) == -1) {
            response.sendRedirect("/error/403");
            return false;
        }
        return true;
    }

    /**
     * 校验权限
     *
     * @return boolean
     * @author tmz
     * @date 2021/8/30
     */
    private boolean checkAuth() {
        return true;
    }
}
