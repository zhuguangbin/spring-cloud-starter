package org.example.spring.starter;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableAdminServer
public class ConfigMonitorServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigMonitorServerApplication.class, args);
    }
}
