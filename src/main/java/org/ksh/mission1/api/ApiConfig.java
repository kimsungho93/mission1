package org.ksh.mission1.api;

public class ApiConfig {
    public static final String URL = "http://openapi.seoul.go.kr:8088/";
    public static final String API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    public static final String TYPE = "json";
    public static final String SERVICE = "TbPublicWifiInfo";

    public static String getWifiAPIUrl(int page, int perPage) {
        return URL + API_KEY + "/" + TYPE + "/" + SERVICE + "/" + page + "/" + perPage+ "/";
    }
}
