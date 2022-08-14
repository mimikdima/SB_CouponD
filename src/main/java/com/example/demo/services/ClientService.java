package com.example.demo.services;

import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.login.ClientType;
import com.example.demo.repos.CompanyRepository;
import com.example.demo.repos.CouponRepository;
import com.example.demo.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password) throws CouponSystemExeption;
}
