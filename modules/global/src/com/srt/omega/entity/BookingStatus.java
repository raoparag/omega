package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum BookingStatus implements EnumClass<Integer> {

    On_Hold(10),
    Invoiced(20),
    Confirmed(30),
    Disbursement_Form_received(40);

    private Integer id;

    BookingStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static BookingStatus fromId(Integer id) {
        for (BookingStatus at : BookingStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}