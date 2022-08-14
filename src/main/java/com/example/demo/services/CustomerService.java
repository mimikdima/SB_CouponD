package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;

import java.util.List;

public interface CustomerService {

    void purchaseCoupon(int customer, int couponId) throws CouponSystemExeption;
    List<Coupon> getCustomerCoupon(int customerId);
    List<Coupon> getCustomerCouponByCategory(int customerId, Category category);
    List<Coupon> getCustomerCouponByMaxPrice(int customerId, double price);
    Customer getCustomerDetails(int customerId) throws CouponSystemExeption;

    boolean login(String email, String password);
}
