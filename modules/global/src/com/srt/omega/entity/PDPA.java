package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum PDPA implements EnumClass<Integer> {

    Allow_to_contact_by_us_and_3rd_parties(10),
    Allow_to_contact_by_us_only(20),
    Allow_to_contact_by_3rd_parties_only(30),
    Do_not_contact(40);

    private Integer id;

    PDPA(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static PDPA fromId(Integer id) {
        for (PDPA at : PDPA.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}