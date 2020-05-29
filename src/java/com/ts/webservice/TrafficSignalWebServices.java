/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.webservice;

import com.ts.dbcon.DBConnection;
import com.ts.general.Controller.ModemResReadSaveController;
import com.ts.junction.Model.JunctionModel;
import com.ts.junction.Model.PlanInfoModel;
import com.ts.junction.Model.TrafficSignalWebServiceModel;
import com.ts.junction.tableClasses.Junction;
import com.ts.junction.tableClasses.PlanInfo;
import com.ts.webservice.Async;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

//import org.json.JSONObject;

import org.json.simple.JSONObject;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Administrator
 */
@Path("/")
public class TrafficSignalWebServices {

    final Junction junction = new Junction();
    public static String arr2;
    public static String mapData = null;
    public static int flag = 0;
    Map<Integer, Junction> junctionList;
    @Context
// private WebServiceContext context;
    ServletContext servletContext;// =    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);

    @POST
    @Path("/trafficSignalData")
    @Produces(MediaType.MULTIPART_FORM_DATA)//http://192.168.1.15:8084/meter_survey/api/service/hello
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public byte[] insertTrafficSignalData(@Context HttpServletRequest requestContext, byte[] receivedBytes) {
        byte[] response = null;
          byte[] responseBytes = {120};
        String result = "Sorry!! something went wrong. ";
        System.out.println("data at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + ": " + requestContext.getRemoteAddr());
        for (int i = 0; i < receivedBytes.length; i++) {
            System.out.print(" " + receivedBytes[i]);
        }
        JunctionModel junctionModel = new JunctionModel();
        System.out.println("");
        ClientResponderWS clientResponder = new ClientResponderWS();
        if (receivedBytes != null && receivedBytes.length != 0) {
            try {
                Connection connection = DBConnection.getConnection(servletContext);
                clientResponder.clientResponderModel.setConnection(connection);
                junctionModel.setConnection(connection);
                if (junctionList == null) {
                    junctionList = junctionModel.getJunctionList();
                }
                
                clientResponder.setJunctionList(junctionList);
                clientResponder.setJunction(junction);
                clientResponder.setContext(servletContext);
                clientResponder.setJunction(new Junction());
                clientResponder.setAsync(new Async());
                clientResponder.setModemResReadSaveCont(new ModemResReadSaveController());
                clientResponder.setIPAddress(requestContext.getRemoteAddr());
                clientResponder.setIpPort(requestContext.getRemotePort() + "");
                String responseVal = null;
                responseVal = clientResponder.readClientResponse(receivedBytes);
                if (responseVal != null && !responseVal.isEmpty()) {
                    response = clientResponder.sendResponseData(responseVal);

                      //for testing
                   int length = response.length;
                    for(int i=0;i<length;i++){
                        int k=response[i];
                            if(k==0){
                            int l=123;
                            byte b=(byte)l;
                            response[i]=b;
                        }
                    }




                  
                    if (response != null && response.length > 0) {
                        result = "Successful!!";
                    }
                }
                connection.close();
            } catch (Exception ex) {
                System.out.println("Exception in insertEnergyMeterData" + ex);
                response = responseBytes;
            } finally {
                clientResponder.closeConnection();
                junctionModel.closeConnection();
            }
        }
        System.out.println("result : " + result + " response");
        return response;
    }

    @POST
    @Path("/junctionData")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject trafficSignalData(JSONObject inputJsonObj) {
        System.out.println("Access trafficSignalData in TrafficSignalWebServices ");
//        String latitude = inputJsonObj.get("latitude").toString();
//        String longitude = inputJsonObj.get("longitude").toString();
//        String imei = inputJsonObj.get("deviceid").toString();
//        String type = inputJsonObj.get("type").toString();
//        String mobile_no = inputJsonObj.get("phoneno").toString();
        String junction_id = inputJsonObj.get("junction_id") == null ? "" : inputJsonObj.get("junction_id").toString();
        String RequestMode = inputJsonObj.get("mode") == null ? "" : inputJsonObj.get("mode").toString();
        String deviceid = inputJsonObj.get("deviceid") == null ? "" : inputJsonObj.get("deviceid").toString();
        String active = inputJsonObj.get("active") == null ? "" : inputJsonObj.get("active").toString();
        String login_time = inputJsonObj.get("login_time") == null ? "" : inputJsonObj.get("login_time").toString();
        String logout_time = inputJsonObj.get("logout_time") == null ? "" : inputJsonObj.get("logout_time").toString();
        JSONObject obj = new JSONObject();
        TrafficSignalWebServiceModel tsModel = new TrafficSignalWebServiceModel();
        try {
            tsModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        if(active.equals("Y")){
        tsModel.insertDeviceRecordWithStatus(inputJsonObj,junction_id,RequestMode);
        }
        else if (active.equals("N")){
        tsModel.updateDeviceRecord(inputJsonObj,junction_id,RequestMode,deviceid);
        }
        else{
        tsModel.insertDeviceRecord(inputJsonObj);
        }
        obj.put("Data", tsModel.getJunctionList(junction_id));
        System.out.println("Data Retrived : " + inputJsonObj + " ");
        tsModel.closeConnection();
        return obj;
    }
    
    

    @POST
    @Path("/LiveJunctionList")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject LiveJunctionList() {
        System.out.println("Access trafficSignalData in TrafficSignalWebServices ");       
        JSONObject obj = new JSONObject();
        TrafficSignalWebServiceModel tsModel = new TrafficSignalWebServiceModel();
        try {
            tsModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        obj.put("Data", tsModel.getJunctionLiveList());        
        tsModel.closeConnection();
        return obj;
    }
    
    @POST
    @Path("/JunctionCoordinateList")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject JunctionCoordinateList(String t) {
        System.out.println("Access trafficSignalData in TrafficSignalWebServices ");       
        JSONObject obj = new JSONObject();
        TrafficSignalWebServiceModel tsModel = new TrafficSignalWebServiceModel();
        try {
            tsModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        obj.put("Data", tsModel.getJunctionCoordinateList());        
        tsModel.closeConnection();
        return obj;
    }
    
    @POST
    @Path("/JunctionEachCoordinateList")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject JunctionEachCoordinateList(String t) {
        System.out.println("Access trafficSignalData in TrafficSignalWebServices ");   
        String latlon[] = t.split(",");
        String lat = latlon[0];
        String lon = latlon[1];
        JSONObject obj = new JSONObject();
        TrafficSignalWebServiceModel tsModel = new TrafficSignalWebServiceModel();
        try {
            tsModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        obj.put("Data", tsModel.getJunctionEachCoordinateList(lat,lon));        
        tsModel.closeConnection();
        return obj;
    }
    
    @POST
    @Path("/JunctioninformationList")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public JSONObject JunctionInformationlatlongList(String t) {
        System.out.println("Access trafficSignalData in TrafficSignalWebServices "); 
        String latlon[] = t.split(",");
        String lat = latlon[0];
        String lon = latlon[1];
        JSONObject obj = new JSONObject();
        TrafficSignalWebServiceModel tsModel = new TrafficSignalWebServiceModel();
        try {
            tsModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in trafficSignalData : " + ex);
        }
        obj.put("Data", tsModel.getJunctionInformationList(lat,lon));        
        tsModel.closeConnection();
        return obj;
    }
    

    @POST
    @Path("/changePlanInfo")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public String changePlanInfo(JSONObject inputJsonObj) {
        //JSONObject obj = new JSONObject();
        boolean result = false;
        PlanInfoModel planinfoModel = new PlanInfoModel();
        try {
            planinfoModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in changePlanInfo in TrafficSignalWebServices : " + ex);
        }
        try {
            int no_of_plans = Integer.parseInt(inputJsonObj.get("No_of_plans").toString());
            int junction_id = Integer.parseInt(inputJsonObj.get("Junction_id").toString());
            int prog_version = Integer.parseInt(inputJsonObj.get("Program_version_no").toString());
            org.json.JSONArray jsonArray = new org.json.JSONArray(inputJsonObj.get("PlanInfo").toString());
            int length = jsonArray.length();
            List<PlanInfo> planInfoList = new ArrayList<PlanInfo>();
            for (int i = 0; i < length; i++) {
                org.json.JSONObject jsonObj = jsonArray.getJSONObject(i);
                int plan_no, plan_revision_no;
                try {
                    plan_no = Integer.parseInt(jsonObj.get("Plan_no").toString());
                    plan_revision_no = planinfoModel.getFinalPlanRevNo(junction_id, prog_version);
                } catch (Exception e) {
                    plan_no = i;
                    plan_revision_no = -1;
                }

                PlanInfo plan_info = new PlanInfo();
                // plan_info.setIs_edited((request.getParameter("edited" + i)==null ? 1 : Integer.parseInt(request.getParameter("edited" + i))));
                plan_info.setJunction_id(junction_id);
                plan_info.setProgram_version_no(prog_version);
                plan_info.setPlan_no(plan_no);
                plan_info.setPlan_revision_no(plan_revision_no);//Integer.parseInt(jsonObj.get("Plan_revision_no").toString())
                plan_info.setOn_time_hour(Integer.parseInt(jsonObj.get("On_time_hour").toString()));
                plan_info.setOn_time_min(Integer.parseInt(jsonObj.get("On_time_min").toString()));
                plan_info.setOff_time_hour(Integer.parseInt(jsonObj.get("Off_time_hour").toString()));
                plan_info.setOff_time_min(Integer.parseInt(jsonObj.get("Off_time_min").toString()));
                plan_info.setMode(jsonObj.get("Mode").toString());
                plan_info.setSide1_green_time(Integer.parseInt(jsonObj.get("Side1_green_time").toString()));
                plan_info.setSide2_green_time(Integer.parseInt(jsonObj.get("Side2_green_time").toString()));
                plan_info.setSide3_green_time(Integer.parseInt(jsonObj.get("Side3_green_time").toString()));
                plan_info.setSide4_green_time(Integer.parseInt(jsonObj.get("Side4_green_time").toString()));
                plan_info.setSide5_green_time(Integer.parseInt(jsonObj.get("Side5_green_time").toString()));
                plan_info.setSide1_amber_time(Integer.parseInt(jsonObj.get("Side1_amber_time").toString()));
                plan_info.setSide2_amber_time(Integer.parseInt(jsonObj.get("Side2_amber_time").toString()));
                plan_info.setSide3_amber_time(Integer.parseInt(jsonObj.get("Side3_amber_time").toString()));
                plan_info.setSide4_amber_time(Integer.parseInt(jsonObj.get("Side4_amber_time").toString()));
                plan_info.setSide5_amber_time(Integer.parseInt(jsonObj.get("Side5_amber_time").toString()));
                planInfoList.add(plan_info);
            }
            result = planinfoModel.insertRecord(planInfoList);
            System.out.println("Data Retrived : " + inputJsonObj + " ");
            planinfoModel.closeConnection();

        } catch (Exception ex) {
        }
        if (result) {
            return "success";
        } else {
            return "fail";
        }
    }

    @POST
    @Path("/reviseJunctionDetail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String reviseJunctionDetail(JSONObject inputJsonObj) {
        boolean result = false;
        JunctionModel junctionModel = new JunctionModel();
        try {
            junctionModel.setConnection(DBConnection.getConnection(servletContext));
        } catch (Exception ex) {
            System.out.println("ERROR: in reviseJunctionDetail in TrafficSignalWebServices : " + ex);
        }
//jsonObject.put("Program_version_no", data.getProgram_version_no() + "");
        try {
            String side_1_name, side_3_name = null, side_4_name = null, side_5_name = null, side_2_name;
            Junction junction = new Junction();
            String junction_name = inputJsonObj.get("Junction_name").toString();
            String address_1 = inputJsonObj.get("Address1").toString();
            String address_2 = inputJsonObj.get("Address2").toString();
            String state_name = inputJsonObj.get("State_name").toString();
            String city_name = inputJsonObj.get("City_name").toString();
            String controller_model = inputJsonObj.get("Controller_model").toString();
            int no_of_sides = Integer.parseInt(inputJsonObj.get("No_of_sides").toString());
            int amber_time = Integer.parseInt(inputJsonObj.get("Amber_time").toString());
            int flash_rate = Integer.parseInt(inputJsonObj.get("Flash_rate").toString());
            int no_of_plans = Integer.parseInt(inputJsonObj.get("No_of_plans").toString());
            String mobile_no = inputJsonObj.get("Mobile_no").toString();
            String sim_no = inputJsonObj.get("Sim_no").toString();
            String imei_no = inputJsonObj.get("Imei_no").toString();
            int instant_green_time = Integer.parseInt(inputJsonObj.get("Instant_green_time").toString());
            String pedestrian = inputJsonObj.get("Pedestrian").toString();
            junction.setPedestrian(pedestrian);//.equals("YES") ? "Y" : "N")
            junction.setPedestrian_time(Integer.parseInt(inputJsonObj.get("Pedestrian_time").toString()));
            junction.setFile_no(Integer.parseInt(inputJsonObj.get("File_no").toString()));
            //junction.setRemark(inputJsonObj.get("remark").toString());

            side_1_name = inputJsonObj.get("Side1_name").toString();
            side_2_name = inputJsonObj.get("Side2_name").toString();
            switch (no_of_sides) {
                case 5:
                    side_5_name = inputJsonObj.get("Side5_name").toString();
                case 4:
                    side_4_name = inputJsonObj.get("Side4_name") == null ? "" : inputJsonObj.get("Side4_name").toString();
                case 3:
                    side_3_name = inputJsonObj.get("Side3_name").toString();
            }

            int id = 0;
            try {
                id = Integer.parseInt(inputJsonObj.get("Junction_id").toString().trim());
            } catch (Exception ex) {
                id = 0;
//                System.out.println("ad_asso_site_detail_id error: " + ex);
            }
//            if (task.equals("Save AS New")) {
//                id = 0;
//            }
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
                result = junctionModel.updateRecord(junction) > 0 ? true : false;
            }
        } catch (Exception ex) {
            System.out.println("ERROR: in reviseJunctionDetail in TrafficSignalWebServices : " + ex);
        }
        if (result) {
            return "success";
        } else {
            return "fail";
        }
    }

    @POST
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
      public byte[] test(@Context HttpServletRequest requestContext, byte[] receivedBytes ) throws JSONException {
        byte[] response = null;
        byte[] responseBytes = {120};
          responseBytes=receivedBytes;

        return receivedBytes;
    }
    @POST
    @Path("/test2")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public String test2() throws JSONException {
    System.out.println("hello Traffic Signal ");
        return "success";
    }

    @POST
    @Path("/test4")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public byte[] test1(byte[] receivedBytes) throws JSONException {
    System.out.println("jyto bytes :: "+receivedBytes);
    ClientResponderWS clientResponder = new ClientResponderWS();
    byte [] pureBytes = clientResponder.arr1;
        return pureBytes ;
     }

    @POST
    @Path("/test1")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public byte[] test1(String receivedBytes) throws JSONException {
    byte [] pureBytes = null;
    ClientResponderWS clientResponder = new ClientResponderWS();
    System.out.println("jyto bytes :: "+receivedBytes);
    String junction_id = receivedBytes;
    int junction_id2 = Integer.parseInt(junction_id);
    if(junction_id2 == 2){
    pureBytes = clientResponder.arr1;
    }
    if(junction_id2 == 11){
    pureBytes = clientResponder.arr11;
    }
    if(junction_id2 == 13){
    pureBytes = clientResponder.arr13;
    }
    if(junction_id2 == 1){
    pureBytes = clientResponder.arryatayat;
    }
    if(junction_id2 == 4){
    pureBytes = clientResponder.arrkatanga;
    }
    if(junction_id2 == 5){
    pureBytes = clientResponder.arrbaldeobag;
    }
    if(junction_id2 == 6){
    pureBytes = clientResponder.arrdeendayal;
    }
    if(junction_id2 == 7){
    pureBytes = clientResponder.arrhighcourt;
    }
    if(junction_id2 == 8){
    pureBytes = clientResponder.arrbloomchowk;
    }
    if(junction_id2 == 12){
    pureBytes = clientResponder.arrmadanmahal;
    }
    if(junction_id2 == 14){
    pureBytes = clientResponder.arrgohalpur;
    }
    return pureBytes ;
     }

    @POST
    @Path("/test3")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public void test3(String receivedBytes) throws JSONException {
    System.out.println("jyto bytes :: "+receivedBytes);
    arr2 = receivedBytes;
     }
    
    @POST
    @Path("/mapdata")
    @Produces(MediaType.APPLICATION_JSON)//http://192.168.1.15:8084/trafficSignals_new/api/service/hello
    @Consumes(MediaType.APPLICATION_JSON)
    public void mapdata(String receivedBytes){
    System.out.println("hello Robin Singh"+receivedBytes);
            TrafficSignalWebServices.flag = 1;
            TrafficSignalWebServices.mapData = receivedBytes;
       
    }


}
