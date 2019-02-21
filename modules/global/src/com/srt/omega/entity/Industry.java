package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@NamePattern("%s|name")
@Table(name = "OMEGA_INDUSTRY")
@Entity(name = "omega$Industry")
public class Industry extends StandardEntity {
    private static final long serialVersionUID = -8556643577038929123L;

    @Column(name = "NAME")
    protected String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}