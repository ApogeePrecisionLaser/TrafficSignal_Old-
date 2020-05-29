/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.dataEntry.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class JQueryStateNameController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        String stateName = null;
        String input = request.getParameter("q").trim();
        String query = "SELECT state_name FROM state ORDER BY state_name ";
        try {
            Class.forName(ctx.getInitParameter("driverClass"));
            Connection connection = DriverManager.getConnection(ctx.getInitParameter("connectionString"), ctx.getInitParameter("db_userName"), ctx.getInitParameter("db_userPassword"));
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            int count = 0;
            while (rset.next()) {    // move cursor from BOR to valid record.
                stateName = rset.getString("state_name");
                if (stateName.toUpperCase().startsWith(input.toUpperCase())) {
                    out.println(stateName);
                    count++;
                }
            }
            if (count == 0) {
                out.println("No Such State Exists.");
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
}
