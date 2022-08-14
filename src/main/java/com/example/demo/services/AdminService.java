package com.example.demo.services;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;

import java.util.List;

public interface AdminService {

    void addCompany(Company company) throws CouponSystemExeption;
    void updateCompany(int companyId, Company company) throws CouponSystemExeption;
    void deleteCompany(int companyId) throws CouponSystemExeption;
    List<Company> getAllCompanies();
    Company getCompany(int companyId) throws CouponSystemExeption;
    boolean isCompanyExist(String email, String password) throws CouponSystemExeption;

    void addCustomer(Customer customer) throws CouponSystemExeption;
    void updateCustomer(int customerId, Customer customer) throws CouponSystemExeption;
    void deleteCustomer(int customerId) throws CouponSystemExeption;
    List<Customer> getAllCustomers();
    Customer getCustomer(int customerId) throws CouponSystemExeption;
    boolean isCustomerExist(String email, String password) throws CouponSystemExeption;

    boolean login(String email, String password);
}
