/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Controller;

import com.ts.junction.Model.JunctionModel;
import com.ts.junction.tableClasses.Junction;
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
public class JunctionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JunctionModel junctionModel = new JunctionModel();
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
                    list = junctionModel.getCityName(q, request.getParameter("action2"));
                } else if (jqstring.equals("getStateName")) {
                    list = junctionModel.getStateName(q);
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

        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 4, noOfRowsInTable;
        if (task == null) {
            task = "";
        }

        if (task.equals("Delete")) {
            // Pretty sure that junction_id will be available.
            int junction_id = Integer.parseInt(request.getParameter("junction_id").trim());
            junctionModel.deleteRecord(junction_id);
        }

        if (task.equals("Save") || task.equals("Save AS New")) {
            String side_1_name, side_3_name = null, side_4_name = null, side_5_name = null, side_2_name;
            Junction junction = new Junction();
            String junction_name = request.getParameter("junction_name");
            String address_1 = request.getParameter("address_1");
            String address_2 = request.getParameter("address_2");
            String state_name = request.getParameter("state_name");
            String city_name = request.getParameter("city_name");
            String controller_model = request.getParameter("controller_model");
            int no_of_sides = Integer.parseInt(request.getParameter("no_of_sides"));
            int amber_time = Integer.parseInt(request.getParameter("amber_time"));
            int flash_rate = Integer.parseInt(request.getParameter("flash_rate"));
            int no_of_plans = Integer.parseInt(request.getParameter("no_of_plans"));
            String mobile_no = request.getParameter("mobile_no");
            String sim_no = request.getParameter("sim_no");
            String imei_no = request.getParameter("imei_no");
            int instant_green_time = Integer.parseInt(request.getParameter("instant_green_time"));
            String pedestrian = request.getParameter("pedestrian");
            junction.setPedestrian(pedestrian.equals("YES") ? "Y" : "N");
            junction.setPedestrian_time(Integer.parseInt(request.getParameter("pedestrian_time")));
            junction.setFile_no(Integer.parseInt(request.getParameter("file_no")));
            junction.setRemark(request.getParameter("remark"));

            side_1_name = request.getParameter("side_1_name");
            side_2_name = request.getParameter("side_2_name");
            switch (no_of_sides) {
                case 5:
                    side_5_name = request.getParameter("side_5_name");
                case 4:
                    side_4_name = request.getParameter("side_4_name");
                case 3:
                    side_3_name = request.getParameter("side_3_name");
            }

            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("junction_id").trim());
            } catch (Exception ex) {
                id = 0;
//                System.out.println("ad_asso_site_detail_id error: " + ex);
            }
            if (task.equals("Save AS New")) {
                id = 0;
            }
            junction.setJunction_id(id);
            junction.setJunction_name(junction_name);
            junction.setAddress1(address_1);
            junction.setAddress2(address_2);
            junction.setState_name(state_name);
            junction.setCity_name(city_name);
            junction.setController_model(controller_model);
            junction.setNo_of_sides(no_of_sides);
            junction.setAmber_time(amber_time);
            junction.setFlash_rate(flash_rate);
            junction.setNo_of_plans(no_of_plans);
            junction.setMobile_no(mobile_no);
            junction.setSim_no(sim_no);
            junction.setImei_no(imei_no);
            junction.setInstant_green_time(instant_green_time);
            junction.setSide1_name(side_1_name);
            junction.setSide2_name(side_2_name);
            junction.setSide3_name(side_3_name);
            junction.setSide4_name(side_4_name);
            junction.setSide5_name(side_5_name);
            if (id == 0) {
                if (!junctionModel.checkImei(imei_no)) {
                    junctionModel.insertRecord(junction);
                } else {
                    junctionModel.setMessage("IMEI is already exists");
                    junctionModel.setMsgBgColor("orange");
                }
            } else {
                // update existing record.
                junctionModel.updateRecord(junction);
            }
        }


        noOfRowsInTable = junctionModel.getNoOfRows();                  // get the number of records (rows) in the table.

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

        if (task.equals("Save") || task.equals("Delete") || task.equals("Save AS New")) {
            lowerLimit = lowerLimit - noOfRowsTraversed;    // Here objective is to display the same view again, i.e. reset lowerLimit to its previous value.
        }

        List<Junction> list1 = junctionModel.showData(lowerLimit, noOfRowsToDisplay);
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
        request.setAttribute("message", junctionModel.getMessage());
        request.setAttribute("msgBgColor", junctionModel.getMsgBgColor());
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.getRequestDispatcher("/junction_view").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
