/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.general.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;

/**
 *
 * @author Shobha
 */
public class MsgSchedularModel extends TimerTask{
    private ServletContext ctx;
    private static Connection connection;


    Date dt = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String cut_dt = df.format(dt);

public void setCtx(ServletContext ctx) {
        this.ctx = ctx;
    }


    public void run() {
        try {
            System.out.println("run method is running");
//            List list = getOverheadtankId();
//            checkOnOff(list);

            List list = getAllLiveJunctionList();
            

        } catch (Exception ex) {
            System.out.println(" run() Error: " + ex);
        }
    }



    public List getAllLiveJunctionList() {
        List list = new ArrayList();
        try {
            String query = " SELECT j.junction_id, junction_name, city_name, ip_address, port, j.program_version_no, j.file_no, "
                    + " IF(synchronization_status is null or synchronization_status = '' ,'N',synchronization_status) AS synchronization_status ,"
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(application_hr,':',application_min,' ',application_date,'-',application_month,'-',application_year)) AS application_last_time, "
                    + " IF(synchronization_status is null or synchronization_status = '','Not Set',CONCAT(junction_hr,':',junction_min,' ',junction_date,'-',junction_month,'-',junction_year)) AS junction_last_time"
                    + " FROM  junction AS j LEFT JOIN time_synchronization_detail AS tsd"
                    + "  ON j.junction_id = tsd.junction_id AND tsd.final_revision='VALID' AND j.program_version_no=tsd.program_version_no "
                    + ", log_history AS lh, city AS c  "
                    + " WHERE j.junction_id=lh.junction_id AND j.city_id=c.city_id AND logout_timestamp_time is null AND j.final_revision='VALID' "
                    + "ORDER BY login_timestamp_date DESC, login_timestamp_time DESC ";
            ResultSet rset = connection.prepareStatement(query).executeQuery();
            while (rset.next()) {

                int junction_id = (rset.getInt("junction_id"));
//                log_history.setProgram_version_no(rset.getInt("program_version_no"));
//                log_history.setFileNo(rset.getInt("file_no"));
//                log_history.setJunction_name(rset.getString("junction_name"));
//                log_history.setCity_name(rset.getString("city_name"));
//                log_history.setIp_address(rset.getString("ip_address"));
//                log_history.setPort(rset.getInt("port"));
//                log_history.setApplication_last_time_set(rset.getString("application_last_time"));
//                log_history.setJunction_last_time_set(rset.getString("junction_last_time"));
//                log_history.setTime_synchronization_status(rset.getString("synchronization_status"));
                list.add(junction_id);
            }

        } catch (Exception e) {
            System.out.println("Error:clientResponderModel-showLoggedInJunctionDetails--- " + e);
        }
        return list;
    }





    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        MsgSchedularModel.connection = connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(" closeConnection() Error: " + e);
        }
    }

}
