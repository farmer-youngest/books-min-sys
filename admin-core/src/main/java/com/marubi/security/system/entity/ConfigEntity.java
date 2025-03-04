package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tmz
 * @since 2021-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("jk_config")
public class ConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配置标识，大写
     */
    private String name;

    /**
     * 配置项
     */
    private String title;

    /**
     * 配置说明
     */
    private String remarks;

    /**
     * 配置选项
     */
    private String options;

    /**
     * 配置值
     */
    private String value;

    /**
     * 状态，1：启用、0：禁用、-1：软删除
     */
    private Integer status;

    /**
     * 配置分租
     */
    private String configGroup;

    /**
     * 配置类型
     */
    private String configType;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否锁定
     */
    private Boolean isFixed;

    /**
     * 更新时间
     */
    private Integer updatedAt;

    /**
     * 开发者模式可见
     */
    private Boolean isDev;


}
