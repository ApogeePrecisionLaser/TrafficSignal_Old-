/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.general.Controller;

import com.ts.junction.tableClasses.History;
import com.ts.tcpServer.ClientResponderModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tarun
 */
public class LoggedInJunctionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setIntHeader("Refresh", 10); // This code is for autorefresh of this servlet after each 10 sec interval.

// Set response content type
        response.setContentType("text/html");
        ClientResponderModel clientResponderModel = new ClientResponderModel();
        ServletContext ctx = getServletContext();
        clientResponderModel.setDriverClass(ctx.getInitParameter("driverClass"));
        clientResponderModel.setConnectionString(ctx.getInitParameter("connectionString"));
        clientResponderModel.setDb_userName(ctx.getInitParameter("db_userName"));
        clientResponderModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        clientResponderModel.setConnection();

        String task = request.getParameter("task");

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

        if (task != null) {
//            if (task.equals("getCurrentStatus")) {
//                PrintWriter out = response.getWriter();
//                String junctionID = request.getParameter("junctionID");
//                request.setAttribute("junctionID", junctionID);
//                String substring = junctionID.substring(1, junctionID.length() - 1);
//                System.out.println(substring);
//                String[] split = substring.split(", ", substring.length());
//                List<Integer> junctionID1 = clientResponderModel.getJunctionID();
//                boolean check = false;
//                int z = 0;
//                if (split.length == junctionID1.size()) {
//                    for (int i = 0; i < split.length; i++) {
//                        if (!split[i].equals("")) {
//                            int val = Integer.parseInt(split[i]);
//                            check = junctionID1.contains(val);
//                        }
//                        if (!check) {
//                            break;
//                        }
//                    }
//                }
//
//                if (check) {
//                    out.println("0");
//                } else {
//                    out.println("1");
//                }
//                return;
//            }
//            if (task.equals("showAllStatus")) {
//            List<History> List = clientResponderModel.showDataBean();
//            request.setAttribute("CoordinatesList", List);
//            request.getRequestDispatcher("/view/general/allStatusMapWindow.jsp").forward(request, response);
//            return;
//        }

        }

        List<History> list1 = clientResponderModel.showLoggedInJunctionDetails();
        List<Integer> junctionID = clientResponderModel.getJunctionID();



        request.setAttribute("junctionID", junctionID);
        request.setAttribute("junction", list1);
        request.getRequestDispatcher("loggedInJunctionView").forward(request, response);

    }

//    public void setContext(ServletContext ctx) {
//        this.ctx = ctx;
//    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
