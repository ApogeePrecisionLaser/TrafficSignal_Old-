/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.login.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shruti
 */
public class LoginModel {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;
    private String message;
    private String msgBgColor;
    private final String COLOR_OK = "lightyellow";
    private final String COLOR_ERROR = "red";

    public boolean isUserAuthentic(String emailid, String pass) {
        boolean isAuthentic = false;
        String query = " SELECT email_id, password FROM user ";
        PreparedStatement pstmt;
        ResultSet rset;
        Map<String, String> logindetails = new HashMap<String, String>();
        try {
            pstmt = connection.prepareStatement(query);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                String emailID = rset.getString("email_id");
                String password = rset.getString("password");
                logindetails.put(emailID, password);
            }
            if (logindetails.containsKey(emailid) && logindetails.containsValue(pass)) {
                isAuthentic = true;
            } else {
                message = "User does not exists.";
                msgBgColor = COLOR_ERROR;
            }
        } catch (Exception e) {
            System.out.println("LoginModel isUserAuthentic error:" + e);
        }
        return isAuthentic;
    }

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = (Connection) DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("Image setConnection() Error: " + e);
        }
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDb_userName(String db_userName) {
        this.db_userName = db_userName;
    }

    public void setDb_userPassword(String db_userPassword) {
        this.db_userPassword = db_userPassword;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgBgColor() {
        return msgBgColor;
    }

    public void setMsgBgColor(String msgBgColor) {
        this.msgBgColor = msgBgColor;
    }
}
