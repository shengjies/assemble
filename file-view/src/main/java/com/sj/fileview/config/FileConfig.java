package com.sj.fileview.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory configFactory = new MultipartConfigFactory();
        configFactory.setMaxFileSize(DataSize.ofMegabytes(10*1024L));
        configFactory.setMaxRequestSize(DataSize.ofMegabytes(100*1024L));
        return configFactory.createMultipartConfig();
    }
}
