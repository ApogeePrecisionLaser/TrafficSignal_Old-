/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Controller;

import com.ts.junction.Model.Jun_Sunrise_SunsetModel;
import com.ts.junction.tableClasses.Jun_Sunrise_Sunset;
import com.ts.junction.tableClasses.NewClass;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
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
public class RiseSetCont extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Jun_Sunrise_SunsetModel junctionModel = new Jun_Sunrise_SunsetModel();
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession(false);
        if(session != null){
            try{
            String userName = session.getAttribute("userName").toString();
            if(userName.equals("")){
                request.getRequestDispatcher("/beforeLoginView").forward(request, response);
                return;
            }
            }catch(Exception e){
                System.out.println(e);
                request.getRequestDispatcher("/beforeLoginView").forward(request, response);
                return;
            }
        }

        junctionModel.setDriverClass(ctx.getInitParameter("driverClass"));
        junctionModel.setConnectionString(ctx.getInitParameter("connectionString"));
        junctionModel.setDb_userName(ctx.getInitParameter("db_userName"));
        junctionModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        junctionModel.setConnection();
        String list1 = junctionModel.showSunriseSunset();
        request.getRequestDispatcher("errorView").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
