package com.srt.omega.web.show;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.srt.omega.entity.Show;
import com.srt.omega.entity.ShowTiming;
import com.srt.omega.entity.ShowVenue;
import com.srt.omega.entity.TicketCategory;
import com.srt.omega.web.bulkadd.BulkAddScreen;
import com.srt.omega.web.events.BulkAddEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ShowEdit extends AbstractEditor<Show> {
    @Inject
    private Screens screens;

    @Inject
    private Metadata metadata;

    @Named("fieldGroup.showVenue")
    private Field<ShowVenue> showVenue;

    private String previousVenueName;

    @Inject
    private DataManager dataManager;

    @Inject
    private Notifications notifications;

    @Inject
    private CollectionDatasource<TicketCategory, UUID> ticketCategoriesDs;

    @Inject
    private CollectionDatasource<ShowTiming, UUID> showTimingsDs;

    @Inject
    private Dialogs dialogs;

    private final String ticketCategoryBulkAddType = "TicketCategory";
    private final String showTimingsBulkAddType = "ShowTimings";

    private Logger logger = LoggerFactory.getLogger(ShowEdit.class);

    @Subscribe
    protected void onInit(InitEvent event) {
        showVenue.addValueChangeListener(stringValueChangeEvent -> {

            int capacity = (int) dataManager.loadValue(
                    "select e.capacity from omega$ShowVenue e " +
                            "where e.name = :name", Integer.class)
                    .parameter("name", showVenue.getValue().getName())
                    .one();

            if(previousVenueName == null && getItem().getVenueCapacity() != null)
                return;
            if((previousVenueName != null && !previousVenueName.equals(showVenue.getValue().getName()))
                    || getItem().getVenueCapacity() == null) {
                getItem().setVenueCapacity(capacity);
                previousVenueName = showVenue.getValue().getName();
            }

        });
    }

    public void onBulkAddTicketCategory() {
        BulkAddScreen bulkAddScreen = screens.create(BulkAddScreen.class);
        bulkAddScreen.setType(ticketCategoryBulkAddType);
        screens.show(bulkAddScreen);
    }

    public void onBulkAddShowTiming() {
        BulkAddScreen bulkAddScreen = screens.create(BulkAddScreen.class);
        bulkAddScreen.setType(showTimingsBulkAddType);
        screens.show(bulkAddScreen);
    }

    @EventListener
    public void onBulkAddEvent(BulkAddEvent bulkAddEvent) {
        BulkAddScreen bulkAddScreen = (BulkAddScreen) bulkAddEvent.getSource();
        String bulkData = bulkAddScreen.getData().getRawValue();
        switch (bulkAddScreen.getType()) {
            case ticketCategoryBulkAddType:
                bulkAddTicketCategories(bulkData);
                break;
            case showTimingsBulkAddType:
                bulkAddShowTimings(bulkData);
                break;
        }
    }

    private void bulkAddShowTimings(String bulkData) {
        String[] lines = bulkData.split("\\n");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        int counter = 0;
        for (String line : lines) {
            try {
                String[] lineData = line.split(",");
                ShowTiming showTiming = metadata.create(ShowTiming.class);
                showTiming.setShowTime(dateFormatter.parse(lineData[0]));
                showTiming.setNotes(lineData[1]);
                showTiming.setShow(getItem());
                showTimingsDs.addItem(showTiming);
                counter++;
            } catch (Exception e) {
                logger.error("error in bulk add show timings", e);
            }
        }
        showBulkAddFinishMessage(counter + " Show Timings");
    }

    private void bulkAddTicketCategories(String bulkData) {
        String[] lines = bulkData.split("\\n");
        int counter = 0;
        for (String line : lines) {
            try {
                String[] lineData = line.split(",");
                TicketCategory ticketCategory = metadata.create(TicketCategory.class);
                ticketCategory.setCategoryName(lineData[0]);
                ticketCategory.setCapacity(Integer.valueOf(lineData[1]));
                ticketCategory.setPrice(Double.valueOf(lineData[2]));
                ticketCategory.setShow(getItem());
                ticketCategoriesDs.addItem(ticketCategory);
                counter++;
            } catch (Exception e) {
                logger.error("error in bulk add Ticket Categories", e);
            }
        }
        showBulkAddFinishMessage(counter + " Ticket Categories");
    }

    private void showBulkAddFinishMessage(String counterMessage){
        dialogs.createMessageDialog()
                .withCaption("Bulk Add Finished")
                .withMessage("Bulk Added " + counterMessage)
                .show();
    }
}