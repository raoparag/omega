package com.srt.omega.web.dashboards;

import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.impl.ValueCollectionDatasourceImpl;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.srt.omega.entity.PaymentCategory;
import com.srt.omega.entity.Show;
import com.srt.omega.entity.ShowTiming;
import com.srt.omega.entity.TicketCategory;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Salesdashboard extends AbstractWindow {

    @Named("showLookup")
    private Field<Show> show;

    @Named("showSummarySalesDs")
    private ValueCollectionDatasourceImpl collectionDatasourceShowSummary;

    @Named("showTimingLookup")
    private LookupField<ShowTiming> showTimingField;

    @Named("paymentCategoryDs")
    private ValueCollectionDatasourceImpl collectionDatasourcePayment;

    @Named("ticketCategoryDs")
    private ValueCollectionDatasourceImpl collectionDatasourceTicket;

    @Inject
    private DataManager dataManager;

    @Inject
    private Notifications notifications;


    @Subscribe
    protected void onInit(InitEvent event) {
        listenerForShow();
        listenerForShowTiming();
    }

    private void listenerForShow() {

        show.addValueChangeListener(stringValueChangeEvent -> {
            cleanShowTiming();
            loadShowTiming();
            listenerForCategoriesTable();
        });
    }

    private void listenerForShowTiming() {
        showTimingField.addValueChangeListener(showTimingValueChangeEvent -> {
            listenerForCategoriesTable();
            updateTicketCountStatus();
        });
    }

    private void cleanShowTiming() {
        showTimingField.setValue(null);
        showTimingField.setOptionsList(new ArrayList<>());
        collectionDatasourceShowSummary.clear();
        collectionDatasourceShowSummary.commit();
        collectionDatasourceShowSummary.setRefreshMode(CollectionDatasource.RefreshMode.NEVER);
    }


    private void loadShowTiming() {
        if (show.getValue() == null) {
            return;
        }
        String queryString = "select s from omega$ShowTiming s" +
                " where s.show = :show order by s.showTime";

        LoadContext<ShowTiming> loadContext = LoadContext.create(ShowTiming.class)
                .setQuery(LoadContext.createQuery(queryString)
                        .setParameter("show", show.getValue()));
        List<ShowTiming> showTimings = dataManager.loadList(loadContext);

        showTimingField.setOptionsList(showTimings);

        updateShowInfo(showTimings);
    }

    private void updateShowInfo(List<ShowTiming> showTimings){
        int addtotalWeekend = 0;
        int addtotalWeekday = 0;

        String queryString = "select sum(b.totalPaidTickets + b.totalComps) from omega$Booking b" +
                " inner join omega$BookingItem b1 on b.bookingItems = b1" +
                " where b.show = :show and b1.showTiming = :showTiming";

        for(int i = 0 ; i < showTimings.size() ; i++){

            Calendar c = Calendar.getInstance();
            c.setTime(showTimings.get(i).getShowTime());
            int dayofWeek = c.get(Calendar.DAY_OF_WEEK); // 1 Sun and 7 Sat

            Optional<Integer> total = dataManager.loadValue(queryString, Integer.class)
                    .parameter("show", show.getValue())
                    .parameter("showTiming", showTimings.get(i))
                    .optional();

            if(total.isPresent()) {
                if (dayofWeek == 1 || dayofWeek == 7) {
                    addtotalWeekend += total.get();
                } else {
                    addtotalWeekday += total.get();
                }
            }
        }

        KeyValueEntity keyValueEntity;
        List<Object> listItems = collectionDatasourceShowSummary.getItemIds(0,1);
        if(CollectionUtils.isEmpty(listItems)){
            keyValueEntity = new KeyValueEntity();
            collectionDatasourceShowSummary.addItem(keyValueEntity);
        }else{
            keyValueEntity = (KeyValueEntity) collectionDatasourceShowSummary.getItem(listItems.get(0));
        }

        keyValueEntity.setValue("weekdaySalesColumn", String.valueOf(addtotalWeekday));
        keyValueEntity.setValue("weekendSalesColumn", String.valueOf(addtotalWeekend));

        /* total sales of all the showtiming */
        Optional<Integer> totalSalesOfAllTiming = dataManager.loadValue(
                "select sum(b.totalPrice) from omega$Booking b " +
                        "where b.show.name = :showName", Integer.class)
                .parameter("showName", show.getValue().getName())
                .optional();


        showTimingField.setOptionsList(showTimings);
        if(totalSalesOfAllTiming.isPresent()){
            keyValueEntity.setValue("totalSalesColumn", String.valueOf(totalSalesOfAllTiming.get()));
        }else{
            keyValueEntity.setValue("totalSalesColumn", String.valueOf(0));
        }


        keyValueEntity.setValue("totalShowColumn", String.valueOf(show.getValue().getShowTimings().size()));

    }

    private void updateTicketCountStatus(){

        int capacity = 0, totalSalesValue = 0;

        if(showTimingField.getValue() != null) {
            /* total show */

            /* total capacity */
            String queryString = "select e.capacity from omega$ShowVenue e " +
                    "where e.name = :venueName";
            capacity = dataManager.loadValue(queryString, Integer.class)
                    .parameter("venueName", show.getValue().getShowVenue().getName())
                    .one();
//        notifications.create().withDescription(String.valueOf(capacity)).show();


            /* number of tickets booked */

            queryString = "select sum(b.totalPaidTickets + b.totalComps) from omega$Booking b " +
                    "inner join omega$BookingItem b1 on b.bookingItems = b1" +
                    " where b.show = :show" +
                    " and b1.showTiming = :showTiming";

            Optional<Integer> totalSalesOptional = dataManager.loadValue(queryString, Integer.class)
                    .parameter("show", show.getValue())
                    .parameter("showTiming", showTimingField.getValue())
                    .optional();
            totalSalesValue = (totalSalesOptional.isPresent()? totalSalesOptional.get() : 0);

        }

        KeyValueEntity keyValueEntity = (KeyValueEntity)collectionDatasourceShowSummary.getItem
                (collectionDatasourceShowSummary.getItemIds(0,1).get(0));
        keyValueEntity.setValue("ticketsBookedColumn", String.valueOf(totalSalesValue));

        /* Available tickets*/
        if(capacity == 0){
            keyValueEntity.setValue("ticketsAvailableColumn", "0/0");
        }else {
            keyValueEntity.setValue("ticketsAvailableColumn", (capacity - totalSalesValue) + " / " + capacity);
        }

    }
    private void listenerForCategoriesTable() {
        clearPreviousData();

        if(show.getValue() == null){
            return;
        }

        String queryString;

        boolean isShowTimingSelected = showTimingField.getValue() != null;

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
                " where b.show = :show";
        queryString += (!isShowTimingSelected ? "" : " and b1.showTiming = :showTiming");

        Optional<Integer> totalSalesOptional = dataManager.loadValue(queryString, Integer.class)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
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
                ((!isShowTimingSelected ? "" : " and b.showTiming = :showTiming")) +
                " group by b.paymentCategory order by b.paymentCategory.name";


        List<KeyValueEntity> paymentCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
                .properties("paymentCategory", "paidTickets", "compTickets")
                .list();
        populatePaymentsTable(paymentCategoryList, totalCapacity);


        /* Populate Ticket Category table */
        queryString = "select b.ticketCategory, sum(b.paidTickets), sum(b.comps) from omega$BookingItem b" +
                " inner join omega$Booking b1 on b.booking = b1" +
                " where b1.show = :show" +
                ((!isShowTimingSelected ? "" : " and b.showTiming = :showTiming")) +
                " group by b.ticketCategory order by b.ticketCategory.categoryName";

        List<KeyValueEntity> ticketCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue())
                .parameter("showTiming", showTimingField.getValue())
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

    }


    private void clearPreviousData() {
        // Remove data
        collectionDatasourcePayment.clear();
        collectionDatasourceTicket.clear();
        // Update the Datasource
        collectionDatasourcePayment.commit();
        collectionDatasourceTicket.commit();
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

//        queryString = "select s.ticketCategories, 0 from omega$Show s" +
                    " where s.id = :show" +
                    ((!isShowTimingSelected ? "" : " and s.showTimings = :showTiming"));

        List<KeyValueEntity> ticketCategoryList = dataManager.loadValues(queryString)
                .parameter("show", show.getValue().getUuid())
                .parameter("showTiming", showTimingField.getValue())

                .properties("ticketCategory", "paidTickets")
                .list();
        populateTicketTable(ticketCategoryList);

    }

}