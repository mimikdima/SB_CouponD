package com.example.demo.services;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.exeptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService{

    @Override
    public void addCompany(Company company) throws CouponSystemExeption {
        if(this.companyRepository.existsByName(company.getName())) {
            throw new CouponSystemExeption(ErrMsg.COMPANY_NAME_EXIST);
        }
        if(this.companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemExeption(ErrMsg.COMPANY_EMAIL_EXIST);
        }

        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemExeption {
        if(!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemExeption(ErrMsg.COMPANY_ID_NOT_EXIST);
        }

        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemExeption {
        if(!this.companyRepository.existsById(companyId)) {
            throw new CouponSystemExeption(ErrMsg.COMPANY_ID_NOT_EXIST);
        }

        this.couponRepository.removeCouponAndHistoryBuying(companyId);
        this.companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getCompany(int companyId) throws CouponSystemExeption {
        return companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExeption(ErrMsg.COMPANY_ID_NOT_EXIST));
    }

    @Override
    public boolean isCompanyExist(String email, String password) throws CouponSystemExeption {
        return this.companyRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemExeption {
        if(this.customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemExeption(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }

        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemExeption {
        if(!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemExeption(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }

        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemExeption {
        if(!this.customerRepository.existsById(customerId)) {
            throw new CouponSystemExeption(ErrMsg.CUSTOMER_ID_NOT_EXIST);
        }

        this.couponRepository.deleteCustomerVsCoupons(customerId);
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(int customerId) throws CouponSystemExeption {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemExeption(ErrMsg.CUSTOMER_ID_NOT_EXIST));
    }

    @Override
    public boolean isCustomerExist(String email, String password) {
        return this.customerRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        if(!email.equals("a") || !password.equals("a")) {
            return false;
        }
        return true;
    }
}
