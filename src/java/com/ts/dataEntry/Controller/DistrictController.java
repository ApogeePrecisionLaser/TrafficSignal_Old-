/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.dataEntry.Controller;

import com.ts.dataEntry.Model.DistrictModel;
import com.ts.dataEntry.tableClasses.District;
import com.ts.util.xyz;
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
 * @author Shruti
 */
public class DistrictController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 15, noOfRowsInTable;
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

        DistrictModel districtModel = new DistrictModel();
        districtModel.setDriverClass(ctx.getInitParameter("driverClass"));
        districtModel.setConnectionString(ctx.getInitParameter("connectionString"));
        districtModel.setDb_userName(ctx.getInitParameter("db_userName"));
        districtModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        districtModel.setConnection();
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        if (task.equals("Delete")) {
            // Pretty sure that district_id will be available.
            districtModel.deleteRecord(Integer.parseInt(request.getParameter("district_id")));
        } else if (task.equals("Save") || task.equals("Save AS New")) {
            int district_id;
            try {
                // district_id may or may NOT be available i.e. it can be update or new record.
                district_id = Integer.parseInt(request.getParameter("district_id"));
            } catch (Exception e) {
                district_id = 0;
            }
            if (task.equals("Save AS New")) {
                district_id = 0;
            }
            District district = new District();
            district.setDistrict_id(district_id);
            district.setDistrict_name(request.getParameter("district_name").trim());
            district.setState_id(districtModel.getStateId(request.getParameter("state_name").trim()));
            if (district_id == 0) {
                // if district_id was not provided, that means insert new record.
                districtModel.insertRecord(district);
            } else {
                // update existing record.
                districtModel.updateRecord(district);
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
        noOfRowsInTable = districtModel.getNoOfRows();                  // get the number of records (rows) in the table.
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
        // Logic to show data in the table.
        List<District> districtList = districtModel.showData(lowerLimit, noOfRowsToDisplay);
        lowerLimit = lowerLimit + districtList.size();
        noOfRowsTraversed = districtList.size();

        // Now set request scoped attributes, and then forward the request to view.
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.setAttribute("districtList", districtList);

        if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
        }
        if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
            request.setAttribute("showNext", "false");
            request.setAttribute("showLast", "false");
        }
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("message", districtModel.getMessage());
        request.setAttribute("msgBgColor", districtModel.getMsgBgColor());
        request.getRequestDispatcher("district_view").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
