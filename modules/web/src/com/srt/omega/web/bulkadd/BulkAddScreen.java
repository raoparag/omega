package com.srt.omega.web.bulkadd;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.DialogMode;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.srt.omega.web.events.BulkAddEvent;

import javax.inject.Inject;

@UiController("omega_BulkAddScreen")
@UiDescriptor("bulk-add-screen.xml")
@DialogMode(forceDialog = true, width = "300px")
public class BulkAddScreen extends Screen {

    @Inject
    public TextArea<String> data;

    @Inject
    private Events events;

    public void cancel() {
        closeWithDefaultAction();
    }

    public void add() {
        events.publish(new BulkAddEvent(""));
        closeWithDefaultAction();
    }
}