package com.heavyrage.syno.apis.genericresponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty(value = "data")
    private Data data;
    @JsonProperty(value = "error")
    private Error error;

    public Response() {
        super();
    }

    public Response(boolean success, Data data, Error error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public Response(boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

    public Response(boolean success, Error error) {
        this.success = success;
        this.error = error;
    }

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @JsonProperty("data")
    public Optional<Data> getData() {
        return Optional.ofNullable(data);
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("error")
    public Optional<Error> getError() {
        return Optional.ofNullable(error);
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("\n{\n");
        stringBuilder.append("success: ").append(this.success);
        stringBuilder.append("\n");
        if (this.data != null) {
            stringBuilder.append("data: {").append(this.data).append("}");
        }
        stringBuilder.append("\n");
        if (this.error != null) {
            stringBuilder.append("error: {").append(this.error).append("}");
        }
        stringBuilder.append("\n}");

        return stringBuilder.toString();
    }
}
