package com.example.customermanagement.demo.controller;



import com.example.customermanagement.demo.entity.CustomerEntity;
import com.example.customermanagement.demo.repository.CustomerRepo;
import com.example.customermanagement.demo.valueobject.CustomerPostResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerEntity> addCustomer(@Valid @RequestBody CustomerEntity customerEntity) {
            return ResponseEntity.ok(customerRepo.save(customerEntity));
    }

    @GetMapping("/getCustomerById/{id}")
    public  ResponseEntity<CustomerEntity> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerRepo.findById(id).orElse(null));
    }

    @GetMapping("/getCustomer/{socialSecurityNumber}")
    public  ResponseEntity<CustomerEntity> getCustomer(@PathVariable String socialSecurityNumber) {
        return ResponseEntity.ok(customerRepo.findBySocialSecurityNumber(socialSecurityNumber)) ;
    }
}
