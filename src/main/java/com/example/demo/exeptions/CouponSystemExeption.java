package com.example.demo.exeptions;

public class CouponSystemExeption extends Throwable {

    public CouponSystemExeption(ErrMsg errMsg){
        super(errMsg.getMsg());
    }
}
