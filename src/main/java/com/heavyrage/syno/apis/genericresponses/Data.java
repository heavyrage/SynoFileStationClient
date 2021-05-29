package com.heavyrage.syno.apis.genericresponses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.heavyrage.syno.apis.genericresponses.deserialization.DataDeserializer;

/*@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginResponse.class, name = "sid"),
        @JsonSubTypes.Type(value = ListShares.class, name = "offset"),
})*/
/*
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, defaultImpl = ListShares.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginResponse.class),
        @JsonSubTypes.Type(value = ListShares.class),
})
*/
//@JsonSerialize(as=Data.class)
@JsonDeserialize(using = DataDeserializer.class)
public abstract class Data {


}
