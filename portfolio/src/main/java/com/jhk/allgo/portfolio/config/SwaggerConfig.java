package com.jhk.allgo.portfolio.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Project Allgo - portfolio")
                .description("MSA - 포트폴리오")
                .build();
    }
	
	private Tag[] tagsInfo() {
		List<Tag> tags = new ArrayList<Tag>();
		
		tags.add(new Tag("allgo.info", "알고리즘 정보 CRUD"));
		tags.add(new Tag("allgo.portfolio", "포트폴리오 정보 CRUD"));
		tags.add(new Tag("allgo.portfolio_view", "포트폴리오 종합정보 조회"));
		tags.add(new Tag("allgo.score", "알고리즘 스코어 CRUD"));
		
		return tags.toArray(new Tag[tags.size()]);
	}
	
    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("allgo-service")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.jhk.allgo.allgo.controller"))
//                .paths(PathSelectors.ant("/api/**"))
                .build()
                .tags(new Tag("PING TEST", "Health Check"), tagsInfo());
    }
    
	@Bean
	public UiConfiguration uiConfig() {
	    return UiConfigurationBuilder
	            .builder()
	            .operationsSorter(OperationsSorter.METHOD)
	            .build();
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
