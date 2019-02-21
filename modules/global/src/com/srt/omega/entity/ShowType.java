package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ShowType implements EnumClass<Integer> {

    Mainstage(10),
    The_Little_Company(20),
    The_Young_Company(30),
    Stage_2(40),
    Others(50);

    private Integer id;

    ShowType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ShowType fromId(Integer id) {
        for (ShowType at : ShowType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}