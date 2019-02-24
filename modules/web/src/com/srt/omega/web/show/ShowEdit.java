package com.srt.omega.web.show;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.srt.omega.entity.Show;
import com.srt.omega.entity.TicketCategory;
import com.srt.omega.web.bulkadd.BulkAddScreen;
import com.srt.omega.web.events.BulkAddEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import javax.inject.Inject;
import java.util.UUID;

public class ShowEdit extends AbstractEditor<Show> {
    @Inject
    private Screens screens;

    private BulkAddScreen bulkAddScreen;

    @Inject
    private Metadata metadata;

    @Inject
    private CollectionDatasource<TicketCategory, UUID> ticketCategoriesDs;

    private Logger logger = LoggerFactory.getLogger(ShowEdit.class);

    public void onBulkAdd() {
        bulkAddScreen = screens.create(BulkAddScreen.class);
        screens.show(bulkAddScreen);
    }

    @EventListener
    public void onBulkAddTC(BulkAddEvent bulkAddEvent) {
        String bulkData = bulkAddScreen.data.getRawValue();
        String[] lines = bulkData.split("\\n");
        for (String line : lines) {
            String[] lineData = line.split(",");
            TicketCategory ticketCategory = metadata.create(TicketCategory.class);
            ticketCategory.setCategoryName(lineData[0]);
            ticketCategory.setCapacity(Integer.valueOf(lineData[1]));
            ticketCategory.setPrice(Double.valueOf(lineData[2]));
            ticketCategory.setShow(getItem());
            ticketCategoriesDs.addItem(ticketCategory);
        }
    }
}