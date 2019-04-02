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
    private TextArea<String> data;

    private String type;

    @Inject
    private Events events;

    public void cancel() {
        closeWithDefaultAction();
    }

    public void add() {
        events.publish(new BulkAddEvent(this));
        closeWithDefaultAction();
    }

    public TextArea<String> getData() {
        return data;
    }

    public void setData(TextArea<String> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}