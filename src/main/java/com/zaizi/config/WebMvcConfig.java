package com.zaizi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String UPLOAD_DIR = "D:/Javaidea/springboot_learn/checkin_test/src/main/resources/static/";
        // 设置资源处理器的路径
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + UPLOAD_DIR);
    }

}
