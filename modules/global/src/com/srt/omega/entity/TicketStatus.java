package com.srt.omega.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TicketStatus implements EnumClass<Integer> {

    Unprinted(10),
    Printed(20);

    private Integer id;

    TicketStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TicketStatus fromId(Integer id) {
        for (TicketStatus at : TicketStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}