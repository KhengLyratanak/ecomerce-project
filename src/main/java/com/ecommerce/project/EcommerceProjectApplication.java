package com.ecommerce.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EcommerceProjectApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(EcommerceProjectApplication.class, args);
    }
    @GetMapping("/api/v1/hello")
    public String sayHello() {
        return "Hello world from Spring Boot";

    }
}

