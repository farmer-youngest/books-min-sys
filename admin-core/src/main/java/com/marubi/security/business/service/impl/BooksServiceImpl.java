package com.marubi.security.business.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marubi.security.business.dto.BookEditParamDto;
import com.marubi.security.business.dto.BookPageParamDto;
import com.marubi.security.business.entity.BooksEntity;
import com.marubi.security.business.mapper.BooksMapper;
import com.marubi.security.business.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marubi.security.business.vo.ShowBookVo;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.marubi.security.system.utils.AdminSecureUtil;
import com.marubi.security.system.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 图书信息表 服务实现类
 * </p>
 *
 * @author tmz
 * @since 2025-03-05
 */
@Service
@Slf4j
public class BooksServiceImpl extends ServiceImpl<BooksMapper, BooksEntity> implements IBooksService {

    @Autowired
    private UserHelper userHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(BookEditParamDto add) {
        add.setBookId(null);
        BooksEntity entity = new BooksEntity();
        BeanUtils.copyProperties(add, entity);
//        ServletUtil.g
        entity.setCreateName(userHelper.geUserName());
        entity.setUpdateName(entity.getCreateName());
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(entity.getCreateTime());
        return save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "getVoById", key = "#edit.bookId")
    @Override
    public boolean edit(BookEditParamDto edit) {
        log.debug("id:{}",edit.getBookId());
        BooksEntity entity = new BooksEntity();
        BeanUtils.copyProperties(edit, entity);
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateName(userHelper.geUserName());
        return updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "getVoById", key = "#delId")
    @Override
    public boolean del(Integer delId) {
        return removeById(delId);
    }

    @Override
    @Cacheable(value = "getVoById", key = "#id")
    public ShowBookVo getVoById(Integer id) {
        BooksEntity entity = getById(id);
        ShowBookVo vo = new ShowBookVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }

    @Override
    public IPage<ShowBookVo> page(BookPageParamDto paramDto) {
        // 判断是否带查询参数，没有则全查询
        Page<BooksEntity> page = page(new Page<>(paramDto.getPageNum(), paramDto.getPageSize()),
                Wrappers.lambdaQuery(BooksEntity.class)
                        .like(StrUtil.isNotBlank(paramDto.getTitle()), BooksEntity::getTitle, paramDto.getTitle())
                        .orderByDesc(BooksEntity::getCreateTime)
        );
        IPage<ShowBookVo> res = new Page<>();
        BeanUtils.copyProperties(page, res, "records");
        List<ShowBookVo> collect = page.getRecords().stream().map(booksEntity -> {
            ShowBookVo vo = new ShowBookVo();
            BeanUtils.copyProperties(booksEntity, vo);
            return vo;
        }).collect(Collectors.toList());
        res.setRecords(collect);
        return res;
    }
}
