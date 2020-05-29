/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.dataEntry.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarun
 */
public class JQueryDistrictNameController extends HttpServlet {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_userName;
    private String db_userPassword;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        driverClass = ctx.getInitParameter("driverClass");
        connectionString = ctx.getInitParameter("connectionString");
        db_userName = ctx.getInitParameter("db_userName");
        db_userPassword = ctx.getInitParameter("db_userPassword");
        setConnection();
        String districtName = null;
        String stateName = request.getParameter("state_name").trim();
        String caller = request.getParameter("caller"); // Holds name of page who called this servlet.
        if (caller == null) {
            caller = "";
        }
        String input = request.getParameter("q").trim();
        String query = "SELECT district_name FROM district WHERE state_id = ? ORDER BY district_name ";
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, getStateId(stateName));
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            while (rset.next()) {    // move cursor from BOR to valid record.
                districtName = rset.getString("district_name");
                if (districtName.toUpperCase().startsWith(input.toUpperCase())) {
                    out.println(districtName);
                    count++;
                }
            }
            if (count == 0) {
                /* Decide what to return, when district_name from district table was not found (or it was empty or null),
                 *  on behalf of state_name.
                 */
                if (caller.equals("district")) {  // check if caller is district.jsp.
                    out.print("");  // Output as per the district.jsp requirement.
                } else {
                    out.print("No Such District Exists."); // For others
                }
            }
        } catch (Exception e) {
            System.out.println("JQueryStateNameController doGet() Error: " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private int getStateId(String stateName) {
        int state_id = -1;
        String query = "SELECT state_id FROM state WHERE state_name = ? ";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, stateName);
            ResultSet rset = pstmt.executeQuery();
            rset.next();    // move cursor from BOR to valid record.
            state_id = rset.getInt("state_id");
        } catch (Exception e) {
            System.out.println("JQueryDistrictNameController getStateId() Error: " + e);
        }
        return state_id;
    }

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("JQueryDistrictNameController setConnection() Error: " + e);
        }
    }
}
