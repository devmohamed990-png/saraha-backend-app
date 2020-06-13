package com.saraha.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfig {

	@Bean
	public ModelMapper getModelMapper() {

		return new ModelMapper();
	}

	@Bean
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}
}