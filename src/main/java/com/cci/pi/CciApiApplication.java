package com.cci.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CciApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CciApiApplication.class, args);
    }

}
