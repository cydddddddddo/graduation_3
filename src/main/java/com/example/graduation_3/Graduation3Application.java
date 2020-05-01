package com.example.graduation_3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = "com.example.graduation_3.dao")
@SpringBootApplication
public class Graduation3Application {

    public static void main(String[] args) {
        SpringApplication.run(Graduation3Application.class, args);
    }

}
