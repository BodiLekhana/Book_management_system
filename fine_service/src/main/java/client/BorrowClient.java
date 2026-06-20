package client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "borrow-service",
        url = "http://localhost:8094"
)

public interface BorrowClient {

    @GetMapping("/borrow/all")

    Object getAllBorrows(
            @RequestHeader("Authorization")
            String token
    );
}
