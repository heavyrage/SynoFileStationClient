package com.heavyrage.syno.apis.model.list;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Folder {

    @JsonProperty("isdir")
    private boolean isdir;
    @JsonProperty("name")
    private String name;
    @JsonProperty("path")
    private String path;

    public Folder() {
        super();
    }

    public Folder(boolean isdir, String name, String path) {
        super();
        this.isdir = isdir;
        this.name = name;
        this.path = path;
    }

    @JsonProperty("isdir")
    public boolean isIsdir() {
        return isdir;
    }

    @JsonProperty("isdir")
    public void setIsdir(boolean isdir) {
        this.isdir = isdir;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return name;
    }
}
