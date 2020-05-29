/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Controller;

import com.ts.junction.Model.Jun_Sunrise_SunsetModel;
import com.ts.junction.tableClasses.Jun_Sunrise_Sunset;
import com.ts.util.xyz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
public class Jun_Sunrise_SunsetController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Jun_Sunrise_SunsetModel junctionModel = new Jun_Sunrise_SunsetModel();
        ServletContext ctx = getServletContext();
        junctionModel.setDriverClass(ctx.getInitParameter("driverClass"));
        junctionModel.setConnectionString(ctx.getInitParameter("connectionString"));
        junctionModel.setDb_userName(ctx.getInitParameter("db_userName"));
        junctionModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        junctionModel.setConnection();
        String task = request.getParameter("task");
        String requester = request.getParameter("requester");
        try {
            //----- This is only for Vendor key Person JQuery
            String jqstring = request.getParameter("action1");
            String q = request.getParameter("q");   // field own input
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (jqstring != null) {
                List<String> list = null;
                if (jqstring.equals("getCityName")) {
                    list = junctionModel.getCityName(q);
                }
                Iterator<String> iter = list.iterator();
                while (iter.hasNext()) {
                    String data = iter.next();
                    if (data.equals("Disable")) {
                        out.print(data);
                    } else {
                        out.println(data);
                    }
                }
                return;

            }
        } catch (Exception e) {
            System.out.println("\n Error --SiteListController get JQuery Parameters Part-" + e);
        }

        String city_name_filter = null, date_filter = null;
        int year = 0, month = 0;
        String city_name_cb = "", month_cb = "", year_cb = "", date_cb = "";

        city_name_filter = request.getParameter("city_name_filter");
        if ((city_name_filter == null) || (city_name_filter.equals(""))) {
            city_name_filter = "";
            city_name_cb = "";
        } else {
            city_name_cb = "checked";
        }
        if (request.getParameter("year") != null) {
            if (Integer.parseInt(request.getParameter("year")) > 0) {
                year = Integer.parseInt(request.getParameter("year"));
                year_cb = "checked";
            } else if (Integer.parseInt(request.getParameter("year")) == 0) {
                year_cb = "";
                year = 0;
            }
        } else {
            year_cb = "";
            year = 0;
        }
        if (request.getParameter("month") != null) {
            if (Integer.parseInt(request.getParameter("month")) > 0) {
                month = Integer.parseInt(request.getParameter("month"));
                month_cb = "checked";
            } else if (Integer.parseInt(request.getParameter("month")) == 0) {
                month_cb = "";
                month = 0;
            }
        } else {
            month_cb = "";
            month = 0;
        }
        date_filter = request.getParameter("date_filter");
        if ((date_filter == null) || (date_filter.equals(""))) {
            date_filter = "";
            date_cb = "";
        } else {
            date_cb = "checked";
        }



        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 10, noOfRowsInTable;
        if (task == null) {
            task = "";
        }

        if (task.equals("DELETE")) {
            // Pretty sure that city_id will be available.
            String date = request.getParameter("date");
            try {
                junctionModel.deleteRecord(date);
            } catch (Exception ex) {
                System.out.println("junction sunrise sunset controller : delete error" + ex);
            }
        }

        if (task.equals("SAVE") || task.equals("Save AS New")) {
            Jun_Sunrise_Sunset junSunriseSunset = new Jun_Sunrise_Sunset();
            String city_name = request.getParameter("city_name");
            junSunriseSunset.setCity_name(city_name);
            String subTask = request.getParameter("subTasks");
            if (subTask.equals("NEW") || task.equals("Save AS New")) {
                try {
                    junctionModel.insertRecord(junSunriseSunset);
                } catch (Exception ex) {
                    System.out.println("junction sunrise sunset controller : insertRecord error" + ex);
                }
            }
        }

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
        noOfRowsInTable = junctionModel.getNoOfRows(city_name_filter, year, month, date_filter);                  // get the number of records (rows) in the table.
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

        if (task.equals("SAVE") || task.equals("DELETE") || task.equals("Save AS New")) {
            lowerLimit = lowerLimit - noOfRowsTraversed;    // Here objective is to display the same view again, i.e. reset lowerLimit to its previous value.
        }

        List<Jun_Sunrise_Sunset> list1 = null;
        try {
            list1 = junctionModel.showData(lowerLimit, noOfRowsToDisplay, city_name_filter, year, month, date_filter);
        } catch (Exception ex) {
            System.out.println("junction sunrise sunset controller : showData error" + ex);
        }
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
        request.setAttribute("city_name_cb", city_name_cb);
        request.setAttribute("month_cb", month_cb);
        request.setAttribute("year_cb", year_cb);
        request.setAttribute("date_cb", date_cb);
        request.setAttribute("city_name_filter", city_name_filter);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("date_filter", date_filter);
        request.setAttribute("RiseSet", list1);
        request.setAttribute("message", junctionModel.getMessage());
        request.setAttribute("msgBgColor", junctionModel.getMsgBgColor());
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.getRequestDispatcher("jun_sunrise_sunset_view").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
