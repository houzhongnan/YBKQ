package com.kqxt.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@MapperScan("com.kqxt.springboot.dao")
public class SpringbootApplication {
    private static final Logger log = LoggerFactory.getLogger(SpringbootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
