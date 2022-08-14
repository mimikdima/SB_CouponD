package com.example.demo.repos;

import com.example.demo.beans.Company;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByName(String name);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String name, String password);
    Company findByEmailAndPassword(String name, String password);

  //  @Query(value = "SELECT * FROM companies WHERE email=? AND password=?", nativeQuery = true)
  //  Company getIdCompany(String email, String password);
}
