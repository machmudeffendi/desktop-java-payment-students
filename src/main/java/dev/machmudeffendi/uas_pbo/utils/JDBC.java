package dev.machmudeffendi.uas_pbo.utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBC {
    private static final String location = "jdbc:mysql://localhost:3306/uas_pbo";
    private static final String user = "root";
    private static final String password = "";
    private static final String requiredTable = "siswa";

    protected static Connection connect() {
        Connection connection;
        try{
            connection = DriverManager.getConnection(location, user, password);
            return connection;
        } catch (Exception e){
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not connect to SQLite DB at " + location);
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": error message " + e.getMessage());
            return null;
        }
    }

    public static boolean checkDrivers(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start Mysql Drivers");
            return false;
        }
    }

    private static boolean checkConnection() {
        try (Connection connection = connect()) {
            return connection != null;
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not connect to database");
            return false;
        }
    }

    private static boolean checkTables() {
        String checkTables =
                "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE() AND table_name = ?";

        try (Connection connection = connect()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(checkTables);
            statement.setString(1, requiredTable);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String tableName = rs.getString("table_name");
                return requiredTable.equals(tableName);
            }
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not find tables in database");
            return false;
        }
        return false;
    }

    public static boolean isOK(){
        if (!checkDrivers()) return false;

        if (!checkConnection()) return false;

        return checkTables();
    }
}
