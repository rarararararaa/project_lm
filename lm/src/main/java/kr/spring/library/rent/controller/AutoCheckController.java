package kr.spring.library.rent.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoCheckController {
    public static void main(String[] args) {
        SpringApplication.run(AutoCheckController.class, args);
    }
    
}

