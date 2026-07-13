package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI crowdfundingAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Crowdfunding Management System API")
                        .version("1.0")
                        .description("REST API for managing crowdfunding campaigns and donations.")
                        .contact(new Contact()
                                .name("Kaniyarasu")
                                .email("kaniyarasu@example.com")));
    }
}
