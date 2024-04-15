package org.ksh.mission1.dto;


public class BookmarkGroupDTO {
    private int BMG_ID; // 북마크 그룹 ID
    private String BMG_NM; // 북마크 그룹 이름
    private int BMG_ORDER; // 순서
    private String BMG_CR_DTTM; // 만든 날짜
    private String BMG_UP_DTTM; // 수정한 날짜

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

    public int getBMG_ORDER() {
        return BMG_ORDER;
    }

    public void setBMG_ORDER(int BMG_ORDER) {
        this.BMG_ORDER = BMG_ORDER;
    }

    public String getBMG_CR_DTTM() {
        return BMG_CR_DTTM;
    }

    public void setBMG_CR_DTTM(String BMG_CR_DTTM) {
        this.BMG_CR_DTTM = BMG_CR_DTTM;
    }

    public String getBMG_UP_DTTM() {
        return BMG_UP_DTTM;
    }

    public void setBMG_UP_DTTM(String BMG_UP_DTTM) {
        this.BMG_UP_DTTM = BMG_UP_DTTM;
    }
}
