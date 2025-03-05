package com.marubi.security.business.service.impl;

import com.marubi.security.business.entity.BooksEntity;
import com.marubi.security.business.mapper.BooksMapper;
import com.marubi.security.business.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书信息表 服务实现类
 * </p>
 *
 * @author tmz
 * @since 2025-03-05
 */
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, BooksEntity> implements IBooksService {

}
