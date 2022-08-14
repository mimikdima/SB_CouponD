package com.example.demo.repos;

import com.example.demo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String name, String password);

}
