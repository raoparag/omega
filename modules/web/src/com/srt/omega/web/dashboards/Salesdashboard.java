package com.srt.omega.web.dashboards;

import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.data.options.DatasourceOptions;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.impl.ValueCollectionDatasourceImpl;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.srt.omega.entity.*;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Salesdashboard extends AbstractWindow {

    @Named("showLookup")
    private LookupField<Show> show;

    @Named("showTimingLookup")
    private LookupField<ShowTiming> showTimingField;


    @Named("showsDs")
    private CollectionDatasource collectionShow;

    @Named("organizationDs")
    private CollectionDatasource collectionOrganization;

    @Named("organizationLookup")
    private LookupField<Organisation> organisationLookupField;

    @Named("showSummarySalesDs")
    private ValueCollectionDatasourceImpl collectionDatasourceShowSummary;

    @Named("paymentCategoryDs")
    private ValueCollectionDatasourceImpl collectionDatasourcePayment;

    @Named("ticketCategoryDs")
    private ValueCollectionDatasourceImpl collectionDatasourceTicket;

    @Inject
    private DataManager dataManager;

    @Inject
    private Notifications notifications;

    private CountDownLatch resetFilters = new CountDownLatch(0);

    private StandardEntity primaryFilter;

    @Subscribe
    protected void onInit(InitEvent event) {
        updateListeners();
    }

    private void updateListeners(){
        organisationLookupField.addValueChangeListener(organisationValueChangeEvent -> runFilterLoadChain());
        show.addValueChangeListener(stringValueChangeEvent -> runFilterLoadChain());
        showTimingField.addValueChangeListener(showTimingValueChangeEvent -> runFilterLoadChain());
    }

    private void runFilterLoadChain(){
        // Not to run the chain when reset button is pressed
        if(resetFilters.getCount() > 0) {
            resetFilters.countDown();
            return;
        }

        loadShows();
        loadOrganizations();
        loadShowTiming();

        listenerForCategoriesTable();

        if(primaryFilter != null){
            // Since Primary filter is selected, remove other data from list to make a clean view
            if(primaryFilter instanceof Show){
                show.setOptionsList(Arrays.asList(show.getValue()));
            }else if(primaryFilter instanceof Organisation){
                organisationLookupField.setOptionsList(Arrays.asList(organisationLookupField.getValue()));
            }
        }else{
            if(show.getValue() != null && organisationLookupField.getValue() == null) {
                primaryFilter = show.getValue();
            }
            else if(show.getValue() == null && organisationLookupField.getValue() != null){
                primaryFilter = organisationLookupField.getValue();
            }
        }
    }


    private void loadShows(){
        // Do not load shows if its already selected as filter
        if (show.getValue() != null) {
            return;
        }

        show.setValue(null);
        show.setOptionsList(new ArrayList<>());

        boolean isOrganisationSelected = organisationLookupField.getValue() != null;

        String queryString = "select DISTINCT b.show from omega$Booking b " +
                (!isOrganisationSelected ? "" : " where b.organisation = :organisation ") +
                " order by b.show.name";


        LoadContext<Show> loadContext = LoadContext.create(Show.class)
                .setQuery(LoadContext.createQuery(queryString)
                            .setParameter("organisation", organisationLookupField.getValue()))
                .setView("show-view");
        List<Show> showList = dataManager.loadList(loadContext);

        show.setOptionsList(showList);
    }

    private void loadOrganizations(){
        // Do not load organizations if its already selected as filter
        if (organisationLookupField.getValue() != null) {
            return;
        }

        organisationLookupField.setValue(null);
        organisationLookupField.setOptionsList(new ArrayList<>());

        boolean isShowTimingSelected = showTimingField.getValue() != null;

        String queryString = "select DISTINCT b.organisation from omega$Booking b " +
                " inner join omega$BookingItem b1 on b.bookingItems = b1" +
                " where b.show = :show" +
                (!isShowTimingSelected ? "" : " and b1.showTiming = :showTiming") +
                " order by b.organisation";

        LoadContext<Organisation> loadContext = LoadContext.create(Organisation.class)
                .setQuery(LoadContext.createQuery(queryString)
                        .setParameter("show", show.getValue())
                        .setParameter("showTiming", showTimingField.getValue()));
        List<Organisation> organizationList = dataManager.loadList(loadContext);

        organisationLookupField.setOptionsList(organizationList);
    }

    private void loadShowTiming() {
        // Do not load timing if show is not selected
        if (show.getValue() == null) {
            // without show, no show summary available
            return;
        }
        // Do not load timing if its already selected as filter
        if(showTimingField.getValue() != null){
            updateShowInfo(Arrays.asList(showTimingField.getValue()));
            return;
        }

        boolean isOrganisationSelected = organisationLookupField.getValue() != null;

        String queryString = "select s from omega$ShowTiming s" +
                " where s.show = :show order by s.showTime";

        if(isOrganisationSelected){
            queryString = "select DISTINCT b1.showTiming from omega$Booking b " +
                    " inner join omega$BookingItem b1 on b.bookingItems = b1" +
                    " where b.show = :show" +
                    " and b.organisation = :organisation" +
                    " order by b1.showTiming";
        }
        LoadContext<ShowTiming> loadContext = LoadContext.create(ShowTiming.class)
                .setQuery(LoadContext.createQuery(queryString)
                        .setParameter("show", show.getValue())
                        .setParameter("organisation", organisationLookupField.getValue()));

        List<ShowTiming> showTimings = dataManager.loadList(loadContext);

        showTimingField.setOptionsList(showTimings);

        updateShowInfo(showTimings);
    }

    private void updateShowInfo(@NotNull List<ShowTiming> showTimings){
        long addtotalWeekend = 0l, addtotalWeekday = 0l, totalTicketValue = 0l, totalTicketsSold = 0l;

        boolean isOrganisationSelected = organisationLookupField.getValue() != null;

        String queryString = "select sum(b.totalPrice), sum(b.totalPaidTickets + b.totalComps) from omega$Booking b" +
                " inner join omega$BookingItem b1 on b.bookingItems = b1" +
                " where b.show = :show and b1.showTiming = :showTiming" +
                (!isOrganisationSelected ? "": " and b.organisation = :organisation");

        for(int i = 0 ; i < showTimings.size() ; i++){

            Calendar c = Calendar.getInstance();
            c.setTime(showTimings.get(i).getShowTime());
            int dayofWeek = c.get(Calendar.DAY_OF_WEEK); // 1 Sun and 7 Sat

            KeyValueEntity totalData = dataManager.loadValues(queryString)
                    .parameter("show", show.getValue())
                    .parameter("showTiming", showTimings.get(i))
                    .parameter("organisation", organisationLookupField.getValue())
                    .properties("totalTicketValue", "totalTicketCount")
                    .one();


            long tempData;
            if(totalData.getValue("totalTicketCount") != null) {
                tempData = totalData.getValue("totalTicketCount");

                if (dayofWeek == 1 || dayofWeek == 7) {
                    addtotalWeekend += tempData;
                } else {
                    addtotalWeekday += tempData;
                }
            }

            double tempDataDouble;
            if(totalData.getValue("totalTicketValue") != null) {
                tempDataDouble = totalData.getValue("totalTicketValue");
                totalTicketValue += tempDataDouble;
            }


        }

        totalTicketsSold = addtotalWeekend + addtotalWeekday;

        int capacity;

        /* total capacity */
        queryString = "select e.capacity from omega$ShowVenue e " +
                "where e.name = :venueName";
        capacity = dataManager.loadValue(queryString, Integer.class)
                .parameter("venueName", show.getValue().getShowVenue().getName())
                .one();

        KeyValueEntity keyValueEntity;
        List<Object> listItems = collectionDatasourceShowSummary.getItemIds(0,1);
        if(CollectionUtils.isEmpty(listItems)){
            keyValueEntity = new KeyValueEntity();
            collectionDatasourceShowSummary.addItem(keyValueEntity);
        }else{
            keyValueEntity = collectionDatasourceShowSummary.getItem(listItems.get(0));
        }

        keyValueEntity.setValue("weekdaySalesColumn", String.valueOf(addtotalWeekday));
        keyValueEntity.setValue("weekendSalesColumn", String.valueOf(addtotalWeekend));
        keyValueEntity.setValue("totalSalesColumn", "$"+ NumberFormat.getNumberInstance(Locale.US).format(totalTicketValue));
        keyValueEntity.setValue("totalShowColumn", String.valueOf(showTimings.size()));
        keyValueEntity.setValue("ticketsBookedColumn", String.valueOf(totalTicketsSold));


        /* Available tickets*/
        if(capacity == 0){
            keyValueEntity.setValue("ticketsAvailableColumn", "0/0");
        }else {
            keyValueEntity.setValue("ticketsAvailableColumn", ((showTimings.size()*capacity - totalTicketsSold)) + " / " + (showTimings.size()*capacity));
        }

    }

    private void listenerForCategoriesTable() {
        clearPreviousData();

        if(show.getValue() == null){
            return;
        }

        String queryString;

        boolean isShowTimingSelected = showTimingField.getValue() != null;
        boolean isOrganisationSelected = organisationLookupField.getValue() != null;

        /* Total capacity */
        queryString = "select e.capacity from omega$ShowVenue e " +
                "where e.name = :venueName";

        int capacity = dataManager.loadValue(queryString, Integer.class)
                .parameter("venueName", show.getValue().getShowVenue().getName())
                .one();

        final int totalCapacity = (isShowTimingSelected ? capacity : capacity * show.getValue().getShowTimings().size());

        /* Total sales */
        queryString = "select sum(b.totalPaidTickets) from omega$Booking b " +
                " inner join omega$BookingItem b1 on b.bookingItems = b1" +
                " where b.show = :show" +
                (!isShowTimingSelected ? "" : " and b1.showTiming = :showTiming") +
                (!isOrganisationSelected ? "" : " and b.organisation = :organisation ");

        Optional<Integer> totalSalesOptional = dataManager.loadValue(queryString, Integer.class)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
                .parameter("organisation", organisationLookupField.getValue())
                .optional();
        if (!totalSalesOptional.isPresent()) {
            loadEmptyData(totalCapacity, isShowTimingSelected);
            return;
        }
//        int totalSales = totalSalesOptional.get();

        /* Populate Payment Category table */
        queryString = "select b.paymentCategory, sum(b.paidTickets), sum(b.comps) from omega$BookingItem b" +
                " inner join omega$Booking b1 on b.booking = b1" +
                " where b1.show = :show" +
                (!isShowTimingSelected ? "" : " and b.showTiming = :showTiming") +
                (!isOrganisationSelected ? "" : " and b1.organisation = :organisation ") +
                " group by b.paymentCategory order by b.paymentCategory.name";


        List<KeyValueEntity> paymentCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
                .parameter("organisation", organisationLookupField.getValue())
                .properties("paymentCategory", "paidTickets", "compTickets")
                .list();
        populatePaymentsTable(paymentCategoryList, totalCapacity);


        /* Populate Ticket Category table */
        queryString = "select b.ticketCategory, sum(b.paidTickets), sum(b.comps) from omega$BookingItem b" +
                " inner join omega$Booking b1 on b.booking = b1" +
                " where b1.show = :show" +
                (!isShowTimingSelected ? "" : " and b.showTiming = :showTiming") +
                (!isOrganisationSelected ? "" : " and b1.organisation = :organisation ") +
                " group by b.ticketCategory order by b.ticketCategory.categoryName";

        List<KeyValueEntity> ticketCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
                .parameter("organisation", organisationLookupField.getValue())
                .properties("ticketCategory", "paidTickets", "compTickets")
                .list();
        populateTicketTable(ticketCategoryList);

    }

    private void populatePaymentsTable(List<KeyValueEntity> paymentCategoryList, final int totalCapacity) {

        DecimalFormat df = new DecimalFormat("#.##");
        paymentCategoryList.forEach(keyValueEntity -> {
            KeyValueEntity newKeyValueEntity = new KeyValueEntity();
            PaymentCategory category = (PaymentCategory) keyValueEntity.getValue("paymentCategory");

            Double paidTicketsValue = 0.0, compTicketsValue = 0.0;
            String paidTicketsLbl = "0", compTicketsLbl = "0";
            Object paidTickets = keyValueEntity.getValue("paidTickets");
            if(paidTickets != null) {
                paidTicketsLbl = paidTickets.toString();
                paidTicketsValue = 1.0 * Integer.parseInt(paidTicketsLbl) / totalCapacity;
                paidTicketsValue = 1.0 * Math.round(paidTicketsValue * 100) / 100;
            }

            Object compTickets = keyValueEntity.getValue("compTickets");
            if(compTickets != null) {
                compTicketsLbl = compTickets.toString();
                compTicketsValue = 1.0 * Integer.parseInt(compTicketsLbl) / totalCapacity;
                compTicketsValue = 1.0 * Math.round(compTicketsValue * 100) / 100;
            }
            newKeyValueEntity.setValue("category", category.getName());
            newKeyValueEntity.setValue("totalCapacity", String.valueOf(totalCapacity));
            newKeyValueEntity.setValue("paidTickets", paidTicketsLbl);
            newKeyValueEntity.setValue("percentPaidTickets", df.format(paidTicketsValue) + "%");
            newKeyValueEntity.setValue("compTickets", compTicketsLbl);
            newKeyValueEntity.setValue("percentCompTickets", df.format(compTicketsValue) + "%");

            collectionDatasourcePayment.addItem(newKeyValueEntity);
        });
        collectionDatasourcePayment.refresh();


    }

    private void populateTicketTable(List<KeyValueEntity> ticketCategoryList) {

        Map<String, Integer> ticketCategoryCapacityMap = new HashMap<>();
        for (TicketCategory ticketCategory : show.getValue().getTicketCategories()){
            ticketCategoryCapacityMap.put(ticketCategory.getCategoryName(), ticketCategory.getCapacity());
        }

        DecimalFormat df = new DecimalFormat("#.##");
        ticketCategoryList.forEach(keyValueEntity -> {
            KeyValueEntity newKeyValueEntity = new KeyValueEntity();
            TicketCategory category = (TicketCategory) keyValueEntity.getValue("ticketCategory");

            int totalCapacity = ticketCategoryCapacityMap.get(category.getCategoryName());
            Double paidTicketsValue = 0.0, compTicketsValue = 0.0;
            String paidTicketsLbl = "0", compTicketsLbl = "0";
            Object paidTickets = keyValueEntity.getValue("paidTickets");
            if(paidTickets != null) {
                paidTicketsLbl = paidTickets.toString();
                paidTicketsValue = 1.0 * Integer.parseInt(paidTicketsLbl) / totalCapacity;
                paidTicketsValue = 1.0 * Math.round(paidTicketsValue * 100) / 100;
            }

            Object compTickets = keyValueEntity.getValue("compTickets");
            if(compTickets != null) {
                compTicketsLbl = compTickets.toString();
                compTicketsValue = 1.0 * Integer.parseInt(compTicketsLbl) / totalCapacity;
                compTicketsValue = 1.0 * Math.round(compTicketsValue * 100) / 100;
            }

            newKeyValueEntity.setValue("category", category.getCategoryName());
            newKeyValueEntity.setValue("totalCapacity", String.valueOf(totalCapacity));
            newKeyValueEntity.setValue("paidTickets", paidTicketsLbl);
            newKeyValueEntity.setValue("percentPaidTickets", df.format(paidTicketsValue) + "%");
            newKeyValueEntity.setValue("compTickets", compTicketsLbl);
            newKeyValueEntity.setValue("percentCompTickets", df.format(compTicketsValue) + "%");

            collectionDatasourceTicket.addItem(newKeyValueEntity);
        });
        collectionDatasourceTicket.refresh();
    }


    private void clearPreviousData() {
        collectionDatasourcePayment.revert();
        collectionDatasourceTicket.revert();
        collectionDatasourcePayment.setRefreshMode(CollectionDatasource.RefreshMode.NEVER);
        collectionDatasourceTicket.setRefreshMode(CollectionDatasource.RefreshMode.NEVER);
    }

    private void loadEmptyData(int totalCapacity, boolean isShowTimingSelected) {
        String queryString = "select p, 0 from omega$PaymentCategory p";

        List<KeyValueEntity> paymentCategoryList = dataManager.loadValues(queryString)
                .properties("paymentCategory", "paidTickets")
                .list();
        populatePaymentsTable(paymentCategoryList, totalCapacity);

        queryString = "select t, 0 from omega$TicketCategory t" +
                    " left join omega$Show s on t.show = s" +
                    " where s.id = :show" +
                    ((!isShowTimingSelected ? "" : " and s.showTimings = :showTiming"));

        List<KeyValueEntity> ticketCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue().getUuid())
                .parameter("showTiming", showTimingField.getValue())

                .properties("ticketCategory", "paidTickets")
                .list();
        populateTicketTable(ticketCategoryList);

    }


    /**
     * Invoked by <code>clearBttn</code> component
     */
    public void clearBttnEventHandler(){
        int countSelectedFilters = 0;
        countSelectedFilters = show.getValue()!=null ? countSelectedFilters+1 : countSelectedFilters;
        countSelectedFilters = showTimingField.getValue()!=null ? countSelectedFilters+1 : countSelectedFilters;
        countSelectedFilters = organisationLookupField.getValue()!=null ? countSelectedFilters+1 : countSelectedFilters;

        primaryFilter = null;
        resetFilters = new CountDownLatch(countSelectedFilters);
        cleanShow();
        cleanShowTiming();
        cleanOrganisation();
        clearPreviousData();
        try {
            resetFilters.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void cleanShow(){
        collectionShow.revert();
        show.setOptions(new DatasourceOptions<>(collectionShow));
        show.setValue(null);
    }

    private void cleanOrganisation(){
        collectionOrganization.revert();
        organisationLookupField.setOptions(new DatasourceOptions<>(collectionOrganization));
        organisationLookupField.setValue(null);
    }

    private void cleanShowTiming() {
        showTimingField.setOptionsList(new ArrayList<>());
        showTimingField.setValue(null);
        collectionDatasourceShowSummary.revert();
        //The NEVER mode is useful if you need to programmatically fill CollectionDatasource with preloaded or created entities.
        collectionDatasourceShowSummary.setRefreshMode(CollectionDatasource.RefreshMode.NEVER);
    }


}