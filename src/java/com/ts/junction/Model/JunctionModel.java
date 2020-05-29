/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Model;

import com.ts.junction.tableClasses.Junction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Shruti
 */
public class JunctionModel extends HttpServlet {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";
    String image_uploaded_for_column = null, uploaded_table = null, destination_path;

    public Map<Integer, Junction> getJunctionList() {
        Map<Integer, Junction> junctionList = new HashMap<Integer, Junction>();
        try {
            String query = "SELECT junction_id,program_version_no, junction_name, address1, address2, city_name, controller_model, no_of_sides, amber_time, "
                    + " flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, "
                    + " side2_name, side3_name, side4_name, side5_name,file_no "
                    + " from junction AS j, city AS c "
                    + " WHERE c.city_id=j.city_id AND j.final_revision='VALID'";

            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                Junction junction = new Junction();
                int junction_id = rset.getInt("junction_id");
                int program_version_no = rset.getInt("program_version_no");
                junction.setJunction_id(junction_id);
                junction.setProgram_version_no(program_version_no);
                junction.setJunction_name(rset.getString("junction_name"));
                junction.setAddress1(rset.getString("address1"));
                junction.setAddress2(rset.getString("address2"));
                junction.setState_name(getStateName());
                junction.setCity_name(rset.getString("city_name"));
                junction.setController_model(rset.getString("controller_model"));
                junction.setNo_of_sides(rset.getInt("no_of_sides"));
                junction.setAmber_time(rset.getInt("amber_time"));
                junction.setFlash_rate(rset.getInt("flash_rate"));
                junction.setNo_of_plans(rset.getInt("no_of_plans"));
                junction.setMobile_no(rset.getString("mobile_no"));
                junction.setSim_no(rset.getString("mobile_no"));
                junction.setImei_no(rset.getString("imei_no"));
                junction.setInstant_green_time(rset.getInt("instant_green_time"));
                String pedestrian = rset.getString("pedestrian");
                junction.setPedestrian(pedestrian.equals("Y") ? "YES" : "NO");
                junction.setPedestrian_time(rset.getInt("pedestrian_time"));
                junction.setSide1_name(rset.getString("side1_name"));
                junction.setSide2_name(rset.getString("side2_name"));
                junction.setSide3_name(rset.getString("side3_name"));
                junction.setSide4_name(rset.getString("side4_name"));
                junction.setSide5_name(rset.getString("side5_name"));
                junction.setFile_no(rset.getInt("file_no"));
                junctionList.put(junction_id, junction);
            }
        } catch (Exception e) {
            System.out.println("Error:junctionModel-showData--- " + e);
        }
        return junctionList;
    }

    public int getJunctionID(String IMEINo) {
//        System.out.println(IMEINo);
        int junction_id = 0;
        String queryJunctionID = " SELECT * FROM junction WHERE imei_number = 'ABC' AND final_revision='VALID' ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(queryJunctionID);
            //pstmt.setString(1, IMEINo);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_id = rset.getInt("junction_id");
                System.out.println("junction_id---" + junction_id);
            }
