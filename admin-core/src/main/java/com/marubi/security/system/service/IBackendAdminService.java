package com.marubi.security.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AdminParamDto;
import com.marubi.security.system.dto.LoginDto;
import com.marubi.security.system.entity.BackendAdminEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 后台管理员表 服务类
 * </p>
 *
 * @author WHH
 * @since 2021-07-31
 */
public interface IBackendAdminService extends IService<BackendAdminEntity> {
    /**
     * 登陆成功返回 token（令牌）
     *
     * @param dto
     * @param request
     * @return
     */
    public Result login(LoginDto dto, HttpServletRequest request);

    /**
     * 修改个人资料
     *
     * @param backendAdminEntity
     * @return
     */
    public Result updCurrInfo(BackendAdminEntity backendAdminEntity, HttpServletRequest request);

    /**
     * 获取用户列表
     *
     * @param param
     * @return
     */
    public Result<IPage<BackendAdminEntity>> getUsers(AdminParamDto param);

    /**
     * 查看管理员详情
     *
     * @param id
     * @return
     */
    public Result userDetail(Integer id);

    /**
     * 修改管理员信息
     *
     * @param param
     * @return
     */
    public Result userEdit(BackendAdminEntity param);

    /**
     * 删除管理员（关联删除）
     *
     * @param param
     * @return
     */
    public Result userDelete(BackendAdminEntity param);

    /**
     * 添加用户
     * @param param
     * @return
     */
    public Result userAdd(BackendAdminEntity param);
}
