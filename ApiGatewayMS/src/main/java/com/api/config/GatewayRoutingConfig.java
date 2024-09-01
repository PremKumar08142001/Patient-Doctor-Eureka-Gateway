package com.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayRoutingConfig {
	
	@Bean
	public RouteLocator configRoute(RouteLocatorBuilder builder) {
		return builder.routes()
						.route("Doctor",r -> r.path("/doctors/**").uri("lb://doctor-service"))
						.route("patients",r -> r.path("/patients/**").uri("lb://patient-service"))
						.route("appointments",r -> r.path("/appointments/**").uri("lb://patient-service"))
						.build();
	}

}
