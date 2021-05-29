package com.heavyrage.syno.apis.model.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

public class Login {

    @NonNull
    private String account;
    @NonNull
    private String passwd;
    @NonNull
    private String session;
    @NonNull
    @Value("sid")
    private String format;
    private String otp_code;

    public Login(String account, String passwd, String session, String format, String otp_code) {
        this.account = account;
        this.passwd = passwd;
        this.session = session;
        this.format = format;
        this.otp_code = otp_code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }
}
