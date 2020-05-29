/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Controller;

import com.ts.junction.Model.PlanInfoModel;
import com.ts.junction.tableClasses.PlanInfo;
import com.ts.util.xyz;
import java.io.IOException;
import java.util.ArrayList;
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
public class PlanInfoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PlanInfoModel planinfoModel = new PlanInfoModel();
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

        planinfoModel.setDriverClass(ctx.getInitParameter("driverClass"));
        planinfoModel.setConnectionString(ctx.getInitParameter("connectionString"));
        planinfoModel.setDb_userName(ctx.getInitParameter("db_userName"));
        planinfoModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        planinfoModel.setConnection();

        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        int junction_id = Integer.parseInt(request.getParameter("junction_id").trim());
        int no_of_sides = Integer.parseInt(request.getParameter("no_of_sides").trim());
        int program_version_no = Integer.parseInt(request.getParameter("program_version_no").trim());

        if (task.equals("SAVE") || task.equals("Save AS New")) {
            int no_of_plans = Integer.parseInt(request.getParameter("no_of_plans").trim());

            //            planinfoModel.deleteRecord(junction_id);
            List<PlanInfo> planInfoList = new ArrayList<PlanInfo>();
            int side1_green_time, side2_green_time, side3_green_time = 0, side4_green_time = 0, side5_green_time = 0;
            int side1_amber_time, side2_amber_time, side3_amber_time = 0, side4_amber_time = 0, side5_amber_time = 0;
            for (int i = 1; i <= no_of_plans; i++) {
                PlanInfo plan_info = new PlanInfo();
                int plan_no, plan_revision_no;
                try {
                    plan_no = Integer.parseInt(request.getParameter("plan_no" + i).trim());
                    plan_revision_no = planinfoModel.getFinalPlanRevNo(junction_id, program_version_no);
                } catch (Exception e) {
                    plan_no = i;
                    plan_revision_no = -1;
                }
                int on_time_hour = Integer.parseInt(request.getParameter("on_time_hour" + i).trim());
                int on_time_min = Integer.parseInt(request.getParameter("on_time_min" + i).trim());
                int off_time_hour = Integer.parseInt(request.getParameter("off_time_hour" + i).trim());
                int off_time_min = Integer.parseInt(request.getParameter("off_time_min" + i).trim());
                String mode = request.getParameter("mode" + i);

                if (mode.equals("Signal") || mode.equals("Blinker")) {
                    side1_green_time = Integer.parseInt(request.getParameter("side1_green_time" + i).trim());
                    side2_green_time = Integer.parseInt(request.getParameter("side2_green_time" + i).trim());
                    side1_amber_time = Integer.parseInt(request.getParameter("side1_amber_time" + i).trim());
                    side2_amber_time = Integer.parseInt(request.getParameter("side2_amber_time" + i).trim());
                    switch (no_of_sides) {
                        case 5:
                            side5_green_time = Integer.parseInt(request.getParameter("side5_green_time" + i).trim());
                            side5_amber_time = Integer.parseInt(request.getParameter("side5_amber_time" + i).trim());
                        case 4:
                            side4_green_time = Integer.parseInt(request.getParameter("side4_green_time" + i).trim());
                            side4_amber_time = Integer.parseInt(request.getParameter("side4_amber_time" + i).trim());
                        case 3:
                            side3_green_time = Integer.parseInt(request.getParameter("side3_green_time" + i).trim());
                            side3_amber_time = Integer.parseInt(request.getParameter("side3_amber_time" + i).trim());
                    }
                } else {
                    side1_green_time = 0;
                    side2_green_time = 0;
                    side3_green_time = 0;
                    side4_green_time = 0;
                    side5_green_time = 0;
                    side1_amber_time = 0;
                    side2_amber_time = 0;
                    side3_amber_time = 0;
                    side4_amber_time = 0;
                    side5_amber_time = 0;
                }

                int id = 0;
                try {
                    id = Integer.parseInt(request.getParameter("junction_id").trim());
                } catch (Exception ex) {
                    id = 0;
                }
                int prog_version = 0;
                try {
                    prog_version = Integer.parseInt(request.getParameter("program_version_no").trim());
                } catch (Exception ex) {
                    prog_version = 0;
                }
                if (task.equals("Save AS New")) {
                    id = 0;
                }
                // plan_info.setIs_edited((request.getParameter("edited" + i)==null ? 1 : Integer.parseInt(request.getParameter("edited" + i))));
                plan_info.setJunction_id(id);
                plan_info.setProgram_version_no(prog_version);
                plan_info.setPlan_no(plan_no);
                plan_info.setPlan_revision_no(plan_revision_no);
                plan_info.setOn_time_hour(on_time_hour);
                plan_info.setOn_time_min(on_time_min);
                plan_info.setOff_time_hour(off_time_hour);
                plan_info.setOff_time_min(off_time_min);
                plan_info.setMode(mode);
                plan_info.setSide1_green_time(side1_green_time);
                plan_info.setSide2_green_time(side2_green_time);
                plan_info.setSide3_green_time(side3_green_time);
                plan_info.setSide4_green_time(side4_green_time);
                plan_info.setSide5_green_time(side5_green_time);
                plan_info.setSide1_amber_time(side1_amber_time);
                plan_info.setSide2_amber_time(side2_amber_time);
                plan_info.setSide3_amber_time(side3_amber_time);
                plan_info.setSide4_amber_time(side4_amber_time);
                plan_info.setSide5_amber_time(side5_amber_time);
                planInfoList.add(plan_info);
            }
//            for(PlanInfo planInfo: planInfoList) {
//                System.out.println(planInfo.getPlan_no() + "\t" + planInfo.getOn_time_hour() + "\t" + planInfo.getSide1_green_time());
//            }
            if (planinfoModel.validatePlans(planInfoList)) {
                // validation was successful so now insert record.
                planinfoModel.insertRecord(planInfoList);
            } else {
                // Get the error message regarding validate plans.
            }
            program_version_no = program_version_no + 1;
        }

        junction_id = Integer.parseInt(request.getParameter("junction_id").trim());
        int valid_program_version_no = planinfoModel.getValidPorogramVersionNo(junction_id);
        //List<PlanInfo> list1 = planinfoModel.showData(junction_id,program_version_no);
        List<PlanInfo> list1 = planinfoModel.showData(junction_id, valid_program_version_no);
        request.setAttribute("no_of_plans", list1.size());


        // request.setAttribute("program_version_no", program_version_no);

        request.setAttribute("program_version_no", valid_program_version_no);

        request.setAttribute("planinfo", list1);
        request.setAttribute("plan_info_name", planinfoModel.getjunctionName(junction_id));
        request.setAttribute("message", planinfoModel.getMessage());
        request.setAttribute("msgBgColor", planinfoModel.getMsgBgColor());
        request.setAttribute("IDGenerator", new xyz());
        request.getRequestDispatcher("/plan_info_view").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
