package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ShowStatus implements EnumClass<Integer> {

    Planned(10),
    Current(20),
    Expired(30);

    private Integer id;

    ShowStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ShowStatus fromId(Integer id) {
        for (ShowStatus at : ShowStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}