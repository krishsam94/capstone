package com.project.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*
	 * Annotate this method with @Bean . This method will return an Object of Docket.
	 * This method will implement logic for swagger
	 */


	@Bean
	public Docket productApi()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.project.auth.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiMetaInfo());
	}	
	
	@SuppressWarnings("deprecation")
	private ApiInfo apiMetaInfo()
	{
		return new ApiInfo("AdminAuth application",
						   "AdminAuth application Description",
						   "1.0",
						   "http://www.ctscapestone.com",
						   "UserAuth app",
						   "Apache 2.0",
						   "http://www.apache.org/license/LICENSE-2.0");
	}

}
