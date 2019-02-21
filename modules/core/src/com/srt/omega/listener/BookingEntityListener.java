package com.srt.omega.listener;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.srt.omega.entity.Booking;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("omega_BookingEntityListener")
public class BookingEntityListener implements BeforeInsertEntityListener<Booking> {
    @Inject
    private UniqueNumbersAPI uniqueNumbers;

    @Override
    public void onBeforeInsert(Booking booking, EntityManager entityManager) {
        long confNumber = uniqueNumbers.getNextNumber(booking.getShow().getCode());
        booking.setBookingConfirmationNumber(booking.getShow().getCode() + String.format("%04d", confNumber));
    }

}