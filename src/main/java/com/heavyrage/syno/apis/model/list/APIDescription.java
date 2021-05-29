package com.heavyrage.syno.apis.model.list;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIDescription {
    @JsonProperty("maxVersion")
    private int maxVersion;
    @JsonProperty("minVersion")
    private int minVersion;
    @JsonProperty("path")
    private String path;

    public APIDescription() {
        super();
    }

    public APIDescription(int maxVersion, int minVersion, String path) {
        super();
        this.maxVersion = maxVersion;
        this.minVersion = minVersion;
        this.path = path;
    }

    @JsonProperty("maxVersion")
    public int getMaxVersion() {
        return maxVersion;
    }

    @JsonProperty("maxVersion")
    public void setMaxVersion(int maxVersion) {
        this.maxVersion = maxVersion;
    }

    @JsonProperty("minVersion")
    public int getMinVersion() {
        return minVersion;
    }

    @JsonProperty("minVersion")
    public void setMinVersion(int minVersion) {
        this.minVersion = minVersion;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("\n");
        buffer.append("\t{\n");
        buffer.append("\t\tmaxVersion: ").append(this.maxVersion).append("\n");
        buffer.append("\t\tminVersion: ").append(this.minVersion).append("\n");
        buffer.append("\t\tpath: \"").append(this.path).append("\"\n");
        buffer.append("\t},\n");

        return buffer.toString();

    }
}
