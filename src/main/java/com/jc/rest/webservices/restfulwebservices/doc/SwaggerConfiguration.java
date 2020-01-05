package com.jc.rest.webservices.restfulwebservices.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// It enables http://localhost:8080/v2/api-docs
// And http://localhost:8080/swagger-ui.html

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Contact DEFAULT_CONTACT = new Contact("JC", "https://github.com/juancarlosrivera", "xxx@gmail.com");
    private static final ApiInfo API_INFO_DEFAULT =
            new ApiInfo("My custom API documentation title", "My custom API documentation description", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0");
    private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO_DEFAULT).produces(PRODUCES_AND_CONSUMES).consumes(PRODUCES_AND_CONSUMES);
    }
}
