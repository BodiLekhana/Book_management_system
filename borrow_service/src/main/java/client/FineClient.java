package client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "FINE-SERVICE",
        url = "http://localhost:8095"
)

public interface FineClient {

    @PostMapping("/fines/create")
    void createFine(
            @RequestParam String email,
            @RequestParam Long borrowId,
            @RequestParam double amount
    );
}

