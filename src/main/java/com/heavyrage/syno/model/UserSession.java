package com.heavyrage.syno.model;

import org.springframework.context.annotation.Scope;

@Scope("singleton")
public class UserSession {
    private String sid;

    public UserSession(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }
}
