package com.srt.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s|showTime")
@Table(name = "OMEGA_SHOW_TIMING")
@Entity(name = "omega$ShowTiming")
public class ShowTiming extends StandardEntity {
    private static final long serialVersionUID = -3604610456162912576L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SHOW_TIME")
    protected Date showTime;

    @Column(name = "NOTES")
    protected String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHOW_ID")
    protected Show show;


    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Date getShowTime() {
        return showTime;
    }


    public void setShow(Show show) {
        this.show = show;
    }

    public Show getShow() {
        return show;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


}