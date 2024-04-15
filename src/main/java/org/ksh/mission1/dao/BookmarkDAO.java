package org.ksh.mission1.dao;

import org.ksh.mission1.dto.BookmarkDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDAO {
    private MysqlConnector connector;

    public BookmarkDAO() {
        connector = new MysqlConnector();
        connector.connect();
    }

    public void createBookmark() throws SQLException {
        connector.connect();
        connector.connect();
        Connection conn = connector.getConnection();

        String createTableQuery = "CREATE TABLE bookmark (" +
                "    BM_ID INT AUTO_INCREMENT PRIMARY KEY," +
                "    BMG_ID INT," +
                "    X_SWIFI_MGR_NO VARCHAR(255)," +
                "    BM_REGI_DTTM VARCHAR(255)," +
                "    FOREIGN KEY (BMG_ID) REFERENCES bookmarkGroup(BMG_ID)," +
                "    FOREIGN KEY (X_SWIFI_MGR_NO) REFERENCES wifiInfo(X_SWIFI_MGR_NO)" +
                ");";
        Statement createStmt = conn.createStatement();
        createStmt.executeUpdate(createTableQuery);
    }

    public void insertBookmark(int bmgId, String mgrNo, String regiDttm) throws SQLException {
        // 데이터베이스 연결 확인 및 연결
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "INSERT INTO bookmark (BMG_ID, X_SWIFI_MGR_NO, BM_REGI_DTTM) VALUES (?, ?, ?)";

        try {
            // 'bookmark' 테이블의 존재 여부를 확인하고, 없는 경우 생성
            if (!connector.checkTableExists("bookmark")) {
                createBookmark();
            }

            // PreparedStatement 생성, 자동 생성되는 키들을 반환받기 위해 RETURN_GENERATED_KEYS 옵션 사용
            pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, bmgId);
            pstmt.setString(2, mgrNo);
            pstmt.setString(3, regiDttm);
            pstmt.executeUpdate();

            // 생성된 키(예: 자동 증가된 ID) 가져오기
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int bmId = rs.getInt(1);  // 생성된 ID 값
                    System.out.println("생성된 북마크 ID: " + bmId);  // 로그로 ID 출력
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // 예외 정보 출력
            throw e;  // 예외를 다시 던져 호출자에게 예외 정보를 제공
        } finally {
            if (pstmt != null) pstmt.close();  // PreparedStatement 자원 해제
            if (conn != null) conn.close();  // Connection 자원 해제
        }
    }

    public List<BookmarkDTO> getBookmark() throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM bookmark b " +
                "INNER JOIN wifiInfo w ON b.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO " +
                "INNER JOIN bookmarkGroup bg ON b.BMG_ID = bg.BMG_ID";
        List<BookmarkDTO> bookmarks = new ArrayList<>();

        try {
            // 만약 bookmark 테이블이 없으면 생성
            if (!connector.checkTableExists("bookmark")) {
                createBookmark();
            }

            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BookmarkDTO bmDTO = new BookmarkDTO();

                int bmId = rs.getInt("BM_ID");
                int bmgId = rs.getInt("BMG_ID");
                String bmgNm = rs.getString("BMG_NM");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String bmRegiDttm = rs.getString("BM_REGI_DTTM");

                bmDTO.setBM_ID(bmId);
                bmDTO.setBMG_ID(bmgId);
                bmDTO.setBMG_NM(bmgNm);
                bmDTO.setX_SWIFI_MAIN_NM(wifiName);
                bmDTO.setBM_REGI_DTTM(bmRegiDttm);
                bookmarks.add(bmDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bookmarks;
    }

    public BookmarkDTO getBookmarkById(int bmId) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "SELECT * FROM bookmark b " +
                "INNER JOIN wifiInfo w ON b.X_SWIFI_MGR_NO = w.X_SWIFI_MGR_NO " +
                "INNER JOIN bookmarkGroup bg ON b.BMG_ID = bg.BMG_ID " +
                "WHERE BM_ID=?";

        BookmarkDTO bmDTO = new BookmarkDTO();
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String bmgNm = rs.getString("BMG_NM");
                String wifiName = rs.getString("X_SWIFI_MAIN_NM");
                String bmRegiDttm = rs.getString("BM_REGI_DTTM");

                bmDTO.setBM_ID(bmId);
                bmDTO.setBMG_NM(bmgNm);
                bmDTO.setX_SWIFI_MAIN_NM(wifiName);
                bmDTO.setBM_REGI_DTTM(bmRegiDttm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        return bmDTO;
    }

    public void deleteBookmark(int bmId) throws SQLException {
        connector.connect();
        Connection conn = connector.getConnection();
        PreparedStatement pstmt = null;
        String query = "DELETE FROM bookmark WHERE BM_ID=?";

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, bmId);
            pstmt.executeUpdate();
        } finally {
            // 연결 및 자원 해제
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
