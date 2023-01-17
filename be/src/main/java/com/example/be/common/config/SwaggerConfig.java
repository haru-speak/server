package com.example.be.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30)
			.useDefaultResponseMessages(false)
			.groupName("haru-speak")
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors
				.basePackage("com.example.be"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("하루 스픽 API")
			.description("API 상세소개 및 사용법 등")
			.version("1.0")
			.build();
	}
}
