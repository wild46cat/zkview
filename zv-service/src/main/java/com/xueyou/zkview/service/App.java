package com.xueyou.zkview.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = {"com.xueyou.zkview.service.dao"})
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}
