package com.srt.omega.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NamePattern("%s - %s|showTiming,ticketCategory")
@Table(name = "OMEGA_BOOKING_ITEM")
@Entity(name = "omega$BookingItem")
public class BookingItem extends StandardEntity {
    private static final long serialVersionUID = 4246187454489717856L;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOW_TIMING_ID")
    protected ShowTiming showTiming;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKET_CATEGORY_ID")
    protected TicketCategory ticketCategory;

    @Column(name = "QUANTITY")
    protected Integer quantity;

    @Column(name = "COMPS")
    protected Integer comps;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_CATEGORY_ID")
    protected PaymentCategory paymentCategory;

    @Column(name = "DISCOUNT")
    protected Double discount;

    @Column(name = "SISTIC_FEE")
    protected Double sisticFee;

    @Column(name = "SEAT_DETAILS")
    protected String seatDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID")
    protected Booking booking;

    public Integer getComps() {
        return comps;
    }

    public void setComps(Integer comps) {
        this.comps = comps;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }


    public void setShowTiming(ShowTiming showTiming) {
        this.showTiming = showTiming;
    }

    public ShowTiming getShowTiming() {
        return showTiming;
    }

    public void setTicketCategory(TicketCategory ticketCategory) {
        this.ticketCategory = ticketCategory;
    }

    public TicketCategory getTicketCategory() {
        return ticketCategory;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setSisticFee(Double sisticFee) {
        this.sisticFee = sisticFee;
    }

    public Double getSisticFee() {
        return sisticFee;
    }

    public void setSeatDetails(String seatDetails) {
        this.seatDetails = seatDetails;
    }

    public String getSeatDetails() {
        return seatDetails;
    }


}