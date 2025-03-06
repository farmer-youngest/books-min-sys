package com.marubi.security;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = {"cn.hutool.*","com.marubi"})
@MapperScan("com.marubi.security.*.mapper")
@EnableCaching
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
    }


    // 最新版 mybatis-plus 分页插件

}
