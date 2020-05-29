/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Model;

import com.ts.junction.tableClasses.SlaveInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Shruti
 */
public class SlaveInfoModel extends HttpServlet {

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

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("Image setConnection() Error: " + e);
        }
    }

    public boolean validateSlaves(List<SlaveInfo> planInfoList) {
        return true;
    }

    public int getNoOfSlaves(int junction_id, int program_version_no) {
        int noOfSides = 0;
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(" SELECT no_of_sides FROM junction WHERE junction_id= ? AND program_version_no = ? ");
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            rset.next();
            noOfSides = rset.getInt(1);
            System.out.println(noOfSides);
        } catch (Exception e) {
            System.out.println("SlaveInfoModel getNoOfSlaves() Error: " + e);
        }
        return noOfSides;
    }

    public boolean insertRecord(List<SlaveInfo> slaveInfoList) {
        String insert_query = null, update_query = null;
        insert_query = "INSERT into slave_info(junction_id, program_version_no, side_no, side_revision_no, side_name, slave_id, transferred_status) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?) ";

        String update_slave_query = "UPDATE slave_info SET final_revision = 'EXPIRED' WHERE junction_id= ? AND program_version_no = ? AND final_revision='VALID' ";

        int rowsAffected = 0;
        int junction_id = 0, program_version_no = 0;



        PreparedStatement pstmt = null;
        boolean errorOccured = false;
        boolean isUpdated = false;
        try {
            boolean autoCommit = connection.getAutoCommit();
            try {
                connection.setAutoCommit(false);
                for (SlaveInfo slaveInfo : slaveInfoList) {
                    rowsAffected = 0;
                    junction_id = slaveInfo.getJunction_id();
                    program_version_no = slaveInfo.getProgram_version_no();

                    // if this is first entry of plan_no, there won't be any record to update expire.
                    boolean firstflag = checkSlaveEntry(junction_id, program_version_no, slaveInfo.getSide_no());
                    if (!firstflag && !isUpdated) {
                        pstmt = connection.prepareStatement(update_slave_query);
                        pstmt.setInt(1, slaveInfo.getJunction_id());
                        pstmt.setInt(2, slaveInfo.getProgram_version_no());
                        rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            isUpdated = true;
                            rowsAffected = 0;
                            pstmt.close();
                        } else {
                            throw new SQLException("previous record is not updated");
                        }
                    }
                   //---------------------------------------------------------------------------------------------------------------------
                    pstmt = connection.prepareStatement(insert_query);
                    pstmt.setInt(1, slaveInfo.getJunction_id());
                    pstmt.setInt(2, slaveInfo.getProgram_version_no());
                    pstmt.setInt(3, slaveInfo.getSide_no());
                    pstmt.setInt(4, slaveInfo.getSide_revision_no() + 1);
                    pstmt.setString(5, slaveInfo.getSideName());
                    pstmt.setString(6, slaveInfo.getSlave_id());
                    pstmt.setString(7, "NO");
                    rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        pstmt.close();
                    } else {
                        throw new SQLException("All records are NOT saved.");
                    }
                }
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
                System.out.println("SlaveInfoModel insert() Error: " + message + " Cause: " + sqlEx.getMessage());
            } finally {
                pstmt.close();
                connection.setAutoCommit(autoCommit);
            }
        } catch (Exception e) {
            System.out.println("SlaveInfoModel insert() Error: " + e);
        }
        return !errorOccured;

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
            System.out.println("SlaveInfoModel:getjunctionName() error" + e);
        }
        return junction_name;
    }

    public String getSideName(int junction_id, int program_version_no, int side_no) {
        String junction_name = null;
        String query = " SELECT side"+side_no+"_name FROM junction "
                + " WHERE junction_id= ? AND program_version_no = ? ";
        PreparedStatement pstmt;
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                junction_name = rset.getString("side"+side_no+"_name");
            }
        } catch (Exception e) {
            System.out.println("SlaveInfoModel:getSideName() error " + e);
        }
        return junction_name;
    }

    public boolean checkSlaveEntry(int junction_id, int program_version_no, int side_no) {
        int no = 0;
        String query1 = " SELECT count(*) AS c FROM slave_info WHERE junction_id = ? AND program_version_no= ? AND side_no= ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query1);
            pstmt.setInt(1, junction_id);
            pstmt.setInt(2, program_version_no);
            pstmt.setInt(3, side_no);
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                no = rset.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("SlaveInfoModel getFinalPlanRevisionNo() error" + e);
        }
        return no > 0 ? false : true;
    }

    public List<SlaveInfo> showData(int junction_id, int program_version_no) {
        List<SlaveInfo> list = new ArrayList<SlaveInfo>();
        String query = " SELECT junction_name, p.side_no, p.side_revision_no, p.side_name, slave_id "
                + " FROM slave_info AS p, junction AS j "
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
                SlaveInfo slaveinfo = new SlaveInfo();
                slaveinfo.setJunction_name(rset.getString("junction_name"));
                slaveinfo.setSide_no(rset.getInt("side_no"));
                slaveinfo.setSide_revision_no(rset.getInt("side_revision_no"));
                slaveinfo.setSideName(rset.getString("side_name"));
                slaveinfo.setSlave_id(rset.getString("slave_id"));
                list.add(slaveinfo);
            }
        } catch (Exception e) {
            System.out.println("Error:SlaveInfoModel-showData--- " + e);
        }
        return list;
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

    public String getMessage() {
        return message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

}
