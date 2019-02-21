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
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;

@NamePattern("%s %s (%s)|firstName,lastName,email")
@Table(name = "OMEGA_CONTACT")
@Entity(name = "omega$Contact")
public class Contact extends StandardEntity {
    private static final long serialVersionUID = 7732474371598556183L;

    @Column(name = "FIRST_NAME", length = 100)
    protected String firstName;

    @Column(name = "LAST_NAME", length = 100)
    protected String lastName;

    @Column(name = "EMAIL", length = 150)
    protected String email;

    @Column(name = "PHONE", length = 50)
    protected String phone;

    @Column(name = "JOB_TITLE", length = 150)
    protected String jobTitle;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDATE")
    protected Date birthdate;

    @Column(name = "PDPA")
    protected Integer pdpa;

    @Column(name = "NOTES")
    protected String notes;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANISATION_ID")
    protected Organisation organisation;

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }

    public Organisation getOrganisation() {
        return organisation;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setPdpa(PDPA pdpa) {
        this.pdpa = pdpa == null ? null : pdpa.getId();
    }

    public PDPA getPdpa() {
        return pdpa == null ? null : PDPA.fromId(pdpa);
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }


}