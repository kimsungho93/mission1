package org.ksh.mission1.dto;


public class BookmarkDTO {
    private int BM_ID; // 북마크 ID
    private int BMG_ID; // 북마크 그룹 ID
    private String BMG_NM; // 북마크 그룹 이름
    private String X_SWIFI_MAIN_NM; // 와이파이 이름
    private String BM_REGI_DTTM; // 북마크 등록한 날짜

    public int getBM_ID() {
        return BM_ID;
    }

    public void setBM_ID(int BM_ID) {
        this.BM_ID = BM_ID;
    }

    public int getBMG_ID() {
        return BMG_ID;
    }

    public void setBMG_ID(int BMG_ID) {
        this.BMG_ID = BMG_ID;
    }

    public String getBMG_NM() {
        return BMG_NM;
    }

    public void setBMG_NM(String BMG_NM) {
        this.BMG_NM = BMG_NM;
    }

    public String getX_SWIFI_MAIN_NM() {
        return X_SWIFI_MAIN_NM;
    }

    public void setX_SWIFI_MAIN_NM(String x_SWIFI_MAIN_NM) {
        X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
    }

    public String getBM_REGI_DTTM() {
        return BM_REGI_DTTM;
    }

    public void setBM_REGI_DTTM(String BM_REGI_DTTM) {
        this.BM_REGI_DTTM = BM_REGI_DTTM;
    }
}
