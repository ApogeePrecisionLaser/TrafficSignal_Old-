/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dataEntry.Model;

import com.ts.dataEntry.tableClasses.District;
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
public class DistrictModel {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("DistrictModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("SELECT COUNT(*) FROM district ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("DistrictModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public List<District> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<District> list = new ArrayList<District>();
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query = "SELECT d.district_id, d.district_name, s.state_name "
                + "FROM district d, state s "
                + "WHERE d.state_id = s.state_id "
                + "ORDER BY s.state_name, d.district_name "
                + "LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                District state = new District();
                state.setDistrict_id(rset.getInt("district_id"));
                state.setDistrict_name(rset.getString("district_name"));
                state.setState_name(rset.getString("state_name"));
                list.add(state);
            }
        } catch (Exception e) {
            System.out.println("DistrictModel showData() Error: " + e);
        }
        return list;
    }

    public int insertRecord(District district) {
        String query = "INSERT INTO district(district_name, state_id) VALUES(?, ?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, district.getDistrict_name());
            pstmt.setInt(2, district.getState_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DistrictModel insertRecord() Error: " + e);
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

    public int updateRecord(District district) {
        String query = "UPDATE district SET district_name = ? WHERE district_id = ? ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, district.getDistrict_name());
            pstmt.setInt(2, district.getDistrict_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("DistrictModel updateRecord() Error: " + e);
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

    public int deleteRecord(int district_id) {
        String query = "DELETE FROM district WHERE district_id = " + district_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("DistrictModel deleteRecord() Error: " + e);
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

    public int getStateId(String stateName) {
        int state_id = -1;
        String query = "SELECT state_id FROM state WHERE state_name = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, stateName);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            state_id = rset.getInt("state_id");
        } catch (Exception e) {
            System.out.println("DistrictModel getStateId() Error: " + e);
        }
        return state_id;
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
        this.db_userPassword = db_userPasswrod;
    }
}

