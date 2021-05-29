package com.heavyrage.syno.apis.helper;

import com.heavyrage.syno.apis.model.list.responses.APIDescriptionResponse;
import com.heavyrage.syno.model.UserSession;
import org.springframework.stereotype.Component;

@Component
public class QueryURLBuilder {


    public String baseURL;
    private APIDescriptionResponse apiDescription;
    private UserSession userSession;

    public QueryURLBuilder() {
    }

    public QueryURLBuilder(String baseURL) {
        this.baseURL = baseURL;
    }

    public void setApiDescription(APIDescriptionResponse apiDescription) {
        this.apiDescription = apiDescription;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return this.userSession;
    }

    public String buildInitQuery() {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append("query.cgi?")
                .append("api=").append(SynologyAPINames.SYNO_INFO_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_INFO_API.getVersion())
                .append("&method=query")
                .append("&query=all");
        return builder.toString();
    }

    public String buildAuthenticationQuery(String login, String password) {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append(apiDescription.getAuth().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_AUTH_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_AUTH_API.getVersion())
                .append("&method=login")
                .append("&account=").append(login)
                .append("&passwd=").append(password)
                .append("&session=").append(SynologyAPINames.SYNO_SESSION)
                .append("&format=sid");
        return builder.toString();
    }

    public String buildLogoutQuery() {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI)
                .append(apiDescription.getAuth().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_AUTH_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_AUTH_API.getVersion())
                .append("&method=logout")
                .append("&session=").append(SynologyAPINames.SYNO_SESSION);
        return builder.toString();
    }

    public String buildCreateFolderQuery(String folderName, String parentPath) {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append(apiDescription.getCreateFolder().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_CREATE_FOLDER_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_CREATE_FOLDER_API.getVersion())
                .append("&method=create")
                .append("&folder_path=").append("\"").append(parentPath).append("\"")
                .append("&name=").append("\"").append(folderName).append("\"")
                .append("&_sid=").append(userSession.getSid());
        return builder.toString();
    }

    public String buildListShareQuery() {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append(apiDescription.getList().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_LIST_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_LIST_API.getVersion())
                .append("&method=list_share")
                .append("&_sid=").append(userSession.getSid());
        return builder.toString();
    }

    public String buildGetFolderQuery(String folderpath) {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append(apiDescription.getList().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_LIST_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_LIST_API.getVersion())
                .append("&method=list")
                .append("&filetype=all").append("&folder_path=").append("\"").append(folderpath).append("\"")
                .append("&_sid=").append(userSession.getSid());
        return builder.toString();
    }

    public String buildUploadFileQuery() {
        StringBuilder builder = new StringBuilder(baseURL);
        builder.append(SynologyAPINames.BASE_API_URI).append(apiDescription.getUpload().get().getPath()).append("?")
                .append("api=").append(SynologyAPINames.SYNO_UPLOAD_API)
                .append("&version=").append(SynologyAPIVersions.SYNO_UPLOAD_API.getVersion())
                .append("&method=upload")
                .append("&_sid=").append(userSession.getSid());
        return builder.toString();
    }

}
