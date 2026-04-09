package com.sbabo.invoicify.controller;

import org.springframework.web.bind.annotation.*;

import com.sbabo.invoicify.model.Invoice;
import com.sbabo.invoicify.model.Client;

import com.sbabo.invoicify.repository.InvoiceRepository;
import com.sbabo.invoicify.repository.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;

    public InvoiceController(InvoiceRepository invoiceRepository, ClientRepository clientRepository) {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public Invoice createInvoice(@RequestParam Long clientId, @RequestBody Invoice invoice) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        invoice.setClient(client);
        return invoiceRepository.save(invoice);
    }

    @GetMapping
    public List<Invoice> getInvoicesByClientId(@RequestParam Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }
}
