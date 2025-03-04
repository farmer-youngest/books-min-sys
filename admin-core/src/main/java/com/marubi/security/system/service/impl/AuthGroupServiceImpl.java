package com.marubi.security.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.dto.AuthParamDto;
import com.marubi.security.system.entity.*;
import com.marubi.security.system.mapper.AuthGroupMapper;
import com.marubi.security.system.service.IAuthGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marubi.security.system.service.IAuthRuleService;
import com.marubi.security.system.service.IBackendMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员组明细表 服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-06
 */
@Service
@Slf4j
public class AuthGroupServiceImpl extends ServiceImpl<AuthGroupMapper, AuthGroupEntity> implements IAuthGroupService {

    @Autowired
    AuthGroupAccessServiceImpl authGroupAccessServiceImpl;

    @Autowired
    IAuthRuleService authRuleService;

    @Override
    public Result getAuthGroupList(AuthParamDto param) {
        LambdaQueryWrapper<AuthGroupEntity> queryWrapper = Wrappers.lambdaQuery(AuthGroupEntity.class);
        // 判断是否带查询参数，没有则全查询
        if (StrUtil.isNotBlank(param.getTitle())) {
            queryWrapper.like(AuthGroupEntity::getTitle, param.getTitle());
        }
        if (null != param.getStatus() && StrUtil.isNotBlank(param.getStatus().toString())) {
            queryWrapper.eq(AuthGroupEntity::getStatus, param.getStatus());
        }
        return Result.build(page(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result authAdd(AuthGroupEntity groupEntity) {
        Result result = Result.build(ErrorCode.B0001);
        boolean sqlRes;
        try {
            //判断管理员组名是否重复
            LambdaQueryWrapper<AuthGroupEntity> lambdaQuery = Wrappers.lambdaQuery(AuthGroupEntity.class)
                    .eq(AuthGroupEntity::getTitle, groupEntity.getTitle());
            int count = count(lambdaQuery);
            if (count >= 1) {
                result = Result.build(ErrorCode.A0203);
                log.info(ErrorCode.A0203.getMsg() + "->{}", groupEntity.getTitle());
                return result;
            }
            sqlRes = save(groupEntity);
            if (sqlRes) {
                return Result.SUCCESS;
            }

        } catch (Exception e) {
            Result.build("-1", e.getMessage());
            log.error("新增管理员组失败->{}", e.getMessage());
            throw e;
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result authDelete(AuthGroupEntity groupEntity) {
        Result result = Result.build(ErrorCode.B0001);
        try {
            //判断该组是否存在用户，若存在不可删除
            LambdaQueryWrapper<AuthGroupAccessEntity> queryWrapper = Wrappers.lambdaQuery(AuthGroupAccessEntity.class)
                    .eq(AuthGroupAccessEntity::getGroupId, groupEntity.getId());
            int count = authGroupAccessServiceImpl.count(queryWrapper);
            if (count > 0) {
                result = Result.build(ErrorCode.A0204);
                log.info(ErrorCode.A0204.getMsg() + "->{}", groupEntity.getTitle());
                return result;
            }
            boolean sqlRes = removeById(groupEntity.getId());
            if (sqlRes) {
                return Result.SUCCESS;
            }
        } catch (Exception e) {
            Result.build("-1", e.getMessage());
            log.error("删除管理员组失败->{}", e.getMessage());
            throw e;
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result authEdit(AuthGroupEntity groupEntity) {
        Result<Object> result = Result.build(ErrorCode.B0001);
        try {
            LambdaUpdateWrapper<AuthGroupEntity> updateWrapper = Wrappers.lambdaUpdate(AuthGroupEntity.class)
                    .eq(AuthGroupEntity::getId, groupEntity.getId())
                    .set(AuthGroupEntity::getTitle, groupEntity.getTitle())
                    .set(AuthGroupEntity::getDescription, groupEntity.getDescription())
                    .set(AuthGroupEntity::getStatus, groupEntity.getStatus());
            boolean sqlRes = update(updateWrapper);
            if (sqlRes) {
                return Result.SUCCESS;
            }
        } catch (Exception e) {
            Result.build("-1", e.getMessage());
            log.error("更新管理员组失败->{}", e.getMessage());
            throw e;
        }
        return result;
    }

    @Override
    public Result<List<Tree<String>>> getAuthAccessTree(Integer id) {
        //查当前用户组的权限
        String curAccess = getById(id).getRules();
        String temp = ",%s,";
        final String checkedStr = String.format(temp, curAccess);

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        List<AuthRuleEntity> ruleEntities = authRuleService.list();
        //返回参数属性名对应
        treeNodeConfig.setChildrenKey("children")
                .setNameKey("label")
                .setIdKey("id");
        //树的最大递归深度
        treeNodeConfig.setDeep(3);
        List<Tree<String>> treeNodes = TreeUtil.build(ruleEntities, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setName(treeNode.getTitle());
                    //节点是否为禁用状态。默认 false
                    tree.putExtra("disabled", false);

                    //判断规则id是否在当前操作组的规则字符串中
                    if (checkedStr.indexOf(String.format(temp, treeNode.getId())) != -1) {
                        tree.putExtra("checked", true);
                    } else {
                        tree.putExtra("checked", false);
                    }

                });

        return Result.build(treeNodes);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveAuthAccess(Map<String, Object> param) {
        Result result = Result.build(ErrorCode.B0001);
        String ruleString = "";
        try{
            ArrayList<Map> elList = (ArrayList) param.get("el");
            for (int i = 0; i < elList.size(); i++) {
                if (i + 1 == elList.size()) {
                    ruleString = ruleString + elList.get(i).get("id");
                } else {
                    ruleString = ruleString + elList.get(i).get("id") + ",";
                }
            }
            LambdaUpdateWrapper<AuthGroupEntity> updateWrapper = Wrappers.lambdaUpdate(AuthGroupEntity.class)
                    .eq(AuthGroupEntity::getId, param.get("id"))
                    .set(AuthGroupEntity::getRules, ruleString);

            boolean sqlRes = update(updateWrapper);

            if (sqlRes) {
                return Result.SUCCESS;
            } else {
                result = Result.build(ErrorCode.A0201);
            }
        }catch (Exception e){
            log.error("保存用户组授权规则失败->{}",e.getMessage());
            throw e;
        }

        return result;
    }
}
