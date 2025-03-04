package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 管理员组明细表
 * </p>
 *
 * @author whh
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("jk_auth_group")
public class AuthGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组名
     */
    private String title;

    /**
     * 用户组描述
     */
    private String description;

    /**
     * 状态：为1正常，为0禁用
     */
    private Integer status;

    /**
     * 用户组拥有的规则id, 多个规则","隔开
     */
    private String rules;


}
