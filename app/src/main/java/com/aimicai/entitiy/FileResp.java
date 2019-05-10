package com.aimicai.entitiy;

import java.io.Serializable;

public class FileResp implements Serializable {

    /**
     * {
     *   "filename": filename,
     *   "url": url,
     *   "cdn":cdnname
     * }
     */

    private String filename;
    private String url;
    private String cdnname;
    private String image_url;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCdnname() {
        return cdnname;
    }

    public void setCdnname(String cdnname) {
        this.cdnname = cdnname;
    }
}
