package security;

import feign.RequestInterceptor;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration

public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {

            RequestAttributes requestAttributes =
                    RequestContextHolder.getRequestAttributes();

            if (requestAttributes instanceof ServletRequestAttributes attributes) {

                HttpServletRequest request =
                        attributes.getRequest();

                String token =
                        request.getHeader(
                                "Authorization"
                        );

                if (token != null) {

                    requestTemplate.header(
                            "Authorization",
                            token
                    );
                }
            }
        };
    }
}