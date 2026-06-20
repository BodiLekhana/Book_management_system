package controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import entity.BorrowRecord;
import service.BorrowService;

@RestController
@RequestMapping("/borrow")

public class BorrowController {

    private final BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }

    @PostMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public BorrowRecord borrowBook(
            @PathVariable Long bookId,
            Principal principal) {

        return service.borrowBook(
                bookId,
                principal.getName()
        );
    }

    @PutMapping("/return/{id}")
    @PreAuthorize("hasRole('USER')")
    public String returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }

    @GetMapping("/my-books")
    @PreAuthorize("hasRole('USER')")
    public List<BorrowRecord> myBooks(Principal principal) {
        return service.getUserRecords(principal.getName());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public List<BorrowRecord> allRecords() {
        return service.getAllRecords();
    }
    
    @GetMapping("/count")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public long totalBorrows() {
        return service.totalBorrows();
    }
    
    @GetMapping("/pending")
    @PreAuthorize("hasRole('USER')")
    public List<BorrowRecord> pendingBooks(
            Principal principal) {

        return service.getPendingBorrows(
                principal.getName()
        );
    }
}
