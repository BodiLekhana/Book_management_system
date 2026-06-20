package controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import entity.Book;
import service.BookService;

@RestController
@RequestMapping("/books")

public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
    	System.out.println("BOOK CONTROLLER HIT");
        return service.getAllBooks();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public String deleteBook( @PathVariable Long id) {
        service.deleteBook(id);
        return "Book Deleted";
    }
    
    @GetMapping("/{id}")
    public Book getBookById(
            @PathVariable Long id) {

        return service.getBookById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book) {

        return service.updateBook(id, book);
    }
    
    @PutMapping("/reduce/{id}")
    public String reduceBookQuantity(@PathVariable Long id) {
        service.decreaseQuantity(id);
        return "Book quantity reduced";
    }

    @PutMapping("/increase/{id}")
    public String increaseBookQuantity(@PathVariable Long id) {
        service.increaseQuantity(id);
        return "Book quantity increased";
    }
}
