package org.example.spring.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.example.spring.starter.common.feign.client")
public class FrontendApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FrontendApplication.class, args);
    }
}
