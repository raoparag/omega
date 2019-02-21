package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum PaymentMethod implements EnumClass<Integer> {

    Cheque(10),
    Cash(20),
    Bank_Transfer(30),
    Credit_Card(40),
    I_F_A_A_S(50);

    private Integer id;

    PaymentMethod(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static PaymentMethod fromId(Integer id) {
        for (PaymentMethod at : PaymentMethod.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}