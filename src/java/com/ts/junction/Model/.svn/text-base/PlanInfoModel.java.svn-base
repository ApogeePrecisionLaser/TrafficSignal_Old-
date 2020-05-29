/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Model;

import com.ts.junction.tableClasses.PlanInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Shruti
 */
public class PlanInfoModel extends HttpServlet {

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

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("Image setConnection() Error: " + e);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (Exception ex) {
            System.out.println("ERROR: in closeConnection in TrafficSignalWebServiceModel : " + ex);
        }
    }

    public boolean validatePlans(List<PlanInfo> planInfoList) {
        return true;
    }

    public int getNoOfPlans(int junction_id, int program_version_no) {
        int noOfPlans = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT COUNT(*) FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND final_revision = 'VALID' ");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfPlans = Integer.parseInt(rset.getString(1));
            System.out.println(noOfPlans);
        } catch (Exception e) {
            System.out.println("PlanInfoModel getNoOfPlans() Error: " + e);
        }
        return noOfPlans;
    }

    public int getFinalPlanRevNo(int junction_id, int program_version_no) {
        int plan_revision_no = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT plan_revision_no FROM plan_info WHERE junction_id= ? AND program_version_no = ? AND final_revision = 'VALID' LIMIT 1");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            plan_revision_no = Integer.parseInt(rset.getString(1));
            System.out.println(plan_revision_no);
        } catch (Exception e) {
            System.out.println("PlanInfoModel plan_revision_no() Error: " + e);
        }
        return plan_revision_no;
    }

    public int getNoOfPlansFromJunction(int junction_id, int program_version_no) {
        int noOfPlans = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT no_of_plans FROM junction WHERE junction_id= ? AND program_version_no = ? ");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfPlans = Integer.parseInt(rset.getString(1));
            System.out.println(noOfPlans);
        } catch (Exception e) {
            System.out.println("PlanInfoModel getNoOfPlans() Error: " + e);
        }
        return noOfPlans;
    }

    public boolean insertRecord(List<PlanInfo> planInfoList) {
        String insert_query = null, update_query = null, insert_program_version = null, update_junction = null;
        insert_query = "INSERT into plan_info(junction_id, plan_no, on_time_hour, on_time_min, off_time_hour, off_time_min, mode, "
                + " side1_green_time, side2_green_time, side3_green_time, side4_green_time, side5_green_time, "
                + " side1_amber_time, side2_amber_time, side3_amber_time, side4_amber_time, side5_amber_time, transferred_status,"
                + " program_version_no,plan_revision_no) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        String update_plan_query = "UPDATE plan_info SET final_revision = 'EXPIRED' WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";
//
//        update_query = "UPDATE junction SET no_of_plans= ? WHERE junction_id= ? AND program_version_no = ? ";

        insert_program_version = "INSERT INTO junction(junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, "
                + "amber_time, flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, side2_name, "
                + "side3_name, side4_name, side5_name, program_version_no, transferred_status, file_no, remark) "
                + "SELECT junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, "
                + "amber_time, flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, side2_name,"
                + " side3_name, side4_name, side5_name, ? , transferred_status, file_no, remark "
                + " FROM junction WHERE junction_id= ? AND program_version_no = ? ";
        int rowsAffected = 0, count = planInfoList.size();
        int junction_id = 0, program_version_no = 0;
        PreparedStatement pstmt = null;
        boolean errorOccured = false;
        boolean isUpdated = false;
        try {
            boolean autoCommit = connection.getAutoCommit();
            try {
                connection.setAutoCommit(false);
                for (PlanInfo planInfo : planInfoList) {
                    junction_id = planInfo.getJunction_id();
                    program_version_no = planInfo.getProgram_version_no();

                    //condition1 : if this is first entry of plan_no, there won't be any record to make expire.
                    boolean firstflag = checkPlanEntry(junction_id, program_version_no, planInfo.getPlan_no());
                    if (!firstflag && !isUpdated) {
                        pstmt = connection.prepareStatement(update_plan_query);
                        pstmt.setInt(1, planInfo.getJunction_id());
                        pstmt.setInt(2, planInfo.getProgram_version_no());
                        rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            isUpdated = true;
                            rowsAffected = 0;
                            pstmt.close();
                        } else {
                            throw new SQLException("previous record is not updated");
                        }
                    }

                    pstmt = connection.prepareStatement(insert_query);
                    pstmt.setInt(1, planInfo.getJunction_id());
                    pstmt.setInt(2, planInfo.getPlan_no());
                    pstmt.setInt(3, planInfo.getOn_time_hour());
                    pstmt.setInt(4, planInfo.getOn_time_min());
                    pstmt.setInt(5, planInfo.getOff_time_hour());
                    pstmt.setInt(6, planInfo.getOff_time_min());
                    pstmt.setString(7, planInfo.getMode());
                    pstmt.setInt(8, planInfo.getSide1_green_time());
                    pstmt.setInt(9, planInfo.getSide2_green_time());
                    pstmt.setInt(10, planInfo.getSide3_green_time());
                    pstmt.setInt(11, planInfo.getSide4_green_time());
                    pstmt.setInt(12, planInfo.getSide5_green_time());
                    pstmt.setInt(13, planInfo.getSide1_amber_time());
                    pstmt.setInt(14, planInfo.getSide2_amber_time());
                    pstmt.setInt(15, planInfo.getSide3_amber_time());
                    pstmt.setInt(16, planInfo.getSide4_amber_time());
                    pstmt.setInt(17, planInfo.getSide5_amber_time());
                    pstmt.setInt(17, planInfo.getSide5_amber_time());
                    pstmt.setString(18, "NO");
                    pstmt.setInt(19, planInfo.getProgram_version_no());
                    pstmt.setInt(20, planInfo.getPlan_revision_no() + 1);
                    rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        rowsAffected = 0;
                        pstmt.close();
                    } else {
                        throw new SQLException("All records are NOT saved.");
                    }
                }


//                 //condition2 :  if some plan_nos are deleted, those plan nos must be updated as expired.

//                int prevNoOfPlans = getNoOfPlansFromJunction(junction_id, program_version_no);
//                if(prevNoOfPlans > count) {
//                    pstmt = connection.prepareStatement("UPDATE plan_info SET final_revision = 'EXPIRED' WHERE junction_id= "+junction_id+" "
//                            + "AND program_version_no = "+program_version_no+" AND final_revision = 'VALID' AND plan_no NOT IN ("+plan_nos+")");
//                    rowsAffected = pstmt.executeUpdate();
//                    if (rowsAffected > 0) {
//                        rowsAffected = 0;
//                        pstmt.close();
//                    } else {
//                        throw new SQLException("records were NOT deleted.");
//                    }
//                }

                //condition3 : no of plans should always updated in junction table.

//                pstmt = connection.prepareStatement(update_query);
//                pstmt.setInt(1, getNoOfPlans(junction_id, program_version_no));
//                pstmt.setInt(2, junction_id);
//                pstmt.setInt(3, program_version_no);
                rowsAffected = reviseProgramVersionNo(junction_id, program_version_no, count);

                if (rowsAffected > 0) {
                    // Finally commit the connection.
                    connection.commit();
                    message = "Data has been saved successfully.";
                    msgBgColor = COLOR_OK;
                } else {
                    throw new SQLException("All records were NOT saved.");
                }


            } catch (SQLException sqlEx) {
                errorOccured = true;
                connection.rollback();
                message = "Could NOT save all data , some error.";
                msgBgColor = COLOR_ERROR;
                System.out.println("PlanInfoModel insert() Error: " + message + " Cause: " + sqlEx.getMessage());
            } finally {
                pstmt.close();
                connection.setAutoCommit(autoCommit);
            }
        } catch (Exception e) {
            System.out.println("PlanInfoModel insert() Error: " + e);
        }
        return !errorOccured;
    }

    public int reviseProgramVersionNo(int junction_id, int old_program_version_no, int count) {
        int rowAffected = 0;
        boolean autocommit = false;
        String insert_program_version = "INSERT INTO junction(junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, "
                + "amber_time, flash_rate, no_of_plans, mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, side2_name, "
                + "side3_name, side4_name, side5_name, program_version_no, transferred_status, file_no, remark) "
                + "SELECT junction_id, junction_name, address1, address2, city_id, controller_model, no_of_sides, "
                + "amber_time, flash_rate, ? , mobile_no, sim_no, imei_no, instant_green_time, pedestrian, pedestrian_time, side1_name, side2_name,"
                + " side3_name, side4_name, side5_name, ? , transferred_status, file_no, remark "
                + " FROM junction WHERE junction_id= ? AND program_version_no = ? ";

        String updateStatus = "UPDATE junction SET final_revision='EXPIRED' WHERE junction_id = ? AND program_version_no = ? ";

        String update_plan_query = "UPDATE plan_info SET program_version_no = ? WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        try {
            autocommit = connection.getAutoCommit();
            PreparedStatement pstmt;
            try {
                connection.setAutoCommit(false);
                pstmt = connection.prepareStatement(insert_program_version);
                pstmt.setInt(1, count);
                pstmt.setInt(2, old_program_version_no + 1);
                pstmt.setInt(3, junction_id);
                pstmt.setInt(4, old_program_version_no);
                rowAffected = pstmt.executeUpdate();
                if (rowAffected > 0) {
                    pstmt.close();
                    rowAffected = 0;
                    pstmt = connection.prepareStatement(updateStatus);
                    pstmt.setInt(1, junction_id);
                    pstmt.setInt(2, old_program_version_no);
                    rowAffected = pstmt.executeUpdate();
                    if (rowAffected > 0) {
                        rowAffected = 0;
                        pstmt = connection.prepareStatement(update_plan_query);
                        pstmt.setInt(1, old_program_version_no + 1);
                        pstmt.setInt(2, junction_id);
                        pstmt.setInt(3, old_program_version_no);
                        rowAffected = pstmt.executeUpdate();
                        if (rowAffected > 0) {
                            // Finally commit the connection.
                            connection.commit();
                        } else {
                            throw new SQLException("some Error in update_plan_query");
                        }
                    } else {
                        throw new SQLException("some Error in updateStatus");
                    }
                }
            } catch (Exception e) {
                System.out.println("PlanInfoModel:reviseProgramVersionNo() error" + e);
            }
        } catch (Exception e) {
            System.out.println("reviseProgramVersionNo exception in planInfo: " + e);
        } finally {
            try {
                connection.setAutoCommit(autocommit);
            } catch (SQLException ex) {
                Logger.getLogger(PlanInfoModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rowAffected;
    }

    public int getValidPorogramVersionNo(int junction_id) {
        int plan_revision_no = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT program_version_no FROM junction WHERE junction_id= ?  AND final_revision = 'VALID' LIMIT 1");
            pstmt.setInt(1, junction_id);
            //pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            plan_revision_no = Integer.parseInt(rset.getString(1));
            System.out.println(plan_revision_no);
        } catch (Exception e) {
            System.out.println("PlanInfoModel getValidPorogramVersionNo() Error: " + e);
        }
        return plan_revision_no;
    }

    public String getjunctionName(int junction_id) {
        String junction_name = null;
        String query = " SELECT junction_name FROM junction "
                + " WHERE junction_id= ? AND final_revision = 'VALID' ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junction_id);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_name = rset.getString("junction_name");
            }
        } catch (Exception e) {
            System.out.println("PlanInfoModel:getjunctionName() error" + e);
        }
        return junction_name;
    }

    public boolean checkPlanEntry(int junction_id, int program_version_no, int plan_no) {
        int no = 0;
        String query1 = " SELECT count(*) AS c FROM plan_info WHERE junction_id = ? AND program_version_no= ? AND plan_no= ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, plan_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                no = rset.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("PlanInfoModel getFinalPlanRevisionNo() error" + e);
        }
        return no > 0 ? false : true;
    }

    public List<PlanInfo> showData(int junction_id, int program_version_no) {
        List<PlanInfo> list = new ArrayList<PlanInfo>();
        String query = " SELECT junction_name, plan_no, plan_revision_no, on_time_hour, on_time_min, off_time_hour, off_time_min, mode, side1_green_time, "
                + " side2_green_time, side3_green_time, side4_green_time, side5_green_time, side1_amber_time, side2_amber_time, "
                + " side3_amber_time, side4_amber_time, side5_amber_time "
                + " FROM plan_info AS p, junction AS j "
                + " WHERE p.junction_id=j.junction_id "
                + " AND p.program_version_no= j.program_version_no"
                + " AND j.junction_id= ? AND j.program_version_no = ? AND p.final_revision='VALID'";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                PlanInfo planinfo = new PlanInfo();
                planinfo.setJunction_name(rset.getString("junction_name"));
                planinfo.setPlan_no(rset.getInt("plan_no"));
                planinfo.setPlan_revision_no(rset.getInt("plan_revision_no"));
                planinfo.setOn_time_hour(rset.getInt("on_time_hour"));
                planinfo.setOn_time_min(rset.getInt("on_time_min"));
                planinfo.setOff_time_hour(rset.getInt("off_time_hour"));
                planinfo.setOff_time_min(rset.getInt("off_time_min"));
                String mode = rset.getString("mode");
                planinfo.setMode(mode);
                planinfo.setSide1_green_time(rset.getInt("side1_green_time"));
                planinfo.setSide2_green_time(rset.getInt("side2_green_time"));
                planinfo.setSide3_green_time(rset.getInt("side3_green_time"));
                planinfo.setSide4_green_time(rset.getInt("side4_green_time"));
                planinfo.setSide5_green_time(rset.getInt("side5_green_time"));
                planinfo.setSide1_amber_time(rset.getInt("side1_amber_time"));
                planinfo.setSide2_amber_time(rset.getInt("side2_amber_time"));
                planinfo.setSide3_amber_time(rset.getInt("side3_amber_time"));
                planinfo.setSide4_amber_time(rset.getInt("side4_amber_time"));
                planinfo.setSide5_amber_time(rset.getInt("side5_amber_time"));
                list.add(planinfo);
            }
        } catch (Exception e) {
            System.out.println("Error:PlanInfoModel-showData--- " + e);
        }
        return list;
    }
}
