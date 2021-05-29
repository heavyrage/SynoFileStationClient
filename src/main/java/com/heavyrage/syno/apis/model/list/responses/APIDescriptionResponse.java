package com.heavyrage.syno.apis.model.list.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.model.list.APIDescription;

import java.util.Optional;


@JsonDeserialize(using = JsonDeserializer.None.class)
public class APIDescriptionResponse extends Data {

    @JsonProperty(value = "SYNO.API.Info")
    private APIDescription info;

    @JsonProperty(value = "SYNO.API.Auth")
    private APIDescription auth;

    @JsonProperty(value = "SYNO.FileStation.List")
    private APIDescription list;

    @JsonProperty(value = "SYNO.FileStation.CreateFolder")
    private APIDescription createFolder;

    @JsonProperty(value = "SYNO.FileStation.Upload")
    private APIDescription upload;

    public APIDescriptionResponse() {
        super();
    }

    public APIDescriptionResponse(APIDescription info, APIDescription auth, APIDescription list, APIDescription upload) {
        this.info = info;
        this.auth = auth;
        this.list = list;
        this.upload = upload;
    }

    public Optional<APIDescription> getInfo() {
        return Optional.ofNullable(info);
    }

    public void setInfo(APIDescription info) {
        this.info = info;
    }

    public Optional<APIDescription> getAuth() {
        return Optional.ofNullable(auth);
    }

    public void setAuth(APIDescription auth) {
        this.auth = auth;
    }

    public Optional<APIDescription> getList() {
        return Optional.ofNullable(list);
    }

    public void setList(APIDescription list) {
        this.list = list;
    }

    public Optional<APIDescription> getCreateFolder() {
        return Optional.ofNullable(createFolder);
    }

    public void setCreateFolder(APIDescription createFolder) {
        this.createFolder = createFolder;
    }

    public Optional<APIDescription> getUpload() {
        return Optional.ofNullable(upload);
    }

    public void setUpload(APIDescription upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return "APIDescriptionResponse{" +
                "info=" + info +
                ", auth=" + auth +
                ", list=" + list +
                ", createFolder=" + createFolder +
                '}';
    }
}
