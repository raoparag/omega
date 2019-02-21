package com.srt.omega.jmx;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "Sample data generator")
public interface SampleDataGeneratorMBean {
    @ManagedOperation(description = "Generates sample data. Params- number of shows, number of orgs & number of bookings to be generated")
    String generateSampleData(int numberOfShows, int numberOfOrgs, int numberOfBookings);

    @ManagedOperation(description = "Removes all data from the database. Pass 'ok' in parameter")
    String removeAllData(String confirm);
}