package com.heavyrage.syno.apis.helper;

public enum SynologyAPIVersions {
    SYNO_INFO_API(1),
    SYNO_AUTH_API(3),
    SYNO_LIST_API(2),
    SYNO_CREATE_FOLDER_API(2),
    SYNO_UPLOAD_API(2);

    private int value;

    SynologyAPIVersions(int v) {
        value = v;
    }

    public int getVersion() {
        return value;
    }
}
