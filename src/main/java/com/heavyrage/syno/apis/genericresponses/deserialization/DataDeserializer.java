package com.heavyrage.syno.apis.genericresponses.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.model.auth.responses.LoginResponse;
import com.heavyrage.syno.apis.model.list.responses.APIDescriptionResponse;
import com.heavyrage.syno.apis.model.list.responses.FoldersResponse;
import com.heavyrage.syno.apis.model.list.responses.ListSharesResponse;
import com.heavyrage.syno.apis.model.upload.responses.UploadResponse;

import java.io.IOException;

public class DataDeserializer extends StdDeserializer<Data> {

    protected DataDeserializer() {
        super(Data.class);
    }
    @Override
    public Data deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode node = p.readValueAsTree();

        // Select the concrete class based on the existence of a property
        if (node.get("sid") != null) {
            return p.getCodec().treeToValue(node, LoginResponse.class);
        }
        if (node.get("shares") != null) {
            return p.getCodec().treeToValue(node, ListSharesResponse.class);
        }
        if (node.get("folders") != null) {
            return p.getCodec().treeToValue(node, FoldersResponse.class);
        }
        if (node.fieldNames().next().contains("SYNO.")) {
            return p.getCodec().treeToValue(node, APIDescriptionResponse.class);
        }
        if (node.get("file") != null) {
            return p.getCodec().treeToValue(node, UploadResponse.class);
        }
        return p.getCodec().treeToValue(node, FoldersResponse.class);
    }
}
