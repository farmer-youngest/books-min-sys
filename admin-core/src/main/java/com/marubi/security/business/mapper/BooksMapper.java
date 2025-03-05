package com.marubi.security.business.mapper;

import com.marubi.security.business.entity.BooksEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 图书信息表 Mapper 接口
 * </p>
 *
 * @author tmz
 * @since 2025-03-05
 */
@CacheNamespace
public interface BooksMapper extends BaseMapper<BooksEntity> {

}
