package com.mycompany.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	// localhost:8080 (vue.js 포트) 에서의 api 호출 시 CORS 위반이 뜨는 것을 막아주는 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowCredentials(true);
	}
}
