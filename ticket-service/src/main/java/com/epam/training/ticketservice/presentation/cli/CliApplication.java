package com.epam.training.ticketservice.presentation.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.epam.training.ticketservice")
@EnableJpaRepositories(basePackages = "com.epam.training.ticketservice.dao.repository")
@EntityScan(basePackages = "com.epam.training.ticketservice.dao.repository.entity")
public class CliApplication {

    public static void main(String[] args) {
        SpringApplication.run(CliApplication.class, args);
    }

}
