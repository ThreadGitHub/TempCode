package com.thread.springsecuritytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    /**
     * 项目描述
     * @return
     */
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                //描述字段支持Markdown语法
                .description("项目描述暂定")
                .termsOfServiceUrl("")
                .contact("ThreadEmail@163.com")
                .version("1.0")
                .build();
    }

    @Bean(value = "userBean")
    public Docket userBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                //分组名称
                .groupName("用户服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.thread.springsecuritytest.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
