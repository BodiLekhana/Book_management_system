package controller;

import dto.RegisterRequest;
import entity.User;
import service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller",
        description = "APIs for ADMIN and SUPER_ADMIN")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private static final Logger logger =
            LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // =========================
    // CREATE ADMIN
    // ONLY SUPER_ADMIN
    // =========================
    @Operation(summary = "Create ADMIN user")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-admin")
    public String createAdmin(
            @RequestBody RegisterRequest request) {

        logger.info("SUPER_ADMIN creating ADMIN: {}",
                request.getEmail());

        return userService.createAdmin(request);
    }

    // =========================
    // CREATE SUPER_ADMIN
    // OPTIONAL
    // =========================
    @Operation(summary = "Create SUPER_ADMIN")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create-super-admin")
    public String createSuperAdmin(
            @RequestBody RegisterRequest request) {

        logger.info("Creating SUPER_ADMIN: {}",
                request.getEmail());

        return userService.createSuperAdmin(request);
    }
    
    @GetMapping("/total-users")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public long totalUsers() {
        return userService.totalUsers();
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public List<User> getAllUsers() {

        return userService.getAllUsers();

    }
}
