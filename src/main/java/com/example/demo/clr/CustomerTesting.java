package com.example.demo.clr;

import com.example.demo.beans.Category;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.services.CompanyService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CustomerTesting implements CommandLineRunner {

    @Autowired
    LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--------------------------------------- START CUSTOMER ---------------------------------------");
        CustomerService customerService = (CustomerService) loginManager.login("cc1", "cc1", ClientType.CUSTOMER);


        System.out.println("-------------  DAO GET CUSTOMER Coupons -------------");
        System.out.println(customerService.getCustomerCoupon(1));

        System.out.println("-------------  DAO GET CUSTOMER Coupons BY CATEGORY -------------");
        System.out.println(customerService.getCustomerCouponByCategory(1, Category.FOOD));

        System.out.println("-------------  DAO GET CUSTOMER Coupons BY PRICE -------------");
        System.out.println(customerService.getCustomerCouponByMaxPrice(1, 201));

        System.out.println("--------------------------------------- END CUSTOMER ---------------------------------------");
    }
}
