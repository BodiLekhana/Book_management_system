package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Fine;

public interface FineRepository
        extends JpaRepository<Fine, Long> {

    List<Fine> findByUserEmail(String email);
    
    @Query("SELECT SUM(f.amount) FROM Fine f")
    Double getTotalFineAmount();
}
