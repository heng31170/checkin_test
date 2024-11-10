package com.zaizi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String UPLOAD_DIR;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置资源处理器的路径
        registry.addResourceHandler("/**")
                .addResourceLocations("file:" + UPLOAD_DIR);
    }

}
