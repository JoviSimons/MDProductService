package com.example.productservice.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.*

@Configuration
class SwaggerConfig {
 /*   @Bean
    fun swaggerConfiguration(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com"))
            .build()
            .apiInfo(apiDetails())
    }

    fun apiDetails(): ApiInfo {
        return ApiInfo(
            "MyDrugs products API",
            "All the categories and products for MyDrugs",
            "1.0",
            "MyDrugsOnly",
            Contact("Jovi Simons", "www.jovisimons.com", "j.simons@student.fontys.nl"),
            "MyDrugs license",
            "www.jovisimons.com",
            Collections.emptyList()
        )
    }*/
}