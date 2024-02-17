package dev.machmudeffendi.uas_pbo.utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudHelper {

    public static boolean executeUpdate(String query) {
        Connection conn = JDBC.connect();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            return true;
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE,LocalDateTime.now() + ": Could not execute update to database");
            Logger.getAnonymousLogger().log(Level.SEVERE,LocalDateTime.now() + ": Error " + e.getLocalizedMessage());
            Alerts.error(
                    "Database error",
                    "Could not save",
                    "Error: " + e.getLocalizedMessage()
            ).showAndWait();
            return false;
        }
    }

    public static ResultSet executeQuery(String query) {
        Connection conn = JDBC.connect();
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE,LocalDateTime.now() + ": Could not execute query to database");
            Logger.getAnonymousLogger().log(Level.SEVERE,LocalDateTime.now() + ": error " + e.getMessage());
            Alerts.error(
                    "Database error",
                    "Could not get data",
                    "Error: " + e.getLocalizedMessage()
            ).showAndWait();
            return null;
        }
    }
}
