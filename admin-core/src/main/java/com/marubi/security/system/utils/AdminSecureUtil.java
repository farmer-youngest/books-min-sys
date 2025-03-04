package com.marubi.security.system.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SecureUtil.md5
 * @author whh
 */
@Component
@Slf4j
public class AdminSecureUtil {
    @Value("${admin.solt: amazing}")
    private String adminSolt="amazing";

    /**
     * 加密密码
     * @param password
     * @return
     */
    public String asc(String password){
        return SecureUtil.md5().digestHex(new StringBuilder(password)
                .append(adminSolt).toString(), CharsetUtil.UTF_8);
    }

    /**
     * 校验密码
     * @param target 未加密的密码
     * @param source 数据库中加密的密码
     * @return
     */
    public Boolean checkPassword(String target,String source){
        return StrUtil.equals(asc(target),source);
    }

    public static void main(String[] args) {
        String res = new AdminSecureUtil().asc("chunji");
        System.out.println(res);
    }
}
