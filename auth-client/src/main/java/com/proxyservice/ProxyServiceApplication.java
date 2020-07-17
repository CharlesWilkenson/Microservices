package com.proxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.proxyservice.filters.SimpleFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ProxyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyServiceApplication.class, args);
	}
	  @Bean
	    public SimpleFilter simpleFilter() {
	      return new SimpleFilter();
	    } 
}
