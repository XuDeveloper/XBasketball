package com.xu.xbasketball.model.http.webview;

/**
 * Created by xu on 2019-05-22.
 */
public class LocalResource {

    private String mimeType;

    private String localPath;

    public LocalResource(String mimeType, String localPath) {
        this.mimeType = mimeType;
        this.localPath = localPath;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
