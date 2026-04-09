package com.sbabo.invoicify.repository;

import com.sbabo.invoicify.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    
}