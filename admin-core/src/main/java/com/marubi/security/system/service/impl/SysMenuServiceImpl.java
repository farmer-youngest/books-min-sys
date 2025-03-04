package com.marubi.security.system.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.marubi.security.common.codes.ErrorCode;
import com.marubi.security.common.dto.Result;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.mapper.SysMenuMapper;
import com.marubi.security.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marubi.security.system.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author whh
 * @since 2021-08-23
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {
    @Autowired
    UserHelper userHelper;

    @Override
    public List<Tree<String>> getMenuTreeSelect() {
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        List<SysMenuEntity> menuEntities = list(Wrappers.lambdaQuery(SysMenuEntity.class)
                .eq(SysMenuEntity::getDelStatus,0)
                .in(SysMenuEntity::getType, 0, 1, 3)
                .orderByAsc(SysMenuEntity::getSort));

        //返回参数属性对应
        treeNodeConfig.setChildrenKey("children")
                .setNameKey("name")
                .setIdKey("id")
                .setDeep(4);

        List<Tree<String>> treeNodes = TreeUtil.build(menuEntities, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId() != null ? treeNode.getParentId().toString() : "");
                    tree.setName(treeNode.getModuleName());
                    //是否展开
                    tree.putExtra("open", false);
                });

        Tree<String> rootTree = new Tree<>();
        rootTree.setId("0")
                .setName("根节点")
                .setChildren(treeNodes);
        rootTree.putExtra("open",true);

        return Lists.newArrayList(rootTree);
    }

    @Override
    public Result getMenuDetail(Integer id) {
        SysMenuEntity entity = getById(id);
        return Result.build(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result saveMenuInfo(SysMenuEntity param, HttpServletRequest request) {
        Result result = Result.build(ErrorCode.B0001);
        LambdaUpdateWrapper<SysMenuEntity> updateWrapper = Wrappers.lambdaUpdate(SysMenuEntity.class);
        //无id为新增，否则为修改
        if (null == param.getId()) {
            //唯一标识是否存在
            LambdaQueryWrapper<SysMenuEntity> queryWrapper = Wrappers.lambdaQuery(SysMenuEntity.class).eq(SysMenuEntity::getLabelUnq, param.getLabelUnq());
            int count = count(queryWrapper);
            if (count > 0) {
                return Result.build(ErrorCode.M0101);
            }
            param.setCreateTime(LocalDateTime.now());
            param.setUpdateTime(LocalDateTime.now());
            param.setOperator(userHelper.getCurrentUserInfo(request).getData().getUsername());
            if (save(param)) {
                result = Result.SUCCESS;
            }
        } else {
            //唯一标识是否存在
            LambdaQueryWrapper<SysMenuEntity> queryWrapper = Wrappers.lambdaQuery(SysMenuEntity.class).eq(SysMenuEntity::getLabelUnq, param.getLabelUnq());
            SysMenuEntity one = getOne(queryWrapper);
            //id不相同但唯一标识相同，则已存在
            if (null != one && (!one.getId().equals(param.getId()))) {
                return Result.build(ErrorCode.M0101);
            }
            updateWrapper.eq(SysMenuEntity::getId, param.getId())
                    .set(SysMenuEntity::getParentId, param.getParentId())
                    .set(SysMenuEntity::getModuleName,param.getModuleName())
                    .set(SysMenuEntity::getType, param.getType())
                    .set(SysMenuEntity::getEnableStatus, param.getEnableStatus())
                    .set(SysMenuEntity::getLabelUnq, param.getLabelUnq())
                    .set(SysMenuEntity::getMapperUrl, param.getMapperUrl())
                    .set(SysMenuEntity::getViewUrl, param.getViewUrl())
                    .set(SysMenuEntity::getIsFixed, param.getIsFixed())
                    .set(SysMenuEntity::getSort, param.getSort())
                    .set(SysMenuEntity::getOperator, userHelper.getCurrentUserInfo(request).getData().getUsername())
                    .set(SysMenuEntity::getUpdateTime, LocalDateTime.now());

            if (update(updateWrapper)) {
                result = Result.SUCCESS;
            }
        }
        return result;
    }

    @Override
    public Result deleteMenu(Integer id, HttpServletRequest request) {
        Result result = Result.build(ErrorCode.B0001);
        SysMenuEntity entity = getById(id);
        if (entity.getIsFixed()==1){
            return Result.build(ErrorCode.M0102);
        }
        //当父节点下仍有子节点存在时 不可删除
        List<SysMenuEntity> childNodes = list(Wrappers.lambdaQuery(SysMenuEntity.class).eq(SysMenuEntity::getParentId, id));
        Integer zero = 0;
        for(int i =0;i<childNodes.size();i++){
            //非操作请求的菜单和模块
            if (childNodes.get(i).getType()!=2){
                if ("0".equals(childNodes.get(i).getDelStatus())||zero.equals(childNodes.get(i).getDelStatus())){
                    return Result.build(ErrorCode.M0103);
                }
            }
        }

        LambdaUpdateWrapper<SysMenuEntity> updateWrapper = Wrappers.lambdaUpdate(SysMenuEntity.class);
        updateWrapper.eq(SysMenuEntity::getId, id)
                .set(SysMenuEntity::getDelStatus,1)
                .set(SysMenuEntity::getOperator, userHelper.getCurrentUserInfo(request).getData().getUsername())
                .set(SysMenuEntity::getUpdateTime, LocalDateTime.now());

        if (update(updateWrapper)) {
            result = Result.SUCCESS;
        } else {
            result = Result.build(ErrorCode.A0012);
        }
        return result;
    }
}
