package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.exeptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {


    @Override
    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemExeption {
        if(this.couponRepository.customerHaveCoupon(customerId, couponId) == 1) {
            throw new CouponSystemExeption(ErrMsg.CUSTOMER_HAVE_COUPON);
        }
        if(this.couponRepository.existsCouponAmount(couponId) == 0) {
            throw new CouponSystemExeption(ErrMsg.COUPON_AMOUNT_END);
        }
        if(this.couponRepository.couponDateExpired(couponId) == 0) {
            throw new CouponSystemExeption(ErrMsg.COUPON_DATE_EXPIRED);
        }
        this.couponRepository.purchaseCoupon(customerId, couponId);
        this.couponRepository.updateCouponAmount(couponId);
    }

    @Override
    public List<Coupon> getCustomerCoupon(int customerId) {
        return this.couponRepository.findCouponsByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(int customerId, Category category) {
        return this.couponRepository.findCouponsByCustomerIdAndCategory(customerId, category.name());
    }

    @Override
    public List<Coupon> getCustomerCouponByMaxPrice(int customerId, double price) {
        return this.couponRepository.findCouponsByCustomerIdAndPriceLessThan(customerId, price);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemExeption {
        return this.customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemExeption(ErrMsg.CUSTOMER_NOT_EXIST));
    }

    @Override
    public boolean login(String email, String password) {
        boolean isCustomerExist = this.customerRepository.existsByEmailAndPassword(email, password);
        if(!isCustomerExist) {
            return false;
        }
        return true;
    }
}
