package com.marubi.security.core.config;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.marubi.security.common.dto.Result;
import com.marubi.security.common.utils.UploadCommonUtils;
import com.marubi.security.core.service.ViewService;
import com.marubi.security.system.entity.SysMenuEntity;
import com.marubi.security.system.handler.AuthHandlerInterceptor;
import com.marubi.security.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.*;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //登录拦截器
    @Autowired
    AuthHandlerInterceptor authHandlerInterceptor;
    @Autowired
    MenuService menuService;
    @Autowired
    ViewService viewService;
    // url - html
    @Deprecated
    public static final Map<String,String> mapperPage =
            MapUtil.newHashMap();
    static {
        mapperPage.put("/","login");
        mapperPage.put("/login","login");
        mapperPage.put("/index","index");
        mapperPage.put("/welcome","welcome");
        mapperPage.put("/demo1","demo/demo1");
        //管理员信息
        mapperPage.put("/curUser","users/curUser");
        mapperPage.put("/userList","users/userList");
        mapperPage.put("/userDetail","users/userDetail");
        mapperPage.put("/userUpdate","users/userUpdate");
        mapperPage.put("/userAdd","users/userAdd");
        mapperPage.put("/userAuth","users/userAuth");
        //权限管理
        mapperPage.put("/authList","auth/authList");
        mapperPage.put("/authAdd","auth/authAdd");
        mapperPage.put("/authEdit","auth/authEdit");
        mapperPage.put("/authAccess","auth/authAccess");
        //生产商管理
        mapperPage.put("/manufacturerList","manufacturer/manufacturerList");
        mapperPage.put("/manufacturerEditOrAdd","manufacturer/manufacturerEditOrAdd");
        //品牌管理
        mapperPage.put("/brandList","brand/brandList");
        mapperPage.put("/brandEditOrAdd","brand/brandEditOrAdd");
        //产品列表
        mapperPage.put("/goodsList","goods/goodsList");
        mapperPage.put("/goodsEditOrAdd","goods/goodsEditOrAdd");
        //菜单管理
        mapperPage.put("/menuTree","menu/menuTree");
        //配置管理
        mapperPage.put("/configPage","configGroup/config");



    }

    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedOrigins("*");
    }

    /**
     * 菜单页视图映射初始化
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        List<SysMenuEntity> mapperPage = menuService.getViewAll().getData();
        mapperPage.forEach((item) -> {
            registry.addViewController(item.getMapperUrl())
                    .setViewName(item.getViewUrl());
        });
        viewService.setRegistry(registry);
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加自定义的登录拦截器
        InterceptorRegistration registration = registry.addInterceptor(authHandlerInterceptor);
        registration.addPathPatterns("/**/**")     // 设置拦截器的通配符：该路径瞎所有请求都被拦截（除了excludePathPattherns）
                .excludePathPatterns(
                        "/login",
                        "/user/login","/marubi/file/**","/t1/**",
                        "/test/**","/error/**",
                        "/api/**",
                        "/config/gerConfigGroup"); // 不走拦截器的请求
    }

    /**
     * SpringBoot 2.x要重写该方法，不然css、js、image 等静态资源路径无法访问
     * 只要是请求静态资源，就不会走上述的拦截器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/",
                        "classpath:/resources/", "classpath:/static/", "classpath:/public/");
        //添加资源url 映射本地文件地址
        registry.addResourceHandler("/marubi/file/**")
                .addResourceLocations(String.format("file:%s/", UploadCommonUtils.getInstance().getFilePathLcoal()));
    }


    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        //日期和时间格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        return builder
                .modules(javaTimeModule)
                .simpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .defaultViewInclusion(false)
                .failOnUnknownProperties(false)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        mappingJackson2HttpMessageConverter.setDefaultCharset(UTF_8);
        return mappingJackson2HttpMessageConverter;
    }



    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }


    static class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

        @Override
        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
            return new StringToEnumConverterFactory.StringToEnum(getEnumType(targetType));
        }

        private Class<?> getEnumType(Class<?> targetType) {
            Class<?> enumType = targetType;
            while (enumType != null && !enumType.isEnum()) {
                enumType = enumType.getSuperclass();
            }
            if (enumType == null) {
                throw new IllegalArgumentException(
                        "The target type " + targetType.getName() + " does not refer to an enum");
            }
            return enumType;
        }


        private class StringToEnum<T extends Enum> implements Converter<String, T> {

            private final Class<T> enumType;

            public StringToEnum(Class<T> enumType) {
                this.enumType = enumType;
            }

            @Override
            public T convert(String source) {
                if (source.isEmpty()) {
                    return null;
                } else {
                    source = source.trim();
                    if (containsAnnotation(JsonCreator.class)) {
                        return JSONUtil.toBean(source, enumType);
                    }
                    if (source.matches("\\d+")) {
                        int i = Integer.parseInt(source);
                        if (i >= 0 && i < this.enumType.getEnumConstants().length) {
                            return this.enumType.getEnumConstants()[i];
                        }
                    }
                    return (T) Enum.valueOf(this.enumType, source.trim());
                }
            }

            /**
             * 公共方法中是否包含指定注解类
             *
             * @param annotationClass
             * @return
             */
            private boolean containsAnnotation(Class annotationClass) {
                for (Method method : enumType.getMethods()) {
                    if (method.getDeclaredAnnotation(annotationClass) != null) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

}
