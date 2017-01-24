//package com.twominutes.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.resource.PathResourceResolver;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//	// @Bean
//	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	//
//	// registry.addResourceHandler("/web/**").addResourceLocations("/resources/static/web/");
//	//
//	// }
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/web/**").addResourceLocations("classpath:static/web/")
//				.resourceChain(true).addResolver(new PathResourceResolver());
//	}
//
//}
