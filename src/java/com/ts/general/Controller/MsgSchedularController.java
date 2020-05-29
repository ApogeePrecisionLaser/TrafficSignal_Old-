/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.general.Controller;

import com.mysql.jdbc.Connection;
import com.ts.dbcon.DBConnection;
import com.ts.general.Model.MsgSchedularModel;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Shobha
 */
public class MsgSchedularController extends HttpServlet{


    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        Connection con = null;
        DBConnection dbCon = new DBConnection();
        System.out.println("TimeSchedulerController is accessed");

        try {
            con = (Connection) dbCon.getConnection(ctx);
        } catch (SQLException ex) {
            Logger.getLogger(MsgSchedularController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (con != null) {
            MsgSchedularModel sm = new MsgSchedularModel();
            MsgSchedularModel.setConnection( (com.mysql.jdbc.Connection) con);
            sm.setCtx(ctx);

            ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
            scheduledThreadPool.scheduleAtFixedRate(sm, 0, 3, TimeUnit.MINUTES);

//
//
//             ScheduleModel1 sm1 = new ScheduleModel1();
//            ScheduleModel.setConnection( (com.mysql.jdbc.Connection) con);
//            sm1.setCtx(ctx);
//
//            ScheduledExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(5);
//            scheduledThreadPool1.scheduleAtFixedRate(sm, 0, 1, TimeUnit.HOURS);
        }
        System.out.println("---------------SchedulerController is Running--------------");
    }

}
