package com.marubi.security;


import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.tree.Tree;
import com.marubi.security.core.api.res.ResultResp;
import com.marubi.security.system.service.IBackendAdminService;
import com.marubi.security.system.service.MenuService;
import com.marubi.security.core.service.FileService;
import com.marubi.security.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
@Slf4j
public class SpringBootMyTest {
    @Autowired
    private IBackendAdminService learnService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IAuthGroupService authGroupService;
    @Autowired
    private IGroupMapperService groupMapperService;
    @Autowired
    private ISysMenuService smenuService;
    @Autowired
    FileService fileService;
    @Test
    public void getLearn(){
       log.info("111,{}",learnService.list());
    }

    @Test
    public void test1(){
//        Result<List<Tree<Integer>>> menuList = menuService.viewMenuList();
//        Console.log(menuList);
/*        String relative = fileService.getRelative("http://127.0.0.1:8089/marubi/file/images//20210905//cc003c74edbc4726948b1caedbf00455.jpg");

        String abs = fileService.getAbs(relative);
        Console.log(relative);
        Console.log(abs);*/
        List<Tree<String>> treeSelect = smenuService.getMenuTreeSelect();
        Tree<String> root = new Tree<>();
        root.setChildren(treeSelect);
        Console.log(treeSelect);
    }

}
