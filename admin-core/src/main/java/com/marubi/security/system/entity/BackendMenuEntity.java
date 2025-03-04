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
 * 后台系统菜单
 * </p>
 *
 * @author whh
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("jk_backend_menu")
public class BackendMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标识
     */
    @TableField("`name`")
    private String name;

    /**
     * 显示名称
     */
    private String title;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 是否隐藏
     */
    private Boolean isHide;

    /**
     * 是否仅开发者模式可见
     */
    private Boolean isDev;

    /**
     * 提示
     */
    private String tip;

    /**
     * 分组
     */
    @TableField("`group`")
    private String group;

    private Boolean isFixed;


}
