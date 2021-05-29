package com.heavyrage.syno.client;

import com.heavyrage.syno.apis.genericresponses.Data;
import com.heavyrage.syno.apis.helper.SynologyAPIVersions;
import com.heavyrage.syno.client.exceptions.AuthenticationFailureException;
import com.heavyrage.syno.client.exceptions.CompatibilityException;
import com.heavyrage.syno.client.exceptions.ResponseException;

import java.io.File;

public interface SynoRestClientIface {
    void init();
    String getVersion();
    void checkAPICompatibility(SynologyAPIVersions apiName) throws CompatibilityException;
    void authenticate(String login, String password) throws AuthenticationFailureException, CompatibilityException;
    Data listShares() throws ResponseException, CompatibilityException;
    Data getFolder(String path) throws ResponseException, CompatibilityException;
    void logout() throws AuthenticationFailureException, CompatibilityException;
    boolean createFolder(String foldername, String parentPath) throws ResponseException, CompatibilityException;
    boolean uploadFile(String filename, String filePath, File file) throws ResponseException, CompatibilityException;

}
