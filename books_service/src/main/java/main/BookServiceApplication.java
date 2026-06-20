package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
public class BookServiceApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    BookServiceApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(
                BookServiceApplication.class,
                args
        );
    }

    @Bean
    CommandLineRunner run() {

        return args -> {

            logger.info("=================================");
            logger.info(" BOOK SERVICE RUNNING ");
            logger.info("=================================");

            logger.info(
                    "http://localhost:8093/swagger-ui/index.html"
            );
        };
    }
}
