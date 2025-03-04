package com.marubi.security.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author tmz
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("m_auth_group_mapper")
public class AuthGroupMapperEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组名
     */
    private String name;

    /**
     * 0:已启用，1:未启用
     */
    private Integer enable;

    private LocalDateTime createTime;

    /**
     * 操作人
     */
    private String operator;


}
