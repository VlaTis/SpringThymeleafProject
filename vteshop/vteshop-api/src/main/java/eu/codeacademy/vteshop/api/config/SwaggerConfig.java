package eu.codeacademy.vteshop.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("eu.codeacademy.vteshop.api"))
                .build();
    }

    private static ApiInfo getInfo(){
        return new ApiInfo(
                "Eshop Restfull Api Documentation",
                "Documentation using swagger and SpringFox",
                "Ver. 0.0.1",
                "Eshop term URL",
                new Contact("Vla", "www.codeacademy.eu", "egx@codeacademy.eu"),
                "VtEshop Licence",
                "Licence URL",
                Collections.emptyList()


        );
    }
}
