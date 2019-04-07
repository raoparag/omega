package com.srt.omega.web.booking;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.srt.omega.entity.Booking;
import com.srt.omega.entity.BookingItem;
import com.srt.omega.entity.Show;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class BookingEdit extends AbstractEditor<Booking> {
    @Inject
    private CollectionDatasource<Show, UUID> showsDs;
    @Inject
    private CollectionDatasource<BookingItem, UUID> bookingItemsDs;
    @Named("bookingItemsTable.create")
    private CreateAction bookingItemsTableCreate;
    @Named("bookingItemsTable.edit")
    private EditAction bookingItemsTableEdit;
    @Inject
    private Dialogs dialogs;
    private Logger logger = LoggerFactory.getLogger(BookingEdit.class);

    @Override
    public void init(Map<String, Object> params) {
        showsDs.addItemChangeListener((event) -> {
            if ((bookingItemsDs.size() > 0)
                    && (!bookingItemsDs.getItems().stream().findFirst().get().getShowTiming().getShow().getId()
                    .equals(getItem().getShow().getId()))) {
                dialogs.createOptionDialog()
                        .withCaption(getMessage("confirmShowChangeForBooking.title"))
                        .withMessage(getMessage("confirmShowChangeForBooking.msg"))
                        .withActions(
                                new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> {
                                    bookingItemsDs.clear();
                                    setShowParam();
                                }),
                                new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL).withHandler(e -> {
                                    getItem().setShow(event.getPrevItem());
                                })
                        )
                        .show();
            } else
                setShowParam();
        });
        bookingItemsDs.addCollectionChangeListener(event -> calculateTotals());
        bookingItemsDs.addItemPropertyChangeListener(event -> {
            if (event.getProperty().equalsIgnoreCase("paidTickets")
                    || event.getProperty().equalsIgnoreCase("discount")
                    || event.getProperty().equalsIgnoreCase("sisticFee")
                    || event.getProperty().equalsIgnoreCase("ticketCategory"))
                calculateTotals();
        });
    }

    private void calculateTotals() {
        int totalPaidTickets = bookingItemsDs.getItems().parallelStream().mapToInt(BookingItem::getPaidTickets).sum();
        getItem().setTotalPaidTickets(totalPaidTickets);
        int totalComps = bookingItemsDs.getItems().parallelStream().mapToInt(BookingItem::getComps).sum();
        getItem().setTotalComps(totalComps);
        double totalAmount = 0;
        for (BookingItem item : bookingItemsDs.getItems()) {
            totalAmount = totalAmount + (item.getPaidTickets() * (item.getTicketCategory().getPrice() + item.getSisticFee()));
            totalAmount = totalAmount - (item.getDiscount() / 100 * item.getPaidTickets() * item.getTicketCategory().getPrice());
        }
        getItem().setTotalPrice(totalAmount);
    }

    private void setShowParam() {
        bookingItemsTableCreate.setWindowParams(ParamsMap.of(
                "show", getItem().getShow()
        ));
        bookingItemsTableEdit.setWindowParams(ParamsMap.of(
                "show", getItem().getShow()
        ));
    }
}