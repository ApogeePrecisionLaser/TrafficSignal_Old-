/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.Controller;

import com.ts.junction.Model.SlaveInfoModel;
import com.ts.junction.tableClasses.SlaveInfo;
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
public class SlaveInfoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SlaveInfoModel slaveinfoModel = new SlaveInfoModel();
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

        slaveinfoModel.setDriverClass(ctx.getInitParameter("driverClass"));
        slaveinfoModel.setConnectionString(ctx.getInitParameter("connectionString"));
        slaveinfoModel.setDb_userName(ctx.getInitParameter("db_userName"));
        slaveinfoModel.setDb_userPasswrod(ctx.getInitParameter("db_userPassword"));
        slaveinfoModel.setConnection();

        String task = request.getParameter("task");
        if (task == null) {
            task = "";
        }
        int junction_id = Integer.parseInt(request.getParameter("junction_id").trim());
        int no_of_sides = Integer.parseInt(request.getParameter("no_of_sides").trim());
        int program_version_no = Integer.parseInt(request.getParameter("program_version_no").trim());
        if (task.equals("SAVE") || task.equals("Save AS New")) {
            List<SlaveInfo> slaveInfoList = new ArrayList<SlaveInfo>();
            for (int i = 1; i <= no_of_sides; i++) {
                SlaveInfo slave_info = new SlaveInfo();

                int side_no = Integer.parseInt(request.getParameter("side_no" + i).trim());
                int side_revision_no = (request.getParameter("side_revision_no" + i)==null ? 0 : Integer.parseInt(request.getParameter("side_revision_no" + i)));
               
                String slave_id = request.getParameter("slave_id" + i)==null ? " " : request.getParameter("slave_id" + i);

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
                slave_info.setJunction_id(id);
                slave_info.setProgram_version_no(prog_version);
                slave_info.setSide_no(side_no);
                slave_info.setSide_revision_no(side_revision_no);
                slave_info.setSideName(slaveinfoModel.getSideName(junction_id, program_version_no, side_no));
                slave_info.setSlave_id(slave_id.trim());
                slaveInfoList.add(slave_info);
            }
            if (slaveinfoModel.validateSlaves(slaveInfoList)) {
                // validation was successful so now insert record.
                slaveinfoModel.insertRecord(slaveInfoList);
            } else {
                // Get the error message regarding validate plans.
            }
        }

        junction_id = Integer.parseInt(request.getParameter("junction_id").trim());
        List<SlaveInfo> list1 = slaveinfoModel.showData(junction_id,program_version_no);

        request.setAttribute("no_of_sides", list1.size());
        request.setAttribute("slaveinfo", list1);
        request.setAttribute("slave_info_name", slaveinfoModel.getjunctionName(junction_id));
        request.setAttribute("message", slaveinfoModel.getMessage());
        request.setAttribute("msgBgColor", slaveinfoModel.getMsgBgColor());
        request.setAttribute("IDGenerator", new xyz());
        request.getRequestDispatcher("/slave_info_view").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
