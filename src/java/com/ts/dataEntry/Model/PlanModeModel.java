/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dataEntry.Model;

import com.ts.dataEntry.tableClasses.PlanMode;
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
public class PlanModeModel {

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

    public List<PlanMode> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<PlanMode> list = new ArrayList<PlanMode>();
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query = "SELECT * FROM plan_mode ORDER BY state_name LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                PlanMode planMode = new PlanMode();
                planMode.setPlan_mode_id(rset.getInt("plan_mode_id"));
                planMode.setPlan_mode_name(rset.getString("plan_mode_name"));
                list.add(planMode);
            }
        } catch (Exception e) {
            System.out.println("PlanModeModel showData() Error: " + e);
        }
        return list;
    }

    public int insertRecord(PlanMode planMode) {
        String query = "INSERT INTO plan_mode(plan_mode_name) VALUES(?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, planMode.getPlan_mode_name());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PlanModeModel insertRecord() Error: " + e);
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

    public int updateRecord(PlanMode planMode) {
        String query = "UPDATE plan_mode SET plan_mode_name = ? WHERE plan_mode_id = ? ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, planMode.getPlan_mode_name());
            pstmt.setInt(2, planMode.getPlan_mode_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("PlanModeModel updateRecord() Error: " + e);
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

    public int deleteRecord(int plan_mode_id) {
        String query = "DELETE FROM plan_mode WHERE plan_mode_id = " + plan_mode_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("PlanModeModel deleteRecord() Error: " + e);
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

