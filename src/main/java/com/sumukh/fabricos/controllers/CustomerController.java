package com.sumukh.fabricos.controllers;

import com.sumukh.fabricos.Dtos.AddCustomerDto;
import com.sumukh.fabricos.Entities.Customer;
import com.sumukh.fabricos.Repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
    
    private final CustomerRepository customerRepository;
    
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody AddCustomerDto customer){
        Customer entity = new Customer();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());
        entity.setAddress(customer.getAddress());
        Customer saved = customerRepository.save(entity);
        return ResponseEntity.ok(saved);
    }
}
