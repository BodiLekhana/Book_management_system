package service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import entity.Fine;
import repository.FineRepository;

@Service
public class FineService {

    private static final Logger logger = LoggerFactory.getLogger(FineService.class);

    private final FineRepository repository;
    private final PdfService pdfService;

    public FineService(
            FineRepository repository,
            PdfService pdfService) {

        this.repository = repository;
        this.pdfService = pdfService;
    }

    public Fine createFine(
            String email,
            Long borrowId,
            double amount) {

        logger.info("Creating fine");

        Fine fine = new Fine();

        fine.setUserEmail(email);
        fine.setBorrowId(borrowId);
        fine.setAmount(amount);
        fine.setPaid(false);

        return repository.save(fine);
    }

    public List<Fine> myFines(String email) {
        return repository.findByUserEmail(email);
    }

    public List<Fine> allFines() {
        return repository.findAll();
    }

    public ByteArrayInputStream payFine(Long id) {

        Fine fine = repository.findById(id).orElseThrow();
        fine.setPaid(true);
        repository.save(fine);
        return pdfService.generateReceipt(fine);
    }
    
    public long totalFines() {
        return repository.count();
    }
    
    public Double totalFineAmount() {
        return repository.getTotalFineAmount();
    }
}
