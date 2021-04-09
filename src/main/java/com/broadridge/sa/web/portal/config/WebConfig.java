package com.broadridge.sa.web.portal.config;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	private static final String[] RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };
//	@Override
//	public void configurePathMatch(PathMatchConfigurer configurer) {
//		configurer.setUseSuffixPatternMatch(true);
//	}
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    	final Logger logger = LoggerFactory.getLogger(WebConfig.class);
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        registry.viewResolver(resolver);
    }	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	if (!registry.hasMappingForPattern("/webjars/**")) {
    		registry.addResourceHandler("/webjars/**").addResourceLocations(
    				"classpath:/META-INF/resources/webjars/");
    	}
    	if (!registry.hasMappingForPattern("/**")) {
    		registry.addResourceHandler("/**").addResourceLocations(
    				RESOURCE_LOCATIONS);
    	}
    }   
}
