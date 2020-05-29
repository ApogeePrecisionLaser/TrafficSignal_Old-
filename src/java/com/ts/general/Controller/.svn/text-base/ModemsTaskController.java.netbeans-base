/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.general.Controller;

import com.ts.tcpServer.ClientResponder;
import com.ts.tcpServer.TcpServer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarun
 */
public class ModemsTaskController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        TcpServer tcpServer = (TcpServer) getServletContext().getAttribute("tcpServer");
        try {
            ClientResponder clientResponder = tcpServer.getClientResponder();
            String task = request.getParameter("task");
            task = task != null ? task.trim() : "";
            if (task.equals("sendMsg")) {
                String message = request.getParameter("customMsg").trim();
                clientResponder.sendResponse(message);
                out.print("Message sent.");
            }
        } catch (Exception ex) {
            out.print("Error communicating with TcpServer.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
