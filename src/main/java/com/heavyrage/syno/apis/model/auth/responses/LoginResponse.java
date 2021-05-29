package com.heavyrage.syno.apis.model.auth.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.genericresponses.Response;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class LoginResponse extends Data {

    @JsonProperty("sid")
    private String sid;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String sid) {
        super();
        this.sid = sid;
    }

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    @JsonProperty("sid")
    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "sid: "+this.sid;
    }
}
