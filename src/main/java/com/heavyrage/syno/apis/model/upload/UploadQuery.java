package com.heavyrage.syno.apis.model.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.heavyrage.syno.apis.helper.SynologyAPINames;

public class UploadQuery {

    @JsonProperty("api")
    private String api;
    @JsonProperty("version")
    private int version;
    @JsonProperty("method")
    private String method;
    @JsonProperty("_sid")
    private String sid;
    @JsonProperty("path")
    private String path;
    @JsonProperty("create_parents")
    private boolean create_parents;
    @JsonProperty("overwrite")
    private boolean overwrite;

    public UploadQuery() {
        super();
    }

    public UploadQuery(String api, int version, String method, String sid, String path, boolean create_parents, boolean overwrite) {
        this.api = SynologyAPINames.SYNO_UPLOAD_API;
        this.version = 2;
        this.method = "upload";
        this.sid = sid;
        this.path = path;
        this.create_parents = create_parents;
        this.overwrite = overwrite;
    }

    public String getApi() {
        return api;
    }

    public int getVersion() {
        return version;
    }

    public String getMethod() {
        return method;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCreate_parents() {
        return create_parents;
    }

    public void setCreate_parents(boolean create_parents) {
        this.create_parents = create_parents;
    }

    public boolean isOverwrite() {
        return overwrite;
    }

    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    @Override
    public String toString() {
        return "{" +
                "'api':'" + api + '\'' +
                ", 'version':'" + version + '\'' +
                ", 'method':'" + method + '\'' +
                ", '_sid':" + sid +
                ", 'path':" + path +
                ", 'create_parents':\"" + create_parents + "\"" +
                ", 'overwrite':\"" + overwrite + "\"" +
                "}";
    }
}
