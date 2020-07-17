package com.baiding.vue.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

import static org.springframework.util.unit.DataUnit.MEGABYTES;

/**
 * @Author: BaiDing
 * @Date: 2018/10/26 21:07
 * @Email: liujiabaiding@foxmail.com
 *
 * 处理跨域请求问题
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.of(100,MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(100,MEGABYTES));
        return factory.createMultipartConfig();
    }
}
