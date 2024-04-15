package org.ksh.mission1.api;

import lombok.RequiredArgsConstructor;
import org.ksh.mission1.dto.WifiResponse;

import java.io.IOException;

public class ApiService {
    private final ApiClient apiClient;

    public ApiService(ApiClient apiClient) {
        this.apiClient = ApiClient.getInstance(ApiConfig.URL, ApiConfig.API_KEY, ApiConfig.TYPE, ApiConfig.SERVICE);
    }

    public WifiResponse getWifiInfo(int page, int perPage) throws IOException {
        return apiClient.getWifiInfo(page, perPage);
    }
}
