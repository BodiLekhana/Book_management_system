package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.
EnableFeignClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {
        "controller",
        "service",
        "security",
        "exception"
})

@EntityScan(basePackages = {
        "entity"
})

@EnableJpaRepositories(basePackages = {
        "repository"
})

@SpringBootApplication
@EnableFeignClients
public class FineServiceApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    FineServiceApplication.class
            );

    public static void main(String[] args) {

        SpringApplication.run(
                FineServiceApplication.class,
                args
        );
    }

    @Bean
    CommandLineRunner run() {

        return args -> {
            logger.info("FINE SERVICE RUNNING");
            logger.info("http://localhost:8095/swagger-ui/index.html");
        };
    }
}
