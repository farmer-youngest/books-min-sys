package com.marubi.security.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marubi.security.system.entity.AuthGroupAccessEntity;
import com.marubi.security.system.service.IAuthGroupAccessService;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.utils.IpUtil;
import com.marubi.security.system.dto.AdminParamDto;
import com.marubi.security.system.dto.LoginDto;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.marubi.security.system.mapper.BackendAdminMapper;
import com.marubi.security.system.service.IBackendAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marubi.security.system.utils.AdminSecureUtil;
import com.marubi.security.system.utils.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台管理员表 服务实现类
 * </p>
 *
 * @author WHH
 * @since 2021-07-31
 */
@Slf4j
@Service
public class BackendAdminServiceImpl extends ServiceImpl<BackendAdminMapper, BackendAdminEntity> implements IBackendAdminService {
    @Autowired
    UserHelper userHelper;
    @Autowired
    AdminSecureUtil adminSecureUtil;
    @Autowired
    IAuthGroupAccessService iAuthGroupAccessService;

    @Override
    public Result login(LoginDto dto, HttpServletRequest request) {
        //判断是否已登录，session是否存在
        Result result = userHelper.checkSession(request, dto.getUsername());
        if (result.isSuccess()) {
            return result;
        }
        LambdaQueryWrapper<BackendAdminEntity> login = Wrappers.lambdaQuery(BackendAdminEntity.class)
                .eq(BackendAdminEntity::getUsername, dto.getUsername())
                .eq(BackendAdminEntity::getPassword, adminSecureUtil.asc(dto.getPassword()))
                .eq(BackendAdminEntity::getStatus, 1);
        int count = count(login);
        if (count > 1) {
            return Result.build(ErrorCode.A0101);
        } else if (count == 1) {
            //登陆成功  查询  用户信息
            BackendAdminEntity entity = getOne(login);
            if (entity != null) {
                String ipAddr = IpUtil.getIpAddr(request);
                log.info("最后登录ip: {}",ipAddr);
                //更新最后登录IP
                entity.setLastLoginIp(ipAddr);
                //更新最后登陆时间
                entity.setLastLoginTime(LocalDateTime.now());
                updateById(entity);
                return userHelper.createToken(request, entity);
            }
        }

        return Result.build(ErrorCode.B0005);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updCurrInfo(BackendAdminEntity backendAdminEntity, HttpServletRequest request) {
        backendAdminEntity.setUpdatedAt(LocalDateTime.now());
        Result result = Result.build(ErrorCode.A0201);
        try{
            boolean isSuccess = updateById(backendAdminEntity);
            if (isSuccess) {
                //更新成功则更新token
                return userHelper.createToken(request, backendAdminEntity);
            } else {
                return Result.build(ErrorCode.A0201);
            }
        }catch (Exception e) {
            result = Result.build("-1",e.getMessage());
            log.error("修改个人资料失败->{}",e.getMessage());
            throw e;
        }
    }

    @Override
    public Result<IPage<BackendAdminEntity>> getUsers(AdminParamDto param) {
        LambdaQueryWrapper<BackendAdminEntity> lambdaQuery = Wrappers.lambdaQuery(BackendAdminEntity.class);
        // 判断是否带查询参数，没有则全查询
        if (StrUtil.isNotBlank(param.getUsername())) {
            lambdaQuery.like(BackendAdminEntity::getUsername, param.getUsername());
        }
        if (StrUtil.isNotBlank(param.getEmail())) {
            lambdaQuery.eq(BackendAdminEntity::getEmail, param.getEmail());
        }
        if (StrUtil.isNotBlank(param.getMobile())) {
            lambdaQuery.eq(BackendAdminEntity::getMobile, param.getMobile());
        }
        if (null != param.getStatus() && StrUtil.isNotBlank(param.getStatus().toString())) {
            lambdaQuery.eq(BackendAdminEntity::getStatus, param.getStatus());
        }
        return Result.build(page(new Page<>(param.getPageNum(), param.getPageSize()), lambdaQuery));
    }

    @Override
    public Result userDetail(Integer id) {
        BackendAdminEntity entity = getById(id);
        return Result.build(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result userEdit(BackendAdminEntity param) {
        //设置修改时间
        param.setUpdatedAt(LocalDateTime.now());
        boolean sqlRes ;
        Result result = Result.build(ErrorCode.A0201);
        LambdaUpdateWrapper<BackendAdminEntity> updateWrapper = Wrappers.lambdaUpdate(BackendAdminEntity.class)
                .set(BackendAdminEntity::getUsername, param.getUsername())
                .set(BackendAdminEntity::getEmail, param.getEmail())
                .set(BackendAdminEntity::getMobile, param.getMobile())
                .set(BackendAdminEntity::getStatus, param.getStatus())
                .eq(BackendAdminEntity::getId, param.getId());
        try{
            // 如果密码不为空，则加密存储
            if (StrUtil.isNotBlank(param.getPassword())) {
                AdminSecureUtil adminSecureUtil = new AdminSecureUtil();
                param.setPassword(adminSecureUtil.asc(param.getPassword()));
                updateWrapper.set(BackendAdminEntity::getPassword,param.getPassword());
            }
            sqlRes = update(updateWrapper);
            if (sqlRes) {
                return Result.SUCCESS;
            }
        }catch (Exception e){
            result=Result.build("-1",e.getMessage());
            log.error("修改管理员信息失败->{}",e.getMessage());
            throw e;
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result userDelete(BackendAdminEntity param) {
        Result result = null;
        //1.判断是否存在该用户，存在则删除，如锁定用户则不可删除
        try{
            BackendAdminEntity entity = getById(param.getId());
            //存在且非锁定状态才删除
            if (null != entity && 0 == param.getIsFixed() ){
                //2.先将该用户从对应管理员组中移除（没有就不删）
                LambdaQueryWrapper<AuthGroupAccessEntity> authWrapper = Wrappers.lambdaQuery(AuthGroupAccessEntity.class)
                        .eq(AuthGroupAccessEntity::getUid,param.getId());
                List<AuthGroupAccessEntity> authList = iAuthGroupAccessService.list(authWrapper);
                if(null != authList && authList.size()>0){
                    boolean authDelRes = iAuthGroupAccessService.remove(authWrapper);
                    if (authDelRes) {
                        //3.最后删除用户
                        boolean delRes = removeById(param.getId());
                        if (delRes){
                            result = Result.SUCCESS;
                        } else {
                            result = Result.build(ErrorCode.A0012);
                        }
                    } else {
                        result = Result.build("-1","移除用户对应管理员组失败");
                        log.info("移除用户对应管理员组失败");
                    }
                } else {
                    //3.最后删除用户
                    boolean delRes = removeById(param.getId());
                    if (delRes){
                        result = Result.SUCCESS;
                    } else {
                        result = Result.build(ErrorCode.A0012);
                    }
                }

            } else {
                result = Result.build(ErrorCode.A0102);
                log.info("用户不存在->{}",entity);
            }
        }catch (Exception e) {
            result = Result.build("-1",e.getMessage());
            log.error("操作用户异常->{}",e.getMessage());
            throw e;
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result userAdd(BackendAdminEntity param) {
        Result result = Result.build(ErrorCode.B0001);
        boolean sqlRes ;
        try{
            //判断用户名是否重复
            LambdaQueryWrapper<BackendAdminEntity> existUser = Wrappers.lambdaQuery(BackendAdminEntity.class)
                    .eq(BackendAdminEntity::getUsername, param.getUsername());
            int count = count(existUser);
            if (count >= 1) {
                result=Result.build(ErrorCode.A0202);
                log.info(ErrorCode.A0202.getMsg()+"-->"+param.getUsername());
                return result;
            }
            //创建时间
            param.setCreatedAt(LocalDateTime.now());
            //修改时间
            param.setUpdatedAt(LocalDateTime.now());
            //最后登陆时间
            param.setLastLoginTime(LocalDateTime.now());
            //加密
            AdminSecureUtil adminSecureUtil = new AdminSecureUtil();
            param.setPassword(adminSecureUtil.asc(param.getPassword()));
            sqlRes = save(param);
            if (sqlRes) {
                return Result.SUCCESS;
            }
        }catch (Exception e) {
            result=Result.build("-1",e.getMessage());
            log.error("新增用户失败->{}",e.getMessage());
            throw e;
        }
        return result;
    }
}
