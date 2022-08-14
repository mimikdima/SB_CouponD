package com.example.demo.login;

import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginManager {

    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType){
        if(clientType == ClientType.ADMINISTRATOR) {
            AdminService adminService = ctx.getBean(AdminService.class);
            if(adminService.login(email, password)) {
                return (ClientService) adminService;
            }
            return null;
        }
        if(clientType == ClientType.COMPANY) {
            CompanyService companyService = ctx.getBean(CompanyService.class);
            if(companyService.login(email, password)) {
                return (ClientService) companyService;
            }
            return null;
        }
        if(clientType == ClientType.CUSTOMER) {
            CustomerService customerService = ctx.getBean(CustomerService.class);
            if(customerService.login(email, password)) {
                return (ClientService) customerService;
            }
            return null;
        }
        return null;
    }
}
