/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.login.Controller;

import com.ts.login.Model.LoginModel;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shruti
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel loginModel = new LoginModel();
        ServletContext ctx = getServletContext();
        loginModel.setDriverClass(ctx.getInitParameter("driverClass"));
        loginModel.setConnectionString(ctx.getInitParameter("connectionString"));
        loginModel.setDb_userName(ctx.getInitParameter("db_userName"));
        loginModel.setDb_userPassword(ctx.getInitParameter("db_userPassword"));
        loginModel.setConnection();
        String task = request.getParameter("task");

        HttpSession session  =  request.getSession();

        if (task == null) {
            task = "";
        }
        if (task.equals("Login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            boolean userAuthentic = loginModel.isUserAuthentic(username, password);
            if (userAuthentic == true) {
                session.setAttribute("userName", username);
                request.getRequestDispatcher("/after_login.jsp").forward(request, response);
            } else {
                request.setAttribute("message", loginModel.getMessage());
                request.setAttribute("msgBgColor", loginModel.getMsgBgColor());
                request.getRequestDispatcher("/beforeLoginView").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
