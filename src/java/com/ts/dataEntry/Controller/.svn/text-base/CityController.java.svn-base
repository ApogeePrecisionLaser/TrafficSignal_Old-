/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.dataEntry.Controller;

import com.ts.dataEntry.Model.CityModel;
import com.ts.dataEntry.tableClasses.City;
import com.ts.util.xyz;
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
public class CityController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int lowerLimit, noOfRowsTraversed, noOfRowsToDisplay = 15, noOfRowsInTable;
        ServletContext ctx = getServletContext();

        CityModel cityModel = new CityModel();
        cityModel.setDriverClass(ctx.getInitParameter("driverClass"));
        cityModel.setConnectionString(ctx.getInitParameter("connectionString"));
        cityModel.setDb_userName(ctx.getInitParameter("db_userName"));
        cityModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        cityModel.setConnection();
        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        if (task.equals("Delete")) {
            // Pretty sure that city_id will be available.
            cityModel.deleteRecord(Integer.parseInt(request.getParameter("city_id")));
        } else if (task.equals("Save") || task.equals("Save AS New")) {
            int city_id;
            try {
                // city_id may or may NOT be available i.e. it can be update or new record.
                city_id = Integer.parseInt(request.getParameter("city_id"));
            } catch (Exception e) {
                city_id = 0;
            }
            if (task.equals("Save AS New")) {
                city_id = 0;
            }
            City city = new City();
            city.setCity_id(city_id);
            city.setCity_name(request.getParameter("city_name").trim());
            city.setDistrict_id(cityModel.getDistrictId(request.getParameter("district_name").trim()));
            city.setState_id(cityModel.getStateId(request.getParameter("state_name").trim()));
            city.setPin_code(Integer.parseInt(request.getParameter("pin_code").trim()));
            city.setStd_code(request.getParameter("std_code").trim());
            if (city_id == 0) {
                // if city_id was not provided, that means insert new record.
                cityModel.insertRecord(city);
            } else {
                // update existing record.
                cityModel.updateRecord(city);
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
        noOfRowsInTable = cityModel.getNoOfRows();                  // get the number of records (rows) in the table.
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
        List<City> cityList = cityModel.showData(lowerLimit, noOfRowsToDisplay);
        lowerLimit = lowerLimit + cityList.size();
        noOfRowsTraversed = cityList.size();

        // Now set request scoped attributes, and then forward the request to view.
        request.setAttribute("lowerLimit", lowerLimit);
        request.setAttribute("noOfRowsTraversed", noOfRowsTraversed);
        request.setAttribute("cityList", cityList);

        if ((lowerLimit - noOfRowsTraversed) == 0) {     // if this is the only data in the table or when viewing the data 1st time.
            request.setAttribute("showFirst", "false");
            request.setAttribute("showPrevious", "false");
        }
        if (lowerLimit == noOfRowsInTable) {             // if No further data (rows) in the table.
            request.setAttribute("showNext", "false");
            request.setAttribute("showLast", "false");
        }
        request.setAttribute("IDGenerator", new xyz());
        request.setAttribute("message", cityModel.getMessage());
        request.setAttribute("msgBgColor", cityModel.getMsgBgColor());
        request.getRequestDispatcher("city_view").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}

