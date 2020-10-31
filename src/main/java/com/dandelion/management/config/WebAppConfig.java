package com.dandelion.management.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 解决springboot2.0静态资源无法直接访问问题
 * 路径暂时还未设置
 * @author Sunqingxin
 */
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("");
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            //项目访问路径
            registry.addResourceHandler("").addResourceLocations("");
        }else{
            //项目访问路径
            registry.addResourceHandler("").addResourceLocations("");
        }
        super.addResourceHandlers(registry);
    }

}
