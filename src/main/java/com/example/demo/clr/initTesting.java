package com.example.demo.clr;

import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.services.AdminService;
import com.example.demo.services.CompanyService;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(1)
public class initTesting implements CommandLineRunner {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {


        Company company1 = Company.builder().
                name("company 1").
                email( "c1").
                password( "c1").
                build();

        Company company2 = Company.builder().
                name("company 2").
                email( "company2@mail.ru").
                password( "1111").
                build();

        Company company3 = Company.builder().
                name("company 3").
                email( "company3@mail.ru").
                password( "1111").
                build();



        Customer customer1 = Customer.builder().
                firstName("customer").
                lastName("1").
                email( "cc1").
                password( "cc1").
                build();

        Customer customer2 = Customer.builder().
                firstName("customer").
                lastName("2").
                email( "customer2@mail.ru").
                password( "1111").
                build();

        Customer customer3 = Customer.builder().
                firstName("customer").
                lastName("3").
                email( "customer3@mail.ru").
                password( "1111").
                build();



        Coupon coupon1 = Coupon.builder()
                .title("Coupon 1")
                .description("Description coupon 1")
                .category(Category.FOOD)
                .startDate(String.valueOf(LocalDate.of(2022, 1 ,15)))
                .endDate(String.valueOf(LocalDate.of(2023, 3 ,15)))
                .amount(10)
                .price(100)
                .build();

        Coupon coupon2 = Coupon.builder()
                .title("Coupon 2")
                .description("Description coupon 1")
                .category(Category.PC)
                .startDate(String.valueOf(LocalDate.of(2022, 2 ,15)))
                .endDate(String.valueOf(LocalDate.of(2023, 2 ,15)))
                .amount(20)
                .price(200)
                .build();

        Coupon coupon3 = Coupon.builder()
                .title("Coupon 3")
                .description("Description coupon 3")
                .category(Category.VACATION)
                .startDate(String.valueOf(LocalDate.of(2022, 3 ,15)))
                .endDate(String.valueOf(LocalDate.of(2023, 3 ,15)))
                .amount(30)
                .price(300)
                .build();



        try {
            System.out.println("------------- Company ADDING -------------");
            adminService.addCompany(company1);
            adminService.addCompany(company2);
            adminService.addCompany(company3);

            System.out.println("------------- Customer ADDING -------------");
            adminService.addCustomer(customer1);
            adminService.addCustomer(customer2);
            adminService.addCustomer(customer3);

            System.out.println("------------- COUPON ADDING -------------");
            companyService.addCoupon(1, coupon1);
            companyService.addCoupon(2, coupon2);
            companyService.addCoupon(1, coupon3);

            System.out.println("-------------  PURCHASE CUSTOMER Coupons -------------");
            this.customerService.purchaseCoupon(1, 1);
            this.customerService.purchaseCoupon(1, 2);
            this.customerService.purchaseCoupon(2, 2);

        } catch (CouponSystemExeption e) {
            e.printStackTrace();
        }

    }
}
