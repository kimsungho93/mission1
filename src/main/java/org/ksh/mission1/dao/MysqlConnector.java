package org.ksh.mission1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnector {
    private Connection connection;
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/mission1";
    private String username = "user1";
    private String password = "1234";

    public void connect() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkTableExists(String tableName) throws SQLException {
        Connection conn = getConnection();
        ResultSet tables = conn.getMetaData().getTables(null, null, tableName, null);
        boolean tableExists = tables.next();
        tables.close();
        return tableExists;
    }
}
