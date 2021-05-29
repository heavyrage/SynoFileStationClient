package com.heavyrage.syno.apis.helper;

import java.util.Arrays;
import java.util.List;

public abstract class SynologyAPINames {

    public final static String BASE_API_URI = "webapi/";
    public final static String SYNO_SESSION = "FileStation";
    public final static String SYNO_INFO_API = "SYNO.API.Info";
    public final static String SYNO_AUTH_API = "SYNO.API.Auth";
    public final static String SYNO_LIST_API = "SYNO.FileStation.List";
    public final static String SYNO_CREATE_FOLDER_API = "SYNO.FileStation.CreateFolder";
    public final static String SYNO_UPLOAD_API = "SYNO.FileStation.Upload";


    public List<String> getAllowedMethods(String apiName) {
        if(apiName == SYNO_AUTH_API) {
            return Arrays.asList("login", "logout");
        }
        if(apiName == SYNO_LIST_API) {
            return Arrays.asList("list_share", "list", "getinfo");
        }
        if(apiName == SYNO_CREATE_FOLDER_API) {
            return Arrays.asList("create");
        }
        if(apiName == SYNO_UPLOAD_API) {
            return Arrays.asList("upload");
        }
        throw new IllegalStateException("Unexpected value: " + apiName);
    }


}
