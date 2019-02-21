package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "OMEGA_PAYMENT_CATEGORY")
@Entity(name = "omega$PaymentCategory")
public class PaymentCategory extends StandardEntity {
    private static final long serialVersionUID = -6516219900734040116L;

    @Column(name = "NAME", length = 100)
    protected String name;

    @Column(name = "NOTES")
    protected String notes;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


}