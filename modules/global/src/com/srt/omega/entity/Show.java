package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s @ %s|name,showVenue")
@Table(name = "OMEGA_SHOW")
@Entity(name = "omega$Show")
public class Show extends StandardEntity {
    private static final long serialVersionUID = 1226709347363851718L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "CODE", length = 3)
    protected String code;

    @Column(name = "TYPE_")
    protected Integer type;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE")
    protected Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    protected Date endDate;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOW_VENUE_ID")
    protected ShowVenue showVenue;

    @Column(name = "STATUS")
    protected Integer status;

    @Column(name = "NOTES")
    protected String notes;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "show")
    protected Set<ShowTiming> showTimings;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "show")
    protected Set<TicketCategory> ticketCategories;

    public void setTicketCategories(Set<TicketCategory> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }

    public Set<TicketCategory> getTicketCategories() {
        return ticketCategories;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(ShowType type) {
        this.type = type == null ? null : type.getId();
    }

    public ShowType getType() {
        return type == null ? null : ShowType.fromId(type);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setShowVenue(ShowVenue showVenue) {
        this.showVenue = showVenue;
    }

    public ShowVenue getShowVenue() {
        return showVenue;
    }

    public void setStatus(ShowStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public ShowStatus getStatus() {
        return status == null ? null : ShowStatus.fromId(status);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setShowTimings(Set<ShowTiming> showTimings) {
        this.showTimings = showTimings;
    }

    public Set<ShowTiming> getShowTimings() {
        return showTimings;
    }


}