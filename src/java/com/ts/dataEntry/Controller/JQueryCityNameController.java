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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Soft_Tech
 */
public class JQueryCityNameController extends HttpServlet {

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
        String desiredOutput = request.getParameter("desiredOutput"); // Holds the info about required response.
        String dataNotFoundAlert = request.getParameter("dataNotFoundAlert"); // Holds the info about required response.
        if (desiredOutput == null) {
            desiredOutput = "";
        }
        if (dataNotFoundAlert == null) {
            dataNotFoundAlert = "";
        }
        String query;
        if (desiredOutput.equals("filteredCity")) {
            query = "SELECT c.city_name "
                    + "FROM city AS c, district AS d, state AS s "
                    + "WHERE c.district_id = d.district_id AND d.state_id = s.state_id AND "
                    + "s.state_name = ? AND d.district_name = ? AND c.city_name LIKE ? "
                    + "ORDER BY city_name ";
        } else if (desiredOutput.equals("filteredCityByStateName")) {
            query = "SELECT c.city_name "
                    + "FROM city AS c, district AS d, state AS s "
                    + "WHERE c.district_id = d.district_id AND "
                    + "d.state_id = s.state_id AND "
                    + "s.state_name = ? AND c.city_name LIKE ? "
                    + "ORDER BY city_name ";
        } else {
            query = "SELECT city_name FROM city WHERE city_name LIKE ? ORDER BY city_name ";
        }
        String input = request.getParameter("q");
        if (input != null) {
            input = input.trim();
        }
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            PreparedStatement pstmt = connection.prepareStatement(query);
            if (desiredOutput.equals("filteredCity")) {
                pstmt.setString(1, request.getParameter("state_name").trim());
                pstmt.setString(2, request.getParameter("district_name").trim());
                pstmt.setString(3, input + '%');
            } else if (desiredOutput.equals("filteredCityByStateName")) {
                pstmt.setString(1, request.getParameter("state_name").trim());
                pstmt.setString(2, input + '%');
            } else {
                pstmt.setString(1, input + '%');
            }
            ResultSet rset = pstmt.executeQuery();
            int count = 0;
            while (rset.next()) {    // move cursor from BOR to valid record.
                out.println(rset.getString("city_name"));
                count++;
            }
            if (count == 0) {
                /* Decide what to return, when city_name from city table was not found (or it was empty or null),
                 *  on behalf of state_name, and district_name.
                 */
                if (desiredOutput.equals("filteredCity") || desiredOutput.equals("filteredCityByStateName")) {  // check if caller is district.jsp.
                    if (dataNotFoundAlert.equals("yes")) {
                        out.print("No Such City Exists.");
                    } else {
                        out.print("");  // Output as per the district.jsp requirement.
                    }
                } else {
                    out.print("No Such City Exists."); // For others
                }
            }
        } catch (Exception e) {
            System.out.println("JQCityNameCont doGet() Error Occured: " + e);
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
            System.out.println("JQCityNameCont getStateId() Error: " + e);
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
            System.out.println("JQCityNameCont getDistrictId() Error: " + e);
        }
        return district_id;
    }

    public void setConnection() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connectionString, db_userName, db_userPassword);
        } catch (Exception e) {
            System.out.println("JQCityNameCont setConnection() Error: " + e);
        }
    }
}
