package com.marubi.security.system.handler;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marubi.security.system.MenuType;
import com.marubi.security.system.entity.AuthEntity;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.service.IAuthGroupService;
import com.marubi.security.system.service.IAuthService;
import com.marubi.security.system.service.IGroupMapperService;
import com.marubi.security.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 *
 * @author tmz
 * @date 2021/9/16
 */
@Slf4j
@Component
public class FlexAppContentRunning implements ApplicationContextAware, CommandLineRunner {
    // 定义一个私有的方便本class中调用
    private ApplicationContext applicationContext;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IAuthGroupService authGroupService;
    @Autowired
    private IGroupMapperService groupMapperService;
    @Autowired
    private ISysMenuService smenuService;
    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(RestController.class);

        for (Map.Entry<String, Object> entry : controllers.entrySet()) {
            Object value = entry.getValue();
            System.out.println("拿到controller："+entry.getKey()+"，拿到value："+value);
            Class<?> aClass = AopUtils.getTargetClass(value);
            System.out.println("拿到Class:"+aClass);
//            RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
            RequestMapping declaredAnnotation = aClass.getDeclaredAnnotation(RequestMapping.class);
            String temp = null;
            if (declaredAnnotation.value().length!=1) {
                continue;
            }
            if (declaredAnnotation.value()[0].indexOf("/")!=-1) {
                temp = StrUtil.format("{}/{}",declaredAnnotation.value()[0]);
            }else{
                temp = StrUtil.format("/{}/{}",declaredAnnotation.value()[0]);
            }
            final List<String> list = new ArrayList<>();
            List<Method> methods = Arrays.asList(aClass.getMethods());
            System.out.println("Public Methods:" + methods);
            List<Method> declaredMethods = Arrays.asList(aClass.getDeclaredMethods());
            for (int i = 0; i < declaredMethods.size() ; i++) {
                //view filter
                if (declaredMethods.get(i).getReturnType().equals(ModelAndView.class)) {
                    continue;
                }
                GetMapping getMapping = declaredMethods.get(i).getAnnotation(GetMapping.class);
                PostMapping postMapping = declaredMethods.get(i).getDeclaredAnnotation(PostMapping.class);
                PutMapping putMapping = declaredMethods.get(i).getDeclaredAnnotation(PutMapping.class);
                DeleteMapping delMapping = declaredMethods.get(i).getDeclaredAnnotation(DeleteMapping.class);
//                System.out.println("Get相关的："+ JSONUtil.toJsonStr(getMapping));
//                System.out.println("Post相关的："+JSONUtil.toJsonStr(postMapping));
                if(getMapping!=null){
                    list.add(StrUtil.format(temp,getMapping.value()[0].replace("/","")));
                }
                if(postMapping!=null){
                    list.add(StrUtil.format(temp,postMapping.value()[0].replace("/","")));
                }
                if(putMapping!=null){
                    list.add(StrUtil.format(temp,putMapping.value()[0].replace("/","")));
                }
                if(delMapping!=null){
                    list.add(StrUtil.format(temp,delMapping.value()[0].replace("/","")));
                }

            }
            batch(list);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private void batch(List<String> list){
        LocalDateTime now = LocalDateTime.now();
        list.forEach(item->{
            SysMenuEntity entity = new SysMenuEntity();
            LambdaQueryWrapper<SysMenuEntity> eq = Wrappers.lambdaQuery(SysMenuEntity.class);
            eq.eq(SysMenuEntity::getMapperUrl,item)
                    .eq(SysMenuEntity::getType,MenuType.REQUEST.ordinal());
            entity.setUpdateTime(now)
                    .setParentId(0)
                    .setOperator("system")
                    .setMapperUrl(item)
                    .setModuleName(IdUtil.randomUUID())
                    .setLabelUnq(IdUtil.randomUUID())
                    .setEnableStatus(0)
                    .setType(MenuType.REQUEST.ordinal())
                    .setCreateTime(now)
                    .setDelStatus(0)
                    .setSort(0);
            smenuService.saveOrUpdate(entity,eq);
        });
    }
}
