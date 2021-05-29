package com.heavyrage.syno.apis.model.upload.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.heavyrage.syno.apis.genericresponses.Data;

@JsonDeserialize(using = JsonDeserializer.None.class)
public class UploadResponse extends Data {

    @JsonProperty(value="blSkip")
    private boolean blSkip;
    @JsonProperty(value="file")
    private String file;
    @JsonProperty(value="pid")
    private int pid;
    @JsonProperty(value="progress")
    private int progress;

    @JsonProperty(value="blSkip")
    public boolean isBlSkip() {
        return blSkip;
    }

    @JsonProperty(value="blSkip")
    public void setBlSkip(boolean blSkip) {
        this.blSkip = blSkip;
    }

    @JsonProperty(value="file")
    public String getFile() {
        return file;
    }

    @JsonProperty(value="file")
    public void setFile(String file) {
        this.file = file;
    }

    @JsonProperty(value="pid")
    public int getPid() {
        return pid;
    }

    @JsonProperty(value="pid")
    public void setPid(int pid) {
        this.pid = pid;
    }

    @JsonProperty(value="progress")
    public int getProgress() {
        return progress;
    }

    @JsonProperty(value="progress")
    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "blSkip=" + blSkip +
                ", file='" + file + '\'' +
                ", pid=" + pid +
                ", progress=" + progress +
                '}';
    }
}
