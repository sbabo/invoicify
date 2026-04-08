package com.sbabo.invoicify.repository;

import com.sbabo.invoicify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}