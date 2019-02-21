package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|categoryName")
@Table(name = "OMEGA_TICKET_CATEGORY")
@Entity(name = "omega$TicketCategory")
public class TicketCategory extends StandardEntity {
    private static final long serialVersionUID = 1537225413422732554L;

    @Column(name = "CATEGORY_NAME", length = 50)
    protected String categoryName;

    @Column(name = "CAPACITY")
    protected Integer capacity;

    @Column(name = "PRICE")
    protected Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOW_ID")
    protected Show show;

    public void setShow(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }


    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }


}