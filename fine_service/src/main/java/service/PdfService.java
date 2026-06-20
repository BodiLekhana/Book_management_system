package service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import entity.Fine;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

    public ByteArrayInputStream generateReceipt(Fine fine) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18
                    );

            Paragraph para = new Paragraph(
                            "Library Fine Receipt",
                            font
                    );

            para.setAlignment(Element.ALIGN_CENTER);

            document.add(para);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Fine ID: " + fine.getId()));
            document.add(new Paragraph("User: " + fine.getUserEmail()));
            document.add(new Paragraph("Borrow ID: " + fine.getBorrowId()));
            document.add(new Paragraph("Amount: ₹" + fine.getAmount()));
            document.add(new Paragraph("Status: PAID"));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(
                out.toByteArray()
        );
    }
}
