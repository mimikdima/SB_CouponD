package com.example.demo.clr;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.login.ClientType;
import com.example.demo.login.LoginManager;
import com.example.demo.services.AdminService;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Order(2)
public class AdminTesting implements CommandLineRunner {

    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception{


        System.out.println("START");
/*
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin email");
        String email = scanner.nextLine();
        System.out.println("Admin password");
        String password = scanner.nextLine();
*/
        System.out.println("--------------------------------------- START ADMIN ---------------------------------------");
        AdminService adminService = (AdminService) loginManager.login("a", "a", ClientType.ADMINISTRATOR);

        try {

                System.out.println("------------- Company Get all -------------");
                adminService.getAllCompanies().forEach(System.out::println);

                System.out.println("------------- Company GET ONE-------------");
                Company getCompany2 = adminService.getCompany(3);
                System.out.println(getCompany2);

                System.out.println("------------- Company UPDATED -------------");
                getCompany2.setName("updated Company");
                adminService.updateCompany(2, getCompany2);

                System.out.println("------------- Company DELETED -------------");
                adminService.deleteCompany(2);

                System.out.println("------------- Company Get all -------------");
                adminService.getAllCompanies().forEach(System.out::println);

                System.out.println("------------- Company EXIST CHECK-------------");
                System.out.println(adminService.isCompanyExist("company3@mail.ru", "1111"));



                System.out.println("------------- Customer Get all -------------");
                adminService.getAllCustomers().forEach(System.out::println);

                System.out.println("------------- Customer GET ONE-------------");
                Customer getCustomer2 = adminService.getCustomer(2);
                System.out.println(getCustomer2);

                System.out.println("------------- Customer UPDATED -------------");
                getCustomer2.setFirstName("updated Customer");
                adminService.updateCustomer(2, getCustomer2);

                System.out.println("------------- Customer DELETED -------------");
                adminService.deleteCustomer(3);

                System.out.println("------------- Customer Get all -------------");
                adminService.getAllCustomers().forEach(System.out::println);

                System.out.println("------------- Customer EXIST CHECK-------------");
                System.out.println(adminService.isCustomerExist("customer1@mail.ru", "1111"));

        } catch (CouponSystemExeption e) {
            e.printStackTrace();
        }



        System.out.println("END ADMIN");
    }
}
