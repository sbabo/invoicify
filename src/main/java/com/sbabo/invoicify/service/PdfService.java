package com.sbabo.invoicify.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.sbabo.invoicify.model.Invoice;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

@Service
public class PdfService {
    public ByteArrayInputStream generateInvoicePdf(Invoice invoice) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph para = new Paragraph("Invoice #" + invoice.getId(), font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);

            document.add(new Paragraph("Client: " + invoice.getClient().getName()));
            document.add(new Paragraph("Email: " + invoice.getClient().getEmail()));
            document.add(new Paragraph("Amount: $" + invoice.getAmount()));
            document.add(new Paragraph("Status: " + invoice.getStatus()));
            document.add(new Paragraph("Date: " + invoice.getDate()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
