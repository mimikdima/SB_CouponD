package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.exeptions.CouponSystemExeption;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    void addCoupon(int companyId, Coupon coupon)  throws CouponSystemExeption;
    void updateCoupon(int companyId, int couponId, Coupon coupon)  throws CouponSystemExeption;
    Coupon getCoupon( int couponId)  throws CouponSystemExeption;
    void deleteCoupon( int couponId)  throws CouponSystemExeption;
    List<Coupon> getCompanyCoupons(int companyId)  throws CouponSystemExeption;
    List<Coupon> getCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemExeption;
    List<Coupon> getCompanyCouponsByMaxPrice(int companyId, double maxPRice) throws CouponSystemExeption;
    Company getCompanyDetails(int companyId)  throws CouponSystemExeption;

    boolean login(String email, String password);
}
