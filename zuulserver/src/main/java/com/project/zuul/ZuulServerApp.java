package com.project.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.project.zuul.config.JwtFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZuulServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApp.class, args);
    }

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean<JwtFilter> authFilter() {
        @SuppressWarnings("rawtypes")
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/eventinfo/api/event/*", "/eventinfo/api/user/*");

        return filterRegistrationBean;
    }
}
