package org.ksh.mission1.api;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.ksh.mission1.dto.WifiResponse;

import java.io.IOException;

public class ApiClient {
    private static ApiClient instance;
    private final String URL;
    private final String API_KEY;
    private final String TYPE;
    private final String SERVICE;


    public ApiClient(String url, String apiKey, String type, String service) {
        this.URL = url;
        this.API_KEY = apiKey;
        this.TYPE = type;
        this.SERVICE = service;
    }

    public static ApiClient getInstance(String URL, String API_KEY, String TYPE, String SERVICE) {
        if (instance == null) {
            instance = new ApiClient(URL, API_KEY, TYPE, SERVICE);
        }
        return instance;
    }

    public WifiResponse getWifiInfo(int page, int perPage) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("openapi.seoul.go.kr")
                .port(8088)
                .addPathSegment(API_KEY)
                .addPathSegment(TYPE)
                .addPathSegment(SERVICE)
                .addPathSegment(String.valueOf(page))
                .addPathSegment(String.valueOf(perPage))
                .build();

        Request request = new Request.Builder()
                .url(url).get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            System.out.println(responseBody);
            Gson gson = new Gson();
            return gson.fromJson(responseBody, WifiResponse.class);
        }
    }
}
