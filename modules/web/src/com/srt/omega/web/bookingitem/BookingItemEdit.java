package com.srt.omega.web.bookingitem;

import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.srt.omega.entity.BookingItem;
import com.srt.omega.entity.Show;
import com.srt.omega.entity.ShowTiming;
import com.srt.omega.entity.TicketCategory;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class BookingItemEdit extends AbstractEditor<BookingItem> {
    @WindowParam
    private Show show;
    @Inject
    private CollectionDatasource<ShowTiming, UUID> showTimingsDs;
    @Inject
    private CollectionDatasource<TicketCategory, UUID> ticketCategoriesDs;

    @Override
    public void init(Map<String, Object> params) {
        if (show != null) {
            showTimingsDs.setQuery(
                    "select e from omega$ShowTiming e where e.show = :param$show");
            ticketCategoriesDs.setQuery(
                    "select e from omega$TicketCategory e where e.show = :param$show");
        }
    }
}