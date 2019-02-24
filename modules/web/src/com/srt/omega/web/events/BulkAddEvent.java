package com.srt.omega.web.events;

import com.haulmont.cuba.gui.events.UiEvent;
import org.springframework.context.ApplicationEvent;

public class BulkAddEvent extends ApplicationEvent implements UiEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public BulkAddEvent(Object source) {
        super(source);
    }
}
