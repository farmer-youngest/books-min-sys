package com.marubi.security.core.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marubi.security.common.dto.LayuiTreeDto;
import com.marubi.security.common.dto.Result;
import com.marubi.security.core.dto.FormDemo1Dto;
import com.marubi.security.core.dto.TestQuery;
import com.marubi.security.system.entity.BackendMenuEntity;
import com.marubi.security.system.service.IBackendMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.marubi.security.core.entity.Test1Entity;
import com.marubi.security.core.service.ITest1Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test")
@Slf4j
public class TestContorller {
    @Autowired
    private ITest1Service iTest1Service;
    @Autowired
    IBackendMenuService backendMenuService;
    @RequestMapping("test1")
    public Result<Object> test1(){
        return Result.build(iTest1Service.list());
    }
    @PostMapping("testPage")
    public Result<IPage<TestQuery>> testPage(@RequestBody TestQuery query){
        Page<Test1Entity> page = iTest1Service.page(new Page<Test1Entity>(1, 100));
        Page<TestQuery> page1 = new Page(1, 100);
        page1.setTotal(page.getTotal());
        page1.setPages(page.getPages());
        page1.setRecords(page.getRecords().stream().map(item->{
            TestQuery query1 = new TestQuery();
            query1.setCreateTime(LocalDateTime.now());
            query1.setId(item.getId());
            query1.setName(item.getName()+"tmz_diy");
            query1.setVersion(item.getVersion());
            return query1;
        }).collect(Collectors.toList()));
        return Result.build(page1);
    }
    @PostMapping("aa")
    public ModelAndView aa(@RequestBody Map<String,Object> params){
        log.info("嗯嗯嗯额->{}",params);
        return new ModelAndView("index");
    }
    @PostMapping("bb")
    public Map bb(@RequestBody Map<String,Object> params){
        log.info("index页面->{}",params);
        return params;
    }
    /**
     * 表单不跳转 返回提交结果
     * @param dto
     * @return
     */
    @PostMapping("demo1")
    public Result demo1(FormDemo1Dto dto){
        log.info("表单提交数据，{}",dto);
        return Result.SUCCESS;
    }
    //表单跳转页面 {@link welcome.html}
   /* @PostMapping("demo1")
    public ModelAndView demo1View(FormDemo1Dto dto){
        log.info("表单提交数据，{}",dto);
        return new ModelAndView("/welcome");
    }*/
    @PostMapping("tree")
    public Result<List<Tree<String>>> tree(){
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        List<BackendMenuEntity> list = backendMenuService.list();
// 自定义属性名 都要默认值的
        treeNodeConfig.setChildrenKey("children")
                .setNameKey("label")
                .setIdKey("id");
        treeNodeConfig.setDeep(3);
        List<Tree<String>> treeNodes = TreeUtil.build(list, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setWeight(treeNode.getSort().toString());
                    tree.setName(treeNode.getTitle());
                    //节点是否初始展开，默认 false
                    tree.putExtra("isOpen",  true);
                    //节点是否为禁用状态。默认 false
                    tree.putExtra("disabled",  false);
/*                    // 节点字段名	String	一般对应表字段名
                    tree.putExtra("field", treeNode.getName());

                    //节点是否初始为选中状态 默认 false
                    tree.putExtra("checked",  true);
                    */
                });
        return Result.build(treeNodes);
    }
}
