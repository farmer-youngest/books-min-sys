package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台管理员表
 * </p>
 *
 * @author tmz
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("jk_backend_admin")
public class BackendAdminEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String email;

    private String mobile;

    /**
     * 状态，0:禁用、1:启用
     */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 是否锁定，0:否、1:是
     */
    private Integer isFixed;


}
