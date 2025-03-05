package com.marubi.security.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.marubi.security.business.dto.BookEditParamDto;
import com.marubi.security.business.dto.BookPageParamDto;
import com.marubi.security.business.entity.BooksEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marubi.security.business.vo.ShowBookVo;

/**
 * <p>
 * 图书信息表 服务类
 * </p>
 *
 * @author tmz
 * @since 2025-03-05
 */
public interface IBooksService extends IService<BooksEntity> {
    /**
     * 创建书籍
     * @param add
     * @return
     */
    boolean add(BookEditParamDto add);

    /**
     * 修改书籍
     * @param edit
     * @return
     */
    boolean edit(BookEditParamDto edit);

    /**
     * 删除书籍
     * @param delId
     * @return
     */
    boolean del(Integer delId);

    /**
     * 根据Id获取对应的书籍
     * @param id
     * @return
     */
    ShowBookVo getVoById(Integer id);

    /**
     * 分页查询
     * @param paramDto
     * @return
     */
    IPage<ShowBookVo> page(BookPageParamDto paramDto);
}
