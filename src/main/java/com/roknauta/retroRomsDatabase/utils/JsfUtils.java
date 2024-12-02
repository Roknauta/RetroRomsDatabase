package com.roknauta.retroRomsDatabase.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import java.util.ResourceBundle;

public class JsfUtils {

    private static final ResourceBundle rb = ResourceBundle.getBundle("messages", getFacesContext().getViewRoot().getLocale());

    static public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    static public void addMessage(final String clientId, final FacesMessage.Severity severity, final String message) {
        getFacesContext().addMessage(clientId, new FacesMessage(severity, null, message));
    }

    static public void addMessage(final FacesMessage.Severity severity, final String message) {
        addMessage(null, severity, message);
    }

    static public void addInfoMessage(final String key) {
        addMessage(FacesMessage.SEVERITY_INFO, rb.getString(key));
    }

}
