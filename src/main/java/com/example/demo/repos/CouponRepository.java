package com.example.demo.repos;

import com.example.demo.beans.Category;
import com.example.demo.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByCompanyId(int id);
    List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);
    List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double price);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customers_coupons (customer_id, coupons_id) VALUES (?1, ?2)", nativeQuery = true)
    void purchaseCoupon(int customerId, int couponId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE coupons SET amount = amount-1 WHERE id =?1", nativeQuery = true)
    void updateCouponAmount(int couponId);

    @Transactional
    @Query(value =  "Select coupons.* FROM coupons " +
                    "INNER JOIN customers_coupons ON customers_coupons.coupons_id = coupons.id " +
                    "WHERE customers_coupons.customer_id = ?1", nativeQuery = true)
    List<Coupon> findCouponsByCustomerId(int customerId);

    @Transactional
    @Query(value =  "Select coupons.* FROM coupons " +
            "INNER JOIN customers_coupons ON customers_coupons.coupons_id = coupons.id " +
            "WHERE customers_coupons.customer_id = ?1 AND coupons.category = ?2", nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdAndCategory(int customerId, String category);

    @Transactional
    @Query(value =  "Select coupons.* FROM coupons " +
                    "INNER JOIN customers_coupons ON customers_coupons.coupons_id = coupons.id " +
                    "WHERE customers_coupons.customer_id = ?1 AND coupons.price < ?2", nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdAndPriceLessThan(int customerId, double price);

    @Query(value =  "SELECT EXISTS(Select * FROM customers_coupons " +
                    "WHERE customer_id = ?1 AND coupons_id = ?2)", nativeQuery = true)
    long customerHaveCoupon(int customerId, int couponId);

    @Query(value =  "SELECT COUNT(*) FROM coupons WHERE id = ?1 AND end_date > CURRENT_DATE()", nativeQuery = true)
    long couponDateExpired(int couponId);

    @Query(value =  "SELECT EXISTS(Select * FROM coupons WHERE  id = ?1 AND amount > 0)", nativeQuery = true)
    long existsCouponAmount(int couponId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM coupons WHERE company_id = ?1", nativeQuery = true)
    void deleteCouponsByCompanyId(int companyId);

    @Transactional
    @Modifying
    @Query(value =  "DELETE customers_coupons, coupons FROM customers_coupons " +
                    "JOIN coupons ON coupons.id = customers_coupons.coupons_id " +
                    "WHERE coupons.company_id = ?1", nativeQuery = true)
    void removeCouponAndHistoryBuying(int companyId);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customers_coupons WHERE customer_id = ?1", nativeQuery = true)
    void deleteCustomerVsCoupons(int customerId);
}
