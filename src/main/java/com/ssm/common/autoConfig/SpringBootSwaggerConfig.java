package com.ssm.common.autoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by 赵梦杰 on 2018/3/13.
 */
@SuppressWarnings("SpellCheckingInspection")
@Configuration
@EnableSwagger2
public class SpringBootSwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo()).groupName("apiGroup")
                .select().apis(RequestHandlerSelectors.basePackage("com.ssm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("ssmtest")
                .termsOfServiceUrl("#")
                .description("com.ssm api document")
                .contact(new Contact("zhaomengjie", "", "zhaomengjie@cnpc.com.cn"))
                .build();
    }
}
