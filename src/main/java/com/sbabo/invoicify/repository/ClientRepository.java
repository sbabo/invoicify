package com.sbabo.invoicify.repository;

import com.sbabo.invoicify.model.Client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
    List<Client> findByUserId(Long userId);
}
