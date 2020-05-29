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

/**
 *
 * @author Shruti
 */
public class LoggerHistoryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientResponderModel clientResponderModel = new ClientResponderModel();
        ServletContext ctx = getServletContext();
        clientResponderModel.setDriverClass(ctx.getInitParameter("driverClass"));
        clientResponderModel.setConnectionString(ctx.getInitParameter("connectionString"));
        clientResponderModel.setDb_userName(ctx.getInitParameter("db_userName"));
        clientResponderModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        clientResponderModel.setConnection();

        String task = request.getParameter("task");
        if (task != null) {
        }
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 10, noOfRowsInTable;
        try {
            lowerLimit = Integer.parseInt(request.getParameter("lowerLimit"));
            noOfRowsTraversed = Integer.parseInt(request.getParameter("noOfRowsTraversed"));
        } catch (Exception e) {
            lowerLimit = noOfRowsTraversed = 0;
        }
        String buttonAction = request.getParameter("buttonAction"); // Holds the name of any of the four buttons: First, Previous, Next, Delete.
        if (buttonAction == null) {
            buttonAction = "none";
        }

        String ipAddress;
        int port;
        if (request.getParameter("action1") != null) { // request from popup parent window---
            String JQstring = request.getParameter("action1");
            if (JQstring.equals("showAllDetails")) {
                port = Integer.parseInt(request.getParameter("port"));
                ipAddress = request.getParameter("ipAddress");
                noOfRowsInTable = clientResponderModel.getNoOfRowsInShowAll(ipAddress, port);                  // get the number of records (rows) in the table.
                if (buttonAction.equals("Next")); // lowerLimit already has value such that it shows forward records, so do nothing here.
                else if (buttonAction.equals("Previous")) {
                    int temp = lowerLimit - noOfRowsToDisplay - noOfRowsTraversed;
                    if (temp < 0) {
                        noOfRowsToDisplay = lowerLimit - noOfRowsTraversed;
                        lowerLimit = 0;
                    } else {
                        lowerLimit = temp;
                    }
                } else if (buttonAction.equals("First")) {
                    lowerLimit = 0;
                } else if (buttonAction.equals("Last")) {
                    lowerLimit = noOfRowsInTable - noOfRowsToDisplay;
                    if (lowerLimit < 0) {
                        lowerLimit = 0;
                    }
                }

                try {
                    List<History> detailsList = clientResponderModel.showDetails(ipAddress, port, lowerLimit, noOfRowsToDisplay);
                    lowerLimit = lowerLimit + detailsList.size();
                    noOfRowsTraversed = detailsList.size();

                    if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
                        request.setAttribute("showFirst", "false");
                        request.setAttribute("showPrevious", "false");
                    }
                    if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
                        request.setAttribute("showNext", "false");
                        request.setAttribute("showLast", "false");
                    }

                    request.setAttribute("detailList", detailsList);
                    request.setAttribute("lowerLimit", lowerLimit);
                    request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
                    request.getRequestDispatcher("/historyDetailsView").forward(request, response);
                    return;
                } catch (Exception e) {
                    port = 0;
                    ipAddress = null;
                }
            }
        }

        noOfRowsInTable = clientResponderModel.getNoOfRows();                  // get the number of records (rows) in the table.
        if (buttonAction.equals("Next")); // lowerLimit already has value such that it shows forward records, so do nothing here.
        else if (buttonAction.equals("Previous")) {
            int temp = lowerLimit - noOfRowsToDisplay - noOfRowsTraversed;
            if (temp < 0) {
                noOfRowsToDisplay = lowerLimit - noOfRowsTraversed;
                lowerLimit = 0;
            } else {
                lowerLimit = temp;
            }
        } else if (buttonAction.equals("First")) {
            lowerLimit = 0;
        } else if (buttonAction.equals("Last")) {
            lowerLimit = noOfRowsInTable - noOfRowsToDisplay;
            if (lowerLimit < 0) {
                lowerLimit = 0;
            }
        }

        List<History> list1 = clientResponderModel.showData(lowerLimit, noOfRowsToDisplay);
        lowerLimit = lowerLimit + list1.size();
        noOfRowsTraversed = list1.size();

        if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
        }
        if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
            request.setAttribute("showNext", "false");
            request.setAttribute("showLast", "false");
        }

        request.setAttribute("junction", list1);
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.getRequestDispatcher("/logHistoryView").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
