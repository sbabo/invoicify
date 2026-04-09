package com.sbabo.invoicify.repository;

import com.sbabo.invoicify.model.Invoice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByClientId(Long clientId);
    
}
