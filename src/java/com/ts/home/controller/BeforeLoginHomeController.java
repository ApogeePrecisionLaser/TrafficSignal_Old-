/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.home.controller;

import com.ts.tcpServer.ClientResponderModel;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Neha
 */
public class BeforeLoginHomeController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext ctx = getServletContext();
        ClientResponderModel clientResponderModel = new ClientResponderModel();
        clientResponderModel.setDriverClass(ctx.getInitParameter("driverClass"));
        clientResponderModel.setConnectionString(ctx.getInitParameter("connectionString"));
        clientResponderModel.setDb_userName(ctx.getInitParameter("db_userName"));
        clientResponderModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        clientResponderModel.setConnection();
        request.getRequestDispatcher("beforeLoginView").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
