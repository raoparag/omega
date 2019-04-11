package com.srt.omega.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Listeners("omega_BookingEntityListener")
@NamePattern("%s|bookingConfirmationNumber")
@Table(name = "OMEGA_BOOKING")
@Entity(name = "omega$Booking")
public class Booking extends StandardEntity {
    private static final long serialVersionUID = 1527457388503468288L;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANISATION_ID")
    protected Organisation organisation;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOW_ID")
    protected Show show;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "booking")
    protected List<BookingItem> bookingItems;

    @Column(name = "BOOKING_STATUS")
    protected Integer bookingStatus;

    @Column(name = "TICKET_STATUS")
    protected Integer ticketStatus;

    @Column(name = "TOTAL_PAID_TICKETS")
    protected Integer totalPaidTickets;

    @Column(name = "TOTAL_COMPS")
    protected Integer totalComps;

    @Column(name = "TOTAL_PRICE")
    protected Double totalPrice;

    @Column(name = "PAYMENT_METHOD")
    protected Integer paymentMethod;

    @Column(name = "BOOKING_CONFIRMATION_NUMBER", length = 10)
    protected String bookingConfirmationNumber;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SRT_CONTACT_ID")
    protected Contact srtContact;

    @Lookup(type = LookupType.DROPDOWN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_CONTACT_ID")
    protected Contact orgContact;

    @Column(name = "NOTES")
    protected String notes;

    public Integer getTotalComps() {
        return totalComps;
    }

    public void setTotalComps(Integer totalComps) {
        this.totalComps = totalComps;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }

    public void setBookingItems(List<BookingItem> bookingItems) {
        this.bookingItems = bookingItems;
    }

    public List<BookingItem> getBookingItems() {
        return bookingItems;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus == null ? null : bookingStatus.getId();
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus == null ? null : BookingStatus.fromId(bookingStatus);
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus == null ? null : ticketStatus.getId();
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus == null ? null : TicketStatus.fromId(ticketStatus);
    }

    public void setTotalPaidTickets(Integer totalPaidTickets) {
        this.totalPaidTickets = totalPaidTickets;
    }

    public Integer getTotalPaidTickets() {
        return totalPaidTickets;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.getId();
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod == null ? null : PaymentMethod.fromId(paymentMethod);
    }

    public void setBookingConfirmationNumber(String bookingConfirmationNumber) {
        this.bookingConfirmationNumber = bookingConfirmationNumber;
    }

    public String getBookingConfirmationNumber() {
        return bookingConfirmationNumber;
    }

    public void setSrtContact(Contact srtContact) {
        this.srtContact = srtContact;
    }

    public Contact getSrtContact() {
        return srtContact;
    }

    public void setOrgContact(Contact orgContact) {
        this.orgContact = orgContact;
    }

    public Contact getOrgContact() {
        return orgContact;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


}