//            connection.close();
        } catch (Exception e) {
            System.out.println("ClientResponder: registerModem() Error" + e);
        }
        return junction_id;
    }

    public List<String> getCityName(String q, String state_name) {
        List<String> list = new ArrayList<String>();
        PreparedStatement pstmt;
        String query = "SELECT c.city_name "
                + " FROM city AS c, state AS s "
                + " WHERE c.state_id=s.state_id AND state_name= ? "
                + " ORDER BY city_name ";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, state_name);
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String city_name = rset.getString("city_name");
                if (city_name.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(city_name);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such city_name exists.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    public List<String> getStateName(String q) {
        List<String> list = new ArrayList<String>();
        PreparedStatement pstmt;
        String query = "SELECT state_name "
                + " FROM state "
                + " ORDER BY state_name ";
        try {
            pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            q = q.trim();
            while (rset.next()) {    // move cursor from BOR to valid record.
                String state_name = rset.getString("state_name");
                if (state_name.toUpperCase().startsWith(q.toUpperCase())) {
                    list.add(state_name);
                    count++;
                }
            }
            if (count == 0) {
                list.add("No such state_name exists.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    public int deleteRecord(int junction_id) {
        String junctionQuery = "UPDATE junction SET final_revision = 'EXPIRED' WHERE junction_id= " + junction_id + " AND final_revision = 'VALID'";
        String planQuery = "UPDATE plan_info SET final_revision = 'EXPIRED' WHERE junction_id= " + junction_id + " AND final_revision = 'VALID'";
        int rowsAffected = 0;
        try {
            connection.setAutoCommit(false);
            rowsAffected = connection.prepareStatement(junctionQuery).executeUpdate();
            if (rowsAffected > 0) {
                rowsAffected = 0;
                rowsAffected = connection.prepareStatement(planQuery).executeUpdate();
                if (rowsAffected > 0) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            }
        } catch (Exception e) {
            System.out.println("Error:Delete:JunctionModel-- " + e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(JunctionModel.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public int getNoOfRows() {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select count(*) from junction WHERE final_revision= 'VALID' ").executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
            System.out.println(noOfRows);
        } catch (Exception e) {
            System.out.println("JunctionModel getNoOfRows() Error: " + e);
        }
        return noOfRows;
    }

    public int getCityID(String city_name) {
        int city_id = 0;
        String query = "SELECT city_id "
                + " FROM city WHERE city_name= ? ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, city_name);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                city_id = rset.getInt("city_id");
            }
        } catch (Exception ex) {
            System.out.println("JunctionModel getCityID() Error: " + ex);
        }
        return city_id;
    }

    public String getStateName() {
        String query = "SELECT state_name "
                + " FROM junction as j, city as c, district as d, state as s "
                + " WHERE j.city_id = c.city_id and "
                + " c.district_id = d.district_id and "
                + " d.state_id = s.state_id GROUP BY state_name ";
        String state_name = null;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                state_name = rset.getString("state_name");
            }
        } catch (Exception e) {
            System.out.println("Error:Delete:JunctionModel-- " + e);
        }
        return state_name;
    }

    public boolean insertRecord(Junction junction) {
        String junctionQuery = "INSERT INTO junction (junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, amber_time, "
                + " flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, "
                + " side2_name, side3_name, side4_name, side5_name, program_version_no, transferred_status, file_no, remark, created_by, latitude, longitude) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        String slave_insert_query = "INSERT into slave_info(junction_id, program_version_no, side_no, side_revision_no, side_name) "
                + " VALUES(?, ?, ?, ?, ?) ";

        String query = " SELECT MAX(junction_id) AS max_id FROM junction ";
        int junction_id = 0;
        int count = 0, rowsAffected = 0;
        PreparedStatement pstmt = null;
        boolean errorOccured = false;
        boolean autoCommit = true;
        try {
            pstmt = connection.prepareStatement(query);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_id = rset.getInt("max_id");
            }
        } catch (Exception e) {
            System.out.println("Junction Model insertRecord() error" + e);
        }

        try {
            autoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(junctionQuery);
            pstmt.setInt(1, (junction_id + 1));
            pstmt.setString(2, junction.getJunction_name());
            pstmt.setString(3, junction.getAddress1());
            pstmt.setString(4, junction.getAddress2());
            pstmt.setInt(5, getCityID(junction.getCity_name()));
            pstmt.setString(6, junction.getController_model());
            pstmt.setInt(7, junction.getNo_of_sides());
            pstmt.setInt(8, junction.getAmber_time());
            pstmt.setInt(9, junction.getFlash_rate());
            pstmt.setInt(10, junction.getNo_of_plans());
            pstmt.setString(11, junction.getMobile_no());
            pstmt.setString(12, junction.getSim_no());
            pstmt.setString(13, junction.getImei_no());
            pstmt.setInt(14, junction.getInstant_green_time());
            pstmt.setString(15, junction.getPedestrian());
            pstmt.setInt(16, junction.getPedestrian_time());
            pstmt.setString(17, junction.getSide1_name());
            pstmt.setString(18, junction.getSide2_name());
            pstmt.setString(19, junction.getSide3_name());
            pstmt.setString(20, junction.getSide4_name());
            pstmt.setString(21, junction.getSide5_name());
            pstmt.setInt(22, 1);
            pstmt.setString(23, "NO");
            pstmt.setInt(24, junction.getFile_no());
            pstmt.setString(25, junction.getRemark());
            pstmt.setInt(26, 1);
            pstmt.setString(27, junction.getLatitude());
            pstmt.setString(28, junction.getLongitude());
            rowsAffected = pstmt.executeUpdate();
            for (int i = 0; i < junction.getNo_of_sides() && rowsAffected > 0; i++) {
                rowsAffected = 0;
                pstmt.close();
                pstmt = connection.prepareStatement(slave_insert_query);
                pstmt.setInt(1, (junction_id + 1));
                pstmt.setInt(2, 1);
                pstmt.setInt(3, i + 1);
                pstmt.setInt(4, 1);
                pstmt.setString(5, i == 0 ? junction.getSide1_name()
                        : i == 1 ? junction.getSide2_name()
                        : i == 2 ? junction.getSide3_name()
                        : i == 3 ? junction.getSide4_name()
                        : i == 4 ? junction.getSide5_name() : "");
                rowsAffected = pstmt.executeUpdate();
            }
            if (rowsAffected > 0) {
                // Finally commit the connection.
                connection.commit();
                message = "Record saved successfully.";
                msgBgColor = COLOR_OK;
            } else {
                throw new SQLException("Record updated in plan_info.");
            }

        } catch (SQLException sqlEx) {
            errorOccured = true;
            message = "Could NOT save data , some error.";
            msgBgColor = COLOR_ERROR;
            System.out.println("JUnctionModel insert() Error: " + message + " Cause: " + sqlEx.getMessage());
        } finally {
            try {
                pstmt.close();
                connection.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.getLogger(JunctionModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return !errorOccured;
    }

    public int updateRecord(Junction junction) {
        String query = null;
        PreparedStatement pstmt = null;
        int program_version_no = getFinalProgramVersionNo(junction.getJunction_id());
        query = "INSERT INTO junction (junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, amber_time, "
                + " flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, "
                + " side2_name, side3_name, side4_name, side5_name, program_version_no, transferred_status, file_no, remark, created_by, latitude, longitude) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        String updateStatus = "UPDATE junction SET final_revision='EXPIRED' WHERE junction_id = ? AND program_version_no = ? ";

        String update_plan_query = "UPDATE plan_info SET program_version_no = ? WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        String delete_plan_query = "UPDATE slave_info SET final_revision = 'EXPIRED' WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        String update_slave_query = "UPDATE slave_info SET program_version_no = ? WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        String slave_insert_query = "INSERT into slave_info(junction_id, program_version_no, side_no, side_revision_no, side_name) "
                + " VALUES(?, ?, ?, ?, ?) ";

        String update_slave_query1 = "UPDATE slave_info SET final_revision = 'EXPIRED' WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        int rowsAffected = 0;
        int sideRevisionNo = 0;
        int prevNoOfSides = getNoOfSides(junction.getJunction_id(), program_version_no);
        int prevNoOfPlans = getNoOfPlans(junction.getJunction_id(), program_version_no);
        try {
            boolean autoCommit = connection.getAutoCommit();
            try {
                connection.setAutoCommit(false);
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, (junction.getJunction_id()));
                pstmt.setString(2, junction.getJunction_name());
                pstmt.setString(3, junction.getAddress1());
                pstmt.setString(4, junction.getAddress2());
                pstmt.setInt(5, getCityID(junction.getCity_name()));
                pstmt.setString(6, junction.getController_model());
                pstmt.setInt(7, junction.getNo_of_sides());
                pstmt.setInt(8, junction.getAmber_time());
                pstmt.setInt(9, junction.getFlash_rate());
                pstmt.setInt(10, junction.getNo_of_plans());
                pstmt.setString(11, junction.getMobile_no());
                pstmt.setString(12, junction.getSim_no());
                pstmt.setString(13, junction.getImei_no());
                pstmt.setInt(14, junction.getInstant_green_time());
                pstmt.setString(15, junction.getPedestrian());
                pstmt.setInt(16, junction.getPedestrian_time());
                pstmt.setString(17, junction.getSide1_name());
                pstmt.setString(18, junction.getSide2_name());
                pstmt.setString(19, junction.getSide3_name());
                pstmt.setString(20, junction.getSide4_name());
                pstmt.setString(21, junction.getSide5_name());
                pstmt.setInt(22, program_version_no + 1);
                pstmt.setString(23, "NO");
                pstmt.setInt(24, junction.getFile_no());
                pstmt.setString(25, junction.getRemark());
                pstmt.setInt(26, 1);
                pstmt.setString(27, junction.getLatitude());
                pstmt.setString(28, junction.getLongitude());
                rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    pstmt.close();
                    rowsAffected = 0;
                    pstmt = connection.prepareStatement(updateStatus);
                    pstmt.setInt(1, junction.getJunction_id());
                    pstmt.setInt(2, program_version_no);
                    rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        if (prevNoOfPlans != junction.getNo_of_plans() && junction.getNo_of_plans() != 0) {
                            rowsAffected = 0;
                            pstmt.close();
                            pstmt = connection.prepareStatement(delete_plan_query);
                            pstmt.setInt(1, junction.getJunction_id());
                            pstmt.setInt(2, program_version_no);
                            rowsAffected = pstmt.executeUpdate();
                        } else if (prevNoOfPlans != 0) {
                            rowsAffected = 0;
                            pstmt.close();
                            pstmt = connection.prepareStatement(update_plan_query);
                            pstmt.setInt(1, program_version_no + 1);
                            pstmt.setInt(2, junction.getJunction_id());
                            pstmt.setInt(3, program_version_no);
                            rowsAffected = pstmt.executeUpdate();
                        }
                        if (rowsAffected > 0) {
                            pstmt.close();
                            if (prevNoOfSides != junction.getNo_of_sides()) {
                                rowsAffected = 0;
                                sideRevisionNo = getFinalSideRevisionNo(junction.getJunction_id(), program_version_no);
                                pstmt = connection.prepareStatement(update_slave_query1);
                                pstmt.setInt(1, junction.getJunction_id());
                                pstmt.setInt(2, program_version_no);
                                rowsAffected = pstmt.executeUpdate();
                                if (rowsAffected > 0) {
                                    for (int i = 0; i < junction.getNo_of_sides() && rowsAffected > 0; i++) {
                                        rowsAffected = 0;
                                        pstmt.close();
                                        pstmt = connection.prepareStatement(slave_insert_query);
                                        pstmt.setInt(1, junction.getJunction_id());
                                        pstmt.setInt(2, program_version_no + 1);
                                        pstmt.setInt(3, i + 1);
                                        pstmt.setInt(4, sideRevisionNo + 1);
                                        pstmt.setString(5, i == 0 ? junction.getSide1_name()
                                                : i == 1 ? junction.getSide2_name()
                                                : i == 2 ? junction.getSide3_name()
                                                : i == 3 ? junction.getSide4_name()
                                                : i == 4 ? junction.getSide5_name() : "");
                                        rowsAffected = pstmt.executeUpdate();
                                    }
                                } else {
                                    throw new SQLException("all pervious slaves records are not updateded");
                                }
                            } else {
                                pstmt = connection.prepareStatement(update_slave_query);
                                pstmt.setInt(1, program_version_no + 1);
                                pstmt.setInt(2, junction.getJunction_id());
                                pstmt.setInt(3, program_version_no);
                                //rowsAffected =
                                pstmt.executeUpdate();
                            }
                        } else {
                            throw new SQLException("Record updated in plan_info.");
                        }

                    } else {
                        throw new SQLException("Record NOT updated in junction.");
                    }
                } else {
                    throw new SQLException("Record NOT saved in junction.");
                }
            } catch (SQLException sqlEx) {
                connection.rollback();
                message = "Could NOT save data , some error.";
                msgBgColor = COLOR_ERROR;
                System.out.println("JUnctionModel updateRecord() Error: " + message + " Cause: " + sqlEx.getMessage());
            } finally {
                pstmt.close();
                connection.setAutoCommit(autoCommit);
            }
        } catch (Exception e) {
            System.out.println("JUnctionModel updateRecord() Error: " + e);
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

    public List<Junction> showData(int lowerLimit, int noOfRowsToDisplay) {
        List<Junction> list = new ArrayList<Junction>();
        try {
            String query = "SELECT junction_id, junction_name,latitude,longitude, address1, address2, city_name, controller_model, no_of_sides, amber_time, "
                    + " flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, "
                    + " side2_name, side3_name, side4_name, side5_name,file_no, program_version_no,remark "
                    + " from junction AS j, city AS c "
                    + " WHERE c.city_id=j.city_id AND j.final_revision='VALID' "
                    + "LIMIT " + lowerLimit + ", " + noOfRowsToDisplay;
            System.out.println(lowerLimit + "," + noOfRowsToDisplay);
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {
                Junction junction = new Junction();
                junction.setJunction_id(rset.getInt("junction_id"));
                junction.setJunction_name(rset.getString("junction_name"));
                junction.setAddress1(rset.getString("address1"));
                junction.setAddress2(rset.getString("address2"));
                junction.setState_name(getStateName());
                junction.setCity_name(rset.getString("city_name"));
                junction.setController_model(rset.getString("controller_model"));
                junction.setNo_of_sides(rset.getInt("no_of_sides"));
                junction.setAmber_time(rset.getInt("amber_time"));
                junction.setFlash_rate(rset.getInt("flash_rate"));
                junction.setNo_of_plans(rset.getInt("no_of_plans"));
                junction.setMobile_no(rset.getString("mobile_no"));
                junction.setSim_no(rset.getString("mobile_no"));
                junction.setImei_no(rset.getString("imei_no"));
                junction.setInstant_green_time(rset.getInt("instant_green_time"));
                String pedestrian = rset.getString("pedestrian");
                junction.setPedestrian(pedestrian.equals("Y") ? "YES" : "NO");
                junction.setPedestrian_time(rset.getInt("pedestrian_time"));
                junction.setSide1_name(rset.getString("side1_name"));
                junction.setSide2_name(rset.getString("side2_name"));
                junction.setSide3_name(rset.getString("side3_name"));
                junction.setSide4_name(rset.getString("side4_name"));
                junction.setSide5_name(rset.getString("side5_name"));
                junction.setFile_no(rset.getInt("file_no"));
                junction.setProgram_version_no(rset.getInt("program_version_no"));
                junction.setRemark(rset.getString("remark"));
                junction.setLatitude(rset.getString("latitude"));
                junction.setLongitude(rset.getString("longitude"));
                list.add(junction);
            }

        } catch (Exception e) {
            System.out.println("Error:junctionModel-showData--- " + e);
        }
        return list;
    }

    public boolean checkImei(String imei_no) {
        boolean result = false;
        String query1 = " SELECT count(*) AS c FROM junction WHERE imei_no = '" + imei_no + "' AND final_revision='VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                result = rset.getInt("c") == 0 ? false : true;
            }
        } catch (Exception e) {
            System.out.println("Junction Model checkImei() error" + e);
        }
        return result;
    }

    public int getFinalProgramVersionNo(int junction_id) {
        int program_version_no = 0;
        String query1 = " SELECT program_version_no FROM junction WHERE junction_id = ? AND final_revision='VALID'";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, junction_id);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                program_version_no = rset.getInt("program_version_no");
            }

        } catch (Exception e) {
            System.out.println("Junction Model getFinalProgramVersionNo() error" + e);
        }
        return program_version_no;
    }

    public int getFinalPlanRevisionNo(int junction_id, int programVersionNo) {
        int getFinalPlanRevisionNo = 0;
        String query1 = " SELECT plan_revision_no FROM plan_info WHERE junction_id = ? AND program_version_no = ?  AND final_revision='VALID' LIMIT 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, programVersionNo);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                getFinalPlanRevisionNo = rset.getInt("plan_revision_no");
            }

        } catch (Exception e) {
            System.out.println("Junction Model getFinalPlanRevisionNo() error" + e);
        }
        return getFinalPlanRevisionNo;
    }

    public int getNoOfPlans(int junction_id, int program_version_no) {
        int noOfplans = 0;
        try {
            ResultSet rset = connection.prepareStatement("select no_of_plans from junction WHERE junction_id = " + junction_id + " AND program_version_no = " + program_version_no).executeQuery();
            rset.next();
            noOfplans = Integer.parseInt(rset.getString(1));
            System.out.println(noOfplans);
        } catch (Exception e) {
            System.out.println("JunctionModel getNoOfPlans() Error: " + e);
        }
        return noOfplans;
    }

    public int getFinalSideRevisionNo(int junction_id, int programVersionNo) {
        int side_revision_no = 0;
        String query1 = " SELECT side_revision_no FROM slave_info WHERE junction_id = ? AND program_version_no = ? AND final_revision='VALID' LIMIT 1";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, programVersionNo);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                side_revision_no = rset.getInt("side_revision_no");
            }

        } catch (Exception e) {
            System.out.println("Junction Model getFinalSideRevisionNo() error" + e);
        }
        return side_revision_no;
    }

    public int getNoOfSides(int junction_id, int program_version_no) {
        int noOfRows = 0;
        try {
            ResultSet rset = connection.prepareStatement("select no_of_sides from junction WHERE junction_id = " + junction_id + " AND program_version_no = " + program_version_no).executeQuery();
            rset.next();
            noOfRows = Integer.parseInt(rset.getString(1));
            System.out.println(noOfRows);
        } catch (Exception e) {
            System.out.println("JunctionModel getNoOfSides() Error: " + e);
        }
        return noOfRows;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMsgBgColor(String msgBgColor) {
        this.msgBgColor = msgBgColor;
    }

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("Image setConnection() Error: " + e);
        }
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (Exception ex) {
            System.out.println("ERROR: on closeConnection() in ClientResponderModel : " + ex);
        }
    }
}
