package com.example.demo.clr;

import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.services.AdminService;
import com.example.demo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
@Order(3)
public class CompanyTesting implements CommandLineRunner {

    @Autowired
    LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {
/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Company email");
        String email = scanner.nextLine();
        System.out.println("Company password");
        String password = scanner.nextLine();
*/
        System.out.println("--------------------------------------- START COMPANY ---------------------------------------");
        CompanyService companyService = (CompanyService) loginManager.login("c1", "c1", ClientType.COMPANY);

        try {

            companyService.getCompanyCoupons(1).forEach(System.out::println);

            System.out.println("------------- Company Details-------------");
            Company currentCompany = companyService.getCompanyDetails(1);
            System.out.println(currentCompany);

            System.out.println("------------- Coupon Details-------------");
            Coupon couponDetail = companyService.getCoupon(1);
            System.out.println(couponDetail);

            System.out.println("------------- Coupon UPDATED -------------");
            couponDetail.setAmount(666);
            companyService.updateCoupon(1, 1, couponDetail);

            System.out.println("------------- Coupon DELETED -------------");
            companyService.deleteCoupon(3);

            System.out.println("------------- Coupon By Current Company -------------");
            companyService.getCompanyCoupons(1).forEach(System.out::println);

            System.out.println("------------- Coupon By Category-------------");
            System.out.println(companyService.getCompanyCouponsByCategory(1, Category.FOOD));

            System.out.println("------------- Coupon By Max Price-------------");
            System.out.println(companyService.getCompanyCouponsByMaxPrice(1, 201));

        } catch (CouponSystemExeption e){
            System.out.println(e.getMessage());
        }

        System.out.println("END COMPANY");
    }
}
