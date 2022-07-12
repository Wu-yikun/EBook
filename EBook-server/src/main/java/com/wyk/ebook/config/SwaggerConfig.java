package com.wyk.ebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author Wu-YiKun
 * @Date 2021/12/21 7:18
 * @Description TODO
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean("docket")
    public Docket getSwaggerDocket(Environment environment) {
        Profiles profiles = Profiles.of("dev", "test", "prod");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    // 配置 Swagger 的 ApiInfo
    private ApiInfo apiInfo() {
        // 作者信息
        Contact contact = new Contact("吴仪坤", "https://wu-yikun.top", "w577159462@163.com");
        return new ApiInfo(
                "Ebook Api Interface",
                "Swagger接口文档",
                "2.0",
                "https://wu-yikun.top",
                contact,
                "Apache 2.0",
                "www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
