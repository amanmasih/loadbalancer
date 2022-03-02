package com.niit.userproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="userproductservice")
public class UserproductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserproductserviceApplication.class, args);
	}



	@LoadBalanced
	@Bean
	RestTemplate loadBalanced() {
		return new RestTemplate();
	}
}
