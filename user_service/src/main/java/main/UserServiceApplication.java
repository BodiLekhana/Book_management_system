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
import org.springframework.security.crypto.password.PasswordEncoder;

import entity.Role;
import entity.User;
import repository.UserRepository;

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
public class UserServiceApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceApplication.class);

    public static void main(String[] args) {

        logger.info("Starting USER SERVICE...");

        SpringApplication.run(
                UserServiceApplication.class,
                args
        );

        logger.info("USER SERVICE STARTED SUCCESSFULLY");
    }

    // OPTIONAL STARTUP LOG
    @Bean
    CommandLineRunner startupRunner() {

        return args -> {

            logger.info("===================================");
            logger.info(" USER SERVICE IS RUNNING ");
            logger.info("===================================");

            logger.info("Swagger URL:");
            logger.info(
                    "http://localhost:8082/swagger-ui/index.html"
            );
        };
    }
    
//    @Bean
//    CommandLineRunner init(
//            UserRepository repository,
//            PasswordEncoder encoder) {
//
//        return args -> {
//
//            if (repository.findByEmail(
//                    "superadmin@gmail.com").isEmpty()) {
//
//                User user = new User();
//
//                user.setEmail(
//                        "superadmin@gmail.com");
//
//                user.setPassword(
//                        encoder.encode("admin123")
//                );
//
//                user.setRole(
//                        Role.SUPER_ADMIN
//                );
//
//                repository.save(user);
//            }
//        };
//    }
}
