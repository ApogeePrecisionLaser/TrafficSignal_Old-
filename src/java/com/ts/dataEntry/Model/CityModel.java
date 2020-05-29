/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dataEntry.Model;


import com.ts.dataEntry.tableClasses.City;
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
public class CityModel {

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
            System.out.println("CityModel setConnection() Error: " + e);
        }
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from city ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
        } catch (Exception e) {
            System.out.println("CityModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public List<City> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<City> list = new ArrayList<City>();
        // Use DESC or ASC for descending or ascending order respectively of fetched data.
        String query = "SELECT c.city_id, c.city_name, d.district_name, s.state_name, c.pin_code, c.std_code "
                + "FROM city AS c, district AS d, state AS s "
                + "WHERE c.district_id = d.district_id AND c.state_id = s.state_id "
                + "ORDER BY s.state_name, d.district_name, c.city_name "
                + "LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
        try {
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                City city = new City();
                city.setCity_id(rset.getInt("city_id"));
                city.setCity_name(rset.getString("city_name"));
                city.setDistrict_name(rset.getString("district_name"));
                city.setState_name(rset.getString("state_name"));
                city.setPin_code(rset.getInt("pin_code"));
                city.setStd_code(rset.getString("std_code"));
                list.add(city);
            }
        } catch (Exception e) {
            System.out.println("CityModel showData() Error: " + e);
        }
        return list;
    }

    public int insertRecord(City city) {
        String query = "INSERT INTO city(city_name, district_id, state_id, pin_code, std_code) "
                + "VALUES(?, ?, ?, ?, ?) ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, city.getCity_name());
            pstmt.setInt(2, city.getDistrict_id());
            pstmt.setInt(3, city.getState_id());
            pstmt.setInt(4, city.getPin_code());
            pstmt.setString(5, city.getStd_code());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("CityModel insertRecord() Error: " + e);
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

    public int updateRecord(City city) {
        String query = "UPDATE city SET city_name = ?, district_id = ?, state_id = ?, pin_code = ?, std_code = ? WHERE city_id = ? ";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, city.getCity_name());
            pstmt.setInt(2, city.getDistrict_id());
            pstmt.setInt(3, city.getState_id());
            pstmt.setInt(4, city.getPin_code());
            pstmt.setString(5, city.getStd_code());
            pstmt.setInt(6, city.getCity_id());
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("CityModel updateRecord() Error: " + e);
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

    public int deleteRecord(int city_id) {
        String query = "DELETE FROM city WHERE city_id = " + city_id;
        int rowsAffected = 0;
        try {
            rowsAffected = connection.prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            System.out.println("CityModel deleteRecord() Error: " + e);
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
            System.out.println("CityModel getStateId() Error: " + e);
        }
        return state_id;
    }

    public int getDistrictId(String districtName) {
        int district_id = -1;
        String query = "SELECT district_id FROM district WHERE district_name = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, districtName);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            district_id = rset.getInt("district_id");
        } catch (Exception e) {
            System.out.println("CityModel getDistrictId() Error: " + e);
        }
        return district_id;
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


