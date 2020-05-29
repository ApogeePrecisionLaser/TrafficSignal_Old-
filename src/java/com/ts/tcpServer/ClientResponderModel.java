/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.tcpServer;

import com.ts.junction.tableClasses.History;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Shruti
 */
public class ClientResponderModel extends HttpServlet {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("ClientResponderModel setConnection() Error: " + e);
        }
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    public void closeConnection() {
        try{
            this.connection.close();
        }catch(Exception ex){
            System.out.println("ERROR: on closeConnection() in ClientResponderModel : " + ex);
        }
    }

    public List<Integer> getPlanTiming(int planNo, int junctionID) {
        List<Integer> planTime = new ArrayList<Integer>();
        String query = "SELECT on_time_hour, on_time_min, off_time_hour, off_time_min FROM plan_info WHERE junction_id= ? AND plan_no= ? AND final_revision='VALID'";
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, planNo);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                int on_time_hour = rset.getInt("on_time_hour");
                int on_time_min = rset.getInt("on_time_min");
                int off_time_hour = rset.getInt("off_time_hour");
                int off_time_min = rset.getInt("off_time_min");
                planTime.add(on_time_hour);
                planTime.add(on_time_min);
                planTime.add(off_time_hour);
                planTime.add(off_time_min);
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanTiming() Error: " + e);
        }
        return planTime;
    }

    public int getNoOfSides(int junction_id, int program_version_no) {
        int noOfSides = 0;

        String query = "SELECT no_of_sides FROM junction WHERE junction_id= ? AND program_version_no= ? ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                noOfSides = rset.getInt("no_of_sides");
                System.out.println(noOfSides);
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getNoOfPlans() Error: " + e);
        }
        return noOfSides;
    }

    public int getCityName(int junctionID) {
        int cityID = 0;
        String query = " SELECT c.city_id FROM city AS c, junction As j WHERE j.city_id=c.city_id AND junction_id= ? ";
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                cityID = rset.getInt("c.city_id");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getCityName() Error: " + e);
        }
        return cityID;
    }

    public List<Integer> getCitySunriseSunset(int month, int cityID) {
        List<Integer> risesetList = new ArrayList<Integer>();
        Integer sunriseHrs = 0, sunriseMin = 0, sunsetHrs = 0, sunsetMin = 0;
        String query = " SELECT sunrise_hr, sunrise_min, sunset_hr, sunset_min FROM jn_rise_set_time "
                + " WHERE SUBSTRING(date, 6, 2) = ? AND city_id = ? ";
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, month);
            pstmt.setInt(2, cityID);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                sunriseHrs = rset.getInt("sunrise_hr");
                sunriseMin = rset.getInt("sunrise_min");
                sunsetHrs = rset.getInt("sunset_hr");
                sunsetMin = rset.getInt("sunset_min");
                risesetList.add(sunriseHrs);
                risesetList.add(sunriseMin);
                risesetList.add(sunsetHrs);
                risesetList.add(sunsetMin);
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getCitySunriseSunset() Error: " + e);
        }
        return risesetList;
    }

    public String getJunctionName(int junctionID, int program_version_no) {
        String query = " SELECT junction_name FROM junction WHERE junction_id= ? AND program_version_no = ?";
        String junction_name = "";
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_name = rset.getString("junction_name");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getJunctionName() Error: " + e);
        }
        return junction_name;
    }

    public String getSideName(int sideNo, int junctionID, int program_version_no) {
        String query = "SELECT side" + sideNo + "_name FROM junction WHERE junction_id = ? AND program_version_no= ? ";
        String sideNames = "";
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                sideNames = rset.getString("side" + sideNo + "_name");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getSideName() Error: " + e);
        }
        return sideNames;
    }

    public int getPlanGreenTime(int sideNo, int planNo, int junctionID, int program_version_no) {
        String query = "SELECT side" + sideNo + "_green_time FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND plan_no= ? AND final_revision='VALID' ";
        int plan_green_time = 0;
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, planNo);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                plan_green_time = rset.getInt("side" + sideNo + "_green_time");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanGreenTime() Error: " + e);
        }
        return plan_green_time;
    }

    public String getPlanMode(int planNo, int junctionID, int program_version_no) {
        String query = "SELECT mode FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND plan_no= ? AND final_revision='VALID'";
        String signal_mode = null;
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, planNo);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                signal_mode = rset.getString("mode");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanMode() Error: " + e);
        }
        return signal_mode;
    }

    public List<Integer> getPlanNo(int junction_id) {
        List<Integer> planNo = new ArrayList<Integer>();

        String query = "SELECT plan_no FROM plan_info WHERE junction_id= ? AND final_revision='VALID' ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junction_id);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                int planNum = rset.getInt("plan_no");
                System.out.println(planNum);
                planNo.add(planNum);
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanNo() Error: " + e);
        }
        return planNo;
    }

    public int getPlanAmberTime(int sideNo, int planNo, int junctionID, int program_version_no) {
        String query = "SELECT side" + sideNo + "_amber_time FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND plan_no= ? AND final_revision='VALID'";
        int plan_amber_time = 0;
        PreparedStatement pstmt;
        ResultSet rset;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, planNo);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                plan_amber_time = rset.getInt("side" + sideNo + "_amber_time");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanAmberTime() Error: " + e);
        }
        return plan_amber_time;
    }

    public int updateFileNo(int junctionID, int file_no, int program_version_no) {
        String query = null;
        PreparedStatement pstmt = null;
        query = "UPDATE junction SET file_no= ? WHERE junction_id = ? AND program_version_no = ? ";
        int rowsAffected = 0;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, file_no);
            pstmt.setInt(2, junctionID);
            pstmt.setInt(3, program_version_no);
            rowsAffected = pstmt.executeUpdate();
        } catch (SQLException sqlEx) {
            System.out.println("ClientResponderModel updateFileNo() Error: " + sqlEx.getMessage());
        }
        return rowsAffected;
    }

    public boolean insertRecord(History history) {
        int rowsReturned = 0;
        if (checkJunctionHistory(history.getIp_address())) {
            updateRecord(history);
        }
        String junctionQuery = " INSERT INTO log_history(ip_address, port, login_timestamp_date, login_timestamp_time, status, junction_id, program_version_no) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?) ";
        PreparedStatement pstmt;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        String currentTime = dateFormat.format(cal.getTime());
        java.util.Date parsedUtilDate = null;
        java.util.Date parsedUtilTime = null;
        try {
            parsedUtilDate = dateFormat.parse(currentDate);
            System.out.println(parsedUtilDate);
            parsedUtilTime = dateFormat.parse(currentTime);
        } catch (ParseException ex) {
            Logger.getLogger(ClientResponderModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqltDate = new java.sql.Date(parsedUtilDate.getTime());
        java.sql.Time sqltTime = new java.sql.Time(parsedUtilTime.getTime());
        boolean errorOccured = false;
        try {
            pstmt = connection.prepareStatement(junctionQuery);
            pstmt.setString(1, history.getIp_address());
            pstmt.setInt(2, history.getPort());
            pstmt.setDate(3, sqltDate);
            pstmt.setTime(4, sqltTime);
            pstmt.setString(5, history.isStatus() == false ? "Y" : "N");
            pstmt.setInt(6, history.getJunction_id());
            pstmt.setInt(7, history.getProgram_version_no());
            rowsReturned = pstmt.executeUpdate();
            if (rowsReturned > 0) {
                System.out.println("ClientResponderModel insertRecord() Record inserted successfully ");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel insertRecord() Error: " + e);
        }
        return !errorOccured;
    }

    public boolean updateRecord(History history) {
        String junctionQuery = " UPDATE log_history SET status= ?, logout_timestamp_date= ?, "
                + " logout_timestamp_time= ? WHERE ip_address= ?  AND logout_timestamp_date is null AND logout_timestamp_time is null  ";
        PreparedStatement pstmt = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        System.out.println(junctionQuery);
        String currentDate = dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        String currentTime = dateFormat.format(cal.getTime());
        java.util.Date parsedUtilDate = null;
        java.util.Date parsedUtilTime = null;
        try {
            parsedUtilDate = dateFormat.parse(currentDate);
            System.out.println(parsedUtilDate);
            parsedUtilTime = dateFormat.parse(currentTime);
        } catch (ParseException ex) {
            Logger.getLogger(ClientResponderModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqltDate = new java.sql.Date(parsedUtilDate.getTime());
        java.sql.Time sqltTime = new java.sql.Time(parsedUtilTime.getTime());

        boolean errorOccured = false;
        int rowsReturned = 0;
        try {
            pstmt = connection.prepareStatement(junctionQuery);
            boolean status = history.isStatus();
            pstmt.setString(1, "N");
            pstmt.setDate(2, sqltDate);
            pstmt.setTime(3, sqltTime);
            pstmt.setString(4, history.getIp_address());
            rowsReturned = pstmt.executeUpdate();
            if (rowsReturned > 0) {
                System.out.println("ClientResponderModel update() Record updated successfully ");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel update() Error: " + e);
        }
        return !errorOccured;
    }

    public boolean checkJunctionHistory(String ip) {
        String query = " SELECT Count(*) FROM log_history WHERE ip_address= ? AND logout_timestamp_date is null AND logout_timestamp_time is null  ";

        int rowsReturned = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, ip);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                rowsReturned = rset.getInt(1);
            }
            rset.close();
            pstmt.close();
        } catch (Exception e) {
            System.out.println("ClientResponderModel checkJunctionHistory() Error: " + e);
        }
        return rowsReturned > 0 ? true : false;
    }

    public boolean updateErrorStateOfLoggedInJunctions() {
        String junctionQuery = " UPDATE log_history SET status= ?, logout_timestamp_date= ?, logout_timestamp_time= ?, error_state= ? "
                + " WHERE status = 'Y' AND logout_timestamp_date is null AND logout_timestamp_time is null  ";
        PreparedStatement pstmt = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        System.out.println(junctionQuery);
        String currentDate = dateFormat.format(date);
        Calendar cal = Calendar.getInstance();
        String currentTime = dateFormat.format(cal.getTime());
        java.util.Date parsedUtilDate = null;
        java.util.Date parsedUtilTime = null;
        try {
            parsedUtilDate = dateFormat.parse(currentDate);
            System.out.println(parsedUtilDate);
            parsedUtilTime = dateFormat.parse(currentTime);
        } catch (ParseException ex) {
            Logger.getLogger(ClientResponderModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqltDate = new java.sql.Date(parsedUtilDate.getTime());
        java.sql.Time sqltTime = new java.sql.Time(parsedUtilTime.getTime());

        boolean errorOccured = false;
        int rowsReturned = 0;
        try {
            pstmt = connection.prepareStatement(junctionQuery);
            pstmt.setString(1, "N");
            pstmt.setDate(2, sqltDate);
            pstmt.setTime(3, sqltTime);
            pstmt.setString(4, "Y");
            rowsReturned = pstmt.executeUpdate();
            if (rowsReturned > 0) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Error state of all last logged in junctions updated successfully @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel updateErrorStateOfLoggedInJunctions() Error: " + e);
        }
        return !errorOccured;
    }

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from log_history WHERE logout_timestamp_time is null ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
            System.out.println(noOfRows);
        } catch (Exception e) {
            System.out.println("ClientResponderModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public int getNoOfRowsInShowAll(String ipAddress, int port) {
        int noOfRows = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement("select count(*) from log_history WHERE ip_address= ? AND port= ? ");
            pstmt.setString(1, ipAddress);
            pstmt.setInt(2, port);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
            System.out.println(noOfRows);
        } catch (Exception e) {
            System.out.println("ClientResponderModel getNoOfRowsInShowAll() Error: " + e);
        }
        return noOfRows;
    }

    public List<History> showDetails(String ipAddress, int port, int lowerLimit, int noOfRowsToDisplay) {
        List<History> list = new ArrayList<History>();
        try {
            String query = " SELECT ip_address, port, login_timestamp_date, login_timestamp_time, status, logout_timestamp_date, "
                    + " logout_timestamp_time , status FROM  log_history WHERE ip_address= ? AND port= ? ORDER BY login_timestamp_date DESC, "
                    + " login_timestamp_time DESC LIMIT " + lowerLimit + "," + noOfRowsToDisplay;
            PreparedStatement pstmt;
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, ipAddress);
            pstmt.setInt(2, port);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                History log_history = new History();
                log_history.setIp_address(rset.getString("ip_address"));
                log_history.setPort(rset.getInt("port"));
                log_history.setLogin_timestamp_date(rset.getString("login_timestamp_date"));
                log_history.setLogin_timestamp_time(rset.getString("login_timestamp_time"));
                log_history.setStatus(rset.getString("status").equals("Y") ? true : false);
                String logout_timestamp_date = rset.getString("logout_timestamp_date");
                String logout_timestamp_time = rset.getString("logout_timestamp_time");
                if (logout_timestamp_date == null) {
                    logout_timestamp_date = "";
                }
                if (logout_timestamp_time == null) {
                    logout_timestamp_time = "";
                }
                log_history.setLogout_timestamp_date(logout_timestamp_date);
                log_history.setLogout_timestamp_time(logout_timestamp_time);
                list.add(log_history);
            }

        } catch (Exception e) {
            System.out.println("Error:clientResponderModel-showDetails--- " + e);
        }
        return list;
    }

    public List<History> showLoggedInJunctionDetails() {
        List<History> list = new ArrayList<History>();
        try {
            String query = " SELECT j.junction_id, junction_name, city_name, ip_address, port, j.program_version_no, j.file_no, "
                    + " IF(synchronization_status is null or synchronization_status = '' ,'N',synchronization_status) AS synchronization_status ,"
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(application_hr,':',application_min,' ',application_date,'-',application_month,'-',application_year)) AS application_last_time, "
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(junction_hr,':',junction_min,' ',junction_date,'-',junction_month,'-',junction_year)) AS junction_last_time"
                    + " FROM  junction AS j LEFT JOIN time_synchronization_detail AS tsd"
                    + "  ON j.junction_id = tsd.junction_id AND tsd.final_revision='VALID' AND j.program_version_no=tsd.program_version_no "
                    + ", log_history AS lh, city AS c  "
                    + " WHERE j.junction_id=lh.junction_id AND j.city_id=c.city_id AND logout_timestamp_time is null AND j.final_revision='VALID' "
                    + "ORDER BY login_timestamp_date DESC, login_timestamp_time DESC ";
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                History log_history = new History();
                log_history.setJunction_id(rset.getInt("junction_id"));
                log_history.setProgram_version_no(rset.getInt("program_version_no"));
                log_history.setFileNo(rset.getInt("file_no"));
                log_history.setJunction_name(rset.getString("junction_name"));
                log_history.setCity_name(rset.getString("city_name"));
                log_history.setIp_address(rset.getString("ip_address"));
                log_history.setPort(rset.getInt("port"));
                log_history.setApplication_last_time_set(rset.getString("application_last_time"));
                log_history.setJunction_last_time_set(rset.getString("junction_last_time"));
                log_history.setTime_synchronization_status(rset.getString("synchronization_status"));
                list.add(log_history);
            }

        } catch (Exception e) {
            System.out.println("Error:clientResponderModel-showLoggedInJunctionDetails--- " + e);
        }
        return list;
    }

    public List<Integer> getJunctionID() {
        int junction_id = 0;
        List<Integer> list = new ArrayList<Integer>();
        String query = "SELECT j.junction_id FROM  junction AS j, log_history AS lh, city AS c "
                + " WHERE j.junction_id=lh.junction_id AND j.city_id=c.city_id AND logout_timestamp_time is null "
                + " ORDER BY login_timestamp_date DESC, login_timestamp_time DESC ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_id = rset.getInt("j.junction_id");
                list.add(junction_id);
            }
        } catch (Exception e) {
            System.out.println("Error: clientResponderModel getJunctionID " + e);
        }
        return list;
    }

    public boolean checkJunctionId(int junctionID) {
        boolean result = false;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT COUNT(*) FROM junction WHERE junction_id= ? AND final_revision='VALID'");
            pstmt.setInt(1, junctionID);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            result = rset.getInt(1) > 0 ? true : false;
            System.out.println(result);
        } catch (Exception e) {
            result = false;
            System.out.println("ClientResponderModel checkProgramVersionNo() Error: " + e);
        }
        return result;
    }

    public List<History> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<History> list = new ArrayList<History>();
        try {
            String query = " SELECT ip_address, port, login_timestamp_date, login_timestamp_time, status, logout_timestamp_date, "
                    + " logout_timestamp_time FROM  log_history WHERE logout_timestamp_time is null ORDER BY login_timestamp_date DESC, "
                    + " login_timestamp_time DESC LIMIT " + lowerLimit + "," + noOfRowsToDisplay;
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                History log_history = new History();
                log_history.setIp_address(rset.getString("ip_address"));
                log_history.setPort(rset.getInt("port"));
                log_history.setLogin_timestamp_date(rset.getString("login_timestamp_date"));
                log_history.setLogin_timestamp_time(rset.getString("login_timestamp_time"));
                log_history.setStatus(rset.getString("status").equals("Y") ? true : false);
                list.add(log_history);
            }

        } catch (Exception e) {
            System.out.println("Error:clientResponderModel-showData--- " + e);
        }
        return list;
    }

    public boolean checkJunctionLastSynchronisation(String ipAddress, String port, int junctionID, int program_version_no, boolean testRequest) {
        boolean result = false;
        try {
            String query1 = " SELECT COUNT(*) FROM log_history "
                    + " WHERE CONCAT_WS(' ',logout_timestamp_date,logout_timestamp_time) BETWEEN DATE_SUB(now(),INTERVAL 4 Hour) AND now() "
                    + " AND ip_address='" + ipAddress + "' AND status='N' AND junction_id= " + junctionID + " AND program_version_no = " + program_version_no
                    + " AND IF(" + testRequest + "=1, error_state LIKE '%%' , error_state = 'Y') ";

            ResultSet rset1 = connection.prepareStatement(query1).executeQuery();
            if (rset1.next()) {
                result = rset1.getInt(1) > 0 ? true : false;
            }

        } catch (Exception e) {
            System.out.println("Error:clientResponderModel-checkJunctionLastSynchronisation--- " + e);
        }
        return result;
    }

    public boolean checkJunctionIfLive(String ipAddress, String port, int junctionID, int program_version_no) {
        boolean result = false;
        try {
            String query = " SELECT COUNT(*) FROM log_history WHERE ip_address='" + ipAddress + "' AND status='Y' AND junction_id= " + junctionID + " AND program_version_no = " + program_version_no;

            ResultSet rset = connection.prepareStatement(query).executeQuery();
            if (rset.next()) {
                result = rset.getInt(1) > 0 ? true : false;
            }
        } catch (Exception e) {
           System.out.println("Error:clientResponderModel-checkJunctionIfLive--- " + e);
        }
        return result;
    }

    public int getNoOfPlans(int junction_id, int program_version_no) {
        int noOfPlans = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT COUNT(*) FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID'");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfPlans = Integer.parseInt(rset.getString(1));
            System.out.println(noOfPlans);
        } catch (Exception e) {
            System.out.println("ClientResponderModel getNoOfPlans() Error: " + e);
        }
        return noOfPlans;
    }

    public List<History> showDataBeanForSingleJunction(int junction_id) {
        List<History> list = new ArrayList<History>();
        String revision="VALID";
        int rev = 3;
        String query = " select side1_latlon as ColValue from junction where junction_id ="+junction_id+" and final_revision='VALID'"+
                        " union all select side2_latlon as ColValue from junction where junction_id ="+junction_id+" and final_revision='VALID'" +
                        " union all select side3_latlon as ColValue from junction where junction_id ="+junction_id+" and final_revision='VALID'" +
                        " union all select side4_latlon as ColValue from junction where junction_id ="+junction_id+" and final_revision='VALID'";
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                History bean = new History();
                String latlon = rset.getString("ColValue");
                String latlonArray[] = latlon.split(",");
                bean.setLatitude(latlonArray[0]);
                bean.setLongitude(latlonArray[1]);            
                list.add(bean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowDataBean() :ShiftLoginModel" + e);
        }
        return list;
    }
    
    public List<History> showDataBean() {
        List<History> list = new ArrayList<History>();
        String revision="VALID";
        int rev = 3;
        String query = " select junction_id,junction_name,no_of_sides,no_of_plans,latitude,longitude,path from junction where final_revision='VALID'";
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                History bean = new History();
                bean.setLatitude(rset.getString("latitude"));
                bean.setLongitude(rset.getString("longitude"));
                bean.setJunction_name(rset.getString("junction_name"));
                bean.setNo_of_sides(rset.getInt("no_of_sides"));
                bean.setNo_of_plans(rset.getInt("no_of_plans"));
                int junction_id=rset.getInt("junction_id");
                String active=getactiveJunction(junction_id);
                if(active.equals("No")){
                active= "NO";
                bean.setActive(active);
                }else{
                active="YES";
                bean.setActive(active);
                }
                //bean.setActive(getactiveJunction(junction_id));
                bean.setPath(rset.getString("path"));
                list.add(bean);
            }
        } catch (Exception e) {
            System.out.println("Error in ShowDataBean() :ShiftLoginModel" + e);
        }
        return list;
    }
    
    public String showDataBeanForJunction(String latitude,String longitude) {
        List<History> list = new ArrayList<History>();
        String junction_list="";
        int rev = 3;
        String query = " select junction_id,junction_name,no_of_sides,no_of_plans from junction where final_revision='VALID' and latitude="+latitude+" and longitude="+longitude;
        try {
            java.sql.PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                History bean = new History();           
                String junction_name = rset.getString("junction_name");
                String   no_of_sides = String.valueOf(rset.getInt("no_of_sides"));
                String no_of_plans = String.valueOf(rset.getInt("no_of_plans"));
                String junction_id = String.valueOf(rset.getInt("junction_id"));               
                junction_list = junction_name+","+no_of_sides+","+no_of_plans+","+junction_id;
            }
        } catch (Exception e) {
            System.out.println("Error in ShowDataBean() :ShiftLoginModel" + e);
        }
        return junction_list;
    }

    public String getActiveRequest(int junction_id) {
        String onTime = "";
        String query = "SELECT "
                + "active  FROM device_detail "
                + "WHERE RequestMode='M' and active = 'Y' AND junction_id = " + junction_id;
        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                onTime = rset.getString("active");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanOnTime() Error: " + e);
        }
        return onTime;
    }

    public String getactiveJunction(int junction_id) {
        String onTime = "No";
//        String query = "SELECT "
//                + "junction_id  FROM time_synchronization_detail "
//                + "WHERE junction_id = " + junction_id;
         String query = " SELECT j.junction_id, junction_name, city_name, ip_address, port, j.program_version_no, j.file_no, "
                    + " IF(synchronization_status is null or synchronization_status = '' ,'N',synchronization_status) AS synchronization_status ,"
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(application_hr,':',application_min,' ',application_date,'-',application_month,'-',application_year)) AS application_last_time, "
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(junction_hr,':',junction_min,' ',junction_date,'-',junction_month,'-',junction_year)) AS junction_last_time"
                    + " FROM  junction AS j LEFT JOIN time_synchronization_detail AS tsd"
                    + "  ON j.junction_id = tsd.junction_id AND tsd.final_revision='VALID' AND j.program_version_no=tsd.program_version_no "
                    + ", log_history AS lh, city AS c  "
                    + " WHERE j.junction_id=lh.junction_id AND j.city_id=c.city_id AND logout_timestamp_time is null AND j.final_revision='VALID' and j.junction_id =" + junction_id
                    + " ORDER BY login_timestamp_date DESC, login_timestamp_time DESC ";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                String junct=Integer.toString(rset.getInt("junction_id"));
                if(junct!=" "){
                onTime="Yes";
                }else{
                onTime="No";
                }
                //onTime = Integer.toString(rset.getInt("junction_id"));
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanOnTime() Error: " + e);
        }
        return onTime;
    }

    public int getFileNo(int junction_id, int program_version_no) {
        int fileNo = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT file_no FROM junction WHERE junction_id= ? AND program_version_no = ? ");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            fileNo = rset.getInt("file_no");
            //System.out.println(fileNo);
        } catch (Exception e) {
            System.out.println("ClientResponderModel getFIleNo() Error: " + e);
        }
        return fileNo;
    }

    public int getJunctionID(String imeiNo) {
//        System.out.println(IMEINo);
        int junction_id = 0;
        String queryJunctionID = " SELECT junction_id FROM junction WHERE imei_no = ? ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setString(1, imeiNo);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_id = rset.getInt("junction_id");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: getJunctionID() Error" + e);
        }
        return junction_id;
    }
    
    public int getJunctionIdByLatLon(String latitude, String longitude) {
//        System.out.println(IMEINo);
        int junction_id = 0;
        String queryJunctionID = " SELECT junction_id FROM junction WHERE latitude = ? and longitude = ? and final_revision = 'VALID'";

        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setString(1, latitude);
            pstmt.setString(2, longitude);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_id = rset.getInt("junction_id");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: getJunctionID() Error" + e);
        }
        return junction_id;
    }

    public int getSideNo(int junction_id, int program_version_no, String slave_id) {
//        System.out.println(slave_id);
        int side_no = 0;
        String queryJunctionID = " SELECT side_no FROM slave_info WHERE slave_id = ? AND junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setString(1, slave_id);
            pstmt.setInt(2, junction_id);
            pstmt.setInt(3, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                side_no = rset.getInt("side_no");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: getSideNo() Error" + e);
        }
        return side_no;
    }

    public int getProgramVersionNo(String imeiNo, int junctionID) {
        int program_Version_no = 0;
        String queryJunctionID = " SELECT program_version_no FROM junction WHERE imei_no = ? AND junction_id = ? AND final_revision='VALID' ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setString(1, imeiNo);
            pstmt.setInt(2, junctionID);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                program_Version_no = rset.getInt("program_version_no");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: getProgramVersionNo() Error" + e);
        }
        return program_Version_no;
    }

    public int getPedestrianTime(int junctionID, int program_version_no) {
        int pedestrian_time = 0;
        String queryJunctionID = " SELECT pedestrian_time AS pedestrian_time  FROM junction WHERE junction_id = ? AND program_version_no = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                pedestrian_time = rset.getInt("pedestrian_time");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponderModel: getPedestrianTime() Error" + e);
        }
        return pedestrian_time;
    }

    public boolean isPedestrian(int junctionID, int program_version_no) {
        boolean pedestrian = false;
        String queryJunctionID = " SELECT pedestrian FROM junction WHERE junction_id = ? AND program_version_no = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                pedestrian = rset.getString("pedestrian").equals("Y") ? true : false;
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponderModel: isPedestrian() Error" + e);
        }
        return pedestrian;
    }

    public boolean isValidJunction(int junctionID, int program_version_no) {
        boolean result = false;
        String queryJunctionID = " SELECT count(*) AS c FROM junction WHERE junction_id = ? AND program_version_no = ? AND final_revision='VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                result = rset.getInt("c") > 0 ? true : false;
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponderModel: isValidJunction() Error" + e);
        }
        return result;
    }

    public String getPlanOnTime(int junction_id, int program_version_no, int plan_no) {
        String onTime = "";
        String query = "SELECT "
                + "CONCAT(on_time_hour,':',on_time_min) AS onTime FROM plan_info "
                + "WHERE final_revision='VALID' AND junction_id = " + junction_id + " AND program_version_no= " + program_version_no + " AND plan_no= " + plan_no;
        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                onTime = rset.getString("onTime");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanOnTime() Error: " + e);
        }
        return onTime;
    }

    public String getPlanOffTime(int junction_id, int program_version_no, int plan_no) {
        String onTime = "";
        String query = "SELECT "
                + "CONCAT(off_time_hour,':',off_time_min) AS offTime FROM plan_info "
                + "WHERE final_revision='VALID' AND junction_id = " + junction_id + " AND program_version_no= " + program_version_no + " AND plan_no= " + plan_no;
        try {
            PreparedStatement pstmt = this.connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                onTime = rset.getString("offTime");
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel getPlanOffTime() Error: " + e);
        }
        return onTime;
    }

    public int getProgramVersionNo(int junctionID) {
        int program_Version_no = 0;
        String queryJunctionID = " SELECT program_version_no FROM junction WHERE junction_id = ? AND final_revision='VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionID);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                program_Version_no = rset.getInt("program_version_no");
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: getProgramVersionNo() Error" + e);
        }
        return program_Version_no;
    }

    public boolean updatePlanTransferredStatus(int junctionId, int program_version_no, int planNo) {
        boolean isTransferred = false;
        String queryJunctionID = " UPDATE plan_info SET transferred_status = 'YES' WHERE junction_id = ?  AND program_version_no= ? AND plan_no = ? AND final_revision='VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionId);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, planNo);
            int executeUpdate = pstmt.executeUpdate();
            if (executeUpdate > 0) {
                isTransferred = true;
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel: updatePlanTransferredStatus() Error" + e);
        }
        return isTransferred;
    }

    public boolean updateSlaveTransferredStatus(int junctionId, int program_version_no, int side_no) {
        boolean isTransferred = false;
        String queryJunctionID = " UPDATE slave_info SET transferred_status = 'YES' WHERE junction_id = ?  AND program_version_no= ? AND side_no = ? AND final_revision = 'VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setInt(1, junctionId);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, side_no);
            int executeUpdate = pstmt.executeUpdate();
            if (executeUpdate > 0) {
                isTransferred = true;
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel: updateSlaveTransferredStatus() Error" + e);
        }
        return isTransferred;
    }

    public boolean updateJunctionTransferredStatus(int junctionId, int program_version_no, String status) {
        boolean isTransferred = false;
        String queryJunctionID = " UPDATE junction SET transferred_status = ? WHERE junction_id = ? AND program_version_no= ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            pstmt.setString(1, status);
            pstmt.setInt(2, junctionId);
            pstmt.setInt(3, program_version_no);
            int executeUpdate = pstmt.executeUpdate();
            if (executeUpdate > 0) {
                isTransferred = true;
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel: updateJunctionTransferredStatus() Error" + e);
        }
        return isTransferred;
    }

    public int synchronizeTimeData(int junctionID, int program_version_no, int juncHr, int juncMin, int juncDat, int juncMonth, int juncYear, int appHr, int appMin, int appDat, int appMonth, int appYear) {
        int rowAffected = 0;
        String activity = "";
        String lastTimeSynchronizationStatus = getLastTimeSynchronizationStatus(junctionID, program_version_no);
        String currentTimeSynchronizationStatus = getCurrentTimeSynchronizationStatus(juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);

        if (lastTimeSynchronizationStatus.equals(currentTimeSynchronizationStatus)) {
            rowAffected = updateSynchronizeTimeData(junctionID, program_version_no, juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);
            activity = "updated";
        } else {
            rowAffected = insertSynchronizeTimeData(junctionID, program_version_no, juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear, currentTimeSynchronizationStatus);
            activity = "inserted";
        }
        if (rowAffected > 0) {
            System.out.println("Synchronized time data " + activity + " successfully.");
        }
        return rowAffected;
    }

    public String getCurrentTimeSynchronizationStatus(int juncHr, int juncMin, int juncDat, int juncMonth, int juncYear, int appHr, int appMin, int appDat, int appMonth, int appYear) {
        String currentTimeSynchronizationStatus = "N";
        if (juncHr == appHr && juncMin == appMin && juncDat == appDat && juncMonth == appMonth && juncYear == appYear) {
            currentTimeSynchronizationStatus = "Y";
        } else {
            currentTimeSynchronizationStatus = "N";
        }
        return currentTimeSynchronizationStatus;
    }

    public String getLastTimeSynchronizationStatus(int junctionID, int program_version_no) {
        String status = "No Record";
        int final_rev_no = getLastTimeSynchronizationId(junctionID, program_version_no);
        if (final_rev_no != -1) {
            String query = " SELECT synchronization_status FROM time_synchronization_detail "
                    + " WHERE time_synchronization_detail_id = ? "
                    + " AND junction_id = ? AND program_version_no = ? ";
            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, final_rev_no);
                pstmt.setInt(2, junctionID);
                pstmt.setInt(3, program_version_no);
                ResultSet rset = pstmt.executeQuery();
                while (rset.next()) {
                    status = rset.getString("synchronization_status");
                }
//            connection.close();
            } catch (Exception e) {
                System.out.println("ClientResponderModel: getLastTimeSynchronizationStatus() Error" + e);
            }
        }
        return status;
    }

    public int updateSynchronizeTimeData(int junctionID, int program_version_no, int juncHr, int juncMin, int juncDat, int juncMonth, int juncYear, int appHr, int appMin, int appDat, int appMonth, int appYear) {
        int rowsReturned = 0;
        String insertQuery = " UPDATE time_synchronization_detail SET  junction_hr = ?, application_hr = ?, junction_min = ?, application_min = ?, "
                + " junction_date = ?, application_date = ?, junction_month = ?, application_month = ?, junction_year = ?, application_year = ? WHERE "
                + " junction_id = ? AND time_synchronization_detail_id = ? AND program_version_no = ? ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(insertQuery);
            pstmt.setInt(1, juncHr);
            pstmt.setInt(2, appHr);
            pstmt.setInt(3, juncMin);
            pstmt.setInt(4, appMin);
            pstmt.setInt(5, juncDat);
            pstmt.setInt(6, appDat);
            pstmt.setInt(7, juncMonth);
            pstmt.setInt(8, appMonth);
            pstmt.setInt(9, juncYear);
            pstmt.setInt(10, appYear);
            pstmt.setInt(11, junctionID);
            pstmt.setInt(12, getLastTimeSynchronizationId(junctionID, program_version_no));
            pstmt.setInt(13, program_version_no);
            rowsReturned = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ClientResponderModel updateSynchronizeTimeData() Error: " + e);
        }
        return rowsReturned;
    }

    public int insertSynchronizeTimeData(int junctionID, int program_version_no, int juncHr, int juncMin, int juncDat, int juncMonth, int juncYear, int appHr, int appMin, int appDat, int appMonth, int appYear, String currentTimeSynchronizationStatus) {
        int rowsReturned = 0;
        String insertQuery = " INSERT INTO time_synchronization_detail(junction_id, time_synchronization_detail_id, junction_hr, application_hr, junction_min, application_min, "
                + " junction_date, application_date, junction_month, application_month, junction_year, application_year, synchronization_status, remark, program_version_no) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        PreparedStatement pstmt;
        try {
            int final_rev_no = getLastTimeSynchronizationId(junctionID, program_version_no);
            pstmt = connection.prepareStatement(insertQuery);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, (final_rev_no + 1));
            pstmt.setInt(3, juncHr);
            pstmt.setInt(4, appHr);
            pstmt.setInt(5, juncMin);
            pstmt.setInt(6, appMin);
            pstmt.setInt(7, juncDat);
            pstmt.setInt(8, appDat);
            pstmt.setInt(9, juncMonth);
            pstmt.setInt(10, appMonth);
            pstmt.setInt(11, juncYear);
            pstmt.setInt(12, appYear);
            pstmt.setString(13, currentTimeSynchronizationStatus);
            pstmt.setString(14, "");
            pstmt.setInt(15, program_version_no);
            rowsReturned = pstmt.executeUpdate();
            if (rowsReturned > 0 && final_rev_no != -1) {
                rowsReturned = 0;
                rowsReturned = updateSynchronizeTimeFinalRevision(junctionID, program_version_no, final_rev_no);
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel insertSynchronizeTimeData() Error: " + e);
        }
        return rowsReturned;
    }

    public int updateSynchronizeTimeFinalRevision(int junctionID, int program_version_no, int time_synchronization_detail_id) {
        String updateQuery = "UPDATE time_synchronization_detail SET  final_revision = ? WHERE  junction_id = ? AND time_synchronization_detail_id = ? AND program_version_no = ? ";
        int rowsReturned = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(updateQuery);
            pstmt.setString(1, "EXPIRED");
            pstmt.setInt(2, junctionID);
            pstmt.setInt(3, time_synchronization_detail_id);
            pstmt.setInt(4, program_version_no);
            rowsReturned = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("ClientResponderModel updateSynchronizeTimeFinalRevision() Error: " + e);
        }
        return rowsReturned;
    }

    public int getLastTimeSynchronizationId(int junctionID, int program_version_no) {
        int id = 0;
        String query = " SELECT COUNT(*) AS c,time_synchronization_detail_id AS id FROM time_synchronization_detail WHERE final_revision='VALID' AND junction_id = ? AND program_version_no = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junctionID);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                id = rset.getInt("c") != 0 ? rset.getInt("id") : -1;
            }
        } catch (Exception e) {
            System.out.println("ClientResponderModel: getLastTimeSynchronizationId() Error" + e);
        }
        return id;
    }

    public void setDriverClass(String driverclass) {
        this.driverClass = driverclass;
    }

    public void setConnectionString(String connectionstring) {
        this.connectionString = connectionstring;
    }

    public void setDb_userName(String username) {
        this.db_userName = username;
    }

    public void setDb_userPasswrod(String pass) {
        this.db_userPassword = pass;
    }
}