package com.jjdev.sqs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author jgilson
 */
@Configuration
@EnableSwagger2
public class JSwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jjdev.sqs.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()).enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AWS SQS API")
                .description("AWS SQS API endpoints.")
                .version("1.0")
                .build();
    }

}
