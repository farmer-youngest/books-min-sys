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
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_auth")
public class AuthEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * m_sys_menu id
     */
    private Integer menuUrlId;

    /**
     * 权限组ID
     */
    private Integer groupId;


}
