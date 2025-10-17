package com.sumukh.fabricos.controllers;

import com.sumukh.fabricos.Dtos.AddCustomerDto;
import com.sumukh.fabricos.Entities.Customer;
import com.sumukh.fabricos.Repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


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
        entity.setBirth(LocalDate.parse(customer.getBirth()));
        Customer saved = customerRepository.save(entity);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("get-all")
    public  ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id){
        Customer deltedCustomer = customerRepository.findById(id).orElse(null);
                customerRepository.deleteById(id);
        return ResponseEntity.ok(deltedCustomer);
    }
}
