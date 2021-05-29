package com.heavyrage.syno.apis.model.auth;

import org.springframework.lang.NonNull;

public class Logout {
    @NonNull
    private String session;

    public Logout(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
