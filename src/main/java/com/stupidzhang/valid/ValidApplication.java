package com.stupidzhang.valid;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.stupidzhang.valid.mapper"})
@SpringBootApplication
public class ValidApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidApplication.class, args);
    }

}
