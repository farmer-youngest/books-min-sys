package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限认证：规则表
 * </p>
 *
 * @author tmz
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("jk_auth_rule")
public class AuthRuleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规则唯一标识
     */
    @TableField("`name`")
    private String name;

    /**
     * 规则中文名称
     */
    private String title;

    /**
     * 规则类型，1：url，2：main
     */
    private Boolean type;

    /**
     * 状态：为1正常，为0禁用
     */
    private Boolean status;

    /**
     * 规则表达式，为空表示存在就验证，不为空表示按照条件验证
     */
    @TableField("`condition`")
    private String condition;

    /**
     * 父级规则ID
     */
    private Integer parentId;


}
