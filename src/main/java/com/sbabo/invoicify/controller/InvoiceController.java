package com.sbabo.invoicify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sbabo.invoicify.model.Invoice;
import com.sbabo.invoicify.model.InvoiceStatus;
import com.sbabo.invoicify.model.Client;

import com.sbabo.invoicify.repository.InvoiceRepository;
import com.sbabo.invoicify.repository.ClientRepository;

import com.sbabo.invoicify.service.PdfService;

import java.util.List;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/invoices")
@CrossOrigin
public class InvoiceController {
    
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final PdfService pdfService;

    public InvoiceController(InvoiceRepository invoiceRepository, ClientRepository clientRepository, PdfService pdfService) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.pdfService = pdfService;
    }

    @PostMapping
    public Invoice createInvoice(@RequestParam Long clientId, @RequestBody Invoice invoice) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        invoice.setClient(client);
        invoice.setDate(LocalDate.now());
        invoice.setStatus(InvoiceStatus.UNPAID);
        return invoiceRepository.save(invoice);
    }

    @GetMapping
    public List<Invoice> getInvoicesByClientId(@RequestParam Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getInvoicePdf(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));

        ByteArrayInputStream pdfStream = pdfService.generateInvoicePdf(invoice);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=invoice_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }

    @PutMapping("/{id}/pay")
    public Invoice payInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setStatus(InvoiceStatus.PAID);
        return invoiceRepository.save(invoice);
    }

    @GetMapping("/status")
    public List<Invoice> getByStatus(@RequestParam String status) {
        return invoiceRepository.findByStatus(status);
    }

    @GetMapping("/filter")
    public List<Invoice> getByClientIdAndStatus(@RequestParam Long clientId, @RequestParam String status) {
        return invoiceRepository.findByClientIdAndStatus(clientId, status);
    }
}

