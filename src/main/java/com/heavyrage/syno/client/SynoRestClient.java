package com.heavyrage.syno.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.genericresponses.Error;
import com.heavyrage.syno.apis.genericresponses.Response;
import com.heavyrage.syno.apis.helper.QueryURLBuilder;
import com.heavyrage.syno.apis.helper.SynologyAPINames;
import com.heavyrage.syno.apis.helper.SynologyAPIVersions;
import com.heavyrage.syno.apis.model.auth.responses.LoginResponse;
import com.heavyrage.syno.apis.model.list.APIDescription;
import com.heavyrage.syno.apis.model.list.responses.APIDescriptionResponse;
import com.heavyrage.syno.apis.model.list.responses.FoldersResponse;
import com.heavyrage.syno.apis.model.list.responses.ListSharesResponse;
import com.heavyrage.syno.apis.model.upload.UploadQuery;
import com.heavyrage.syno.client.exceptions.AuthenticationFailureException;
import com.heavyrage.syno.client.exceptions.CompatibilityException;
import com.heavyrage.syno.client.exceptions.ResponseException;
import com.heavyrage.syno.client.interceptors.SynoUploadHttpRequestInterceptor;
import com.heavyrage.syno.model.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynoRestClient implements SynoRestClientIface {

    private static final Logger log = LoggerFactory.getLogger(SynoRestClient.class);
    public RestTemplate rest;
    public QueryURLBuilder queryURLBuilder;
    private Map<SynologyAPIVersions, APIDescription> apiVersionList = new HashMap<>();


    public SynoRestClient(RestTemplate template) {
        this.rest = template;
    }

    public void setQueryURLBuilder(QueryURLBuilder queryURLBuilder) {
        this.queryURLBuilder = queryURLBuilder;
    }

    @Override
    public void init() {
        String query = this.queryURLBuilder.buildInitQuery();
        Response response = this.rest.getForObject(query, Response.class);
        APIDescriptionResponse descriptionResponse = (APIDescriptionResponse) response.getData().get();
        this.queryURLBuilder.setApiDescription(descriptionResponse);
        this.apiVersionList.put(SynologyAPIVersions.SYNO_INFO_API, descriptionResponse.getInfo().orElse(null));
        this.apiVersionList.put(SynologyAPIVersions.SYNO_AUTH_API, descriptionResponse.getAuth().orElse(null));
        this.apiVersionList.put(SynologyAPIVersions.SYNO_CREATE_FOLDER_API, descriptionResponse.getCreateFolder().orElse(null));
        this.apiVersionList.put(SynologyAPIVersions.SYNO_UPLOAD_API, descriptionResponse.getUpload().orElse(null));
        this.apiVersionList.put(SynologyAPIVersions.SYNO_LIST_API, descriptionResponse.getList().orElse(null));

    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public void checkAPICompatibility(SynologyAPIVersions apiName) throws CompatibilityException {
        // check if the api is available on remote DiskStation
        if(this.apiVersionList.get(apiName) == null) {
            throw new CompatibilityException("The api "+apiName+" is not available on the remote DiskStation");
        }
        // check that version of this client is in the range of compatible versions
        if (apiName.getVersion() < this.apiVersionList.get(apiName).getMinVersion() || apiName.getVersion() > this.apiVersionList.get(apiName).getMaxVersion()) {
            throw new CompatibilityException("This client is not compatible with the remote DiskStation api version");
        }
    }

    @Override
    public void authenticate(String login, String password) throws AuthenticationFailureException, CompatibilityException {
        Assert.notNull(login, "Login cannot be empty");
        Assert.notNull(password, "Password cannot be empty");

        checkAPICompatibility(SynologyAPIVersions.SYNO_AUTH_API);
        String query = this.queryURLBuilder.buildAuthenticationQuery(login, password);
        Response response = this.rest.getForObject(query, Response.class);
        if(response.isSuccess()) {
            if(response.getData().isPresent()) {
                LoginResponse loginResponse = (LoginResponse) response.getData().get();
                UserSession session = new UserSession(loginResponse.getSid());
                this.queryURLBuilder.setUserSession(session);
            }
        } else {
            if(response.getError().isPresent()) {
                Error error = (Error) response.getError().get();
                throw new AuthenticationFailureException(error.toString());
            }
            throw new AuthenticationFailureException("An error occured during authentication");
        }

    }

    @Override
    public Data listShares() throws ResponseException, CompatibilityException {
        checkAPICompatibility(SynologyAPIVersions.SYNO_LIST_API);
        String query = this.queryURLBuilder.buildListShareQuery();
        Response response = rest.getForObject(query, Response.class);
        if(response.isSuccess()) {
            Data data = response.getData().get();
            if(data instanceof ListSharesResponse) {
                return (ListSharesResponse) data;
            } else {
                throw new ResponseException("Data type is not as expected, please check API version compatibility");
            }
        } else {
            throw new ResponseException();
        }
    }

    @Override
    public Data getFolder(String path) throws ResponseException, CompatibilityException {
        checkAPICompatibility(SynologyAPIVersions.SYNO_LIST_API);
        String query = queryURLBuilder.buildGetFolderQuery(path);
        Response response = rest.getForObject(query, Response.class);
        if(response.isSuccess()) {
            Data data = response.getData().get();
            if(data instanceof FoldersResponse) {
                return (FoldersResponse) data;
            } else {
                throw new ResponseException("Data type is not as expected, please check API version compatibility");
            }
        } else {
            if (response.getError().isPresent()) {

            }
        }
        throw new ResponseException();
    }

    @Override
    public void logout() throws AuthenticationFailureException, CompatibilityException {
        checkAPICompatibility(SynologyAPIVersions.SYNO_AUTH_API);
        String query = this.queryURLBuilder.buildLogoutQuery();
        Response response = this.rest.getForObject(query, Response.class);
        if(!response.isSuccess()) {
                throw new AuthenticationFailureException("An error occured while trying to logout");
        }
    }

    @Override
    public boolean createFolder(String folderName, String parentPath) throws ResponseException, CompatibilityException {
        Assert.notNull(folderName, "folderName cannot be null");
        Assert.notNull(parentPath, "parentPath cannot be null");
        checkAPICompatibility(SynologyAPIVersions.SYNO_CREATE_FOLDER_API);
        String query = this.queryURLBuilder.buildCreateFolderQuery(folderName, parentPath);
        Response response = this.rest.getForObject(query, Response.class);
        if(response.isSuccess()) {
            return true;
        } else {
            throw new ResponseException();
        }
    }

    @Override
    public boolean uploadFile(String filename, String filePath, File file) throws ResponseException, CompatibilityException {
        checkAPICompatibility(SynologyAPIVersions.SYNO_UPLOAD_API);
        String query = this.queryURLBuilder.buildUploadFileQuery();
        UploadQuery json = new UploadQuery("", 2, "", this.queryURLBuilder.getUserSession().getSid(), filePath, false, false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Arrays.asList(MediaType.ALL));
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("api", SynologyAPINames.SYNO_UPLOAD_API.getBytes());
        body.add("version", String.valueOf(SynologyAPIVersions.SYNO_UPLOAD_API.getVersion()).getBytes());
        body.add("method", "upload".getBytes());
        body.add("path", json.getPath().getBytes());
        body.add("overwrite", "false".getBytes());
        body.add("create_parents", "false".getBytes());
        body.add("_sid", this.queryURLBuilder.getUserSession().getSid().getBytes());
        body.add("filename", filename.getBytes());

        ContentDisposition content = null;
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();

        content = ContentDisposition.builder("form-data").name("file").filename(filename).build();

        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, content.toString());
        HttpEntity<FileSystemResource> fileEntity = new HttpEntity<>(new FileSystemResource(file), fileMap);
        body.add("file", fileEntity);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity(body, headers);
        RestTemplate template = new RestTemplate();
        template.setInterceptors(Arrays.asList(new SynoUploadHttpRequestInterceptor()));
        ResponseEntity<String> result = template.exchange(query, HttpMethod.POST, requestEntity, String.class);
        Response response = null;
        try {
            response = new ObjectMapper().readValue(result.getBody(),Response.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()) {
            return true;
        } else {
            throw new ResponseException();
        }
    }

}
