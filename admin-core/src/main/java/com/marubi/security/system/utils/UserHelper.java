package com.marubi.security.system.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.marubi.security.system.service.IBackendAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * JWT（Java Web Token） 令牌=token=String
 * 登陆相关生成 JWT工具类
 */
@Slf4j
@Component
public class UserHelper {
    @Autowired
    private IBackendAdminService backendAdminService;
    @Value("${marubi.jwt.key: nice}")
    private String key;

    /**
     * 生成 token
     *
     * @param entity
     * @return
     */
    public String creatToken(BackendAdminEntity entity) {
        Date now = new Date();
        String token = JWT.create()
                .setPayload(Contants.INFO, JSONUtil.toJsonStr(entity))
                .setPayload(Contants.USERID, entity.getId())
                .setPayload(Contants.USERNAME, entity.getUsername())
                .setKey(StrUtil.bytes(key, CharsetUtil.UTF_8))
                .setIssuedAt(now)
                .setExpiresAt(DateUtil.offsetHour(now, 24))
                .sign();
        return token;
    }

    /**
     * 校验token 并获取 用户信息
     *
     * @param token
     * @return
     */
    public Result<BackendAdminEntity> checkAndReturnInfo(String token) {
        //check 有效性
        try {
            JWT jwt = JWT.of(token).setKey(StrUtil.bytes(key, CharsetUtil.UTF_8));
            if (jwt.verify()) {
                Object payload = jwt.getPayload(Contants.INFO);
                String jsonStrOfInfo = StrUtil.toString(payload);
                BackendAdminEntity entity = JSONUtil.toBean(jsonStrOfInfo, BackendAdminEntity.class);
                if (entity != null) {
                    return Result.build(entity);
                }
            }
        } catch (Exception e) {
            log.error("token格式不正确->{}", e);
        }
        //失效或者失败
        return Result.FRONTEND_ERR;
    }

    /**
     * 校验 token的有效性
     * ps:超时也是无效的
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        JWT jwt = JWT.of(token).setKey(StrUtil.bytes(key, CharsetUtil.UTF_8));
        return jwt.verify();
    }

    /**
     * 查看session中有没有 token 令牌  并校验登陆用户名是否一致
     *
     * @param request
     * @param username
     * @return
     */
    public Result checkSession(HttpServletRequest request, String username) {
        HttpSession session = request.getSession();
        if (session != null) {
            if (StrUtil.isNotBlank(StrUtil.toString(session.getAttribute(Contants.TOKEN))) &&
                    StrUtil.equals(StrUtil.toString(session.getAttribute(Contants.USERNAME))
                            , username)) {
                log.info("token信息为->{}", session.getAttribute(Contants.TOKEN));
                return Result.SUCCESS;
            }
        }
        return Result.FRONTEND_ERR;
    }

    /**
     * 校验当前请求是不是已经登陆过的
     *
     * @param req
     * @return
     */
    public boolean checkHandler(HttpServletRequest req) {
        HttpSession session = req.getSession();
        boolean check = false;
        if (session != null) {
            if (session.getAttribute(Contants.TOKEN) != null &&
                    session.getAttribute(Contants.USERNAME) != null) {
                check = checkToken((String) session.getAttribute(Contants.TOKEN));
            }
        }
        if (!check) {
            String token = ServletUtil.getHeader(req, "Authorization", CharsetUtil.UTF_8);
            if (StrUtil.startWithIgnoreCase(token, "Bearer ")) {
                token = StrUtil.replaceIgnoreCase(token, "Bearer ", "");
                check = checkToken(token);
            }
        }
        return check;
    }

    /**
     * 根据当前请求获取用户信息
     *
     * @param req
     * @return
     */
    public Result<BackendAdminEntity> getCurrentUserInfo(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session != null) {
            if (StrUtil.isNotBlank(StrUtil.toString(session.getAttribute(Contants.TOKEN))) &&
                    StrUtil.isNotBlank(StrUtil.toString(session.getAttribute(Contants.USERNAME)))) {
                return checkAndReturnInfo(StrUtil.toString(session.getAttribute(Contants.TOKEN)));
            }
        }
        String token = ServletUtil.getHeader(req, "Authorization", CharsetUtil.UTF_8);
        if (StrUtil.startWithIgnoreCase(token, "Bearer ")) {
            token = StrUtil.replaceIgnoreCase(token, "Bearer ", "");
            return checkAndReturnInfo(token);
        }
        return Result.FRONTEND_ERR;
    }

    public BackendAdminEntity getCurrentUserInfo() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return getCurrentUserInfo(request).getData();
        }
        return null;
    }

    //创建token
    public Result createToken(HttpServletRequest request, BackendAdminEntity entity) {
        String token = creatToken(entity);
        HttpSession session = request.getSession(true);
        session.setAttribute(UserHelper.Contants.TOKEN, token);
        session.setAttribute(UserHelper.Contants.USERID, entity.getId());
        session.setAttribute(UserHelper.Contants.USERNAME, entity.getUsername());
        session.setAttribute(UserHelper.Contants.EMAIL, entity.getEmail());
        session.setAttribute(UserHelper.Contants.MOBILE, entity.getMobile());
        return Result.SUCCESS;
    }

    public String geUserName() {
        BackendAdminEntity currentUserInfo = getCurrentUserInfo();

        if (currentUserInfo != null) {
            return currentUserInfo.getUsername();
        }
        return StrUtil.EMPTY;
    }

    public Integer geUserId() {
        BackendAdminEntity currentUserInfo = getCurrentUserInfo();
        if (currentUserInfo != null) {
            return currentUserInfo.getId();
        }
        return null;
    }

    public String getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = null;
        if (attributes != null) {
            HttpServletRequest req = attributes.getRequest();
            HttpSession session = req.getSession();
            if (session != null) {
                if (StrUtil.isNotBlank(StrUtil.toString(session.getAttribute(Contants.TOKEN))) &&
                        StrUtil.isNotBlank(StrUtil.toString(session.getAttribute(Contants.USERNAME)))) {
                    token = StrUtil.toString(session.getAttribute(Contants.TOKEN));
                    return token;
                }
            }
            token = ServletUtil.getHeader(req, "Authorization", CharsetUtil.UTF_8);
        }
        return token;
    }

    private HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession();
            return session;
        }
        return null;
    }

    public interface Contants {
        /**
         * 存放 用户信息的 json字符串
         */
        String INFO = "info";
        String USERID = "userId";
        String USERNAME = "userName";
        String EMAIL = "email";
        String MOBILE = "mobile";
        String TOKEN = "token";
    }
}
