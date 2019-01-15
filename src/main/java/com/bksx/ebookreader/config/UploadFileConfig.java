package com.bksx.ebookreader.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadFileConfig {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        //文件最大
        //factory.setMaxFileSize(DataSize.ofMegabytes(20));

        return factory.createMultipartConfig();
    }
}
