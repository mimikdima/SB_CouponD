package com.example.demo.exeptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    WRONG_EMAIL_OR_PASSWORD("Wrong email or password"),

    COMPANY_NAME_EXIST("You cannot add company with existing name"),
    COMPANY_EMAIL_EXIST("You cannot add company with existing email"),
    COMPANY_ID_NOT_EXIST("Company ID not exist"),
    COMPANY_NOT_EXIST("Company not exist"),
    COMPANY_ID_NOT_UPDATABLE("company ID not updatable"),

    CUSTOMER_EMAIL_EXIST("You cannot add customer with existing email"),
    CUSTOMER_ID_NOT_EXIST("Customer ID not exist"),
    CUSTOMER_NOT_EXIST("Customer not exist"),
    CUSTOMER_HAVE_COUPON("Customer already have that coupon"),

    COUPON_ID_NOT_UPDATABLE("coupon ID not updatable"),
    COUPON_ID_NOT_EXIST("Coupon ID not exist"),
    COUPON_AMOUNT_END("coupons amount already 0"),
    COUPON_DATE_EXPIRED("coupons date expired"),
    COUPON_TITLE_COMPANY_EXIST("coupon for this company with this title already exist");


    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }
}
