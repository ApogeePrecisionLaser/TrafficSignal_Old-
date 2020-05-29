/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.general.Controller;

import com.ts.general.Model.Signal;
import com.ts.tcpServer.ClientResponder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shruti
 */
public class ConnectedIpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext ctx = getServletContext();
            request.getRequestDispatcher("connected_ip_view").forward(request, response);
        } catch (Exception e) {
            System.out.println("ConnectedIpController- doPostError :" + e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

  
}
