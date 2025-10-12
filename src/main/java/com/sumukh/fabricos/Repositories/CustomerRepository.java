package com.sumukh.fabricos.Repositories;

import com.sumukh.fabricos.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
