package com.marubi.security.core.config;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import  com.google.common.base.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String SPLIT = ";";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.marubi.security.business.controller;com.marubi.security.core.controller;com.marubi.security.system.controller"))
//                .apis(RequestHandlerSelectors.any())
                .apis(basePackage("com.marubi.security.business.controller","com.marubi.security.core.controller","com.marubi.security.system.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("图书管理系统api文档")
                .description("简单文档输出")
                .version("1.0")
                .build();
    }
    private static Predicate<RequestHandler> basePackage(final String... basePackages) {
        return input -> declaringClass(input).transform(handlerPackage(basePackages)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String[] basePackages) {
        return input -> {
            for (String basePackage : basePackages) {
                if (input.getPackage().getName().startsWith(basePackage)) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<Class<?>> declaringClass(RequestHandler input) {
        return com.google.common.base.Optional.fromNullable(input.declaringClass());
    }
}
