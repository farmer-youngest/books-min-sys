package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tmz
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_sys_menu")
@Accessors(chain = true)
public class SysMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 视图路径， 模块/操作( type=0/2)的请求路径时可为空
     */
    private String viewUrl;

    /**
     * 映射视图的请求路径，选模块（type=0）时可不填
     */
    private String mapperUrl;

    /**
     * 菜单名，模块名，操作名称
     */
    private String moduleName;

    /**
     * 0：模块，1:二级菜单，2：操作,3:独立页面(弹框)
     * {@link com.marubi.security.system.MenuType}
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 0:未删除，1:已删除
     */
    private Integer delStatus;

    /**
     * 0:已启用，1:未启用
     */
    private Integer enableStatus;

    private LocalDateTime createTime;

    /**
     * 无需更新，mysql自动更新
     */
    private LocalDateTime updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 唯一标识
     */
    private String labelUnq;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否锁定不可删除：0-否 1-是
     */
    private Integer isFixed;

}
