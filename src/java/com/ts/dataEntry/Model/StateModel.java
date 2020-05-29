/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dataEntry.Model;

import com.ts.dataEntry.tableClasses.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shruti
 */
public class StateModel {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPasswrod;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString, db_userName, db_userPasswrod);
        } catch (Exception e) {
            System.out.println("StateModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("SELECT COUNT(*) FROM state ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("StateModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public List<State> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<State> list = new ArrayList<State>();
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query = "SELECT * FROM state ORDER BY state_name LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                State state = new State();
                state.setState_id(rset.getInt("state_id"));
                state.setState_name(rset.getString("state_name"));
                list.add(state);
            }
        } catch (Exception e) {
            System.out.println("StateModel showData() Error: " + e);
        }
        return list;
    }

    public int insertRecord(State state) {
        String query = "INSERT INTO state(state_name) VALUES(?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, state.getState_name());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("StateModel insertRecord() Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record saved successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot save the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

    public int updateRecord(State state) {
        String query = "UPDATE state SET state_name = ? WHERE state_id = ? ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, state.getState_name());
            pstmt.setInt(2, state.getState_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("StateModel updateRecord() Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record updated successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot update the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

    public int deleteRecord(int state_id) {
        String query = "DELETE FROM state WHERE state_id = " + state_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("StateModel deleteRecord() Error: " + e);
        }
        if (rowsAffected > 0) {
            message = "Record deleted successfully.";
            msgBgColor = COLOR_OK;
        } else {
            message = "Cannot delete the record, some error.";
            msgBgColor = COLOR_ERROR;
        }
        return rowsAffected;
    }

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDb_userName(String db_userName) {
        this.db_userName = db_userName;
    }

    public void setDb_userPasswrod(String db_userPasswrod) {
        this.db_userPasswrod = db_userPasswrod;
    }
}

