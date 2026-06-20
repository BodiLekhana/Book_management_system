package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import entity.Book;
import repository.BookRepository;

@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        logger.info("Adding book: {}", book.getTitle());
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return repository.findAll();
    }

    public void deleteBook(Long id) {
        logger.info("Deleting book with id: {}", id);
        repository.deleteById(id);
    }
    
    public Book getBookById(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Book not found"));
    }

    public Book updateBook(
            Long id,
            Book updatedBook) {

        Book book = repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setQuantity(updatedBook.getQuantity());

        return repository.save(book);
    }
    
    public void decreaseQuantity(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException(
                    "Book out of stock"
            );
        }

        book.setQuantity(book.getQuantity() - 1);
        repository.save(book);
        logger.info("Quantity reduced for book id {}",id);
    }

    public void increaseQuantity(Long id) {

        Book book = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        book.setQuantity(book.getQuantity() + 1);
        repository.save(book);
        logger.info("Quantity increased for book id {}",id);
    }
}
