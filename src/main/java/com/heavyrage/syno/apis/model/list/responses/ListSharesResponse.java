package com.heavyrage.syno.apis.model.list.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.model.list.Folder;

import java.util.Collections;
import java.util.List;


@JsonDeserialize(using = JsonDeserializer.None.class)
public class ListSharesResponse extends Data {

    @JsonProperty("offset")
    private int offset;
    @JsonProperty("total")
    private int total;
    @JsonProperty("shares")
    private List<Folder> shares;

    public ListSharesResponse() {
        super();
    }

    public ListSharesResponse(int offset, int total, List<Folder> shares) {
        super();
        this.offset = offset;
        this.total = total;
        this.shares = shares;
    }

    public ListSharesResponse(int offset) {
        this.offset = offset;
        this.total = 0;
        this.shares = Collections.emptyList();
    }

    @JsonProperty("offset")
    public int getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(int offset) {
        this.offset = offset;
    }

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("shares")
    public List<Folder> getShares() {
        return shares;
    }

    @JsonProperty("shares")
    public void setShares(List<Folder> shares) {
        this.shares = shares;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("\n");
        buffer.append("\toffset: ").append(this.offset).append(",\n");
        buffer.append("\tshares: [\n");
        for (Folder folder : this.shares) {
            buffer.append("\t\t{\n");
            buffer.append("\t\t\tisdir: ").append(folder.isIsdir()).append("\n");
            buffer.append("\t\t\tname: ").append(folder.getName()).append("\n");
            buffer.append("\t\t\tpath: \"").append(folder.getPath()).append("\"\n");
            buffer.append("\t\t},\n");
        }
        buffer.append("\t],\n");
        buffer.append("\ttotal: ").append(this.total);

        return buffer.toString();
    }

}
