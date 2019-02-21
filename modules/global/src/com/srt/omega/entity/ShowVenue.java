package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "OMEGA_SHOW_VENUE")
@Entity(name = "omega$ShowVenue")
public class ShowVenue extends StandardEntity {
    private static final long serialVersionUID = -1905536886874707081L;

    @Column(name = "NAME", length = 150)
    protected String name;

    @Column(name = "CAPACITY")
    protected Integer capacity;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }


}