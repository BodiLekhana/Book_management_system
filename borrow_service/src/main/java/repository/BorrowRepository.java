package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.BorrowRecord;

public interface BorrowRepository
        extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByUserEmail(String email);
    List<BorrowRecord> findByUserEmailAndReturned(
            String email,
            boolean returned
    );
}
