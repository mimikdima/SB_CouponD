package com.example.demo.services;

import com.example.demo.beans.Category;
import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.exeptions.CouponSystemExeption;
import com.example.demo.exeptions.ErrMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {

    @Override
    public boolean login(String email, String password) {
        boolean isCompanyExist = this.companyRepository.existsByEmailAndPassword(email, password);
        if(!isCompanyExist) {
            return false;
        }
        return true;
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws CouponSystemExeption {
        if(this.companyRepository.existsByName(coupon.getTitle())) {
            throw new CouponSystemExeption(ErrMsg.COUPON_TITLE_COMPANY_EXIST);
        }
        Company currentCompany = this.getCompanyDetails(companyId);
        coupon.setCompany(currentCompany);
        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int companyId, int couponId, Coupon coupon) throws CouponSystemExeption {
        if(couponId != coupon.getId()) {
            throw new CouponSystemExeption(ErrMsg.COUPON_ID_NOT_UPDATABLE);
        }
        if(companyId != coupon.getCompany().getId()) {
            throw new CouponSystemExeption(ErrMsg.COMPANY_ID_NOT_UPDATABLE);
        }

        this.couponRepository.saveAndFlush(coupon);
    }

    @Override
    public Coupon getCoupon(int couponId) throws CouponSystemExeption {
        return couponRepository.findById(couponId).orElseThrow(() -> new CouponSystemExeption(ErrMsg.COUPON_ID_NOT_EXIST));
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemExeption {
        if(!this.couponRepository.existsById(couponId)) {
            throw new CouponSystemExeption(ErrMsg.COUPON_ID_NOT_EXIST);
        }
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemExeption {
        return this.couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(int companyId, Category category) throws CouponSystemExeption {
        return this.couponRepository.findByCompanyIdAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws CouponSystemExeption {
        return this.couponRepository.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws CouponSystemExeption {
        return this.companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemExeption(ErrMsg.COMPANY_NOT_EXIST));
    }

}
