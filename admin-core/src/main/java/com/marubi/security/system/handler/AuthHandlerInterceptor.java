package com.marubi.security.system.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;
import com.marubi.security.common.codes.ErrorCode;
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
    @Autowired
    ObjectMapper objectMapper;

    private static final List<String> whiteList = Lists.newArrayList(
           "error"
    );
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是资源的请求，则放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        String requestURI = request.getRequestURI();
        for (String path : whiteList) {
            if(requestURI.indexOf(path)!=-1){
                return true;
            }
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
            String accept = request.getHeader("Accept");
            /*if (accept != null && accept.contains("application/json")) {

            } else */
            if (accept != null && accept.contains("text/html")) {
                // 这是一个视图页面请求
                response.sendRedirect("/login");
            }
            response.setStatus(HttpServletResponse.SC_OK); // 设置响应状态码为 200
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.build(ErrorCode.A0004,"未登录")));
            return false;
        }
        //todo  2025-03-05 暂时不做细致鉴权 只做是否登录的权限校验
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
        /*List<SysMenuEntity> reqUrl = (List<SysMenuEntity>) request.getSession()
                .getAttribute("authUrlList");
        if(CollUtil.isEmpty(reqUrl)){
            return false;
        }
        List<String> url = reqUrl.stream().map(SysMenuEntity::getMapperUrl).collect(Collectors.toList());

        if (CollUtil.isEmpty(url) || url.indexOf(requestURI) == -1) {
            String accept = request.getHeader("Accept");
            if (accept != null && accept.contains("text/html")) {
                // 这是一个视图页面请求
                response.sendRedirect("/error/403");
            }
            response.setStatus(HttpServletResponse.SC_OK); // 设置响应状态码为 200
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.build(ErrorCode.A0004,"没有操作权限")));
            return false;
        }*/
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
