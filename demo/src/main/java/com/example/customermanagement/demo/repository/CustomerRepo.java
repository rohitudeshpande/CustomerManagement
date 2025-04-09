package com.example.customermanagement.demo.repository;

import com.example.customermanagement.demo.entity.CustomerEntity;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findBySocialSecurityNumber(@NotEmpty String socialSecurityNumber);
}
