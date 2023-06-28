package com.lab3.grupo01_lab3_2023.subscription;
import java.io.Serializable;
import java.util.List;


/*Esta clse abstrae el contenido de una sola suscripcion que ocurre en lista de suscripciones que figuran en el archivo de suscrpcion(json) */
public class SingleSubscription implements Serializable{

    private String url;
    private List<String> urlParams;
    private String urlType;


    public SingleSubscription(String url, List<String> urlParams, String urlType) {
        super();
        this.url = url;
        this.urlParams = urlParams;
        this.urlType = urlType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getUrlParams() {
        return urlParams;
    }

    public String getUlrParams(int i) {
        return this.urlParams.get(i);
    }

    public void setUrlParams(String urlParam) {
        this.urlParams.add(urlParam);
    }

    public int getUrlParamsSize() {
        return urlParams.size();
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    @Override
    public String toString() {
        return "{url=" + getUrl() + ", urlParams=" + getUrlParams().toString() + ", urlType=" + getUrlType() + "}";
    }

    public void prettyPrint() {
        System.out.println(this.toString());
    }


    public String getFeedToRequest(int i) {
        return this.getUrl().replace("%s", this.getUlrParams(i));
    }

}
