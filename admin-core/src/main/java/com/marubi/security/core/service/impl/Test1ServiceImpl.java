package com.marubi.security.core.service.impl;

import com.marubi.security.core.mapper.Test1Mapper;
import com.marubi.security.core.service.ITest1Service;
import com.marubi.security.core.entity.Test1Entity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TMZ
 * @since 2021-07-28
 */
@Service
public class Test1ServiceImpl extends ServiceImpl<Test1Mapper, Test1Entity> implements ITest1Service {

}
