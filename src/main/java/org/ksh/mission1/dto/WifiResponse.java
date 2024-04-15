package org.ksh.mission1.dto;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.ksh.mission1.api.ApiDeserializer;

import java.util.ArrayList;
import java.util.List;

@JsonAdapter(ApiDeserializer.class)
public class WifiResponse {
    @SerializedName("list_total_count")
    private int totalCount;
    @SerializedName("row")
    private List<WifiDTO> wifiDTOList;

    public WifiResponse(int totalCount, List<WifiDTO> wifiDTOList) {
        this.totalCount = totalCount;
        this.wifiDTOList = wifiDTOList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<WifiDTO> getWifiDTOList() {
        if (wifiDTOList == null) {
            return new ArrayList<>();
        }
        return wifiDTOList;
    }
}
