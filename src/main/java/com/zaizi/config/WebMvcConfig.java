package com.zaizi.config;

import com.zaizi.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/login");
    }

}
