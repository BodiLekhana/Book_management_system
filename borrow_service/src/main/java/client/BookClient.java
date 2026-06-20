package client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import security.FeignConfig;

@FeignClient(
        name = "BOOK-SERVICE",
        url = "http://localhost:8093",
        configuration = FeignConfig.class
)

public interface BookClient {

    @PutMapping("/books/reduce/{id}")
    void reduceQuantity(
            @PathVariable Long id
    );

    @PutMapping("/books/increase/{id}")
    void increaseQuantity(
            @PathVariable Long id
    );
}
