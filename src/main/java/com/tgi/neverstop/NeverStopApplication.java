package com.tgi.neverstop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class NeverStopApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeverStopApplication.class, args);
	}
	
	@Bean
	   public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
	                .securityContexts(Collections.singletonList(
	                        SecurityContext.builder()
	                                .securityReferences(
	                                        Collections.singletonList(SecurityReference.builder()
	                                                .reference("JWT")
	                                                .scopes(new AuthorizationScope[0])
	                                                .build()
	                                        )
	                                )
	                                .build())
	                )
	                .select()
	                .apis(RequestHandlerSelectors
	                        .basePackage("com.tgi.neverstop"))
	                .paths(PathSelectors.regex("/.*"))
	                .build();
	    }

}