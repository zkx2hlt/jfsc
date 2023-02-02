package com.cntomrrow.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JfscServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JfscServerApplication.class,args);
	}
	
	/**
	 * 外部配置容器启动
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JfscServerApplication.class);
	}
}
