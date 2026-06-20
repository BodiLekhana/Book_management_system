package controller;

import dto.AuthResponse;
import dto.LoginRequest;
import dto.RegisterRequest;
import service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Controller",
        description = "APIs for user authentication")
public class AuthController {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // REGISTER USER
    // =========================
    @Operation(summary = "Register normal USER")
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        logger.info("Received registration request for: {}",
                request.getEmail());

        return userService.register(request);
    }

    // =========================
    // LOGIN
    // =========================
    @Operation(summary = "Login and get JWT token")
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        logger.info("Login API called for: {}",
                request.getEmail());

        return userService.login(request);
    }
}
