package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限组映射表
 * </p>
 *
 * @author tmz
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_group_mapper")
public class GroupMapperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * jk_backend_admin id
     */
    private Integer uid;

    /**
     * m_auth_group
     */
    private Integer groupId;


}
