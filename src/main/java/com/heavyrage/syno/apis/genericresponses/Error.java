package com.heavyrage.syno.apis.genericresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {
    private int code;
    private Collection<ErrorInfo> errors;

    public Error() {
        super();
    }

    public Error(int code, Collection<ErrorInfo> errors) {
        this.code = code;
        this.errors = errors;
    }

    public Error(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Collection<ErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ErrorInfo> errors) {
        this.errors = errors;
    }

    public void addErrorInfo(ErrorInfo errorInfo) {
        this.errors.add(errorInfo);
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("code: ").append(code).append("\n");
        if (errors != null) {
            for( ErrorInfo info : errors) {
                stringBuffer.append(info).append("\n");
            }
        }

        return stringBuffer.toString();
    }
}
