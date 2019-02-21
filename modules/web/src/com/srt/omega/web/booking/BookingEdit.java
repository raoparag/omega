package com.srt.omega.web.booking;

import com.haulmont.bali.util.ParamsMap;
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
    private Logger logger = LoggerFactory.getLogger(BookingEdit.class);

    @Override
    public void init(Map<String, Object> params) {
        showsDs.addItemChangeListener((event) -> {
            if ((bookingItemsDs.size() > 0)
                    && (!bookingItemsDs.getItems().stream().findFirst().get().getShowTiming().getShow().getId()
                    .equals(getItem().getShow().getId()))) {
                showOptionDialog(
                        getMessage("confirmShowChangeForBooking.title"),
                        getMessage("confirmShowChangeForBooking.msg"),
                        MessageType.CONFIRMATION,
                        new Action[]{
                                new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(e -> {
                                    bookingItemsDs.clear();
                                    setShowParam();
                                }),
                                new DialogAction(DialogAction.Type.NO, Action.Status.NORMAL).withHandler(e -> {
                                    getItem().setShow(event.getPrevItem());
                                })
                        }
                );
            } else
                setShowParam();
        });
        bookingItemsDs.addCollectionChangeListener(event -> calculateTotals());
        bookingItemsDs.addItemPropertyChangeListener(event -> {
            if (event.getProperty().equalsIgnoreCase("quantity")
                    || event.getProperty().equalsIgnoreCase("discount")
                    || event.getProperty().equalsIgnoreCase("sisticFee")
                    || event.getProperty().equalsIgnoreCase("ticketCategory"))
                calculateTotals();
        });
    }

    private void calculateTotals() {
        int totalQuantity = bookingItemsDs.getItems().parallelStream().mapToInt(BookingItem::getQuantity).sum();
        getItem().setTotalQuantity(totalQuantity);
        double totalAmount = 0;
        for (BookingItem item : bookingItemsDs.getItems()) {
            totalAmount = totalAmount + (item.getQuantity() * (item.getTicketCategory().getPrice() + item.getSisticFee()));
            totalAmount = totalAmount - (item.getDiscount() / 100 * item.getQuantity() * item.getTicketCategory().getPrice());
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